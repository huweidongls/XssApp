package com.jingna.xssapp.page;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.xssapp.R;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.bean.JsonBean;
import com.jingna.xssapp.bean.MemberAddressInfoBean;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.util.GetJsonDataUtil;
import com.jingna.xssapp.util.Logger;
import com.jingna.xssapp.util.SpUtils;
import com.jingna.xssapp.util.StringUtils;
import com.jingna.xssapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InsertAddressActivity extends BaseActivity {

    private Context context = InsertAddressActivity.this;

    @BindView(R.id.tv_address_details)
    TextView tvAddressDetails;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.iv_address_default)
    ImageView ivAddressDefault;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private String radio = "0";
    private String id;
    private String type = "";
    private String Json_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_address);

        type = getIntent().getStringExtra("type");
        id = getIntent().getStringExtra("id");
        ButterKnife.bind(InsertAddressActivity.this);
        initData();
        //String JsonData = new GetJsonDataUtil()
    }

    private void initData() {
        initJsonData();
        tvTitle.setText(type);
        if(!StringUtils.isEmpty(id)){
            ViseHttp.POST(NetUrl.memberAddressInfoUrl)
                    .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.memberAddressInfoUrl))
                    .addParam("id", id)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optInt("code") == 200){
                                    Gson gson = new Gson();
                                    MemberAddressInfoBean infoBean = gson.fromJson(data, MemberAddressInfoBean.class);
                                    etName.setText(infoBean.getObj().getName());
                                    etPhone.setText(infoBean.getObj().getTel());
                                    tvAddressDetails.setText(infoBean.getObj().getAddress());
                                    etAddress.setText(infoBean.getObj().getDetailedaddress());
                                    if(infoBean.getObj().getRadio().equals("0")){
                                        Glide.with(context).load("#ffffff").into(ivAddressDefault);
                                        radio = "0";
                                    }else if(infoBean.getObj().getRadio().equals("1")){
                                        Glide.with(context).load(R.mipmap.address_red2).into(ivAddressDefault);
                                        radio = "1";
                                    }
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

    }

    @OnClick({R.id.rl_back, R.id.rl_address_details, R.id.btn_save, R.id.rl_default})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_address_details:
                OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        String tx = options1Items.get(options1).getPickerViewText() + "-" +
                                options2Items.get(options1).get(options2) + "-" +
                                options3Items.get(options1).get(options2).get(options3);
                        tvAddressDetails.setText(tx);
                    }
                })
                        .setTitleText("城市选择")
                        .setDividerColor(Color.BLACK)
                        .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                        .setContentTextSize(20)
                        .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
                pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
                pvOptions.show();
                break;
            case R.id.btn_save:
                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();
                String address = tvAddressDetails.getText().toString();
                String addressDetails = etAddress.getText().toString();
                if(StringUtils.isEmpty(name)||StringUtils.isEmpty(phone)||StringUtils.isEmpty(address)||StringUtils.isEmpty(addressDetails)){
                    ToastUtil.showShort(context, "数据不能为空");
                }else if(!StringUtils.isPhoneNumberValid(phone)){
                    ToastUtil.showShort(context, "请输入正确的手机号码");
                }else {
                    if(StringUtils.isEmpty(id)){
                        ViseHttp.POST(NetUrl.add_MemberAddressUrl)
                                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.add_MemberAddressUrl))
                                .addParam("uid", SpUtils.getUid(context))
                                .addParam("name", name)
                                .addParam("tel", phone)
                                .addParam("city", address)
                                .addParam("detailedaddress", addressDetails)
                                .addParam("radio", radio)
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
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onFail(int errCode, String errMsg) {

                                    }
                                });
                    }else {
                        ViseHttp.POST(NetUrl.saveMemberAddressUrl)
                                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.saveMemberAddressUrl))
                                .addParam("uid", SpUtils.getUid(context))
                                .addParam("name", name)
                                .addParam("tel", phone)
                                .addParam("city", address)
                                .addParam("detailedaddress", addressDetails)
                                .addParam("radio", radio)
                                .addParam("id", id)
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
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onFail(int errCode, String errMsg) {

                                    }
                                });
                    }
                }
                break;
            case R.id.rl_default:
                if(radio.equals("0")){
                    Glide.with(context).load(R.mipmap.address_red2).into(ivAddressDefault);
                    radio = "1";
                }else if(radio.equals("1")){
                    Glide.with(context).load("#ffffff").into(ivAddressDefault);
                    radio = "0";
                }
                break;
        }
    }
    public static void writeStringToFile(String json, String filePath) {
        File txt = new File(filePath);
        if (!txt.exists()) {
            try {
                txt.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        byte[] bytes = json.getBytes(); //新加的
        int b = json.length(); //改
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(txt);
            fos.write(bytes);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
//        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据
        ViseHttp.POST(NetUrl.BASE_URL+"api/Member/Index/Adress_Insert_List")
                .addParam("id",SpUtils.getCityId(context))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("code").equals("200")){
//                                Json_str = jsonObject.getString("obj");
                                Json_str = "[{\"name\":\"黑龙江省\",\"city\":{\"name\":\"鸡西市\",\"area\":[\"鸡冠区\",\"恒山区\",\"滴道区\",\"梨树区\",\"城子河区\",\"麻山区\",\"鸡东县\",\"虎林市\",\"密山市\"]}}]";
                                Logger.e("123123", Json_str);
                                ArrayList<JsonBean> jsonBean = parseData(Json_str);//用Gson 转成实体

                                /**
                                 * 添加省份数据
                                 *
                                 * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
                                 * PickerView会通过getPickerViewText方法获取字符串显示出来。
                                 */
                                options1Items = jsonBean;
                                for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
                                    ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
                                    ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

                                    for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                                        String CityName = jsonBean.get(i).getCityList().get(c).getName();
                                        CityList.add(CityName);//添加城市
                                        ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                                        //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                                        if (jsonBean.get(i).getCityList().get(c).getArea() == null
                                                || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                                            City_AreaList.add("");
                                        } else {
                                            City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                                        }
                                        Province_AreaList.add(City_AreaList);//添加该省所有地区数据
                                    }

                                    /**
                                     * 添加城市数据
                                     */
                                    options2Items.add(CityList);

                                    /**
                                     * 添加地区数据
                                     */
                                    options3Items.add(Province_AreaList);
                                }
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

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

}
