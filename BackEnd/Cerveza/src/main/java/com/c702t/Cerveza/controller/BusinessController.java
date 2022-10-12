package com.c702t.Cerveza.controller;


import com.c702t.Cerveza.models.entity.BusinessEntity;
import com.c702t.Cerveza.models.request.BusinessRequest;
import com.c702t.Cerveza.models.request.NewsRequest;
import com.c702t.Cerveza.models.request.SlideRequest;
import com.c702t.Cerveza.models.response.BusinessResponse;
import com.c702t.Cerveza.models.response.NewsResponse;
import com.c702t.Cerveza.models.response.PaginationResponse;
import com.c702t.Cerveza.models.response.SlideResponse;
import com.c702t.Cerveza.repository.BusinessRepository;
import com.c702t.Cerveza.service.BusinessService;
import com.c702t.Cerveza.service.NewsService;
import com.c702t.Cerveza.service.SlideService;
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
    @Autowired
    private BusinessRepository businessRepository;
    @Autowired
    private NewsService newsService;
    @Autowired
    private SlideService slideService;

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
                                                        value = "ID of the Business requested",
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

    @PostMapping("news/{id}")
    @ApiOperation(value = "Create News by Business", notes = "Allow busines to insert news")
    @ApiResponses({@ApiResponse(code = 201, message = "News Created!")})
    public ResponseEntity<NewsResponse> addNews (@PathVariable @Valid @NotNull @NotBlank @ApiParam(
                                                            name = "id",
                                                            type = "Long",
                                                            value = "id of the business requested",
                                                            example = "1",
                                                            required = true) Long id,
                                                 @Valid @RequestBody NewsRequest request) throws IOException {

        BusinessEntity entityBusiness = businessRepository.getById(id);
        request.setBusiness_id(id);
        NewsResponse response = newsService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("pageNews/")
    @ApiOperation(value = "Get All News by Page" , notes = "Returns All News ")
    @ApiResponses({ @ApiResponse(code = 200, message = "Return All news created"),
                    @ApiResponse(code = 400, message = "Bad Request")})
    public ResponseEntity<PaginationResponse> getNewsPage (@RequestParam(value = "page", required = false) Optional<Integer> page,
                                                           @RequestParam(value = "size", required = false) Optional<Integer> size) {

        PaginationResponse responses = newsService.getPage(page, size);
        return new ResponseEntity<>(responses, HttpStatus.OK);

    }

    @PostMapping("slides/{id}")
    @ApiOperation(value = "Create Slides by Business", notes = "Allow busines to insert slides")
    @ApiResponses({@ApiResponse(code = 201, message = "Slides Created!")})
    public ResponseEntity<SlideResponse> addSlides (@PathVariable @Valid @NotNull @NotBlank @ApiParam(
                                                                name = "id",
                                                                type = "Long",
                                                                value = "id of the business requested",
                                                                example = "1",
                                                                required = true) Long id,
                                                 @Valid @RequestBody SlideRequest request) throws IOException {

        BusinessEntity entityBusiness = businessRepository.getById(id);
        request.setBusiness_id(id);
        SlideResponse response = slideService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("pageSlides/")
    @ApiOperation(value = "Get All Slide by Page" , notes = "Returns All Slide ")
    @ApiResponses({ @ApiResponse(code = 200, message = "Return All Slide created"),
                    @ApiResponse(code = 400, message = "Bad Request")})
    public ResponseEntity<PaginationResponse> getSlidesPage (@RequestParam(value = "page", required = false) Optional<Integer> page,
                                                       @RequestParam(value = "size", required = false) Optional<Integer> size) {

        PaginationResponse responses = slideService.getPage(page, size);
        return new ResponseEntity<>(responses, HttpStatus.OK);

    }

}
