package com.c702t.Cerveza.service;

import com.c702t.Cerveza.models.request.AuthRequest;
import com.c702t.Cerveza.models.request.UserRequest;
import com.c702t.Cerveza.models.response.AuthResponse;
import com.c702t.Cerveza.models.response.UserDetailsResponse;
import com.c702t.Cerveza.models.response.UserResponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.IOException;

public interface AuthService {
    UserResponse register(UserRequest userRequest) throws UsernameNotFoundException, IOException;
    AuthResponse login(AuthRequest authRequest);
    UserDetailsResponse getPersonalInformation(String token) throws IOException;
    void registerAdmin(UserRequest userRequest) throws IOException;
    UserResponse registerBusiness(UserRequest userRequest) throws IOException;
}