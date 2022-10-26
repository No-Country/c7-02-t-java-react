package com.c702t.Cerveza.controller;


import com.c702t.Cerveza.exception.RuntimeExceptionCustom;
import com.c702t.Cerveza.models.request.BusinessRequest;
import com.c702t.Cerveza.models.response.BusinessResponse;
import com.c702t.Cerveza.models.response.NewsResponse;
import com.c702t.Cerveza.models.response.PaginationResponse;
import com.c702t.Cerveza.models.response.UserResponse;
import com.c702t.Cerveza.service.AwsService;
import com.c702t.Cerveza.service.BusinessService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("business")
@Api(description ="Business CRUD" , tags = "Business")
@CrossOrigin(origins = "http://localhost:3000")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @PostMapping
    @ApiOperation(value = "Create business", notes = "Allows Admin to insert business")
    @ApiResponses({@ApiResponse(code = 201, message = "Business created!")})
    public ResponseEntity<BusinessResponse> create(@Valid @RequestHeader(name="Authorization") String token,
//                                               @ApiParam( name = "idUser", type = "Long",
//                                                       value = "id of the User",
//                                                       example = "3") @RequestParam (required = false) Long idUser,
                                               @RequestPart (required = false) MultipartFile file,
                                               @ApiParam( name = "name", type = "String",
                                                       example = "Pata Beer" ) @RequestParam String name,
                                               @ApiParam( name = "address", type = "String",
                                                       example = "25 de mayo 1515" ) @RequestParam String address,
                                               @ApiParam( name = "city", type = "String",
                                                       example = "Rosario" ) @RequestParam String city,
                                               @ApiParam( name = "state", type = "String",
                                                       example = "Santa Fe" ) @RequestParam String state,
                                               @ApiParam( name = "country", type = "String",
                                                       example = "Santa Fe" ) @RequestParam String country,
                                               @ApiParam( name = "phone", type = "String",
                                                       example = "3516622525" ) @RequestParam String phone,
                                               @ApiParam( name = "email", type = "String",
                                                       example = "patabeer@mail.com" ) @RequestParam String email,
                                               @ApiParam( name = "aboutUsText", type = "String",
                                                       example = "Hola Buen dia" ) @RequestParam String aboutUsText,
                                               @ApiParam( name = "urlInstagram", type = "String",
                                                       example = "pata.instagram.com" ) @RequestParam String urlInstagram,
                                               @ApiParam( name = "urlFacebook", type = "String",
                                                       example = "pata.facebook.com" ) @RequestParam String urlFacebook
                                                                ) throws RuntimeExceptionCustom, IOException {

        BusinessResponse response = this.businessService.create(token, name, file, address, city, state, country,
                                                            phone, email, aboutUsText, urlInstagram, urlFacebook);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

//    @PatchMapping("/uploadPhoto")
//    @ApiOperation(value = "Add Image on Business", code = 201, response = UserResponse.class)
//    @ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = UserResponse.class),
//            @ApiResponse(code = 400, message = "Bad Request"),
//            @ApiResponse(code = 404, message = "Not Found") })
//    public ResponseEntity<BusinessResponse> uploadPhoto(@RequestPart (required = false) MultipartFile file,
//                                                    @RequestParam(value = "id", required = false) Long id
//                                                                                        ) throws Exception {
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(businessService.uploadPhoto(id, file));
//
//    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Soft Delete business By ID", notes = "Allows Admin to delete business by ID")
    @ApiResponses({@ApiResponse(code = 204, message = "Business deleted!"),
            @ApiResponse(code = 404, message = "The inserted ID does not belong to a business")})
    public ResponseEntity<Void> deleteBusiness (@Valid @RequestHeader(name="Authorization") String token,
                                                @PathVariable @Valid @NotNull @NotBlank @ApiParam(
                                                            name = "id",
                                                            type = "Long",
                                                            value = "ID of the business requested",
                                                            example = "1",
                                                            required = true) Long id){

        this.businessService.delete(id, token);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("{id}")
    @ApiOperation(value = "Update Business By ID", notes = "Allows Admin to update an existing business by ID")
    @ApiResponses({ @ApiResponse(code = 200, message = "Business updated!"),
                    @ApiResponse(code = 404, message = "The inserted ID does not belong to a business")})
    public ResponseEntity<BusinessResponse> upload(@Valid @RequestHeader(name="Authorization") String token,
                                               @ApiParam( name = "idBusiness", type = "Long",
                                                       value = "id of the Business",
                                                       example = "3") @RequestParam (required = false) Long idBusiness,
                                               @RequestPart (required = false) MultipartFile file,
                                               @ApiParam( name = "name", type = "String",
                                                       example = "Pata Beer" ) @RequestParam (required = false)String name,
                                               @ApiParam( name = "address", type = "String",
                                                       example = "25 de mayo 1515" ) @RequestParam (required = false)String address,
                                               @ApiParam( name = "city", type = "String",
                                                       example = "Rosario" ) @RequestParam (required = false)String city,
                                               @ApiParam( name = "state", type = "String",
                                                       example = "Santa Fe" ) @RequestParam (required = false)String state,
                                               @ApiParam( name = "country", type = "String",
                                                       example = "Santa Fe" ) @RequestParam (required = false)String country,
                                               @ApiParam( name = "phone", type = "String",
                                                       example = "3516622525" ) @RequestParam (required = false)String phone,
                                               @ApiParam( name = "email", type = "String",
                                                       example = "patabeer@mail.com" ) @RequestParam (required = false)String email,
                                               @ApiParam( name = "aboutUsText", type = "String",
                                                       example = "Hola Buen dia" ) @RequestParam (required = false)String aboutUsText,
                                               @ApiParam( name = "urlInstagram", type = "String",
                                                       example = "pata.instagram.com" ) @RequestParam (required = false)String urlInstagram,
                                               @ApiParam( name = "urlFacebook", type = "String",
                                                       example = "pata.facebook.com" ) @RequestParam (required = false)String urlFacebook
                                                                ) throws RuntimeExceptionCustom, IOException {

        BusinessResponse response = this.businessService.update(token, idBusiness, name, file, address, city, state, country,
                                                            phone, email, aboutUsText, urlInstagram, urlFacebook);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("{id}")
    @ApiOperation(value = "Get Business By ID", notes = "Returns all details of business by ID")
    @ApiResponses({@ApiResponse(code = 200, message = "Return the requested business"),
            @ApiResponse(code = 404, message = "The inserted ID does not belong to a business")})
    public ResponseEntity<BusinessResponse> getById (@PathVariable @Valid @NotNull @NotBlank @ApiParam(
                                                        name = "id",
                                                        type = "Long",
                                                        value = "ID of the news requested",
                                                        example = "1",
                                                        required = true) Long id) throws IOException {

        BusinessResponse response = this.businessService.getById(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @GetMapping
    public ResponseEntity <PaginationResponse> getByFilters (@RequestParam (required = false) @ApiParam(name = "city",
                                                                    type = "String",
                                                                    value = "name of the City",
                                                                    example = "Cordoba") String city,
                                                             @RequestParam (required = false)@ApiParam(name = "state",
                                                                    type = "String",
                                                                    value = "name of the State",
                                                                    example = "Cordoba") String state,
                                                             @RequestParam (required = false)@ApiParam(name = "country",
                                                                     type = "String",
                                                                     value = "name of the Country",
                                                                     example = "Argentina") String country,
                                                             @RequestParam (required = false, defaultValue = "ASC") String order,
                                                             @RequestParam(value = "page", required = false)@ApiParam(
                                                                     name = "page",
                                                                     type = "Integer",
                                                                     value = "page number I want to see",
                                                                     example = "1")Optional<Integer> page,
                                                             @RequestParam(value = "size", required = false)@ApiParam(
                                                                     name = "size",
                                                                     type = "Integer",
                                                                     value = "number of items per page",
                                                                     example = "3") Optional<Integer> size) {

        return new ResponseEntity<>(businessService.getByFilters(city, state, country, order, page, size),
                                    HttpStatus.OK);

    }

    @GetMapping("/getByUser")
    @ApiOperation(value = "Get Business By User ID", notes = "Returns all the business according to the User")
    @ApiResponses({@ApiResponse(code = 200, message = "Return the requested business"),
            @ApiResponse(code = 404, message = "The inserted ID does not belong to a user")})
    public ResponseEntity<?> getByUser(@RequestParam (required = true)  @ApiParam(
            name = "userID",
            type = "Long",
            value = "ID of the user requested",
            example = "1",
            required = true) Long userID,  @RequestParam (required = false, defaultValue = "ASC") String order,  @RequestParam(value = "page", required = false) Optional<Integer> page,
                                           @RequestParam(value = "size", required = false) Optional<Integer> size) {

        return new ResponseEntity<>(businessService.getPageBusinessByUsers(userID,order,page, size), HttpStatus.OK);
    }

}
