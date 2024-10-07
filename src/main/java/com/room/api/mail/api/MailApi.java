package com.room.api.mail.api;

import com.room.api.mail.service.IEmailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/mail-send/v1")
public class MailApi {

    @Autowired
    private IEmailService emailService;

    @PostMapping("/send")
    public String sendEmail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String body,
            @RequestParam String pdfName) {

        try {
            log.info("send mail method start :{}", subject);
            emailService.mailSend(to, subject, body, pdfName);
            log.info("mail send successfully to :{}", to);
            return "Email with attachment sent successfully!";
        } catch (Exception e) {
            log.error("exception occurred during mail send :{}", e.getMessage());
            e.printStackTrace();
            return "Failed to send email with attachment.";
        }
    }
}
