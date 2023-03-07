package com.example.errorhandler.member;

import com.example.errorhandler.validation.EmailUnique;
import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
public class SignUpRequest {
    @Email
    @EmailUnique
    private String email;
}

class ChangeEmailRequest{
    @Email
    @EmailUnique
    private String email;
}