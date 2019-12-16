package org.study.springboot.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;


/**
 * @ClassName: MailController
 * @Description:
 * @Author: Lcc
 * @Date: 2019/8/7
 * @Version 1.0
 */
@Api(value = "邮件模块")
@RestController
public class MailController {

    @Autowired
    private JavaMailSenderImpl mailSender;

    /**
     * @Author: Lcc
     * @Description: 发送简单邮件
     * @Date: 2019/8/7
     * @param
     * @Return: java.lang.String
     */
    @GetMapping("/simpleMail")
    public String sendSimpleMail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("部门例会");
        message.setText("明天上午九点第一会议室开会");
        message.setFrom("2970988183@qq.com");
        message.setTo("1476445302@qq.com");
        mailSender.send(message);
        return "发送成功";
    }

    /**
     * @Author: Lcc
     * @Description: 复杂邮件
     * @Date: 2019/8/7
     * @param
     * @Return: java.lang.String
     */
    @GetMapping("/complexMail")
    public String sendComplexMail() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject("部门例会");
        helper.setText("<b style='color:red'>明天上午九点第一会议室开会</b>", true);
        helper.addAttachment("会议室图片.jpg", new File("C:\\Users\\lcc\\Desktop\\study\\timg.jpg"));
        helper.setFrom("2970988183@qq.com");
        helper.setTo("1476445302@qq.com");
        mailSender.send(message);
        return "发送成功";
    }

}
