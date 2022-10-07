package com.c702t.Cerveza.controller;

import com.c702t.Cerveza.models.request.AuthRequest;
import com.c702t.Cerveza.models.request.UpdatePasswordRequest;
import com.c702t.Cerveza.models.request.RecoverPassRequest;
import com.c702t.Cerveza.models.request.UserRequest;
import com.c702t.Cerveza.models.response.AuthResponse;
import com.c702t.Cerveza.models.response.UserResponse;
import com.c702t.Cerveza.service.AuthService;
import com.c702t.Cerveza.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
@Api(value = "Operations related to Authentication", tags = "Authentication")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "Login a user", response = AuthResponse.class)
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) throws Exception {
        return ResponseEntity.ok(authService.login(authRequest));
    }

    @PostMapping("/register")
    @ApiOperation(value = "Register a new User", code = 201, response = UserResponse.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = UserResponse.class),
                            @ApiResponse(code = 400, message = "Bad Request"),
                            @ApiResponse(code = 403, message = "For Bidden"),
                            @ApiResponse(code = 404, message = "Not Found") })
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest userRequest) throws Exception {

        String confirmPassword = "1234";
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(userRequest));

    }

    @PostMapping("/recoverPassword")
    @ApiOperation(value = "Recover on Password", code = 201, response = UserResponse.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = UserResponse.class),
                            @ApiResponse(code = 400, message = "Bad Request"),
                            @ApiResponse(code = 403, message = "For Bidden"),
                            @ApiResponse(code = 404, message = "Not Found") })
    public ResponseEntity<UserResponse> recoverPassword(@Valid @RequestBody RecoverPassRequest request) throws Exception {

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.recoverPassword(request));

    }

    @PutMapping("/upDatePassword")
    @ApiOperation(value = "Update Password", code = 201, response = UserResponse.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = UserResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "For Bidden"),
            @ApiResponse(code = 404, message = "Not Found") })
    public ResponseEntity<UserResponse> upDatePassword(@Valid @RequestBody UpdatePasswordRequest request) throws Exception {

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.upDatePassword(request));

    }

}

//    @GetMapping("/me")
//    @ApiOperation(value = "Get user details",
//            response = UserDetailsResponse.class)
//    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = UserDetailsResponse.class),
//                            @ApiResponse(code = 404, message = "Not Found") })
//    public ResponseEntity<UserDetailsResponse> getPersonalInformation(@RequestHeader(name = "Authorization") String token) throws IOException {
//
//        return ResponseEntity.status(HttpStatus.OK).body(authService.getPersonalInformation(token));
//
//    }