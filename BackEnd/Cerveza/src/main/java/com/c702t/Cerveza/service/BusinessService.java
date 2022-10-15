package com.c702t.Cerveza.service;

import com.c702t.Cerveza.models.request.BusinessRequest;
import com.c702t.Cerveza.models.response.BusinessResponse;
import com.c702t.Cerveza.models.response.PaginationResponse;
import org.springframework.data.jpa.domain.Specification;

import java.io.IOException;
import java.util.Optional;

public interface BusinessService {

    BusinessResponse create (BusinessRequest request, String token) throws IOException;
    void delete (Long id);
    BusinessResponse update (Long id, BusinessRequest request) throws IOException;
    BusinessResponse getById (Long id) throws IOException;
    PaginationResponse getByFilters(String city, String state, String country, String order, Optional<Integer> pageNumber, Optional<Integer> size);

    void valueRating (Long id, Double totalValue);

    PaginationResponse getPageBusinessByUsers(Long id, String order, Optional<Integer> page, Optional<Integer> size);

}
