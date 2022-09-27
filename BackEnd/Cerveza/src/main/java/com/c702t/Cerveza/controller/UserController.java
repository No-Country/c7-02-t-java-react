package com.c702t.Cerveza.controller;

import com.c702t.Cerveza.models.request.UserRequest;
import com.c702t.Cerveza.models.request.UserUpdateRequest;
import com.c702t.Cerveza.models.response.PaginationResponse;
import com.c702t.Cerveza.models.response.UserDetailsResponse;
import com.c702t.Cerveza.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
@Api(description ="User CRUD" , tags = "Users")
public class UserController {

    @Autowired
    private UserService userService;

//    @ApiOperation(value = "create the users", notes = "")
//    @ApiResponses(value = { @ApiResponse( code = 201, message = "User's list"),
//                            @ApiResponse( code = 403, message = "forbidden") })
//    @PostMapping
//    public ResponseEntity<UserDetailsResponse> register( @Valid @RequestBody UserRequest request) throws IOException {
//
//        UserDetailsResponse response = userService.register(request, token);
//        return ResponseEntity.ok().body(response);
//
//    }

    @ApiOperation(value = "List all the users", notes = "Allows an Admin to List all the registered users")
    @ApiResponses(value = { @ApiResponse( code = 201, message = "User's list"),
            @ApiResponse( code = 403, message = "forbidden") })
    @GetMapping
    public ResponseEntity<PaginationResponse> getAll(
            @RequestParam(value = "page", required = false) Optional<Integer> page,
            @RequestParam(value = "size", required = false) Optional<Integer> size) {

        PaginationResponse responses = userService.getUserPage(page, size);
        return new ResponseEntity<>(responses, HttpStatus.OK);

    }

    @ApiOperation(value = "Update an User", notes = "Allows an User to update itself")
    @ApiResponses(value = { @ApiResponse( code = 201, message = "User updated"),
                            @ApiResponse( code = 403, message = "forbidden") })
    @PatchMapping("/me")
    public ResponseEntity<UserDetailsResponse> updateUser(@RequestHeader(name = "Authorization") String token,
                                                          @RequestBody @Valid UserUpdateRequest request) throws IOException {

        UserDetailsResponse update = userService.updateBasicUser(request, token);
        return ResponseEntity.ok().body(update);

    }

    @ApiOperation(value = "Update an User", notes = "Allows an Admin to update any user")
    @ApiResponses(value = { @ApiResponse( code = 201, message = "User updated"),
                            @ApiResponse( code = 403, message = "forbidden") })
    @PatchMapping("/{id}")
    public ResponseEntity<UserDetailsResponse> updateUserForAdmin(@PathVariable("id") @Valid @NotNull Long id,
                                        @RequestBody @Valid UserUpdateRequest request) throws IOException {

        UserDetailsResponse update = userService.updateUserForAdmin(id, request);
        return ResponseEntity.ok().body(update);

    }

    @ApiOperation(value = "Delete an User", notes = "Allows an Admin to delete any User")
    @ApiResponses(value = { @ApiResponse( code = 201, message = "User deleted"),
                            @ApiResponse( code = 403, message = "forbidden") })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserForAdmin(@PathVariable("id")@Valid @NotNull Long id){

        userService.deleteUserForAdmin(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @ApiOperation(value = "Delete an User", notes = "Allows an User to delete itself")
    @ApiResponses(value = { @ApiResponse( code = 201, message = "User deleted"),
                            @ApiResponse( code = 403, message = "forbidden") })
    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteBasicUser(@RequestHeader(name = "Authorization") String token){

        userService.deleteBasicUser(token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}