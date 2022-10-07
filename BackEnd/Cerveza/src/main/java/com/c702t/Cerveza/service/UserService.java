package com.c702t.Cerveza.service;


import com.c702t.Cerveza.models.request.UserRequest;
import com.c702t.Cerveza.models.request.UserUpdateRequest;
import com.c702t.Cerveza.models.response.PaginationResponse;
import com.c702t.Cerveza.models.response.UserDetailsResponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDetailsResponse register(UserRequest request, String token) throws UsernameNotFoundException, IOException;

    UserDetailsResponse updateBasicUser(UserUpdateRequest request, String token) throws IOException;

    UserDetailsResponse updateUserForAdmin(Long id, UserUpdateRequest request) throws IOException;

    List<UserDetailsResponse> getUsers() throws IOException;

    //UsersPaginationResponse getPaginationUsers(Integer page);
    void deleteUserForAdmin(Long id);

    void deleteBasicUser(String token);

    PaginationResponse getUserPage(Optional<Integer> pageNumber, Optional<Integer> size);


}

