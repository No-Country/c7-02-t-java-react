package com.c702t.Cerveza.service;

import com.c702t.Cerveza.exception.RuntimeExceptionCustom;
import com.c702t.Cerveza.models.request.SlideRequest;
import com.c702t.Cerveza.models.response.SlideResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SlideService {

    SlideResponse create (String token, Long idBusiness, MultipartFile file) throws RuntimeExceptionCustom, IOException;
    public List<SlideResponse> getAllSlidesByBusiness(Long id) throws RuntimeExceptionCustom;
    void delete (Long id, String token) throws RuntimeExceptionCustom;
    SlideResponse getById (Long id) throws RuntimeExceptionCustom;

}
