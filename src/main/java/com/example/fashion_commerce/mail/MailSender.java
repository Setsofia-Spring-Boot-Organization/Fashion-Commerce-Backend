package com.example.fashion_commerce.mail;

import java.util.Map;

public interface MailSender {

    void sendMail(String to, String subject, Map<String, Object> variables, String template);
}
