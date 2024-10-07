package com.room.api.mail.service;

public interface IEmailService {

    void mailSend(String to, String subject, String body, String pdfName);
}
