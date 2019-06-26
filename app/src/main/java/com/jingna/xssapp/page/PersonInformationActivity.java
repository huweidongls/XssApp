package com.jingna.xssapp.page;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.google.gson.Gson;
import com.jingna.xssapp.R;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.bean.UserInfoRowBean;
import com.jingna.xssapp.dialog.InformationSexDialog;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.util.SpUtils;
import com.jingna.xssapp.util.StringUtils;
import com.jingna.xssapp.util.ToastUtil;
import com.jingna.xssapp.util.WeiboDialogUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.observable.ObservableError;
import io.reactivex.schedulers.Schedulers;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class PersonInformationActivity extends BaseActivity {

    private Context context = PersonInformationActivity.this;

    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;

    private int mYear;
    private int mMonth;
    private int mDay;

    private int REQUEST_CODE = 102;
    private String sex;
    private String picUrl;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_information);

        Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        ButterKnife.bind(PersonInformationActivity.this);
        initData();

    }

    private void initData() {
        ViseHttp.POST(NetUrl.userInfo_RowUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.userInfo_RowUrl))
                .addParam("uid", SpUtils.getUid(context))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                Gson gson = new Gson();
                                UserInfoRowBean bean = gson.fromJson(data, UserInfoRowBean.class);
                                Glide.with(context).load(NetUrl.BASE_URL+bean.getObj().getHeadimg()).into(ivAvatar);
                                etName.setText(bean.getObj().getNickname());
                                sex = bean.getObj().getSex();
                                if(sex.equals("0")){
                                    tvSex.setText("男");
                                }else if(sex.equals("1")){
                                    tvSex.setText("女");
                                }
                                tvBirthday.setText(bean.getObj().getBirthday());
                                tvPhone.setText(bean.getObj().getPhone());
                            }else {
                                ToastUtil.showShort(context, jsonObject.optString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

    }

    @OnClick({R.id.rl_back, R.id.rl_sex, R.id.rl_birthday, R.id.rl_avatar, R.id.rl_save})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_sex:
                InformationSexDialog sexDialog = new InformationSexDialog(context, new InformationSexDialog.ClickListener() {
                    @Override
                    public void onNan() {
                        tvSex.setText("男");
                        sex = "0";
                    }

                    @Override
                    public void onNv() {
                        tvSex.setText("女");
                        sex = "1";
                    }
                });
                sexDialog.show();
                break;
            case R.id.rl_birthday:
                new DatePickerDialog(context, onDateSetListener, mYear, mMonth, mDay).show();
                break;
            case R.id.rl_avatar:
                //限数量的多选(比喻最多9张)
                ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setSingle(false)  //设置是否单选
                        .setMaxSelectCount(1) // 图片的最大选择数量，小于等于0时，不限数量。
                        .setViewImage(true) //是否点击放大图片查看,，默认为true
                        .start(PersonInformationActivity.this, REQUEST_CODE); // 打开相册
                break;
            case R.id.rl_save:
                dialog = WeiboDialogUtils.createLoadingDialog(context, "加载中...");
                if(StringUtils.isEmpty(picUrl)){
                    ViseHttp.POST(NetUrl.saveUserInfoUrl)
                            .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.saveUserInfoUrl))
                            .addParam("uid", SpUtils.getUid(context))
                            .addParam("nickname", etName.getText().toString())
                            .addParam("sex", sex)
                            .addParam("birthday", tvBirthday.getText().toString())
                            .request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(data);
                                        if(jsonObject.optInt("code") == 200){
                                            ToastUtil.showShort(context, jsonObject.optString("message"));
                                            finish();
                                        }else {
                                            ToastUtil.showShort(context, jsonObject.optString("message"));
                                        }
                                        WeiboDialogUtils.closeDialog(dialog);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onFail(int errCode, String errMsg) {
                                    WeiboDialogUtils.closeDialog(dialog);
                                }
                            });
                }else {
                    List<String> mList = new ArrayList<>();
                    mList.add(picUrl);
                    Luban.with(context)
                            .load(mList)
                            .ignoreBy(100)
                            .filter(new CompressionPredicate() {
                                @Override
                                public boolean apply(String path) {
                                    return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                                }
                            })
                            .setCompressListener(new OnCompressListener() {
                                @Override
                                public void onStart() {

                                }

                                @Override
                                public void onSuccess(File file) {
                                    ViseHttp.UPLOAD(NetUrl.saveUserInfoUrl)
                                            .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.saveUserInfoUrl))
                                            .addParam("uid", SpUtils.getUid(context))
                                            .addParam("nickname", etName.getText().toString())
                                            .addParam("sex", sex)
                                            .addParam("birthday", tvBirthday.getText().toString())
                                            .addFile("headimg", file)
                                            .request(new ACallback<String>() {
                                                @Override
                                                public void onSuccess(String data) {
                                                    try {
                                                        JSONObject jsonObject = new JSONObject(data);
                                                        if(jsonObject.optInt("code") == 200){
                                                            ToastUtil.showShort(context, jsonObject.optString("message"));
                                                            finish();
                                                        }else {
                                                            ToastUtil.showShort(context, jsonObject.optString("message"));
                                                        }
                                                        WeiboDialogUtils.closeDialog(dialog);
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }

                                                @Override
                                                public void onFail(int errCode, String errMsg) {
                                                    WeiboDialogUtils.closeDialog(dialog);
                                                }
                                            });
                                }

                                @Override
                                public void onError(Throwable e) {
                                    WeiboDialogUtils.closeDialog(dialog);
                                }
                            }).launch();
                }
//                Observable<File> observable = ObservableError.create(new ObservableOnSubscribe<File>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<File> e) throws Exception {
//
//                    }
//                });
//                Observer<File> observer = new Observer<File>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(File value) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                };
//                observable.subscribeOn(Schedulers.newThread())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(observer);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
            ArrayList<String> images = data.getStringArrayListExtra(
                    ImageSelectorUtils.SELECT_RESULT);

            if(images.size()>0){
                picUrl = images.get(0);
                Glide.with(context).load(images.get(0)).into(ivAvatar);
            }

            /**
             * 是否是来自于相机拍照的图片，
             * 只有本次调用相机拍出来的照片，返回时才为true。
             * 当为true时，图片返回的结果有且只有一张图片。
             */
            boolean isCameraImage = data.getBooleanExtra(ImageSelector.IS_CAMERA_IMAGE, false);
        }
    }

    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            final String days;
            if (mMonth + 1 < 10) {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append("0").append(mDay).append("").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").append("0").
                            append(mMonth + 1).append("-").append(mDay).append("").toString();
                }

            } else {
                if (mDay < 10) {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append("0").append(mDay).append("").toString();
                } else {
                    days = new StringBuffer().append(mYear).append("-").
                            append(mMonth + 1).append("-").append(mDay).append("").toString();
                }

            }
            tvBirthday.setText(days);
        }
    };

}
