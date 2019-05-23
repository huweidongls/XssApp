package com.jingna.xssapp.net;

/**
 * Created by Administrator on 2019/5/21.
 */

public class NetUrl {
    public static final String BASE_URL = "http://39.98.188.171/";
    //登录
    public static String loginUrl = "index.php/api/Login/Logins/login";
    //获取验证码
    public static String getCodeUrl = "index.php/api/Login/Logins/CodeSend";
    //用户注册
    public static String registerUrl = "index.php/api/Login/Logins/register";
    //找回密码
    public static String forgetPwdUrl = "index.php/api/Login/Logins/RetrievePassword";
    //获取个人中心信息
    public static String memberInfoUrl = "index.php/api/Member/Index/MemberInfo";
    //交易明细列表
    public static String transactionDetailsUrl = "index.php/api/Member/Index/TransactionDetails";
    //用户地址列表
    public static String memberAddressListUrl = "index.php/api/Member/Index/MemberAddressList";
    //新增用户地址
    public static String add_MemberAddressUrl = "index.php/api/Member/Index/Add_MemberAddress";
    //删除地址信息
    public static String delMember_AddressUrl = "index.php/api/Member/Index/DelMember_Address";
    //获取一条地址信息(修改)
    public static String memberAddressInfoUrl = "index.php/api/Member/Index/MemberAddressInfo";
    //更新地址信息(地址修改)
    public static String saveMemberAddressUrl = "index.php/api/Member/Index/SaveMemberAddress";
    //优惠券列表
    public static String couponListUrl = "index.php/api/Member/Index/CouponList";
    //用户消息列表
    public static String member_messageUrl = "index.php/api/Member/Index/Member_message";
    //编辑用户资料(获取)
    public static String userInfo_RowUrl = "index.php/api/Member/Index/UserInfo_Row";
    //修改用户资料
    public static String saveUserInfoUrl = "index.php/api/Member/Index/SaveUserInfo";
    //首页轮播图
    public static String indexBnnerListUrl = "index.php/api/Index/Home/IndexBnnerList";
    //首页咨询快报
    public static String newslistUrl = "index.php/api/Index/Home/newslist";
    //首页图片推广
    public static String priceListUrl = "index.php/api/Index/Home/PriceList";
    //定位接口
    public static String locationUrl = "index.php/api/Index/Home/Location";
    //首页推荐服务
    public static String indexServiceListUrl = "index.php/api/Index/Home/IndexServiceList";
    //已开通城市列表
    public static String openCityListUrl = "index.php/api/Index/Home/OpenCityList";
    //工人列表
    public static String wokerListUrl = "index.php/api/Index/Home/WokerList";
    //工人详情
    public static String wokerContentUrl = "index.php/api/Index/Home/WokerContent";
    //服务类别列表
    public static String serviceTypeListUrl = "index.php/api/Index/Home/ServiceTypeList";
    //获取服务和二级类别
    public static String serviceTypeAndServiceListUrl = "index.php/api/Index/Home/ServiceTypeAndServiceList";
    //服务详情
    public static String serviceContentUrl = "index.php/api/Index/Home/ServiceContent";
}
