package com.cognizant.microcredential.emailservice.service;

import com.cognizant.microcredential.emailservice.model.MailWithAttachment;
import com.cognizant.microcredential.emailservice.model.SimpleMailInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Objects;

@Service
public class EmailServiceImpl implements EmailService {

    private final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMail(SimpleMailInfo mail) {
        logger.info("Sent mail service has been invoked");
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mail.getTo());
        if(Objects.nonNull(mail.getCc()))
            msg.setTo(mail.getCc());
        if(Objects.nonNull(mail.getBcc()))
            msg.setTo(mail.getBcc());
        msg.setSubject(mail.getSubject());
        msg.setText(mail.getMessage());
        javaMailSender.send(msg);
    }

    @Override
    public void sentMailWithAttachment(MailWithAttachment mail) throws MessagingException, IOException {
        logger.info("the attachment mail service has been invoked");
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(mail.getTo());
        if(Objects.nonNull(mail.getCc()))
            helper.setCc(mail.getCc());
        if(Objects.nonNull(mail.getBcc()))
            helper.setBcc(mail.getBcc());
        helper.setSubject(mail.getSubject());
        helper.setText(mail.getMessage());
        FileSystemResource file = new FileSystemResource(mail.getPathToAttachment());
        helper.addAttachment("attachment.txt", file);
        javaMailSender.send(msg);
    }
}
