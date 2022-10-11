package com.c702t.Cerveza.controller;

import com.c702t.Cerveza.models.request.ReviewRequest;
import com.c702t.Cerveza.models.request.ReviewUpdateRequest;
import com.c702t.Cerveza.models.response.ReviewResponse;
import com.c702t.Cerveza.service.ReviewService;
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
@RequestMapping("review")
@Api(description ="Review CRUD" , tags = "Review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @PostMapping("{businessID}")
    @ApiOperation(value = "Create review", notes = "Create a review")
    @ApiResponses({@ApiResponse(code = 201, message = "Review created")})
    public ResponseEntity<ReviewResponse> create (
            @RequestHeader(name = "Authorization") String token,
            @PathVariable @NotNull @ApiParam(
                    name = "businessID",
                    type = "Long",
                    value = "ID of the business",
                    example = "1",
                    required = true)
            Long businessID,
            @Valid @RequestBody ReviewRequest reviewRequest) {
        ReviewResponse reviewResponse = this.reviewService.create(token, businessID, reviewRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewResponse);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Get review by id", notes = "Returns all details of review by ID")
    @ApiResponses({@ApiResponse(code = 200, message = "Return the requested review"),
            @ApiResponse(code = 404, message = "id does not belong to a review")})
    public ResponseEntity<ReviewResponse> read (
            @PathVariable @NotNull @ApiParam(
                name = "id",
                type = "Long",
                value = "ID of the review",
                example = "1",
                required = true)
            Long id) throws IOException {

        ReviewResponse response = this.reviewService.read(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("business")
    @ApiOperation(value = "Get reviews of businessID", notes = "Get reviews of businessID")
    @ApiResponses({@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")})
    public ResponseEntity<List<ReviewResponse>> readAllByBusinessID (
            @RequestParam(value = "businessID") Long businessID
            ) throws IOException {
        List<ReviewResponse> response = this.reviewService.readAllByBusinessID(businessID);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("user")
    @ApiOperation(value = "Get reviews of userID", notes = "Get reviews of userID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public ResponseEntity<List<ReviewResponse>> readAllByUserID (
            @RequestParam(value = "userID") Long userID
    ) throws IOException {
        List<ReviewResponse> response = this.reviewService.readAllByUserID(userID);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Update review By id", notes = "Update review By id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Review updated"),
            @ApiResponse(code = 404, message = "The id does not belong to a review")
    })
    public ResponseEntity<ReviewResponse> update (
            @PathVariable @NotNull @ApiParam(
                    name = "id",
                    type = "Long",
                    value = "ID of the review",
                    example = "1",
                    required = true)
            Long id,
            @RequestHeader(name = "Authorization") String token,
            @Valid @RequestBody ReviewUpdateRequest request)
            throws IOException {

        ReviewResponse response = this.reviewService.update(id, token, request);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete review by id", notes = "Delete review by id")
    @ApiResponses({@ApiResponse(code = 204, message = "Review deleted"),
            @ApiResponse(code = 404, message = "The id does not belong to a review")})
    public ResponseEntity<Void> delete (
            @PathVariable @NotNull @ApiParam(
                name = "id",
                type = "Long",
                value = "ID of the review",
                example = "1",
                required = true)
            Long id,
            @RequestHeader(name = "Authorization") String token){
        this.reviewService.delete(id, token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    /* Like System
    @PostMapping("/{id}/like")
    @ApiOperation(value = "Like a review", notes = "Like a review")
    @ApiResponses({
            @ApiResponse(code = 201, message = "OK"),
            @ApiResponse(code = 404, message = "The id does not belong to a review")
    })
    public ResponseEntity<ReviewResponse> likeReview (
            @RequestHeader(name = "Authorization") String token,
            @PathVariable @NotNull @ApiParam(
                    name = "id",
                    type = "Long",
                    value = "ID of the review",
                    example = "1",
                    required = true)
            Long reviewID) {
        ReviewResponse reviewResponse = this.reviewService.likeReview(token, reviewID);
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewResponse);
    }
     */
}
