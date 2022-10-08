package com.c702t.Cerveza.models.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    @NotNull(message = "the firstName can't be null")
    @NotEmpty(message = "the firstName can't be empty")
    @NotBlank(message = "the firstName can't  be blank")
    @ApiModelProperty(notes = "firstName of the User.", example = "alejandro", required = true)
    private String firstName;

    @NotNull(message = "the lastName can't be null")
    @NotEmpty(message = "the lastName can't be empty")
    @NotBlank(message = "the lastName can't  be blank")
    @ApiModelProperty(notes = "lastName of the User.", example = "lopez", required = true)
    private String lastName;

    @NotEmpty
    @NotBlank(message = "the password can't be blank")
    @ApiModelProperty(notes = "password of the User.", example = "1234", required = true)
    private String password;

    @NotEmpty
    @NotBlank(message = "the photo can't be blank")
    private String photo;
}
