package org.lanqiao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;

public class emailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(emailService.class);

    private JavaMailSender javaMailSender;

    private SimpleMailMessage simpleMailMessage;

    /**
     * @方法名: sendMailSimple
     * @参数名：@param subject  邮件主题
     * @参数名：@param content 邮件内容
     * @参数名：@param to     收件人Email地址
     * @描述语: 发送邮件
     */

    public void sendMailSimple(String to, String subject, String content) throws Exception {

        try {
            //用于接收邮件的邮箱
            simpleMailMessage.setTo(to);
            //邮件的主题
            simpleMailMessage.setSubject(subject);
            //邮件的正文，第二个boolean类型的参数代表html格式
            simpleMailMessage.setText(content);

            LOGGER.info("---------------------------{}", simpleMailMessage);
            //发送
            javaMailSender.send(simpleMailMessage);

        } catch (Exception e) {
            throw new MessagingException("failed to send mail!", e);
        }
    }

    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
        this.simpleMailMessage = simpleMailMessage;
    }
}