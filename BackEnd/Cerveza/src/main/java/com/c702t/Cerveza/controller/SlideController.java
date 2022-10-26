package com.c702t.Cerveza.controller;

import com.c702t.Cerveza.exception.RuntimeExceptionCustom;
import com.c702t.Cerveza.models.request.SlideRequest;
import com.c702t.Cerveza.models.response.SlideResponse;
import com.c702t.Cerveza.service.SlideService;
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

@RestController
@RequestMapping(path = "/slides")
@CrossOrigin(origins = "http://localhost:3000")
@Api(description ="Slides CRUD" , tags = "Slides")
public class SlideController {

    @Autowired
    private SlideService slideService;

    @ApiOperation(value = "Create Slide", notes = "Allows Business to insert Slide")
    @ApiResponses({ @ApiResponse(code = 201, message = "Slide created!")})
    @PostMapping
    public ResponseEntity<SlideResponse> create (@Valid @RequestHeader(name="Authorization") String token,
                                                 @RequestParam Long idBusiness,
                                                 @RequestPart (required = false) MultipartFile file
                                                                                ) throws RuntimeExceptionCustom, IOException {

        SlideResponse response = slideService.create(token, idBusiness, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @ApiOperation(value = "Get All Slides by Business" , notes = "Returns All Slides by Business")
    @ApiResponses({ @ApiResponse(code = 200, message = "Return All Slides created"),
                    @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("{business_id}")
    public ResponseEntity<List<SlideResponse>> getAllSlidesPage (@PathVariable @Valid @NotNull @NotBlank  @ApiParam(
                                                                        name = "business_id",
                                                                        type = "Long",
                                                                        value = "ID of the business requested",
                                                                        example = "1",
                                                                        required = true) Long business_id ) throws RuntimeExceptionCustom {

        List<SlideResponse> responses = slideService.getAllSlidesByBusiness(business_id);
        return ResponseEntity.status(HttpStatus.OK).body(responses);

    }

    @ApiOperation(value = "Soft Delete Slide By ID", notes = "Allows Business to delete Slide by ID")
    @ApiResponses({ @ApiResponse(code = 204, message = "Slide soft deleted!"),
                    @ApiResponse(code = 404, message = "The inserted ID does not belong to a Slide")})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSlide (@PathVariable @Valid @NotNull @NotBlank  @ApiParam(
                                                                name = "id",
                                                                type = "Long",
                                                                value = "id of the Slides requested",
                                                                example = "1",
                                                                required = true) Long id ,
                                             @RequestHeader(name="Authorization") String token) throws RuntimeExceptionCustom {

        slideService.delete(id, token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @ApiOperation(value = "Get Slides By ID", notes = "Returns all details of slide by ID")
    @ApiResponses({ @ApiResponse(code = 200, message = "Return the requested slide"),
                    @ApiResponse(code = 404, message = "The inserted ID does not belong to a slide")})
    @GetMapping("getById/{id}")
    public ResponseEntity<SlideResponse> getById (@PathVariable @Valid @NotNull @NotBlank Long id) throws RuntimeExceptionCustom {

        try{

            SlideResponse response = slideService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }catch (RuntimeExceptionCustom rec){
            rec.getMessage();
        }

        return null;

    }

}
