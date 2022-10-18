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

import java.util.List;
import java.util.Optional;

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
    @Autowired
    BusinessService businessService;

    public ReviewResponse create(String token, Long businessID, ReviewRequest reviewRequest) {
        token = token.substring(7);
        String username = jwtUtils.extractUsername(token);
        UserEntity userEntity = userRepository.findByEmail(username).get();
        BusinessEntity businessEntity = businessRepository.getById(businessID);
        if (businessEntity == null) {
            throw new RuntimeExceptionCustom(
                    "404 :: business with id " + businessID + "  not found");
        }

        ReviewEntity reviewEntity = reviewMapper.toEntity(reviewRequest);
        reviewEntity.setBusinessEntity(businessEntity);
        reviewEntity.setUserEntity(userEntity);
        ReviewEntity reviewEntitySaved = reviewRepository.save(reviewEntity);
        businessService.valueRating(businessEntity.getId(), reviewEntitySaved.getTotalRate());

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

    public List<ReviewResponse> readAllByBusinessID(Long businessID) {
        Optional<BusinessEntity>  optionalBusinessEntity = businessRepository.findById(businessID);
        if (optionalBusinessEntity.isEmpty()) {
            throw new RuntimeExceptionCustom("404 :: Business with id " + businessID + " not found.");
        }
        return reviewMapper.toResponseList(optionalBusinessEntity.get().getReviewEntitySet());
    }

    public List<ReviewResponse> readAllByUserID(Long userID) {
        Optional<UserEntity>  optionalUserEntity = userRepository.findById(userID);
        if (optionalUserEntity.isEmpty()) {
            throw new RuntimeExceptionCustom("404 :: User with id " + userID + " not found.");
        }
        return reviewMapper.toResponseList(optionalUserEntity.get().getReviewEntitySet());
    }

    public ReviewResponse update(Long id, String token, ReviewUpdateRequest reviewUpdateRequest) {
        // TODO update rating of Business
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
        // TODO update rating of Business
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

    /* Like System
    public ReviewResponse likeReview(String token, Long reviewID) {
        token = token.substring(7);
        String username = jwtUtils.extractUsername(token);
        UserEntity userEntity = userRepository.findByEmail(username).get();
        ReviewEntity reviewEntity = reviewRepository.getById(reviewID);
        if (reviewEntity == null) {
            throw new RuntimeExceptionCustom("404 :: Review with id " + reviewID + " not found.");
        }
    }
    */
}