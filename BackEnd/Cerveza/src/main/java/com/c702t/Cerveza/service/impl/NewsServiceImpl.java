package com.c702t.Cerveza.service.impl;

import com.amazonaws.services.apigateway.model.NotFoundException;
import com.c702t.Cerveza.auth.service.JwtUtils;
import com.c702t.Cerveza.exception.RuntimeExceptionCustom;
import com.c702t.Cerveza.models.entity.*;
import com.c702t.Cerveza.models.mapper.NewsMapper;
import com.c702t.Cerveza.models.request.NewsRequest;
import com.c702t.Cerveza.models.request.specification.BusinessFiltersRequest;
import com.c702t.Cerveza.models.request.specification.NewsFilterRequest;
import com.c702t.Cerveza.models.response.BusinessResponse;
import com.c702t.Cerveza.models.response.NewsResponse;
import com.c702t.Cerveza.models.response.PaginationResponse;
import com.c702t.Cerveza.repository.BusinessRepository;
import com.c702t.Cerveza.repository.NewsRepository;
import com.c702t.Cerveza.repository.RoleRepository;
import com.c702t.Cerveza.repository.UserRepository;
import com.c702t.Cerveza.service.AwsService;
import com.c702t.Cerveza.service.NewsService;
import com.c702t.Cerveza.utils.PaginationByFiltersUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private AwsService awsService;

    @Override
    @Transactional
    public NewsResponse create(String token, Long idBusiness, MultipartFile file, String name, String content,
                               LocalDate startDate, LocalDate endDate) throws RuntimeExceptionCustom, IOException {

        token = token.substring(7);
        String username = jwtUtils.extractUsername(token);
        UserEntity userEntity = userRepository.findByEmail(username).get();

        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
        BusinessEntity business = businessRepository.getById(idBusiness);

        System.out.println("\nid del user token : " + userEntity.getId());
        System.out.println("roleEntity del token : " + roleEntity.getName());
        System.out.println("user Business.getId() : " + business.getUsers().getId());

        if (roleEntity.getName().equalsIgnoreCase("business") && userEntity.getId() == business.getUsers().getId()) {

            NewsRequest request = new NewsRequest();
            request.setBusiness_id(idBusiness);
            request.setName(name);
            request.setContent(content);
            request.setPhoto(awsService.uploadFile(file));
            request.setStartDate(startDate);
            request.setEndDate(endDate);

            NewsEntity entity = newsMapper.Request2Entity(request);
            NewsEntity entitySave = newsRepository.save(entity);
            NewsResponse response = newsMapper.Entity2Response(entitySave);
            return response;
        }

        throw new RuntimeExceptionCustom("the id does not belong to a business or the business are different ");

    }

    @Override
    @Transactional
    public List<NewsResponse> getAllNewsByBusiness(Long id) throws RuntimeExceptionCustom {
        List<NewsEntity> news = newsRepository.findAllByBusiness_id(id);
        System.out.println("news : " + news.toString());

        if (news.isEmpty()) {
            throw new RuntimeExceptionCustom("no news for that business");
        }

        List<NewsResponse> responses = newsMapper.EntityList2ResponsePage(news);

        return responses;
    }

    @Override
    @Transactional
    public void delete(Long id, String token) throws RuntimeExceptionCustom {

        token = token.substring(7);
        String username = jwtUtils.extractUsername(token);
        UserEntity userEntity = userRepository.findByEmail(username).get();

        RoleEntity roleEntity = userEntity.getRoleId().iterator().next();
        NewsEntity newsEntity = newsRepository.getById(id);
        BusinessEntity business = businessRepository.getById(newsEntity.getBusiness().getId());


        System.out.println("\nid.slideEntity : " + newsEntity.getId() + " -  slide_id_business : " + newsEntity.getBusiness().getId());

        if(newsEntity != null){

            System.out.println("\nroleEntity del token : " + roleEntity.getName());
            System.out.println("id del user token : " + userEntity.getId());
            System.out.println("user Business.getId() : " + business.getUsers().getId());

            if(roleEntity.getName().equalsIgnoreCase("BUSINESS") && userEntity.getId() == business.getUsers().getId()) {
                System.out.println(" e l i  m i n a  n d o.............");
                newsRepository.delete(newsEntity);
            } else {
                throw new RuntimeExceptionCustom("the id does not belong to a business or the business are different ");
            }

        }else {
            throw new RuntimeExceptionCustom("the id " + id + " does not belong to a news");
        }
    }

}