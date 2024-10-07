package com.room.api.mail.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Log4j2
public class EmailService implements IEmailService {

    private final String folderPath = "D:/sameer-codes only/SB-ROOM/room-api/room-api/generated_pdfs/";
    @Autowired
    private MailSender mailSender;
    @Value("${spring.mail.username}")
    private String email;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void mailSend(String to, String subject, String body, String pdfName) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            // Use MimeMessageHelper to handle attachments
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(email);    // From email
            helper.setTo(to);         // To email
            helper.setSubject(subject);  // Subject
            helper.setText(body); // body

            String pdfFilePath = folderPath + pdfName;
            log.info("path of file location :{}", pdfFilePath);

            FileSystemResource file = new FileSystemResource(new File(pdfFilePath));
            helper.addAttachment(file.getFilename(), file);

            // Send the email
            javaMailSender.send(message);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }
}
