package com.jingna.xssapp.page;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.google.gson.Gson;
import com.jingna.xssapp.R;
import com.jingna.xssapp.adapter.CommentAddPicAdapter;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.bean.EvaluateInfoBean;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.util.Logger;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class CommentOrderActivity extends BaseActivity {

    private Context context = CommentOrderActivity.this;

    @BindView(R.id.rv_pic)
    RecyclerView recyclerView;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv_service_name)
    TextView tvServiceName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.et_comment)
    EditText etComment;
    @BindView(R.id.iv_manyi)
    ImageView ivManyi;
    @BindView(R.id.iv_bumanyi)
    ImageView ivBumanyi;
    @BindView(R.id.iv_anonymous)
    ImageView ivAnonymous;

    private CommentAddPicAdapter adapter;
    private List<String> mList;
    private int REQUEST_CODE = 102;

    private String id = "";

    private Dialog weiboDialog;

    private String satisfied = "0";//是否满意
    private String anonymous = "0";//是否匿名

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_order);

        id = getIntent().getStringExtra("id");
        ButterKnife.bind(CommentOrderActivity.this);
        initData();

    }

    private void initData() {

        ViseHttp.POST(NetUrl.evaluate_infoUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.evaluate_infoUrl))
                .addParam("oid", id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                Gson gson = new Gson();
                                EvaluateInfoBean bean = gson.fromJson(data, EvaluateInfoBean.class);
                                Glide.with(context).load(NetUrl.BASE_URL+bean.getObj().getImg()).into(iv);
                                tvServiceName.setText(bean.getObj().getTitle());
                                tvTime.setText("下单时间："+bean.getObj().getAddtime());
                                tvStartTime.setText(bean.getObj().getService_start_time());
                                tvEndTime.setText(bean.getObj().getService_end_time());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

        mList = new ArrayList<>();
        adapter = new CommentAddPicAdapter(mList);
        GridLayoutManager manager = new GridLayoutManager(context, 3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setListener(new CommentAddPicAdapter.ClickListener() {
            @Override
            public void addImg() {
                //限数量的多选(比喻最多9张)
                ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setSingle(false)  //设置是否单选
                        .setMaxSelectCount(9-mList.size()) // 图片的最大选择数量，小于等于0时，不限数量。
                        .setViewImage(true) //是否点击放大图片查看,，默认为true
                        .start(CommentOrderActivity.this, REQUEST_CODE); // 打开相册
            }
        });

    }

    @OnClick({R.id.rl_back, R.id.btn_save, R.id.iv_manyi, R.id.iv_bumanyi, R.id.rl_anonymous})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_save:
                String commentContent = etComment.getText().toString();
                if(StringUtils.isEmpty(commentContent)){
                    ToastUtil.showShort(context, "请输入评价内容");
                }else if(mList.size() == 0){
                    ToastUtil.showShort(context, "请上传评价照片");
                }else {
                    onSave();
                }
                break;
            case R.id.iv_manyi:
                Glide.with(context).load(R.mipmap.manyi_r).into(ivManyi);
                Glide.with(context).load(R.mipmap.bumanyi_g).into(ivBumanyi);
                satisfied = "0";
                break;
            case R.id.iv_bumanyi:
                Glide.with(context).load(R.mipmap.manyi).into(ivManyi);
                Glide.with(context).load(R.mipmap.bumanyi).into(ivBumanyi);
                satisfied = "1";
                break;
            case R.id.rl_anonymous:
                if(anonymous.equals("0")){
                    Glide.with(context).load(R.mipmap.address_red2).into(ivAnonymous);
                    anonymous = "1";
                }else if(anonymous.equals("1")){
                    Glide.with(context).load("#ffffff").into(ivAnonymous);
                    anonymous = "0";
                }
                break;
        }
    }

    /**
     * 提交评价
     */
    private void onSave() {

        weiboDialog = WeiboDialogUtils.createLoadingDialog(context,"请等待...");

        Observable<Map<String, File>> observable = Observable.create(new ObservableOnSubscribe<Map<String, File>>() {
            @Override
            public void subscribe(final ObservableEmitter<Map<String, File>> e) throws Exception {
                final Map<String, File> fileMap = new LinkedHashMap<>();
                final List<File> fileList = new ArrayList<>();
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
                                // TODO 压缩开始前调用，可以在方法内启动 loading UI
                            }

                            @Override
                            public void onSuccess(File file) {
                                // TODO 压缩成功后调用，返回压缩后的图片文件
                                fileList.add(file);
                                if(fileList.size() == mList.size()){
                                    for (int i = 0; i<fileList.size(); i++){
                                        fileMap.put("img[" + i + "]", fileList.get(i));
                                    }
                                    e.onNext(fileMap);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                // TODO 当压缩过程出现问题时调用
                            }
                        }).launch();
            }
        });
        Observer<Map<String, File>> observer = new Observer<Map<String, File>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Map<String, File> value) {
                String commentContent = etComment.getText().toString();//获取评论内容
                ViseHttp.UPLOAD(NetUrl.evaluate_saveUrl)
                        .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.evaluate_saveUrl))
                        .addParam("oid", id)
                        .addParam("uid", SpUtils.getUid(context))
                        .addParam("content",commentContent)
                        .addParam("satisfied",satisfied)
                        .addParam("anonymous",anonymous)
                        .addFiles(value)
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                Logger.e("11111",data);
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.optInt("code") == 200){
                                        ToastUtil.showShort(context,"评价成功");
                                        finish();
                                    }else {
                                        ToastUtil.showShort(context,jsonObject.optString("message"));
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                WeiboDialogUtils.closeDialog(weiboDialog);
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                WeiboDialogUtils.closeDialog(weiboDialog);
                            }
                        });
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
            ArrayList<String> images = data.getStringArrayListExtra(
                    ImageSelectorUtils.SELECT_RESULT);

            recyclerView.setVisibility(View.VISIBLE);
            mList.addAll(images);
            adapter.notifyDataSetChanged();

            /**
             * 是否是来自于相机拍照的图片，
             * 只有本次调用相机拍出来的照片，返回时才为true。
             * 当为true时，图片返回的结果有且只有一张图片。
             */
            boolean isCameraImage = data.getBooleanExtra(ImageSelector.IS_CAMERA_IMAGE, false);
        }
    }

}
