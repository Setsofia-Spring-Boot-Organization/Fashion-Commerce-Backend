package com.example.fashion_commerce.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
public class MailSenderImpl implements MailSender {

    private final JavaMailSender mailSender;

    public MailSenderImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${spring.mail.username}")
    private String MAIL_SENDER;

    @Override
    public void sendMail(String to, String subject, Map<String, Object> variables, String template) throws MessagingException {

        TemplateEngine templateEngine = new TemplateEngine();
//        Context context = new Context();
//
//        context.setVariables(variables);

//        String text = templateEngine.process(template, context);

//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//        messageHelper.setPriority(1);
//        messageHelper.setSubject(subject);
//        messageHelper.setFrom(MAIL_SENDER);
//        messageHelper.setTo(to);
//        messageHelper.setText(text, true);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(MAIL_SENDER);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("subject");
        simpleMailMessage.setText("template");

        mailSender.send(simpleMailMessage);
    }
}
