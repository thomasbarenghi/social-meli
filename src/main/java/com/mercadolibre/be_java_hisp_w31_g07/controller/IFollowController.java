package com.mercadolibre.be_java_hisp_w31_g07.controller;

import com.mercadolibre.be_java_hisp_w31_g07.dto.request.BuyerDto;
import com.mercadolibre.be_java_hisp_w31_g07.dto.request.SellerDto;
import com.mercadolibre.be_java_hisp_w31_g07.dto.response.ErrorResponseDto;
import com.mercadolibre.be_java_hisp_w31_g07.dto.response.SellerFollowersCountResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Seller", description = "Operations related to sellers")
public interface IFollowController {

    @Operation(summary = "Get followers - [REQ - 3]", description = "Returns a list of the buyers that follow the given seller.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Bad Request: seller not found.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/users/{userId}/followers/list")
    ResponseEntity<SellerDto> getFollowers(
            @Parameter(description = "Seller id", required = true) @PathVariable UUID userId);

    @Operation(summary = "Follow a seller - [REQ - 1]", description = "Allows a buyer to follow a seller.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Bad Request: invalid request.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    ResponseEntity<Void> followSeller(@PathVariable UUID userId, @PathVariable UUID userIdToFollow);

    @Operation(summary = "Unfollow a seller - [REQ - 7]", description = "Allows a buyer to unfollow a seller.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Bad Request: invalid request.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @PutMapping("/users/{userId}/unfollow/{userIdToUnfollow}")
    ResponseEntity<Void> unfollowSeller(@PathVariable UUID userId, @PathVariable UUID userIdToUnfollow);

    @Operation(summary = "Get followers count - [REQ - 2]", description = "Returns the number of buyers that follow the given seller.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Bad Request: seller not found.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class)))
    })
    @GetMapping("/users/{userId}/followers/count")
    ResponseEntity<SellerFollowersCountResponseDto> getFollowersCount(@PathVariable UUID userId);
    
    @GetMapping("/{sellerId}/followers")
    ResponseEntity<SellerDto> getSortedFollowers(@PathVariable UUID sellerId, @RequestParam String order);

    @Operation(summary = "Get followings - [REQ - 4]", description = "Returns a list of the sellers that the given buyer is following.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Bad Request: buyer not found.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class)))

    })
    @GetMapping("/users/{userId}/followed/list")
    ResponseEntity<BuyerDto> getFollowed(
            @Parameter(description = "Buyer id", required = true) @PathVariable UUID userId);
}