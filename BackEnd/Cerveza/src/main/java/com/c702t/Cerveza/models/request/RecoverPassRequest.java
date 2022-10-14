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
public class RecoverPassRequest {

    @NotNull(message = "the email can't be null")
    @Email(message = "the email is not valid")
    @ApiModelProperty(notes = "Email of the User.", example = "am@mail.com", required = true)
    private String email;

}