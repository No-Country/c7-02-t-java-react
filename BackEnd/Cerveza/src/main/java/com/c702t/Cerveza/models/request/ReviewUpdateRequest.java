package com.c702t.Cerveza.models.request;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ReviewUpdateRequest {
    private Long attentionRate;
    private Long priceRate;
    private Long qualityRate;
    private Long placeRate;
    private Float totalRate;
    private String text;
    private Timestamp timestamp;
}
