package com.c702t.Cerveza.models.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
public class AuthRequest {
    @NotNull
    @NotEmpty(message = "the email can't be null")
    @NotBlank(message = "the email can't be blank")
    @Email(message = "Email is not valid")
    private String email;
    @NotNull
    @NotBlank(message = "the password can't be blank")
    @NotEmpty(message = "the password can't be null")
    private String password;
}