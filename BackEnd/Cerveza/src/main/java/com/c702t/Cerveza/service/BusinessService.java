package com.c702t.Cerveza.service;

import com.c702t.Cerveza.exception.RuntimeExceptionCustom;
import com.c702t.Cerveza.models.request.BusinessRequest;
import com.c702t.Cerveza.models.response.BusinessResponse;
import com.c702t.Cerveza.models.response.PaginationResponse;
import com.c702t.Cerveza.models.response.UserResponse;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface BusinessService {

    BusinessResponse create(String token, String name, MultipartFile file, String address,
                                   String city, String state, String country, String phone, String email,
                                   String aboutUsText, String urlInstagram, String urlFacebook) throws IOException ;
    void delete (Long id, String token);
    BusinessResponse update(String token, Long idBusiness, String name, MultipartFile file, String address,
                            String city, String state, String country, String phone, String email,
                            String aboutUsText, String urlInstagram, String urlFacebook) throws IOException ;
    BusinessResponse getById (Long id) throws IOException;
    PaginationResponse getByFilters(String city, String state, String country, String order, Optional<Integer> pageNumber, Optional<Integer> size);
    void valueRating (Long id, Float totalValue);
    PaginationResponse getPageBusinessByUsers(Long id, String order, Optional<Integer> page, Optional<Integer> size);
//    public BusinessResponse uploadPhoto(Long id, MultipartFile file) throws RuntimeExceptionCustom, IOException;

}
