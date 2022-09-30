package com.c702t.Cerveza.service.impl;

import com.c702t.Cerveza.exception.NotFoundException;
import com.c702t.Cerveza.models.entity.BusinessEntity;
import com.c702t.Cerveza.models.mapper.BusinessMaper;
import com.c702t.Cerveza.models.request.BusinessRequest;
import com.c702t.Cerveza.models.request.specification.BusinessFiltersRequest;
import com.c702t.Cerveza.models.response.BusinessResponse;
import com.c702t.Cerveza.repository.BusinessRepository;
import com.c702t.Cerveza.repository.specification.BusinessSpecification;
import com.c702t.Cerveza.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class BusinessServiceImp implements BusinessService {

    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private BusinessMaper businessMaper;

    @Autowired
    private BusinessSpecification businessSpecification;


    @Override
    public BusinessResponse create(BusinessRequest request) throws IOException {

        BusinessEntity entity = this.businessMaper.Request2Entity(request);
        BusinessEntity entitySave = this.businessRepository.save(entity);
        BusinessResponse responseCreated = this.businessMaper.Entity2Response(entitySave);

        return responseCreated;


    }

    @Override
    public void delete(Long id) {

        Optional<BusinessEntity> entity = this.businessRepository.findById(id);

        if (!entity.isPresent()){

            throw new NotFoundException("the id "+id+" does not belong to a business");

        }

        this.businessRepository.delete(entity.get());

    }

    @Override
    public BusinessResponse update(Long id, BusinessRequest request) throws IOException {
        Optional<BusinessEntity> entity = this.businessRepository.findById(id);

        if (!entity.isPresent()){

            throw new NotFoundException("the id "+id+" does not belong to a business");
        }

        BusinessEntity entityUpdate = this.businessMaper.EntityRefreshValues(entity.get(), request);

        BusinessEntity entitySave = this.businessRepository.save(entityUpdate);

        BusinessResponse response = this.businessMaper.Entity2Response(entitySave);

        return response;
    }

    @Override
    public BusinessResponse getById(Long id) {

        Optional<BusinessEntity> entity = this.businessRepository.findById(id);

        if (!entity.isPresent()){

            throw new NotFoundException("the id "+id+" does not belong to a business");
        }

        BusinessResponse response = this.businessMaper.Entity2Response(entity.get());

        return response;


    }

    @Override
    public List<BusinessResponse> getByFilters(String city, String state, String country, String order) {

        BusinessFiltersRequest filtersRequest = new BusinessFiltersRequest(city, state, country, order);

        List<BusinessEntity> entityList=
                this.businessRepository.findAll(
                        this.businessSpecification.getByFilters(filtersRequest));

        List<BusinessResponse> businessResponseList =
                this.businessMaper.EntityList2ResponseList(entityList);

        return businessResponseList;


    }
}
