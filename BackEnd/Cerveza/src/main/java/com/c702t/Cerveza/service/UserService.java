package com.c702t.Cerveza.service;


import com.c702t.Cerveza.models.request.UpdatePasswordRequest;
import com.c702t.Cerveza.models.request.RecoverPassRequest;
import com.c702t.Cerveza.models.request.UserRequest;
import com.c702t.Cerveza.models.request.UserUpdateRequest;
import com.c702t.Cerveza.models.response.PaginationResponse;
import com.c702t.Cerveza.models.response.UserDetailsResponse;
import com.c702t.Cerveza.models.response.UserResponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {

//    UserDetailsResponse register(UserRequest request, String token) throws UsernameNotFoundException, IOException;
    UserDetailsResponse updateUser(UserUpdateRequest request, String token) throws IOException;
    List<UserDetailsResponse> getUsers() throws IOException;
    //UsersPaginationResponse getPaginationUsers(Integer page);
    PaginationResponse getUserPage(Optional<Integer> pageNumber, Optional<Integer> size);
    UserResponse recoverPassword(RecoverPassRequest request) throws Exception;
    public UserResponse upDatePassword(UpdatePasswordRequest request) throws Exception;

    }

