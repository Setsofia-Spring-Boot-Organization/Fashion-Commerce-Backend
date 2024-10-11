package com.example.fashion_commerce.mail;

public interface MailSender {

    void sendMail(String to, String subject, String content);
}
