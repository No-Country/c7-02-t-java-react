package com.c702t.Cerveza.models.request;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(notes = "Email of the User.", example = "am@mail.com", required = true)
    private String email;

    @NotNull
    @NotBlank(message = "the password can't be blank")
    @NotEmpty(message = "the password can't be null")
    @ApiModelProperty(notes = "Password of the User.", example = "1234", required = true)
    private String password;
}