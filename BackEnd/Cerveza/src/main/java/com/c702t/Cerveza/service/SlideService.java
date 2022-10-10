package com.c702t.Cerveza.service;

import com.c702t.Cerveza.models.request.SlideRequest;
import com.c702t.Cerveza.models.response.SlideResponse;
import com.c702t.Cerveza.models.response.PaginationResponse;

import java.io.IOException;
import java.util.Optional;

public interface SlideService {

    SlideResponse create (SlideRequest request) throws IOException;
    void delete (Long id);
    SlideResponse update (Long id, SlideRequest request) throws IOException;
    SlideResponse getById (Long id);
    PaginationResponse getPage(Optional<Integer> page, Optional<Integer> size);
    
}
