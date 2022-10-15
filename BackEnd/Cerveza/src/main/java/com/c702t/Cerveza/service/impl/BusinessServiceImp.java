package com.c702t.Cerveza.service.impl;

import com.c702t.Cerveza.auth.service.JwtUtils;
import com.c702t.Cerveza.exception.NotFoundException;
import com.c702t.Cerveza.models.entity.BusinessEntity;
import com.c702t.Cerveza.models.entity.UserEntity;
import com.c702t.Cerveza.models.mapper.BusinessMaper;
import com.c702t.Cerveza.models.request.BusinessRequest;
import com.c702t.Cerveza.models.request.specification.BusinessByUserRequest;
import com.c702t.Cerveza.models.request.specification.BusinessFiltersRequest;
import com.c702t.Cerveza.models.response.BusinessByUserResponse;
import com.c702t.Cerveza.models.response.BusinessResponse;
import com.c702t.Cerveza.models.response.PaginationResponse;
import com.c702t.Cerveza.repository.BusinessRepository;
import com.c702t.Cerveza.repository.UserRepository;
import com.c702t.Cerveza.repository.specification.BusinessByUserSpecification;
import com.c702t.Cerveza.repository.specification.BusinessSpecification;
import com.c702t.Cerveza.service.BusinessService;
import com.c702t.Cerveza.utils.PaginationByFiltersUtil;
import com.c702t.Cerveza.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessServiceImp implements BusinessService {

    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private BusinessMaper businessMaper;

    @Autowired
    private BusinessSpecification businessSpecification;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BusinessByUserSpecification businessByUserSpecification;

    @Override
    public BusinessResponse create(BusinessRequest request, String token) throws IOException {

        token = token.substring(7);
        String username = jwtUtils.extractUsername(token);
        UserEntity userEntity = userRepository.findByEmail(username).get();
        BusinessEntity entity = this.businessMaper.Request2Entity(request, userEntity.getId());
        BusinessEntity entitySave = this.businessRepository.save(entity);
        BusinessResponse responseCreated = this.businessMaper.Entity2Response(entitySave);

        return responseCreated;


    }

    @Override
    public void delete(Long id) {

        Optional<BusinessEntity> entity = this.businessRepository.findById(id);

        if (!entity.isPresent()) {

            throw new NotFoundException("the id " + id + " does not belong to a business");

        }

        this.businessRepository.delete(entity.get());

    }

    @Override
    public BusinessResponse update(Long id, BusinessRequest request) throws IOException {
        Optional<BusinessEntity> entity = this.businessRepository.findById(id);

        if (!entity.isPresent()) {

            throw new NotFoundException("the id " + id + " does not belong to a business");
        }

        BusinessEntity entityUpdate = this.businessMaper.EntityRefreshValues(entity.get(), request);

        BusinessEntity entitySave = this.businessRepository.save(entityUpdate);

        BusinessResponse response = this.businessMaper.Entity2Response(entitySave);

        return response;
    }

    @Override
    public BusinessResponse getById(Long id) throws IOException {

        Optional<BusinessEntity> entity = this.businessRepository.findById(id);

        if (!entity.isPresent()) {

            throw new NotFoundException("the id " + id + " does not belong to a business");
        }

        BusinessResponse response = this.businessMaper.Entity2Response(entity.get());

        return response;


    }


    @Override
    public PaginationResponse getByFilters(String city, String state, String country, String order, Optional<Integer> pageNumber, Optional<Integer> size) {

        BusinessFiltersRequest filtersRequest = new BusinessFiltersRequest(city, state, country, order);


        Specification<BusinessEntity> specification= businessSpecification.getByFilters(filtersRequest);

        PaginationByFiltersUtil pagination = new PaginationByFiltersUtil(specification, businessRepository, pageNumber, size,
                "/business/page=%d&size=%d");
        Page page = pagination.getPage();




         List<BusinessResponse>responses = page.getContent();


        return PaginationResponse.builder()
                .entities(responses)
                .nextPageURI(pagination.getNext())
                .prevPageURI(pagination.getPrevious())
                .build();

         //   return null;
    }

    @Override
    public void valueRating(Long idBusiness, Double totalValue) {


        Optional<BusinessEntity> entity = this.businessRepository.findById(idBusiness);

        BusinessEntity businessEntity = this.businessMaper.EntityRefreshRating(entity.get(), totalValue);

         businessEntity = this.businessRepository.save(entity.get());

    }

    @Override
    public PaginationResponse getPageBusinessByUsers(Long idUser,String order,  Optional<Integer> pageNumber, Optional<Integer> size) {
       BusinessByUserRequest filtersRequest = new BusinessByUserRequest(idUser, order);


        Specification<BusinessEntity> specification= businessByUserSpecification.getByUsers(filtersRequest);


        PaginationByFiltersUtil pagination = new PaginationByFiltersUtil(specification,businessRepository, pageNumber, size,
                "/business/getByUser/page=%d&size=%d");
        Page page = pagination.getPage();
        List<BusinessEntity> business = page.getContent();
        List<BusinessByUserResponse> responses = businessMaper.toBusinessByUserResponseList(business);
        return PaginationResponse.builder()
                .entities(business)
                .nextPageURI(pagination.getNext())
                .prevPageURI(pagination.getPrevious())
                .build();
    }


}
