package com.c702t.Cerveza.controller;


import com.c702t.Cerveza.models.request.BusinessRequest;
import com.c702t.Cerveza.models.response.BusinessResponse;
import com.c702t.Cerveza.service.BusinessService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<BusinessResponse> createBusiness (@Valid @RequestBody BusinessRequest request, @RequestHeader(name="Authorization") String token) throws IOException {

        BusinessResponse responseCreate = this.businessService.create(request, token);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseCreate);
    }


    @DeleteMapping("{id}")
    @ApiOperation(value = "Soft Delete business By ID", notes = "Allows Admin to delete business by ID")
    @ApiResponses({@ApiResponse(code = 204, message = "Business deleted!"),
            @ApiResponse(code = 404, message = "The inserted ID does not belong to a business")})
    public ResponseEntity<Void> deleteBusiness (@PathVariable @Valid @NotNull @NotBlank @ApiParam(
            name = "id",
            type = "Long",
            value = "ID of the business requested",
            example = "1",
            required = true) Long id){

        this.businessService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("{id}")
    @ApiOperation(value = "Update Business By ID", notes = "Allows Admin to update an existing business by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Business updated!"),
            @ApiResponse(code = 404, message = "The inserted ID does not belong to a business")})
    public ResponseEntity<BusinessResponse> updateBusiness (@PathVariable @Valid @NotNull @NotBlank  @ApiParam(
                                                             name = "id",
                                                             type = "Long",
                                                             value = "ID of the business requested",
                                                             example = "1",
                                                             required = true) Long id,
                                                            @Valid @RequestBody @ApiParam(
                                                            name = "New Business",
                                                            value = "Business to save",
                                                            required = true) BusinessRequest request) throws IOException {

        BusinessResponse response = this.businessService.update(id, request);

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
    public ResponseEntity <?> getByFilters (
            @RequestParam (required = false) String city,
            @RequestParam (required = false) String state,
            @RequestParam (required = false) String country,
            @RequestParam (required = false, defaultValue = "ASC") String order,
            @RequestParam(value = "page", required = false) Optional<Integer> page,
            @RequestParam(value = "size", required = false) Optional<Integer> size) {

        return new ResponseEntity<>(businessService.getByFilters(city, state, country, order, page, size), HttpStatus.OK);

    }

}
