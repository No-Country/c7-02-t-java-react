package com.c702t.Cerveza.service.impl;

import com.c702t.Cerveza.auth.service.JwtUtils;
import com.c702t.Cerveza.models.entity.UserEntity;
import com.c702t.Cerveza.models.mapper.UserMapper;
import com.c702t.Cerveza.models.request.*;
import com.c702t.Cerveza.models.response.PaginationResponse;
import com.c702t.Cerveza.models.response.UserDetailsResponse;
import com.c702t.Cerveza.models.response.UserResponse;
import com.c702t.Cerveza.repository.UserRepository;
import com.c702t.Cerveza.service.EmailService;
import com.c702t.Cerveza.service.UserService;
import com.c702t.Cerveza.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private EmailService emailService;

    @Transactional
    public UserDetailsResponse register(UserRequest request, String token) throws UsernameNotFoundException, IOException {

        String userToken = rebuildToken(token);
        UserEntity user = userRepository.findByEmail( jwtUtils.extractUsername(userToken)).get();

        if(user == null) {
            if (request.getFirstName() != null && !request.getFirstName().isEmpty() && !request.getFirstName().isBlank()) {
                user.setFirstName(request.getFirstName());
            }
            if (request.getLastName() != null && !request.getLastName().isEmpty() && !request.getLastName().isBlank()) {
                user.setLastName(request.getLastName());
            }
            if (request.getEmail() != null && !request.getEmail().isEmpty() && !request.getEmail().isBlank()) {
                user.setEmail(request.getEmail());
            }
            if (request.getPassword() != null && !request.getPassword().isEmpty() && !request.getPassword().isBlank()) {
                user.setPassword(passwordEncoder.encode(request.getPassword()));
            }
//            if (request.getPhoto() != null && !request.getPhoto().isEmpty() && !request.getPhoto().isBlank()) {
//                user.setPhoto(request.getPhoto());
//            }

            userRepository.save(user);
            return userMapper.userToUserDetail(user);
        }

        throw new RuntimeException("userEmail already exists");

    }

    @Override
    @Transactional
    public UserResponse recoverPassword(RecoverPassRequest request) throws Exception {

        if (request.getEmail().isEmpty() || request.getEmail().isBlank() || request.getEmail() == null)
            throw new Exception("the email is null");

        UserEntity user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        if (!userRepository.findByEmail(request.getEmail()).isPresent())
            throw new Exception("there is no user with that email");

        int n = 20;
        String passAux = getAlphaNumericString(n);
        System.out.println("passAux : " + passAux);

        if (!request.getEmail().contains("@test")){
            emailService.sendRecoverPassword(user.getEmail(), "userRegistered", passAux);
        }

        if(user != null){
            user.setPassword(passwordEncoder.encode(passAux));
        }

        userRepository.save(user);

        return userMapper.toUserResponse(user);

    }

    @Override
    @Transactional
    public UserResponse upDatePassword(UpdatePasswordRequest request) throws Exception {

        if (request.getEmail().isEmpty() || request.getEmail().isBlank() || request.getEmail() == null)
            throw new Exception("the email is null");

        UserEntity user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        if (!userRepository.findByEmail(request.getEmail()).isPresent())
            throw new Exception("there is no user with that email");

        if(!request.getNewPassword().equalsIgnoreCase(request.getConfirmNewPassword()))
            throw new Exception("passwords do not match");

        if(user != null){
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        }

        userRepository.save(user);
        return userMapper.toUserResponse(user);

    }

    @Override
    @Transactional
    public UserDetailsResponse updateBasicUser(UserUpdateRequest request, String token) throws IOException {
        String userToken = rebuildToken(token);
        UserEntity user = userRepository.findByEmail( jwtUtils.extractUsername(userToken)).get();

        if(request.getFirstName() != null && !request.getFirstName().isEmpty() && !request.getFirstName().isBlank() ){
            user.setFirstName(request.getFirstName());}
        if(request.getLastName() != null && !request.getLastName().isEmpty() && !request.getLastName().isBlank()){
            user.setLastName(request.getLastName());}
        if(request.getPassword() != null && !request.getPassword().isEmpty() && !request.getLastName().isBlank()){
            user.setPassword(passwordEncoder.encode(request.getPassword()));}
        if(request.getPhoto() != null && !request.getPhoto().isEmpty() && !request.getPhoto().isBlank()){
            user.setPhoto(request.getPhoto());}
        userRepository.save(user);
        return userMapper.userToUserDetail(user);

    }

    @Transactional
    public UserDetailsResponse updateUserForAdmin(Long id, UserUpdateRequest request) throws IOException {

        UserEntity user = getById(id);

        if(request.getFirstName() != null && !request.getFirstName().isEmpty() && !request.getFirstName().isBlank() ){
            user.setFirstName(request.getFirstName());}
        if(request.getLastName() != null && !request.getLastName().isEmpty() && !request.getLastName().isBlank()){
            user.setLastName(request.getLastName());}
        if(request.getPassword() != null && !request.getPassword().isEmpty() && !request.getLastName().isBlank()){
            user.setPassword(passwordEncoder.encode(request.getPassword()));}
        if(request.getPhoto() != null && !request.getPhoto().isEmpty() && !request.getPhoto().isBlank()){
            user.setPhoto(request.getPhoto());}
        userRepository.save(user);
        return userMapper.userToUserDetail(user);

    }

    @Transactional
    private UserEntity getById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Transactional
    public List<UserDetailsResponse> getUsers() throws IOException {
        List<UserEntity> users = userRepository.findAll();
        List<UserDetailsResponse> Response = userMapper.usersToUserDetailsList(users);
        return Response;

    }

    @Transactional
    public PaginationResponse getAllPage(Optional<Integer> pageNumber, Optional<Integer> size) {
        PaginationUtils pagination = new PaginationUtils(userRepository, pageNumber, size, "/comments/page=%d&size=%d");
        Page page = pagination.getPage();

        List<UserEntity> users = page.getContent();
        List <UserResponse> responses = userMapper.toUsersPaginationResponse(users);

        return PaginationResponse.builder()
                .entities(users)
                .nextPageURI(pagination.getNext())
                .prevPageURI(pagination.getPrevious())
                .build();
    }

    @Override
    @Transactional
    public void deleteUserForAdmin(Long id) {
        UserEntity user = getById(id);
        userRepository.deleteById(user.getId());
    }

    @Transactional
    public void deleteBasicUser(String token){
        String userToken = rebuildToken(token);
        UserEntity user = userRepository.findByEmail( jwtUtils.extractUsername(userToken)).get();
        userRepository.deleteById(user.getId());
    }

    @Override
    @Transactional
    public PaginationResponse getUserPage(Optional<Integer> pageNumber, Optional<Integer> size) {
        PaginationUtils pagination = new PaginationUtils(userRepository, pageNumber, size, "/comments/page=%d&size=%d");

        Page page = pagination.getPage();

        List<UserEntity> users = page.getContent();
        List <UserResponse> responses = userMapper.toUsersPaginationResponse(users);

        return PaginationResponse.builder()
                .entities(users)
                .nextPageURI(pagination.getNext())
                .prevPageURI(pagination.getPrevious())
                .build();
    }

    public String rebuildToken(String token){
        String [] part = token.split(" ");
        String token2 = part[1];
        return token2;
    }


    private String getAlphaNumericString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

}


