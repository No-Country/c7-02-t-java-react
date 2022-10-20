package com.c702t.Cerveza.controller;

import com.c702t.Cerveza.models.entity.CommentEntity;
import com.c702t.Cerveza.models.request.CommentRequest;
import com.c702t.Cerveza.models.response.CommentResponse;
import com.c702t.Cerveza.service.CommentService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("comment")
@Api(description ="Comment CRUD" , tags = "Comment")
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/{reviewID}")
    @ApiOperation(value = "Comment a review", notes = "Comment a review")
    @ApiResponses({
            @ApiResponse(code = 201, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "reviewID not found")
    })
    public ResponseEntity<CommentResponse> create (
            @RequestHeader(name = "Authorization") String token,
            @PathVariable @NotNull @ApiParam(
                    name = "reviewID",
                    type = "Long",
                    value = "ID of the review",
                    example = "1",
                    required = true)
            Long reviewID,
            @Valid @RequestBody CommentRequest commentRequest) {
        //return ResponseEntity.status(HttpStatus.CREATED).body(commentService.create(token, reviewID, commentRequest));
        CommentResponse commentResponse = commentService.create(token, reviewID, commentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponse);
    }

    @GetMapping("/search/byReviewID/{reviewID}")
    @ApiOperation(value = "Get Comments of reviewID", notes = "Get Comments of reviewID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "The reviewID does not belong to a review")
    })
    public ResponseEntity<List<CommentResponse>> readByReviewID (
            @PathVariable @NotNull @ApiParam(
                    name = "reviewID",
                    type = "Long",
                    value = "ID of the review",
                    example = "1",
                    required = true)
            Long reviewID)
            throws IOException {

        List<CommentResponse> response = commentService.readByReviewID(reviewID);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
