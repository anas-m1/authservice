package com.example.authservice.dtos;

import lombok.Data;

@Data
public class SendEmailMsgDTO {
    private String to;
    private String from;
    private String subject;
    private String body;
}
