package com.cognizant.microcredential.emailservice.model;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

public class BaseMailInfo {

    @NotNull
    @NotEmpty
    protected String[] to;

    protected String[] cc;

    protected String[] bcc;

    @NotNull
    @NotEmpty
    protected String subject;

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String[] getBcc() {
        return bcc;
    }

    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "BaseMailInfo{" +
                "to=" + Arrays.toString(to) +
                ", cc=" + Arrays.toString(cc) +
                ", bcc=" + Arrays.toString(bcc) +
                ", subject='" + subject + '\'' +
                '}';
    }
}
