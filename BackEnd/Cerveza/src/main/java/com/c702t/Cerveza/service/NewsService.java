package com.c702t.Cerveza.service;

import com.c702t.Cerveza.exception.RuntimeExceptionCustom;
import com.c702t.Cerveza.models.request.NewsRequest;
import com.c702t.Cerveza.models.response.NewsResponse;
import com.c702t.Cerveza.models.response.PaginationResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface NewsService {

    NewsResponse create (NewsRequest newsRequest, String token) throws RuntimeExceptionCustom;
    public List<NewsResponse> getAllNewsByBusiness(Long id) throws RuntimeExceptionCustom;
    void delete (Long id, String token) throws RuntimeExceptionCustom;


//    NewsResponse getById (Long id);
//    PaginationResponse getPage(Optional<Integer> page, Optional<Integer> size);


}
