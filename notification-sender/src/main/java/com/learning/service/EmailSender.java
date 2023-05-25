package com.learning.service;

import com.learning.config.EmailConfig;
import com.learning.entity.EmailEntity;
import com.learning.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailSender {
    private final JavaMailSender mailSender;
    private final EmailConfig emailConfig;
    private final EmailRepository emailRepository;

    public void sendEmails(EmailEntity email) {
        CompletableFuture.runAsync(() -> {
            try {
                log.info("Sending Email...");
                    sendEmail(email.getRecipient(), email.getMessage(),email.getSubject());
                    log.info(String.format("Mail sent to %s", email));
                log.info(" Mail Sent Successfully...");
            } catch (Exception e) {
                log.error("Error while sending mail", e);
            }
        });
    }
    private void sendEmail(String recipientEmail,String message, String subject) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom(emailConfig.getFrom());
        mimeMessageHelper.setTo(recipientEmail);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(message);

        if (emailConfig.getAttachment() != null && !emailConfig.getAttachment().isEmpty()) {
            FileSystemResource file = new FileSystemResource(new File(emailConfig.getAttachment()));
            mimeMessageHelper.addAttachment(file.getFilename(), file);
        }

        mailSender.send(mimeMessage);

        saveEmailToDatabase(recipientEmail, emailConfig.getSubject(), message);

    }

    private void saveEmailToDatabase(String recipient, String subject, String message){
        EmailEntity email = new EmailEntity();
        email.setSubject(subject);
        email.setMessage(message);
        email.setRecipient(recipient);

        emailRepository.save(email);
    }
}


