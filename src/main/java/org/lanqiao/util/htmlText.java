package org.lanqiao.util;


/**
 * @class_name：htmlText
 * @comments:定义邮件内容
 * @param:
 * @return:
 */
public class htmlText {
    //  返回页面Html携带的6位随机码
    public static String html(String code) {
//"Email地址验证<br/>"+
        String html =
                "这封邮件是由【蓝桥人力资源管理系统】发送的。<br/>" +
                        "<br/>" +
                        "我正式代表小雷家铺子公司聘请你为我公司的<h3 style='color:red;'>技术大神</h3><br/>" +
                        "<br/>" +
                        "请将下面的验证码输入到提示框即可：<h3 style='color:red;'>" + code + "</h3><br/>";
        return html;
    }
}


