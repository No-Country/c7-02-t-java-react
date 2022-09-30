package com.c702t.Cerveza.service;

import com.c702t.Cerveza.models.request.BusinessRequest;
import com.c702t.Cerveza.models.response.BusinessResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BusinessService {

    BusinessResponse create (BusinessRequest request) throws IOException;
    void delete (Long id);
    BusinessResponse update (Long id, BusinessRequest request) throws IOException;
    BusinessResponse getById (Long id);

    List<BusinessResponse> getByFilters (String city, String state, String country, String order);

}
