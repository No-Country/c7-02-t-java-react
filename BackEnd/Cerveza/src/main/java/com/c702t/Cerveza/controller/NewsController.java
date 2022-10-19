package com.c702t.Cerveza.controller;

import com.c702t.Cerveza.models.request.NewsRequest;
import com.c702t.Cerveza.models.response.NewsResponse;
import com.c702t.Cerveza.models.response.PaginationResponse;
import com.c702t.Cerveza.service.NewsService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/news")
@CrossOrigin(origins = "http://localhost:3000")
@Api(description ="News CRUD" , tags = "News")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @ApiOperation(value = "Create News", notes = "Allows Business to insert news")
    @ApiResponses({ @ApiResponse(code = 201, message = "News created!")})
    @PostMapping
    public ResponseEntity<NewsResponse> create (@Valid @RequestBody NewsRequest request) throws IOException {

        NewsResponse response = newsService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @ApiOperation(value = "Soft Delete News By ID", notes = "Allows Business to delete news by ID")
    @ApiResponses({ @ApiResponse(code = 204, message = "News soft deleted!"),
                    @ApiResponse(code = 404, message = "The inserted ID does not belong to a news")})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteNews (@PathVariable @Valid @NotNull @NotBlank Long id){

        newsService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @ApiOperation(value = "Update News By ID", notes = "Allows Business to update an existing news by ID")
    @ApiResponses({ @ApiResponse(code = 200, message = "News updated!"),
                    @ApiResponse(code = 404, message = "The inserted ID does not belong to a news")})
    @PutMapping("{id}")
    public ResponseEntity<NewsResponse> updateNews (@PathVariable @Valid @NotNull @NotBlank  Long id,
                                                    @Valid @RequestBody NewsRequest request) throws IOException {

        NewsResponse response = newsService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @ApiOperation(value = "Get News By ID", notes = "Returns all details of news by ID")
    @ApiResponses({ @ApiResponse(code = 200, message = "Return the requested news"),
                    @ApiResponse(code = 404, message = "The inserted ID does not belong to a news")})
    @GetMapping("{id}")
    public ResponseEntity<NewsResponse> getById (@PathVariable @Valid @NotNull @NotBlank Long id){

        NewsResponse response = newsService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @GetMapping
    @ApiOperation(value = "Get All News by Page" , notes = "Returns All News ")
    @ApiResponses({ @ApiResponse(code = 200, message = "Return All news created"),
                    @ApiResponse(code = 400, message = "Bad Request")})
    public ResponseEntity<PaginationResponse> getPage (@RequestParam(value = "page", required = false) Optional<Integer> page,
                                                       @RequestParam(value = "size", required = false) Optional<Integer> size) {

        PaginationResponse responses = newsService.getPage(page, size);
        return new ResponseEntity<>(responses, HttpStatus.OK);

    }

}
