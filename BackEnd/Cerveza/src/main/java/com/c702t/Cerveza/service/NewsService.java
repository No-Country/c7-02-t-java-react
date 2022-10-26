package com.c702t.Cerveza.service;

import com.c702t.Cerveza.exception.RuntimeExceptionCustom;
import com.c702t.Cerveza.models.request.NewsRequest;
import com.c702t.Cerveza.models.response.NewsResponse;
import com.c702t.Cerveza.models.response.PaginationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface NewsService {

    NewsResponse create (String token, Long idBusiness, MultipartFile file, String name,
                         String content, LocalDate startDate, LocalDate endDate) throws RuntimeExceptionCustom, IOException;
    public List<NewsResponse> getAllNewsByBusiness(Long id) throws RuntimeExceptionCustom;
    void delete (Long id, String token) throws RuntimeExceptionCustom;

}
