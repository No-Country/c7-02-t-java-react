package com.c702t.Cerveza.service.impl;

import com.amazonaws.services.codestar.model.UserProfileAlreadyExistsException;

import com.c702t.Cerveza.auth.service.JwtUtils;
import com.c702t.Cerveza.auth.service.impl.UserDetailsCustomService;
import com.c702t.Cerveza.auth.utility.RoleEnum;
import com.c702t.Cerveza.models.entity.RoleEntity;
import com.c702t.Cerveza.models.entity.UserEntity;
import com.c702t.Cerveza.models.mapper.UserMapper;
import com.c702t.Cerveza.models.request.AuthRequest;
import com.c702t.Cerveza.models.request.UserRequest;
import com.c702t.Cerveza.models.response.AuthResponse;
import com.c702t.Cerveza.models.response.UserDetailsResponse;
import com.c702t.Cerveza.models.response.UserResponse;
import com.c702t.Cerveza.repository.RoleRepository;
import com.c702t.Cerveza.repository.UserRepository;
import com.c702t.Cerveza.service.AuthService;
import com.c702t.Cerveza.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsCustomService userDetailsCustomService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;

    @Transactional
    public AuthResponse login(AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            String token = generateToken(authRequest.getEmail());
            UserEntity user = userRepository.findByEmail(authRequest.getEmail()).orElse(null);

            RoleEntity role = user.getRoleId().iterator().next();


            return AuthResponse.builder()
                    .email(authRequest.getEmail())
                    .token(token)
                    .id(user.getId())
                    .nameRol(role.getName())
                    .build();
        } catch (Exception e) {
            throw new Exception("the email or the password do not match");
        }
    }

    @Transactional
    public UserResponse register(UserRequest userRequest) throws UsernameNotFoundException, IOException {

        if (userRepository.findByEmail(userRequest.getEmail()).isPresent())
            throw new UserProfileAlreadyExistsException("Email already exists");
        if(!userRequest.getPassword().equalsIgnoreCase(userRequest.getConfirmPassword()))
            throw new UserProfileAlreadyExistsException("passwords do not match");

        Set<RoleEntity> roles = new HashSet<>();
        if (userRequest.getRol().equalsIgnoreCase("user")) {
            roles = roleRepository.findByName(RoleEnum.USER.getSimpleRoleName());
            if (roles.isEmpty()) {
                RoleEntity rol = new RoleEntity();
                rol.setName(RoleEnum.USER.getSimpleRoleName());
                rol = roleRepository.save(rol);
                roles.add(rol);
            }
        }else {
            roles = roleRepository.findByName(RoleEnum.BUSINESS.getSimpleRoleName());
            if (roles.isEmpty()) {
                RoleEntity rol = new RoleEntity();
                rol.setName(RoleEnum.BUSINESS.getSimpleRoleName());
                rol = roleRepository.save(rol);
                roles.add(rol);
            }
        }
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        UserEntity userEntity = userMapper.toUserEntity(userRequest, roles);
        userRepository.save(userEntity);

        if (!userRequest.getEmail().contains("@test")){
            emailService.checkFromRequest(userEntity.getEmail(), "userRegistered");
        }

        String token = generateToken(userRequest.getEmail());
        return userMapper.toUserResponse(userEntity);
    }

    @Override
    @Transactional
    public UserResponse registerBusiness(UserRequest userRequest) throws IOException {

        if (userRepository.findByEmail(userRequest.getEmail()).isPresent())
            throw new UsernameNotFoundException("userBusiness already exists");

        Set<RoleEntity> roles = roleRepository.findByName(RoleEnum.BUSINESS.getSimpleRoleName());

        if (roles.isEmpty()){
            RoleEntity rol = new RoleEntity();
            rol.setName(RoleEnum.BUSINESS.getSimpleRoleName());
            rol = roleRepository.save(rol);
            roles.add(rol);
        }

        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        UserEntity userEntity = userMapper.toUserEntity(userRequest, roles);
        userRepository.save(userEntity);
        String token = generateToken(userRequest.getEmail());

        if (!userRequest.getEmail().contains("@test")){
            emailService.checkFromRequest(userEntity.getEmail(), "businessRegistred");
        }

        return userMapper.toUserResponse(userEntity);
    }

    @Transactional
    private String generateToken(String userRequest) {
        return jwtUtils.generateToken(userDetailsCustomService.loadUserByUsername(userRequest));
    }

    @Transactional
    public UserDetailsResponse getPersonalInformation(String token) throws IOException {
        String email = jwtUtils.extractUsername(token.substring(7));
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with the email: " + email));

        return userMapper.userToUserDetail(user);
    }
//
//    @Transactional
//    public void registerAdmin(UserRequest userRequest) throws IOException {
//        if (userRepository.findByEmail(userRequest.getEmail()).isPresent())
//            throw new UsernameNotFoundException("User Admin already exists");
//
//        Set<RoleEntity> roles = roleRepository.findByName(RoleEnum.ADMIN.getSimpleRoleName());
//
//        if (roles.isEmpty()){
//            RoleEntity rol = new RoleEntity();
//            rol.setName(RoleEnum.ADMIN.getSimpleRoleName());
//            rol = roleRepository.save(rol);
//            roles.add(rol);
//        }
//
//        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
//        UserEntity userEntity = userMapper.toUserEntity(userRequest, roles);
//        userRepository.save(userEntity);
//        String token = generateToken(userRequest.getEmail());
//    }
//


}
