package com.jingna.xssapp.net;

/**
 * Created by Administrator on 2019/5/21.
 */

public class NetUrl {
    public static final String BASE_URL = "http://192.168.2.241/";
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
}
