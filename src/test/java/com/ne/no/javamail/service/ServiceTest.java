package com.ne.no.javamail.service;



import com.ne.no.javamail.hello.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
    @Resource
    MailService mailService;

    @Resource
    TemplateEngine templateEngine;

    @Test
    public void sayHelloTest(){
        mailService.sayHello();
    }

    @Test
    public void sendSimpleMailTest(){
        mailService.sendSimpleMail("2532612444@qq.com","这是第一封文本邮件","大家好，这是一封文本邮件");
    }

    @Test
    public void sendHtmlMailTest() throws MessagingException {
        String content="<html>\n"+"<body>\n"+"<h3>hello html mail</h3></body></html>";

        mailService.sendHtmlMail("2532612444@qq.com","html mail",content);
    }

    @Test
    public  void sendAttachmentsMailTest() throws MessagingException {
        String filePath="d:/java/java-mail/README.md";
        mailService.sendAttachmentsMail("2532612444@qq.com","attachments","fujian ",filePath);
    }

    @Test
    public void sendInlineResourceMail() throws MessagingException {
        String imgPath="d:/java/java-mail/1.jpg";
        String rscId="hello";
        String content="<html><body>这是有图片的邮件:<img src=\'cid:"+rscId
                +"\'><img></body></html>";

        mailService.sendInlineResourceMail("2532612444@qq.com","image",content,imgPath,rscId);
    }

    @Test
    public void TemplateMailTest() throws MessagingException {
        Context context=new Context();
        context.setVariable("id","006");
        String emailContent=templateEngine.process("emailTemplate",context);

        mailService.sendHtmlMail("2532612444@qq.com","mubanyouijan",emailContent);
    }

}
