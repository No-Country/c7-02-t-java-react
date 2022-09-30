package com.c702t.Cerveza.models.mapper;

import com.c702t.Cerveza.models.entity.BusinessEntity;
import com.c702t.Cerveza.models.request.BusinessRequest;
import com.c702t.Cerveza.models.response.BusinessResponse;
import com.c702t.Cerveza.repository.BusinessRepository;
import lombok.Builder;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class BusinessMaper {

    @Autowired
    private BusinessRepository businessRepository;

   /* @Autowired
    UserRepository userRepository;
    @Autowired
    AwsService awsService;*/


    public BusinessEntity Request2Entity (BusinessRequest request){

        BusinessEntity entity = BusinessEntity.builder().name(request.getName())
                .image(request.getImage()) //Modificar con método AWS
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .country(request.getCountry())
                .phone(request.getPhone())
                .email(request.getEmail())
                .aboutUsText(request.getAboutUsText())
                .urlFacebook(request.getUrlFacebook())
                .urlInstagram(request.getUrlInstagram())
                /*.user(userRepository.getById(request.getIdUser()))
                .rating(ratingRepository.getById(request.getIdRating()))*/
                .build();

        return entity;

        }

    public BusinessResponse Entity2Response (BusinessEntity entity){

        BusinessResponse response = BusinessResponse.builder().name(entity.getName())
                .image(entity.getImage()) //Modificar con método AWS
                .address(entity.getAddress())
                .city(entity.getCity())
                .state(entity.getState())
                .country(entity.getCountry())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .aboutUsText(entity.getAboutUsText())
                .urlFacebook(entity.getUrlFacebook())
                .urlInstagram(entity.getUrlInstagram())
                /*.ratingId(entity.getRating().getId)*/
                .build();

        return response;

    }


    public List<BusinessResponse> EntityList2ResponseList(List<BusinessEntity> entities){

        List<BusinessResponse> responseList = new ArrayList<>();

        //BusinessResponse response;

        for (BusinessEntity entityList : entities) {
            BusinessResponse response = new BusinessResponse();
            response= BusinessResponse.builder().name(response.getName())
                    .image(response.getImage()) //Modificar con método AWS
                    .address(response.getAddress())
                    .city(response.getCity())
                    .state(response.getState())
                    .country(response.getCountry())
                    .phone(response.getPhone())
                    .email(response.getEmail())
                    .aboutUsText(response.getAboutUsText())
                    .urlFacebook(response.getUrlFacebook())
                    .urlInstagram(response.getUrlInstagram())
                    /*.ratingId(entity.getRating().getId)*/
                    .build();
            responseList.add(response);


        }
        return responseList;
    }


    public BusinessEntity EntityRefreshValues (BusinessEntity entity, BusinessRequest request) throws IOException {

        entity = BusinessEntity.builder().name(request.getName())
                .image(request.getImage()) //Modificar con método AWS
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .country(request.getCountry())
                .phone(request.getPhone())
                .email(request.getEmail())
                .aboutUsText(request.getAboutUsText())
                .urlFacebook(request.getUrlFacebook())
                .urlInstagram(request.getUrlInstagram())
                /*.user(userRepository.getById(request.getIdUser()))
                .rating(ratingRepository.getById(request.getIdRating()))*/
                .build();

        return entity;
    }


}
