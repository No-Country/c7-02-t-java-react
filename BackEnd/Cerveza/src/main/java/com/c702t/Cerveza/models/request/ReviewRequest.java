package com.c702t.Cerveza.models.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "Class representing a Review request.")
public class ReviewRequest {
    @ApiModelProperty(notes = "attentionRate in {1, ... , 5}",
            example = "1",
            required = true)
    @NotNull(message = "attentionRate can not be null")
    @Min(value = 1, message = "attentionRate Must be between 1 to 5")
    @Max(value = 5, message = "attentionRate Must be between 1 to 5")
    private Long attentionRate;

    @ApiModelProperty(notes = "priceRate in {1, ... , 5}",
            example = "1",
            required = true)
    @NotNull(message = "priceRate can not be null")
    @Min(value = 1, message = "priceRate must be between 1 to 5")
    @Max(value = 5, message = "priceRate must be between 1 to 5")
    private Long priceRate;

    @ApiModelProperty(notes = "qualityRate in {1, ... , 5}",
            example = "1",
            required = true)
    @NotNull(message = "qualityRate can not be null")
    @Min(value = 1, message = "qualityRate must be between 1 to 5")
    @Max(value = 5, message = "qualityRate must be between 1 to 5")
    private Long qualityRate;

    @ApiModelProperty(notes = "placeRate in {1, ... , 5}",
            example = "1",
            required = true)
    @NotNull(message = "placeRate can not be null")
    @Min(value = 1, message = "placeRate must be between 1 to 5")
    @Max(value = 5, message = "placeRate must be between 1 to 5")
    private Long placeRate;

    @ApiModelProperty(notes = "Text of the review",
            example = "First review lol",
            required = true)
    @NotEmpty(message = "text can not be empty")
    @NotBlank(message = "text can not be blank")
    @NotNull(message = "text can not be null")
    private String text;
}
