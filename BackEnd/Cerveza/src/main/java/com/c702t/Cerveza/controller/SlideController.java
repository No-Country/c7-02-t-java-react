package com.c702t.Cerveza.controller;

import com.c702t.Cerveza.models.entity.BusinessEntity;
import com.c702t.Cerveza.models.request.SlideRequest;
import com.c702t.Cerveza.models.response.SlideResponse;
import com.c702t.Cerveza.models.response.PaginationResponse;
import com.c702t.Cerveza.repository.BusinessRepository;
import com.c702t.Cerveza.repository.SlideRepository;
import com.c702t.Cerveza.service.NewsService;
import com.c702t.Cerveza.service.SlideService;
import io.swagger.annotations.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/slides")
@CrossOrigin(origins = "http://localhost:3000")
@Api(description ="Slides CRUD" , tags = "Slides")
public class SlideController {

//
//    @Autowired
//    private NewsService newsService;
//    @Autowired
//    private SlideService slideService;
//    @Autowired
//    private BusinessRepository businessRepository;
//
//    @PostMapping("slides/{id}")
//    @ApiOperation(value = "Create Slides by Business", notes = "Allow busines to insert slides")
//    @ApiResponses({@ApiResponse(code = 201, message = "Slides Created!")})
//    public ResponseEntity<SlideResponse> addSlides (@PathVariable @Valid @NotNull @NotBlank @ApiParam(
//            name = "id",
//            type = "Long",
//            value = "id of the business requested",
//            example = "1",
//            required = true) Long id,
//                                                    @Valid @RequestBody SlideRequest request) throws IOException {
//
//        BusinessEntity entityBusiness = businessRepository.getById(id);
//        request.setBusiness_id(id);
//        SlideResponse response = slideService.create(request);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }
//
//    @GetMapping("pageSlides/")
//    @ApiOperation(value = "Get All Slide by Page" , notes = "Returns All Slide ")
//    @ApiResponses({ @ApiResponse(code = 200, message = "Return All Slide created"),
//            @ApiResponse(code = 400, message = "Bad Request")})
//    public ResponseEntity<PaginationResponse> getSlidesPage (@RequestParam(value = "page", required = false) Optional<Integer> page,
//                                                             @RequestParam(value = "size", required = false) Optional<Integer> size) {
//
//        PaginationResponse responses = slideService.getPage(page, size);
//        return new ResponseEntity<>(responses, HttpStatus.OK);
//
//    }
//
//
}







//
//    @Autowired
//    private SlideService slideService;
//
//    @ApiOperation(value = "Create Slide", notes = "Allows Business to insert Slide")
//    @ApiResponses({ @ApiResponse(code = 201, message = "Slide created!")})
//    @PostMapping
//    public ResponseEntity<SlideResponse> create (@Valid @RequestBody SlideRequest request) throws IOException {
//
//        SlideResponse response = slideService.create(request);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//
//    }
//
//    @ApiOperation(value = "Soft Delete Slide By ID", notes = "Allows Business to delete Slide by ID")
//    @ApiResponses({ @ApiResponse(code = 204, message = "Slide soft deleted!"),
//                    @ApiResponse(code = 404, message = "The inserted ID does not belong to a Slide")})
//    @DeleteMapping("{id}")
//    public ResponseEntity<Void> deleteSlide (@PathVariable @Valid @NotNull @NotBlank Long id){
//
//        slideService.delete(id);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//
//    }
//
//    @ApiOperation(value = "Update Slide By ID", notes = "Allows Business to update an existing Slide by ID")
//    @ApiResponses({ @ApiResponse(code = 200, message = "Slide updated!"),
//                    @ApiResponse(code = 404, message = "The inserted ID does not belong to a Slide")})
//    @PutMapping("{id}")
//    public ResponseEntity<SlideResponse> updateSlide (@PathVariable @Valid @NotNull @NotBlank  Long id,
//                                                    @Valid @RequestBody SlideRequest request) throws IOException {
//
//        SlideResponse response = slideService.update(id, request);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//
//    }
//
//    @ApiOperation(value = "Get Slide By ID", notes = "Returns all details of Slide by ID")
//    @ApiResponses({ @ApiResponse(code = 200, message = "Return the requested Slide"),
//                    @ApiResponse(code = 404, message = "The inserted ID does not belong to a Slide")})
//    @GetMapping("{id}")
//    public ResponseEntity<SlideResponse> getById (@PathVariable @Valid @NotNull @NotBlank Long id){
//
//        SlideResponse response = slideService.getById(id);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//
//    }
//
//    @GetMapping
//    @ApiOperation(value = "Get All Slide by Page" , notes = "Returns All Slide ")
//    @ApiResponses({ @ApiResponse(code = 200, message = "Return All Slide created"),
//                    @ApiResponse(code = 400, message = "Bad Request")})
//    public ResponseEntity<PaginationResponse> getPage (@RequestParam(value = "page", required = false) Optional<Integer> page,
//                                                       @RequestParam(value = "size", required = false) Optional<Integer> size) {
//
//        PaginationResponse responses = slideService.getPage(page, size);
//        return new ResponseEntity<>(responses, HttpStatus.OK);
//
//    }


//}
