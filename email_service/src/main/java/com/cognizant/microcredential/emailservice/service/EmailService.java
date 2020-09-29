package com.cognizant.microcredential.emailservice.service;

import com.cognizant.microcredential.emailservice.model.MailWithAttachment;
import com.cognizant.microcredential.emailservice.model.SimpleMailInfo;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {

    public abstract void sendMail(SimpleMailInfo mail);

    public abstract void sentMailWithAttachment(MailWithAttachment mail) throws MessagingException, IOException;

}
