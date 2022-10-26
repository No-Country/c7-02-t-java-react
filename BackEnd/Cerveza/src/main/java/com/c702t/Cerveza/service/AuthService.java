package com.c702t.Cerveza.service;

import com.c702t.Cerveza.exception.RuntimeExceptionCustom;
import com.c702t.Cerveza.models.request.AuthRequest;
import com.c702t.Cerveza.models.request.UserRequest;
import com.c702t.Cerveza.models.response.AuthResponse;
import com.c702t.Cerveza.models.response.UserDetailsResponse;
import com.c702t.Cerveza.models.response.UserResponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AuthService {

    UserResponse register(UserRequest userRequest) throws UsernameNotFoundException, IOException;
    AuthResponse login(AuthRequest authRequest) throws Exception;
    UserDetailsResponse getPersonalInformation(String token) throws IOException;

}