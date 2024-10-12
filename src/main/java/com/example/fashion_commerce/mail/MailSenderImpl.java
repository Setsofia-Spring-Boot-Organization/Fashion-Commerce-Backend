package com.example.fashion_commerce.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    public void sendMail(String to, String subject, Map<String, Object> variables, String template) {

        TemplateEngine templateEngine = new TemplateEngine();
        Context context = new Context();

        context.setVariables(variables);

        String text = templateEngine.process(template, context);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(MAIL_SENDER);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);

        mailSender.send(simpleMailMessage);
    }
}
