package com.c702t.Cerveza.models.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Class representing an Recover Password")
public class UpdatePasswordRequest {

    @NotNull(message = "the email can't be null")
    @Email(message = "the email is not valid")
    @ApiModelProperty(notes = "Email of the User.", example = "alexmoll2008@hotmail.com", required = true)
    private String email;

    @NotNull(message = "the newPassword can't be null")
    @NotEmpty(message = "the newPassword can't be empty")
    @NotBlank(message = "the newPassword can't  be blank")
    @ApiModelProperty(notes = "newPassword of the User.", example = "1234", required = true)
    private String newPassword;

    @NotNull(message = "the confirmNewPassword confirm can't be null")
    @NotEmpty(message = "the confirmNewPassword confirm can't be empty")
    @NotBlank(message = "the confirmNewPassword confirm can't  be blank")
    @ApiModelProperty(notes = "confirmNewPassword of the User.", example = "1234", required = true)
    private String confirmNewPassword;
}
