package com.c702t.Cerveza.controller;

import com.c702t.Cerveza.models.entity.BusinessEntity;
import com.c702t.Cerveza.models.request.NewsRequest;
import com.c702t.Cerveza.models.request.SlideRequest;
import com.c702t.Cerveza.models.response.NewsResponse;
import com.c702t.Cerveza.service.NewsService;
import com.c702t.Cerveza.service.SlideService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/news")
@CrossOrigin(origins = "http://localhost:3000")
@Api(description ="News CRUD" , tags = "News")
public class NewsController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private SlideService slideService;

    @PostMapping("news/{id}")
    @ApiOperation(value = "Create News by Business", notes = "Allow busines to insert news")
    @ApiResponses({@ApiResponse(code = 201, message = "News Created!")})
    public ResponseEntity<NewsResponse> addNews (@Valid @RequestBody NewsRequest request,
                                                 @PathVariable @Valid @NotNull @NotBlank @ApiParam(
                                                         name = "id",
                                                         type = "Long",
                                                         value = "ID of the Business requested",
                                                         example = "1",
                                                         required = true) Long id) throws IOException {

        NewsResponse response = newsService.create(request, id);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("pageNews/")
    @ApiOperation(value = "Get News By Business ID", notes = "Returns all the news according to the Business ID")
    @ApiResponses({@ApiResponse(code = 200, message = "Return the requested news"),
            @ApiResponse(code = 404, message = "The inserted ID does not belong to a business")})
    public ResponseEntity<?> getNewsPage(
//            @PathVariable @Valid @NotNull @NotBlank @ApiParam(
//                                                name = "newsID",
//                                                type = "Long",
//                                                value = "ID of the news requested",
//                                                example = "1",
//                                                required = true) Long businessID,
                                         @RequestParam(value = "id", required = false) Long businessId,
                                         @RequestParam(value = "page", required = false) Optional<Integer> page,
                                         @RequestParam(value = "size", required = false) Optional<Integer> size) {

        return new ResponseEntity<>(newsService.getPageNewsByBusiness(page, size), HttpStatus.OK);
    }

    @GetMapping("pageNewsAll/")
    @ApiOperation(value = "Get All News by Page" , notes = "Returns All News ")
    @ApiResponses({ @ApiResponse(code = 200, message = "Return the requested news"),
            @ApiResponse(code = 404, message = "The inserted ID does not belong to a business")})
    public ResponseEntity<List<NewsResponse>> getAllNewsByBusiness (@RequestParam(value = "page", required = false) Optional<Integer> page
                                                                    ,@RequestParam(value = "size", required = false) Optional<Integer> size
                                                                    ,@RequestParam(value = "id", required = false) Long id ){

        List<NewsResponse> responses = newsService.getAllNewsByBusiness(id);
        return ResponseEntity.status(HttpStatus.OK).body(responses);

    }

}
