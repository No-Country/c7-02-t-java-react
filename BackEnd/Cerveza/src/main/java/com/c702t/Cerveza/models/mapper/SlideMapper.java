package com.c702t.Cerveza.models.mapper;

import com.c702t.Cerveza.models.entity.SlideEntity;
import com.c702t.Cerveza.models.request.SlideRequest;
import com.c702t.Cerveza.models.response.SlideResponse;
<<<<<<< HEAD
import com.c702t.Cerveza.repository.BusinessRepository;
import com.c702t.Cerveza.repository.SlideRepository;
=======
>>>>>>> e4f04e62f93c4fa979e6474861ef713f0bae4e40
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
<<<<<<< HEAD
    private AwsService awsService;
    @Autowired
    private BusinessRepository businessRepository;
=======
    AwsService awsService;
>>>>>>> e4f04e62f93c4fa979e6474861ef713f0bae4e40

    public SlideEntity Request2Entity (SlideRequest request) throws IOException {

        SlideEntity entity = new SlideEntity();

        return entity.builder()
<<<<<<< HEAD
                .business(businessRepository.getById(request.getBusiness_id()))
                .photo(request.getPhoto())
                .timestamp(new Timestamp(System.currentTimeMillis()))
=======
                .business(request.getBusiness())
                .photo(request.getPhoto())
                .timestamp(request.getTimestamp())
>>>>>>> e4f04e62f93c4fa979e6474861ef713f0bae4e40
                .sofdelete(false)
                .build();

    }

    public SlideResponse Entity2Response (SlideEntity entity){

        return SlideResponse.builder()
                .id(entity.getId())
<<<<<<< HEAD
                .business_id(entity.getBusiness().getId())
=======
                .business(entity.getBusiness())
>>>>>>> e4f04e62f93c4fa979e6474861ef713f0bae4e40
                .photo(entity.getPhoto())
                .timestamp(entity.getTimestamp())
                .build();

    }

    public SlideEntity EntityUpdate (SlideEntity entity, SlideRequest request) throws IOException {

        return SlideEntity.builder()
                .id(entity.getId())
<<<<<<< HEAD
//                .business(request.getBusiness())
                .photo(request.getPhoto())
                .timestamp(new Timestamp(System.currentTimeMillis()))
//                .sofdelete(request.getSofdelete())
=======
                .business(request.getBusiness())
                .photo(request.getPhoto())
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .sofdelete(request.getSofdelete())
>>>>>>> e4f04e62f93c4fa979e6474861ef713f0bae4e40
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
