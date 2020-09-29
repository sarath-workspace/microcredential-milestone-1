package com.cognizant.microcredential.emailservice.controller;

import com.cognizant.microcredential.emailservice.model.MailWithAttachment;
import com.cognizant.microcredential.emailservice.model.SimpleMailInfo;
import com.cognizant.microcredential.emailservice.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/message")
public class EmailServiceController {

    private final Logger logger = LoggerFactory.getLogger(EmailServiceController.class);

    @Autowired
    private EmailService emailService;

    @PostMapping("/simple")
    public void sentSimpleMessage(@RequestBody @Valid SimpleMailInfo simpleMailInfo) {
        logger.info("Simple email service has been invoked with {}", simpleMailInfo);
        emailService.sendMail(simpleMailInfo);
    }

    @PostMapping("/simple/attachment")
    public void sentSimpleMessageWithAttachment(@RequestBody @Valid MailWithAttachment mailWithAttachment) throws Exception {
        logger.info("mail with attachment service has been invoked with {}", mailWithAttachment);
        emailService.sentMailWithAttachment(mailWithAttachment);
    }

}
