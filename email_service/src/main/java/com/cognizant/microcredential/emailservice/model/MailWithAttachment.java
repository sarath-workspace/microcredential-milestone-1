package com.cognizant.microcredential.emailservice.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

public class MailWithAttachment extends SimpleMailInfo {

    @NotNull
    @NotEmpty
    private String pathToAttachment;

    public String getPathToAttachment() {
        return pathToAttachment;
    }

    public void setPathToAttachment(String pathToAttachment) {
        this.pathToAttachment = pathToAttachment;
    }

    @Override
    public String toString() {
        return "MailWithAttachment{" +
                "pathToAttachment='" + pathToAttachment + '\'' +
                ", to=" + Arrays.toString(to) +
                ", cc=" + Arrays.toString(cc) +
                ", bcc=" + Arrays.toString(bcc) +
                ", subject='" + subject + '\'' +
                '}';
    }
}
