package com.c702t.Cerveza.service;

import com.c702t.Cerveza.auth.service.JwtUtils;
import com.c702t.Cerveza.exception.RuntimeExceptionCustom;
import com.c702t.Cerveza.models.entity.BusinessEntity;
import com.c702t.Cerveza.models.entity.ReviewEntity;
import com.c702t.Cerveza.models.entity.UserEntity;
import com.c702t.Cerveza.models.mapper.ReviewMapper;
import com.c702t.Cerveza.models.request.ReviewRequest;
import com.c702t.Cerveza.models.request.ReviewUpdateRequest;
import com.c702t.Cerveza.models.response.ReviewResponse;
import com.c702t.Cerveza.repository.BusinessRepository;
import com.c702t.Cerveza.repository.ReviewRepository;
import com.c702t.Cerveza.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    BusinessRepository businessRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReviewMapper reviewMapper;
    @Autowired
    JwtUtils jwtUtils;

    public ReviewResponse create(String token, ReviewRequest reviewRequest) {
        token = token.substring(7);
        String username = jwtUtils.extractUsername(token);
        UserEntity userEntity = userRepository.findByEmail(username).get();
        BusinessEntity businessEntity = businessRepository.getById(reviewRequest.getBusinessId());
        if (businessEntity == null) {
            throw new RuntimeExceptionCustom(
                    "404 :: business with id " + reviewRequest.getBusinessId() + "  not found");
        }

        ReviewEntity reviewEntity = reviewMapper.toEntity(reviewRequest);
        reviewEntity.setBusinessEntity(businessEntity);
        reviewEntity.setUserEntity(userEntity);
        ReviewEntity reviewEntitySaved = reviewRepository.save(reviewEntity);
        ReviewResponse reviewResponse = reviewMapper.toResponse(reviewEntitySaved);
        return reviewResponse;
    }

    public ReviewResponse read(Long id) {
        ReviewEntity reviewEntity = reviewRepository.getById(id);
        if (reviewEntity == null) {
            throw new RuntimeExceptionCustom("404 :: Review with id " + id + " not found.");
        }
        return reviewMapper.toResponse(reviewEntity);
    }

    public ReviewResponse update(Long id, String token, ReviewUpdateRequest reviewUpdateRequest) {
        token = token.substring(7);
        String username = jwtUtils.extractUsername(token);
        UserEntity userEntity = userRepository.findByEmail(username).get();

        ReviewEntity reviewEntity = reviewRepository.getById(id);
        if (reviewEntity == null) {
            throw new RuntimeExceptionCustom("404 :: Review with id " + id + " not found.");
        }
        if (! userEntity.getReviewEntitySet().contains(reviewEntity)) {
            throw new RuntimeExceptionCustom("404 :: Review with id " + id + " not found.");
        }

        if (reviewUpdateRequest.getAttentionRate() != null) {
            reviewEntity.setAttentionRate(reviewUpdateRequest.getAttentionRate());
        }
        if (reviewUpdateRequest.getPlaceRate() != null) {
            reviewEntity.setPlaceRate(reviewUpdateRequest.getPlaceRate());
        }
        if (reviewUpdateRequest.getPriceRate() != null) {
            reviewEntity.setPriceRate(reviewUpdateRequest.getPriceRate());
        }
        if (reviewUpdateRequest.getQualityRate() != null) {
            reviewEntity.setQualityRate(reviewUpdateRequest.getQualityRate());
        }
        if (reviewUpdateRequest.getText() != null) {
            reviewEntity.setText(reviewUpdateRequest.getText());
        }
        Float average = (float) (reviewUpdateRequest.getAttentionRate()
                + reviewUpdateRequest.getPlaceRate()
                + reviewUpdateRequest.getPriceRate()
                + reviewUpdateRequest.getQualityRate()) / 4;
        reviewEntity.setTotalRate(average);
        ReviewEntity reviewEntitySaved = reviewRepository.save(reviewEntity);
        return reviewMapper.toResponse(reviewEntitySaved);
    }

    public void delete(Long id, String token) {
        token = token.substring(7);
        String username = jwtUtils.extractUsername(token);
        UserEntity userEntity = userRepository.findByEmail(username).get();

        ReviewEntity reviewEntity = reviewRepository.getById(id);
        if (reviewEntity == null) {
            throw new RuntimeExceptionCustom("404 :: Review with id " + id + " not found.");
        }
        if (! userEntity.getReviewEntitySet().contains(reviewEntity)) {
            throw new RuntimeExceptionCustom("404 :: Review with id " + id + " not found.");
        }
        reviewRepository.deleteById(id);
    }
}