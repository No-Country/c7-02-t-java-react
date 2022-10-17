package com.c702t.Cerveza.service.impl;

import com.amazonaws.services.apigateway.model.NotFoundException;
import com.c702t.Cerveza.auth.service.JwtUtils;
import com.c702t.Cerveza.exception.RuntimeExceptionCustom;
import com.c702t.Cerveza.models.entity.*;
import com.c702t.Cerveza.models.mapper.SlideMapper;
import com.c702t.Cerveza.models.request.NewsRequest;
import com.c702t.Cerveza.models.request.SlideRequest;
import com.c702t.Cerveza.models.response.NewsResponse;
import com.c702t.Cerveza.models.response.SlideResponse;
import com.c702t.Cerveza.models.response.PaginationResponse;
import com.c702t.Cerveza.repository.BusinessRepository;
import com.c702t.Cerveza.repository.RoleRepository;
import com.c702t.Cerveza.repository.SlideRepository;
import com.c702t.Cerveza.repository.UserRepository;
import com.c702t.Cerveza.service.SlideService;
import com.c702t.Cerveza.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class SlideServiceImpl implements SlideService {

    @Autowired
    private SlideRepository slideRepository;
    @Autowired
    private SlideMapper slideMapper;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BusinessRepository businessRepository;

    @Override
    @Transactional
    public SlideResponse create(SlideRequest request, String token) throws RuntimeExceptionCustom {

        token = token.substring(7);
        String username = jwtUtils.extractUsername(token);
        UserEntity userEntity = userRepository.findByEmail(username).get();

        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
        BusinessEntity business = businessRepository.getById(request.getBusiness_id());

        System.out.println("\nid del user token : " + userEntity.getId());
        System.out.println("roleEntity del token : " + roleEntity.getName());
        System.out.println("user Business.getId() : " + business.getUsers().getId());

        if(roleEntity.getName().equalsIgnoreCase("business") && userEntity.getId() == business.getUsers().getId()){
            SlideEntity entity = slideMapper.Request2Entity(request);
            SlideEntity entitySave = slideRepository.save(entity);
            SlideResponse response = slideMapper.Entity2Response(entitySave);
            return response;
        }

        throw new RuntimeExceptionCustom("the id does not belong to a business or the business are different ");

    }

    @Override
    @Transactional
    public List<SlideResponse> getAllNewsByBusiness(Long id) throws RuntimeExceptionCustom {

        List<SlideEntity> slides = slideRepository.findAllByBusiness_id(id);

        if (!slides.isEmpty()) {
            throw new RuntimeExceptionCustom("no slides for that business");
        }

        List<SlideResponse> responses = slideMapper.EntityList2Response(slides);

        return responses;
    }

    @Override
    @Transactional
    public void delete(Long id, String token) throws RuntimeExceptionCustom {

        token = token.substring(7);
        String username = jwtUtils.extractUsername(token);
        UserEntity userEntity = userRepository.findByEmail(username).get();

        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
        SlideEntity slideEntity = slideRepository.getById(id);
        BusinessEntity business = businessRepository.getById(slideEntity.getBusiness().getId());


        System.out.println("\nid.slideEntity : " + slideEntity.getId() + " -  slide_id_business : " + slideEntity.getBusiness().getId());

        if(slideEntity != null){

            System.out.println("\nroleEntity del token : " + roleEntity.getName());
            System.out.println("id del user token : " + userEntity.getId());
            System.out.println("user Business.getId() : " + business.getUsers().getId());

            if(roleEntity.getName().equalsIgnoreCase("BUSINESS") && userEntity.getId() == business.getUsers().getId()) {
                System.out.println(" e l i  m i n a  n d o.............");
                slideRepository.delete(slideEntity);
            } else {
                throw new RuntimeExceptionCustom("the id does not belong to a business or the business are different ");
            }

        }else {
            throw new RuntimeExceptionCustom("the id " + id + " does not belong to a slide");
        }
    }

    @Override
    @Transactional
    public SlideResponse getById(Long id) throws RuntimeExceptionCustom {

        Optional<SlideEntity> entity = slideRepository.findById(id);

        if (!entity.isPresent()){
            throw new RuntimeExceptionCustom("the id "+id+" does not belong to a slide");
        }
        SlideResponse response = slideMapper.Entity2Response(entity.get());

        return response;
    }

}
