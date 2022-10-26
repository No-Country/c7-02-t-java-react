package com.c702t.Cerveza.models.mapper;

import com.c702t.Cerveza.exception.RuntimeExceptionCustom;
import com.c702t.Cerveza.models.entity.SlideEntity;
import com.c702t.Cerveza.models.request.SlideRequest;
import com.c702t.Cerveza.models.response.SlideResponse;
import com.c702t.Cerveza.repository.BusinessRepository;
import com.c702t.Cerveza.service.AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class SlideMapper {

    @Autowired
    AwsService awsService;
    @Autowired
    BusinessRepository businessRepository;

    public SlideEntity Request2Entity (SlideRequest request) throws RuntimeExceptionCustom {

        SlideEntity entity = new SlideEntity();

        return entity.builder()
                .business(businessRepository.getById(request.getBusiness_id()))
                .photo(request.getPhoto())
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .sofdelete(false)
                .build();

    }

    public SlideResponse Entity2Response (SlideEntity entity){

        return SlideResponse.builder()
                .id(entity.getId())
                .business_id(entity.getBusiness().getId())
                .photo(entity.getPhoto())
                .build();

    }

    public SlideEntity EntityUpdate (SlideEntity entity, SlideRequest request) throws RuntimeExceptionCustom {

        return SlideEntity.builder()
                .id(entity.getId())
                .business(businessRepository.getById(request.getBusiness_id()))
                .photo(request.getPhoto())
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    public List<SlideResponse> EntityList2Response(List<SlideEntity> SlideList){

        List<SlideResponse> responses = new ArrayList<>();

        for ( SlideEntity slide: SlideList){
            responses.add(Entity2Response(slide));
        }

        return responses;
    }

}
