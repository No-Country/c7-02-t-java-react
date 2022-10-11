package com.c702t.Cerveza.service;

import com.c702t.Cerveza.models.request.NewsRequest;
import com.c702t.Cerveza.models.response.NewsResponse;
import com.c702t.Cerveza.models.response.PaginationResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface NewsService {

    NewsResponse create (NewsRequest newsRequest) throws IOException;
    void delete (Long id);
    NewsResponse update (Long id, NewsRequest newsRequest) throws IOException;
    NewsResponse getById (Long id);
    PaginationResponse getPage(Optional<Integer> page, Optional<Integer> size);

}
