package com.c702t.Cerveza.models.request;

import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "Class representing a Review request.")
public class ReviewRequest {

    @NotNull(message = "attentionRate can not be null")
    @Min(value = 1, message = "attentionRate Must be between 1 to 5")
    @Max(value = 5, message = "attentionRate Must be between 1 to 5")
    private Long attentionRate;

    @NotNull(message = "priceRate can not be null")
    @Min(value = 1, message = "priceRate must be between 1 to 5")
    @Max(value = 5, message = "priceRate must be between 1 to 5")
    private Long priceRate;

    @NotNull(message = "qualityRate can not be null")
    @Min(value = 1, message = "qualityRate must be between 1 to 5")
    @Max(value = 5, message = "qualityRate must be between 1 to 5")
    private Long qualityRate;

    @NotNull(message = "placeRate can not be null")
    @Min(value = 1, message = "placeRate must be between 1 to 5")
    @Max(value = 5, message = "placeRate must be between 1 to 5")
    private Long placeRate;

    @NotNull(message = "totalRate can not be null")
    private String text;
}
