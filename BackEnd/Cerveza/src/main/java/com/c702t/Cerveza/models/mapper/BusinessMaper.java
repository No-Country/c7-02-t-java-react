package com.c702t.Cerveza.models.mapper;

import com.c702t.Cerveza.models.entity.BusinessEntity;
import com.c702t.Cerveza.models.entity.UserEntity;
import com.c702t.Cerveza.models.request.BusinessRequest;
import com.c702t.Cerveza.models.response.BusinessByUserResponse;
import com.c702t.Cerveza.models.response.BusinessResponse;
import com.c702t.Cerveza.repository.BusinessRepository;
import com.c702t.Cerveza.repository.UserRepository;
import com.c702t.Cerveza.service.AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class BusinessMaper {

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    AwsService awsService;


    public BusinessEntity Request2Entity (BusinessRequest request, Long userID) throws IOException {

        BusinessEntity entity = BusinessEntity.builder().name(request.getName())
                .image(awsService.uploadFileFromBase64(request.getImage()))
                .businessAddress(request.getAddress())
                .businessCity(request.getCity())
                .businessState(request.getState())
                .businessCountry(request.getCountry())
                .phone(request.getPhone())
                .email(request.getEmail())
                .aboutUsText(request.getAboutUsText())
                .urlFacebook(request.getUrlFacebook())
                .urlInstagram(request.getUrlInstagram())
                .users(userRepository.findById(userID).get())
                 .build();

        entity.setValue(0.00f);
        entity.setCount(0);
        entity.setRating(0.00f);

        return entity;

        }

    public BusinessResponse Entity2Response (BusinessEntity entity) throws IOException {

        BusinessResponse response = BusinessResponse.builder().name(entity.getName())
                .id(entity.getId())
                .image(awsService.uploadFileFromBase64(entity.getImage()))
                .address(entity.getBusinessAddress())
                .city(entity.getBusinessCity())
                .state(entity.getBusinessState())
                .country(entity.getBusinessCountry())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .aboutUsText(entity.getAboutUsText())
                .urlFacebook(entity.getUrlFacebook())
                .urlInstagram(entity.getUrlInstagram())
                .rating(entity.getRating())
                .build();

        return response;

    }


    public List<BusinessResponse> EntityList2ResponseList(List<BusinessEntity> entities) throws IOException {

        List<BusinessResponse> responseList = new ArrayList<>();

        //BusinessResponse response;

        for (BusinessEntity entity : entities) {
            BusinessResponse response = new BusinessResponse();
            response= BusinessResponse.builder().name(entity.getName())
                    .id(entity.getId())
                    .image(awsService.uploadFileFromBase64(entity.getImage()))
                    .address(entity.getBusinessAddress())
                    .city(entity.getBusinessCity())
                    .state(entity.getBusinessState())
                    .country(entity.getBusinessCountry())
                    .phone(entity.getPhone())
                    .email(entity.getEmail())
                    .aboutUsText(entity.getAboutUsText())
                    .urlFacebook(entity.getUrlFacebook())
                    .urlInstagram(entity.getUrlInstagram())
                    .rating(entity.getRating())
                    .build();
            responseList.add(response);


        }
        return responseList;
    }


    public BusinessEntity EntityRefreshRating (BusinessEntity entity, Float totalValue){

        Float sumValues = entity.getValue()+totalValue;
        entity.setValue(sumValues);

        entity.setCount(entity.getCount()+1);

        Integer countNew= entity.getCount();

        Float totalRating = sumValues/countNew;


        entity.setRating(totalRating);

        return entity;

    }

    public BusinessEntity EntityRefreshValues (BusinessEntity entity, BusinessRequest request) throws IOException {

        entity = BusinessEntity.builder().name(request.getName())
                .image(awsService.uploadFileFromBase64(request.getImage()))
                .businessAddress(request.getAddress())
                .businessCity(request.getCity())
                .businessState(request.getState())
                .businessCountry(request.getCountry())
                .phone(request.getPhone())
                .email(request.getEmail())
                .aboutUsText(request.getAboutUsText())
                .urlFacebook(request.getUrlFacebook())
                .urlInstagram(request.getUrlInstagram())
                .build();

        return entity;
    }


    public BusinessByUserResponse toBusinessByUserResponse(BusinessEntity entity)  {
        return BusinessByUserResponse.builder()
                .id(entity.getId())
                .image(entity.getImage())
                .address(entity.getBusinessAddress())
                .city(entity.getBusinessCity())
                .state(entity.getBusinessState())
                .country(entity.getBusinessCountry())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .aboutUsText(entity.getAboutUsText())
                .urlFacebook(entity.getUrlFacebook())
                .urlInstagram(entity.getUrlInstagram())
                .rating(entity.getRating())
                .userId(entity.getUsers().getId())
                .creationDate(entity.getTimestamp())
                .build();
    }

    public List<BusinessByUserResponse> toBusinessByUserResponseList(List<BusinessEntity> entities)  {
        List<BusinessByUserResponse> responseList = new ArrayList<>();
        for (BusinessEntity entity : entities) {
            responseList.add(toBusinessByUserResponse(entity));
        }
        return responseList;
    }

}
