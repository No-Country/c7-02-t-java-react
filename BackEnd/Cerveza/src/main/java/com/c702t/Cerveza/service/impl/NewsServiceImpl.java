package com.c702t.Cerveza.service.impl;

import com.amazonaws.services.apigateway.model.NotFoundException;
import com.c702t.Cerveza.auth.service.JwtUtils;
import com.c702t.Cerveza.models.entity.BusinessEntity;
import com.c702t.Cerveza.models.entity.NewsEntity;
import com.c702t.Cerveza.models.mapper.NewsMapper;
import com.c702t.Cerveza.models.request.NewsRequest;
import com.c702t.Cerveza.models.response.NewsResponse;
import com.c702t.Cerveza.models.response.PaginationResponse;
import com.c702t.Cerveza.repository.BusinessRepository;
import com.c702t.Cerveza.repository.NewsRepository;
import com.c702t.Cerveza.repository.UserRepository;
import com.c702t.Cerveza.service.NewsService;
import com.c702t.Cerveza.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private BusinessRepository businessRepository;

    @Override
    @Transactional
    public NewsResponse create(NewsRequest request, Long id) throws IOException {

        BusinessEntity business = businessRepository.getById(id);
        request.setBusiness_id(business.getId());

        if(!business.getEmail().isEmpty() || !business.getEmail().isBlank() ) {
            NewsEntity entity = newsMapper.Request2Entity(request);
            NewsEntity entitySave = newsRepository.save(entity);
            NewsResponse response = newsMapper.Entity2Response(entitySave);

            return response;
        }

        throw new NotFoundException("the TOKEN does not correspond to that business");


    }

    @Override
    @Transactional
    public void delete(Long id) {

        Optional<NewsEntity> entity = newsRepository.findById(id);

        if (!entity.isPresent()){
            throw new NotFoundException("the id " + id + " does not belong to a news");
        }

        newsRepository.delete(entity.get());

    }

    @Override
    @Transactional
    public NewsResponse update(Long id, NewsRequest request) throws IOException {

        Optional<NewsEntity> entityFound = newsRepository.findById(id);

        if (!entityFound.isPresent()){
            throw new NotFoundException("the id "+id+" does not belong to a news");
        }

        NewsEntity entityUpdate = newsMapper.EntityUpdate(entityFound.get(), request);
        NewsEntity entitySave = newsRepository.save(entityUpdate);
        NewsResponse response = newsMapper.Entity2Response(entitySave);

        return response;
    }

    @Override
    @Transactional
    public NewsResponse getById(Long id) {

        Optional<NewsEntity> entity = newsRepository.findById(id);

        if (!entity.isPresent()){
            throw new NotFoundException("the id "+id+" does not belong to a news");
        }

        NewsResponse response = newsMapper.Entity2Response(entity.get());

        return response;
    }

    // ----------------      PAGINATION      --------------------

    @Override
    public PaginationResponse getPageNewsByBusiness(Optional<Integer> pageNumber, Optional<Integer> size) {

        PaginationUtils pagination = new PaginationUtils(newsRepository, pageNumber, size,"/news/pageNews/page=%d&size=%d");

        Page page = pagination.getPage();
        List<NewsEntity> news = page.getContent();
        List <NewsResponse> responses =  newsMapper.EntityList2ResponsePage(news);

        return PaginationResponse.builder()
                .entities(responses)
                .nextPageURI(pagination.getNext())
                .prevPageURI(pagination.getPrevious())
                .build();

    }


    @Override
    @Transactional
    public List<NewsResponse> getAllNewsByBusiness(Long id) {

        List<NewsEntity> news = newsRepository.findByBusiness_id(id);

        if (news.isEmpty()) {
            throw new NotFoundException("no news for that business");
        }

        List<NewsResponse> responses = newsMapper.EntityList2ResponsePage(news);

        return responses;
    }
}
