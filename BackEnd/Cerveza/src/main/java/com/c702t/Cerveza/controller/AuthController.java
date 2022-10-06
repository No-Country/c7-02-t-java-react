package com.c702t.Cerveza.controller;

import com.c702t.Cerveza.models.request.*;
import com.c702t.Cerveza.models.response.AuthResponse;
import com.c702t.Cerveza.models.response.UserDetailsResponse;
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
import java.io.IOException;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
@Api(value = "Operations related to Authentication", tags = "Authentication")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation(value = "Register a new User", code = 201, response = UserResponse.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = UserResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found") })
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest userRequest) throws Exception {

        String confirmPassword = "1234";
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(userRequest));

    }

    @PostMapping("/login")
    @ApiOperation(value = "Login a user", response = AuthResponse.class)
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) throws Exception {
        return ResponseEntity.ok(authService.login(authRequest));
    }

    @PostMapping("/recoverPassword")
    @ApiOperation(value = "Recover on Password", code = 201, response = UserResponse.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = UserResponse.class),
                            @ApiResponse(code = 400, message = "Bad Request"),
                            @ApiResponse(code = 404, message = "Not Found") })
    public ResponseEntity<UserResponse> recoverPassword(@Valid @RequestBody RecoverPassRequest request) throws Exception {

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.recoverPassword(request));

    }

    @PutMapping("/upDatePassword")
    @ApiOperation(value = "Update Password", code = 201, response = UserResponse.class)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = UserResponse.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found") })
    public ResponseEntity<UserResponse> upDatePassword(@Valid @RequestBody UpdatePasswordRequest request) throws Exception {

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.upDatePassword(request));

    }

    @PatchMapping("/update")
    @ApiOperation(value = "Update an User", notes = "Allows an User to update itself")
    @ApiResponses(value = { @ApiResponse( code = 201, message = "User updated") })
    public ResponseEntity<UserDetailsResponse> updateUser(@RequestHeader(name = "Authorization") String token,
                                                          @RequestBody @Valid UserUpdateRequest request) throws IOException {

        UserDetailsResponse update = userService.updateBasicUser(request, token);
        return ResponseEntity.ok().body(update);

    }

}
