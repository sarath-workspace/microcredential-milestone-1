package com.cognizant.microcredential.emailservice.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

public class SimpleMailInfo extends BaseMailInfo {

    @NotNull
    @NotEmpty
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SimpleMailInfo{" +
                "message='" + message + '\'' +
                ", to=" + Arrays.toString(to) +
                ", cc=" + Arrays.toString(cc) +
                ", bcc=" + Arrays.toString(bcc) +
                ", subject='" + subject + '\'' +
                '}';
    }
}
