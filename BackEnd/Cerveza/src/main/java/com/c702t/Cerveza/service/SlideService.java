package com.c702t.Cerveza.service;

import com.c702t.Cerveza.exception.RuntimeExceptionCustom;
import com.c702t.Cerveza.models.request.SlideRequest;
import com.c702t.Cerveza.models.response.SlideResponse;

import java.io.IOException;
import java.util.List;

public interface SlideService {

    SlideResponse create (SlideRequest request, String token) throws RuntimeExceptionCustom;
    public List<SlideResponse> getAllNewsByBusiness(Long id) throws RuntimeExceptionCustom;
    void delete (Long id, String token) throws RuntimeExceptionCustom;
    SlideResponse getById (Long id) throws RuntimeExceptionCustom;

}
