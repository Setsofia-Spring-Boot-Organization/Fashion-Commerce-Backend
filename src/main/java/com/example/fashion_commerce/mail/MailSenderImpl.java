package com.example.fashion_commerce.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
public class MailSenderImpl implements MailSender {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String MAIL_SENDER;

    public MailSenderImpl(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendMail(String to, String subject, Map<String, Object> variables, String template) throws MessagingException {

        Context context = new Context();
        context.setVariable("username", to);

        String process = templateEngine.process("SuccessfulOrderFeedback", context);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(subject);
        helper.setText(process, true);
        helper.setTo(to);
        helper.setFrom(MAIL_SENDER);

        mailSender.send(mimeMessage);
    }
}
