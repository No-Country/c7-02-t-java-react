package com.c702t.Cerveza.models.mapper;

import com.c702t.Cerveza.exception.RuntimeExceptionCustom;
import com.c702t.Cerveza.models.entity.NewsEntity;
import com.c702t.Cerveza.models.request.NewsRequest;
import com.c702t.Cerveza.models.response.NewsResponse;
import com.c702t.Cerveza.repository.BusinessRepository;
import com.c702t.Cerveza.service.AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class NewsMapper {

    @Autowired
    AwsService awsService;
    @Autowired
    BusinessRepository businessRepository;

    public NewsEntity Request2Entity (NewsRequest request) throws RuntimeExceptionCustom {

        NewsEntity entity = new NewsEntity();

        return entity.builder()
                .name(request.getName())
                .content(request.getContent())
                .photo(request.getPhoto())
                .business(businessRepository.findById(request.getBusiness_id()).get())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .sofdelete(false)
                .build();
    }

    public NewsResponse Entity2Response (NewsEntity entity){

        return NewsResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .content(entity.getContent())
                .photo(entity.getPhoto())
                .business_id(entity.getBusiness().getId())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .build();
    }

    public NewsEntity EntityUpdate (NewsEntity entity, NewsRequest request) throws RuntimeExceptionCustom {

       return NewsEntity.builder()
               .id(entity.getId())
               .name(request.getName())
               .content(request.getContent())
               .photo(request.getPhoto())
               .business(businessRepository.findById(request.getBusiness_id()).get())
               .startDate(request.getStartDate())
               .endDate(request.getEndDate())
               .timestamp(new Timestamp(System.currentTimeMillis()))
               .build();
    }

    public List<NewsResponse> EntityList2ResponsePage(List<NewsEntity> newsList){

        List<NewsResponse> responses = new ArrayList<>();

        for ( NewsEntity news: newsList){
            responses.add(Entity2Response(news));
        }

        return responses;
    }

}
