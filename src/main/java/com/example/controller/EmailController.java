package com.example.controller;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author dzh
 * @since 2020-04-21
 */
@Controller
@RequestMapping("/email")
@Scope(value = "prototype")
public class EmailController {

    @RequestMapping("/sendEmail")
    public static int sendEmail(){
        SimpleEmail email = new SimpleEmail();//创建一个HtmlEmail实例对象
        try {
            //填写邮箱服务器，如是QQ邮箱服务器，直接用：smtp.qq.com
            email.setHostName("smtp.163.com");
            //设置编码格式
            email.setCharset("UTF-8");
            //设置收件人邮箱地址
            email.addTo("709511500@qq.com");
            //设置发送人邮箱，和用户名(乱写)
            email.setFrom("hong709511500@163.com","杜志红");
            //配置邮箱加授权码，相当于等于自己的邮箱
            email.setAuthentication("hong709511500@163.com","ORAORFQHHGPKTZZA");
            //使用安全链接
            email.setSSLOnConnect(true);
            //标题
            email.setSubject("测试邮件发送");
            //邮件内容
            email.setMsg("尊敬的用户：刘狗，你好!\n 注册验证码为：888888");
            //发送
            email.send();
            //0成功  -1失败
            return 0;
        } catch (EmailException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void main(String[] args) {
        sendEmail();
    }

}
