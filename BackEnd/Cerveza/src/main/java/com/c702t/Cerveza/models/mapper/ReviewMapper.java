package com.c702t.Cerveza.models.mapper;

import com.c702t.Cerveza.models.entity.ReviewEntity;
import com.c702t.Cerveza.models.request.ReviewRequest;
import com.c702t.Cerveza.models.response.ReviewResponse;
import com.c702t.Cerveza.repository.ReviewRepository;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    private ReviewRepository reviewRepository;

    public ReviewEntity toEntity(ReviewRequest request) {
        ReviewEntity reviewEntity = ReviewEntity.builder()
                .attentionRate(request.getAttentionRate())
                .placeRate(request.getPlaceRate())
                .priceRate(request.getPriceRate())
                .qualityRate(request.getQualityRate())
                .text(request.getText())
                .build();
        Float average = (float) (request.getAttentionRate()
                + request.getPlaceRate()
                + request.getPriceRate()
                + request.getQualityRate()) / 4;
        reviewEntity.setTotalRate(average);
        return reviewEntity;
    }

    public ReviewResponse toResponse(ReviewEntity entity) {
        ReviewResponse reviewResponse = ReviewResponse.builder()
                .id(entity.getId())
                .attentionRate(entity.getAttentionRate())
                .placeRate(entity.getPlaceRate())
                .priceRate(entity.getPriceRate())
                .qualityRate(entity.getQualityRate())
                .totalRate(entity.getTotalRate())
                .text(entity.getText())
                .timestamp(entity.getTimestamp())
                .build();
        return reviewResponse;
    }
}
