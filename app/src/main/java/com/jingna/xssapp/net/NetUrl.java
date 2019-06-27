package com.jingna.xssapp.net;

/**
 * Created by Administrator on 2019/5/21.
 */

public class NetUrl {
    public static final String BASE_URL = "http://xss.5ijiaoyu.cn/";
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
    //咨询快报列表
    public static String news_ListUrl = "index.php/api/Index/Home/News_List";
    //资讯详情
    public static String news_contentUrl = "index.php/api/Index/Home/News_content";
    //优惠券列表
    public static String CouponListUrl = "index.php/api/Index/Home/CouponList";
    //领取优惠券
    public static String get_couponsUrl = "index.php/api/Index/Home/Get_coupons";
    //搜索资讯
    public static String seachNewsUrl = "index.php/api/Index/Home/SeachNews";
    //预约服务信息获取
    public static String preAboutUrl = "index.php/api/Order/Member_order/PreAbout";
    //提交订单
    public static String order_insertUrl = "index.php/api/Order/Member_order/order_insert";
    //交易记录详情
    public static String transaction_ContentUrl = "index.php/api/Member/Index/Transaction_Content";
    //订单列表
    public static String order_listUrl = "index.php/api/Order/Member_order/Order_list";
    //取消订单
    public static String cancel_orderUrl = "index.php/api/Order/Member_order/cancel_order";
    //去支付
    public static String topay_OrderUrl = "index.php/api/Order/Member_order/Topay_Order";
    //订单退款
    public static String orderRefundUrl = "index.php/api/Order/Member_order/OrderRefund";
    //评价信息获取
    public static String evaluate_infoUrl = "index.php/api/Order/Member_order/evaluate_info";
    //保存评价信息
    public static String evaluate_saveUrl = "index.php/api/Order/Member_order/evaluate_save";
    //服务列表(快速下单)
    public static String pay_ServiceListUrl = "index.php/api/Index/Home/Pay_ServiceList";
    //个人中心评价列表
    public static String member_EvaluateUrl = "index.php/api/Order/Member_order/Member_Evaluate";
    //订单详情
    public static String order_contentUrl = "index.php/api/Order/Member_order/Order_content";
    //所有城市接口
    public static String allcityUrl = "index.php/api/Index/Home/Allcity";
    //申请退款
    public static String order_RefundUrl = "index.php/api/Order/Member_order/Order_Refund";
}
