package com.c702t.Cerveza.models.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentRequest {
    @ApiModelProperty(notes = "text of the comment",
            example = "First comment lol",
            required = true)
    @NotBlank(message = "text can not be blank")
    @NotEmpty(message = "text can not be empty")
    @NotNull(message = "text can not be null")
    String text;
}
