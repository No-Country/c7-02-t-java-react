package com.c702t.Cerveza.service.impl;

import com.amazonaws.services.apigateway.model.NotFoundException;
import com.c702t.Cerveza.models.entity.SlideEntity;
import com.c702t.Cerveza.models.mapper.SlideMapper;
import com.c702t.Cerveza.models.request.SlideRequest;
import com.c702t.Cerveza.models.response.SlideResponse;
import com.c702t.Cerveza.models.response.PaginationResponse;
import com.c702t.Cerveza.repository.SlideRepository;
import com.c702t.Cerveza.service.SlideService;
import com.c702t.Cerveza.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class SlideServiceImpl implements SlideService {

    @Autowired
    private SlideRepository slideRepository;
    @Autowired
    private SlideMapper slideMapper;

    @Override
    @Transactional
    public SlideResponse create(SlideRequest request) throws IOException {

        SlideEntity entity = slideMapper.Request2Entity(request);
        SlideEntity entitySave = slideRepository.save(entity);
        SlideResponse response = slideMapper.Entity2Response(entitySave);

        return response;

    }

    @Override
    @Transactional
    public void delete(Long id) {

        Optional<SlideEntity> entity = this.slideRepository.findById(id);

        if (!entity.isPresent()){
            throw new NotFoundException("the id "+id+" does not belong to a slide");
        }

        slideRepository.delete(entity.get());

    }

    @Override
    @Transactional
    public SlideResponse update(Long id, SlideRequest request) throws IOException {

        Optional<SlideEntity> entityFound = slideRepository.findById(id);
        if (!entityFound.isPresent()){

            throw new NotFoundException("the id "+id+" does not belong to a slide");
        }
        SlideEntity entityUpdate = slideMapper.EntityUpdate(entityFound.get(), request);
        SlideEntity entitySave = slideRepository.save(entityUpdate);
        SlideResponse response = slideMapper.Entity2Response(entitySave);

        return response;
    }

    @Override
    @Transactional
    public SlideResponse getById(Long id) {

        Optional<SlideEntity> entity = slideRepository.findById(id);

        if (!entity.isPresent()){
            throw new NotFoundException("the id "+id+" does not belong to a slide");
        }
        SlideResponse response = slideMapper.Entity2Response(entity.get());

        return response;
    }

    @Override
    public PaginationResponse getPage(Optional<Integer> pageNumber, Optional<Integer> size) {
        PaginationUtils pagination = new PaginationUtils(slideRepository, pageNumber, size,
                "/slide/page=%d&size=%d");
        Page page = pagination.getPage();

        List<SlideEntity> slide = page.getContent();
        List <SlideResponse> responses = slideMapper.EntityList2Response(slide);

        return PaginationResponse.builder()
                .entities(slide)
                .nextPageURI(pagination.getNext())
                .prevPageURI(pagination.getPrevious())
                .build();
    }

}
