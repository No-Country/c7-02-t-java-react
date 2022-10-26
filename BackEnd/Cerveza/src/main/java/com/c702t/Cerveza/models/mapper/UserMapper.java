package com.c702t.Cerveza.models.mapper;

import com.c702t.Cerveza.models.entity.RoleEntity;
import com.c702t.Cerveza.models.entity.UserEntity;
import com.c702t.Cerveza.models.request.UserRequest;
import com.c702t.Cerveza.models.response.UserDetailsResponse;
import com.c702t.Cerveza.models.response.UserResponse;
import com.c702t.Cerveza.service.AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class UserMapper {

    @Autowired
    private AwsService awsService;

    public UserEntity toUserEntity(UserRequest userRequest, Set<RoleEntity> roles) throws IOException {
        return UserEntity.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .roleId(roles)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .sofdelete(Boolean.FALSE)
//                .photo(awsService.uploadFileFromBase64(userRequest.getPhoto()))
                .build();
    }

    public UserResponse toUserResponse(UserEntity userEntity) {
        String hello = "Thanks for register!!!";
        return UserResponse.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .urlPhoto(userEntity.getPhoto())
                .message(hello)
                .build();
    }

    public UserDetailsResponse userToUserDetail(UserEntity update) throws IOException {
        return UserDetailsResponse.builder()
                .id(update.getId())
                .firstName(update.getFirstName())
                .lastName(update.getLastName())
                .email(update.getEmail())
//                .photo(awsService.uploadFileFromBase64(update.getPhoto()))
                .timestamp(update.getTimestamp())
                .build();
    }

    public List<UserDetailsResponse> usersToUserDetailsList(List<UserEntity> users) throws IOException {
        List<UserDetailsResponse> list = new ArrayList<>();

        for(UserEntity user : users) {
            list.add( userToUserDetail(user) );
        }

        return list;
    }

    public List<UserResponse> toUsersPaginationResponse(List<UserEntity> users){
        List<UserResponse> responses = new ArrayList<>();

        for ( UserEntity  user: users){
            responses.add(toUserResponse(user));
        }

        return responses;
    }

}