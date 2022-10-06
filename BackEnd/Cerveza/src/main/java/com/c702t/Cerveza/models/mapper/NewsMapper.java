package com.c702t.Cerveza.models.mapper;

import com.c702t.Cerveza.models.entity.NewsEntity;
import com.c702t.Cerveza.models.request.NewsRequest;
import com.c702t.Cerveza.models.response.NewsResponse;
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

    public NewsEntity Request2Entity (NewsRequest request) throws IOException {

        NewsEntity entity = new NewsEntity();

        return entity.builder()
                .name(request.getName())
                .content(request.getContent())
                .photo(request.getPhoto())
//                .business(request.getBusiness())
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
//                .business(entity.getBusiness())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .timestamp(entity.getTimestamp())
                .build();

    }

    public NewsEntity EntityUpdate (NewsEntity entity, NewsRequest request) throws IOException {

       return NewsEntity.builder()
               .id(entity.getId())
               .name(request.getName())
               .content(request.getContent())
               .photo(request.getPhoto())
//                .business(request.getBusiness())
               .startDate(request.getStartDate())
               .endDate(request.getEndDate())
               .timestamp(new Timestamp(System.currentTimeMillis()))
               .sofdelete(request.getSofdelete())
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
