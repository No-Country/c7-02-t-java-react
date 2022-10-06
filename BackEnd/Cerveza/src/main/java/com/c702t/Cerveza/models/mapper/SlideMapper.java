package com.c702t.Cerveza.models.mapper;

import com.c702t.Cerveza.models.entity.SlideEntity;
import com.c702t.Cerveza.models.request.SlideRequest;
import com.c702t.Cerveza.models.response.SlideResponse;
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

    public SlideEntity Request2Entity (SlideRequest request) throws IOException {

        SlideEntity entity = new SlideEntity();

        return entity.builder()
//                .business(request.getBusiness())
                .photo(request.getPhoto())
                .timestamp(request.getTimestamp())
                .sofdelete(false)
                .build();

    }

    public SlideResponse Entity2Response (SlideEntity entity){

        return SlideResponse.builder()
                .id(entity.getId())
//                .business(entity.getBusiness())
                .photo(entity.getPhoto())
                .timestamp(entity.getTimestamp())
                .build();

    }

    public SlideEntity EntityUpdate (SlideEntity entity, SlideRequest request) throws IOException {

        return SlideEntity.builder()
                .id(entity.getId())
//                .business(request.getBusiness())
                .photo(request.getPhoto())
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .sofdelete(request.getSofdelete())
                .build();
    }

    public List<SlideResponse> EntityList2Response(List<SlideEntity> SlideList){

        List<SlideResponse> responses = new ArrayList<>();

        for ( SlideEntity Slide: SlideList){
            responses.add(Entity2Response(Slide));
        }

        return responses;
    }

}
