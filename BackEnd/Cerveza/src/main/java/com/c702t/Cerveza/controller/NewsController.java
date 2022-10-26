package com.c702t.Cerveza.controller;

import com.c702t.Cerveza.exception.RuntimeExceptionCustom;
import com.c702t.Cerveza.models.request.NewsRequest;
import com.c702t.Cerveza.models.response.NewsResponse;
import com.c702t.Cerveza.models.response.PaginationResponse;
import com.c702t.Cerveza.service.NewsService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/news")
@CrossOrigin(origins = "http://localhost:3000")
@Api(description ="News CRUD" , tags = "News")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @ApiOperation(value = "Create News", notes = "Allows Business to insert news")
    @ApiResponses({@ApiResponse(code = 201, message = "News created!")})
    @PostMapping
    public ResponseEntity<NewsResponse> create(@Valid @RequestHeader(name="Authorization") String token,
                                               @ApiParam( name = "idBusiness", type = "Long",
                                                       value = "id of the business",
                                                       example = "3") @RequestParam (required = false) Long idBusiness,
                                               @ApiParam( name = "name", type = "String",
                                                       example = "Promo 2 x 1 IPA" ) @RequestParam String name,
                                               @ApiParam( name = "content", type = "String",
                                                       example = "La Promo es hasta las 20hs" ) @RequestParam String content,
                                               @ApiParam( name = "startDate", type = "String",
                                                       example = "2022-10-23" ) @RequestParam String startDate,
                                               @ApiParam( name = "endDate", type = "String",
                                                       example = "2022-10-30" ) @RequestParam String endDate,
                                               @RequestPart (required = false) MultipartFile file
                                                                    ) throws RuntimeExceptionCustom, IOException {

        LocalDate desde = LocalDate.parse(startDate);
        LocalDate hasta = LocalDate.parse(endDate);

        NewsResponse response = newsService.create(token, idBusiness, file, name, content, desde, hasta);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("{id}")
    @ApiOperation(value = "Get All News by Business", notes = "Returns All News by Business")
    @ApiResponses({@ApiResponse(code = 200, message = "Return All news created"),
            @ApiResponse(code = 400, message = "Bad Request")})
    public ResponseEntity<List<NewsResponse>> getAllNewsPage(@PathVariable @Valid @NotNull @NotBlank @ApiParam(
            name = "id",
            type = "Long",
            value = "id of the business requested",
            example = "1",
            required = true) Long id) throws RuntimeExceptionCustom {

        List<NewsResponse> responses = newsService.getAllNewsByBusiness(id);
        return ResponseEntity.status(HttpStatus.OK).body(responses);

    }

    @ApiOperation(value = "Soft Delete News By ID", notes = "Allows Business to delete News by ID")
    @ApiResponses({@ApiResponse(code = 204, message = "News soft deleted!"),
            @ApiResponse(code = 404, message = "The inserted ID does not belong to a News")})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable @Valid @NotNull @NotBlank @ApiParam(
            name = "id",
            type = "Long",
            value = "id of the News requested",
            example = "1",
            required = true) Long id,
                                           @RequestHeader(name = "Authorization") String token) throws IOException, RuntimeExceptionCustom {

        newsService.delete(id, token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

//    @GetMapping
//    public ResponseEntity <PaginationResponse> getNewsByFilters (@RequestParam (required = false) @ApiParam(name = "businessId",
//                                                                    type = "Long",
//                                                                    value = "id the business required",
//                                                                    example = "1") Long businessId,
//                                                             @RequestParam(value = "page", required = false)@ApiParam(
//                                                                     name = "page",
//                                                                     type = "Integer",
//                                                                     value = "page number I want to see",
//                                                                     example = "1")Optional<Integer> page,
//                                                             @RequestParam(value = "size", required = false)@ApiParam(
//                                                                     name = "size",
//                                                                     type = "Integer",
//                                                                     value = "number of items per page",
//                                                                     example = "3") Optional<Integer> size) {
//
//        return new ResponseEntity<PaginationResponse>(newsService.getByFilters(city, page, size),
//                HttpStatus.OK);
//
//    }

}


//    @ApiOperation(value = "Get News By ID", notes = "Returns all details of news by ID")
//    @ApiResponses({ @ApiResponse(code = 200, message = "Return the requested news"),
//                    @ApiResponse(code = 404, message = "The inserted ID does not belong to a news")})
//    @GetMapping("{id}")
//    public ResponseEntity<NewsResponse> getById (@PathVariable @Valid @NotNull @NotBlank Long id){
//
//        NewsResponse response = newsService.getById(id);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//
//    }


//    @GetMapping
//    @ApiOperation(value = "Get All News by Page" , notes = "Returns All News ")
//    @ApiResponses({ @ApiResponse(code = 200, message = "Return All news created"),
//                    @ApiResponse(code = 400, message = "Bad Request")})
//    public ResponseEntity<PaginationResponse> getPage (@RequestParam(value = "page", required = false) Optional<Integer> page,
//                                                       @RequestParam(value = "size", required = false) Optional<Integer> size) {
//
//        PaginationResponse responses = newsService.getPage(page, size);
//        return new ResponseEntity<>(responses, HttpStatus.OK);
//
//    }
