package com.c702t.Cerveza.models.response;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "Class representing a Review response.")
public class ReviewResponse {

    private Long id;
    private Long attentionRate;
    private Long priceRate;
    private Long qualityRate;
    private Long placeRate;
    private Float totalRate;
    private String text;
    private Timestamp timestamp;
}
