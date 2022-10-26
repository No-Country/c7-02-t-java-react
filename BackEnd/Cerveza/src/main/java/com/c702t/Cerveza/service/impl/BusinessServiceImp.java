package com.c702t.Cerveza.service.impl;

import com.c702t.Cerveza.auth.service.JwtUtils;
import com.c702t.Cerveza.exception.NotFoundException;
import com.c702t.Cerveza.exception.RuntimeExceptionCustom;
import com.c702t.Cerveza.models.entity.BusinessEntity;
import com.c702t.Cerveza.models.entity.NewsEntity;
import com.c702t.Cerveza.models.entity.RoleEntity;
import com.c702t.Cerveza.models.entity.UserEntity;
import com.c702t.Cerveza.models.mapper.BusinessMaper;
import com.c702t.Cerveza.models.request.BusinessRequest;
import com.c702t.Cerveza.models.request.specification.BusinessByUserRequest;
import com.c702t.Cerveza.models.request.specification.BusinessFiltersRequest;
import com.c702t.Cerveza.models.response.BusinessByUserResponse;
import com.c702t.Cerveza.models.response.BusinessResponse;
import com.c702t.Cerveza.models.response.PaginationResponse;
import com.c702t.Cerveza.models.response.UserResponse;
import com.c702t.Cerveza.repository.BusinessRepository;
import com.c702t.Cerveza.repository.UserRepository;
import com.c702t.Cerveza.repository.specification.BusinessByUserSpecification;
import com.c702t.Cerveza.repository.specification.BusinessSpecification;
import com.c702t.Cerveza.service.AwsService;
import com.c702t.Cerveza.service.BusinessService;
import com.c702t.Cerveza.utils.PaginationByFiltersUtil;
import com.c702t.Cerveza.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
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
    @Autowired
    AwsService awsService;

    @Override
    public BusinessResponse create(String token, String name, MultipartFile file, String address,
                                   String city, String state, String country, String phone, String email,
                                   String aboutUsText, String urlInstagram, String urlFacebook) throws IOException {

        token = token.substring(7);
        String username = jwtUtils.extractUsername(token);
        UserEntity userEntity = userRepository.findByEmail(username).get();

        Optional<BusinessEntity> businessEntity= businessRepository.findByName(name, address);

        if (businessEntity.isPresent()){
            throw new RuntimeExceptionCustom("409 ::the business already exists");
        }

        BusinessRequest request = new BusinessRequest();
        request.setAboutUsText(aboutUsText);
        request.setAddress(address);
        request.setName(name);
        request.setCity(city);
        request.setImage(awsService.uploadFile(file));
        request.setCountry(country);
        request.setEmail(email);
        request.setPhone(phone);
        request.setState(state);
        request.setUrlFacebook(urlFacebook);
        request.setUrlInstagram(urlInstagram);

        BusinessEntity entity = this.businessMaper.Request2Entity(request, userEntity.getId());
        BusinessEntity entitySave = this.businessRepository.save(entity);
        BusinessResponse responseCreated = this.businessMaper.Entity2Response(entitySave);

        return responseCreated;

    }

    @Transactional
    public void delete(Long id, String token) throws RuntimeExceptionCustom {

        token = token.substring(7);
        String username = jwtUtils.extractUsername(token);
        UserEntity userEntity = userRepository.findByEmail(username).get();

        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
        BusinessEntity business= businessRepository.getById(id);

        if(business != null){

            System.out.println("\nroleEntity del token : " + roleEntity.getName());
            System.out.println("id del user token : " + userEntity.getId());
            System.out.println("user Business.getId() : " + business.getUsers().getId());

            if(roleEntity.getName().equalsIgnoreCase("BUSINESS") && userEntity.getId() == business.getUsers().getId()) {
                System.out.println(" e l i  m i n a  n d o.............");
                businessRepository.delete(business);
            } else {
                throw new RuntimeExceptionCustom("the id does not belong to a business or the business are different ");
            }

        }else {
            throw new RuntimeExceptionCustom("the id " + id + " does not belong to a Business");
        }
    }

    @Override
    public BusinessResponse update(String token, Long idBusiness, String name, MultipartFile file, String address,
                            String city, String state, String country, String phone, String email,
                            String aboutUsText, String urlInstagram, String urlFacebook) throws IOException {

        token = token.substring(7);
        String username = jwtUtils.extractUsername(token);
        UserEntity userEntity = userRepository.findByEmail(username).get();

        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
        BusinessEntity business= businessRepository.getById(idBusiness);

        if(business != null){

            System.out.println("\nroleEntity del token : " + roleEntity.getName());
            System.out.println("id del user del token : " + userEntity.getId());
            System.out.println("id del idBusiness : " + business.getUsers().getId());

            if(roleEntity.getName().equalsIgnoreCase("BUSINESS") && userEntity.getId() == business.getUsers().getId()) {

                BusinessRequest request = new BusinessRequest();
                request.setIdUser(userEntity.getId());
                request.setAboutUsText(aboutUsText);
                request.setAddress(address);
                request.setName(name);
                request.setCity(city);
                if(file != null){
                    request.setImage(awsService.uploadFile(file));
                }
                request.setCountry(country);
                request.setEmail(email);
                request.setPhone(phone);
                request.setState(state);
                request.setUrlFacebook(urlFacebook);
                request.setUrlInstagram(urlInstagram);

                BusinessEntity entityUpdate = this.businessMaper.EntityRefreshValues(business, request);
                entityUpdate.setId(business.getId());
                BusinessEntity entitySave = this.businessRepository.save(entityUpdate);
                BusinessResponse response = this.businessMaper.Entity2Response(entitySave);

                return response;
            } else {
                throw new RuntimeExceptionCustom("the id does not belong to a business or the business are different ");
            }

        }else {
            throw new RuntimeExceptionCustom("the id " + idBusiness + " does not belong to a Business");
        }
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
    public void valueRating(Long idBusiness, Float totalValue) {

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
