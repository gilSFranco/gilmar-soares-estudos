package com.compasso.rest_spring.controller;

import com.compasso.rest_spring.data.vo.v1.BookVO;
import com.compasso.rest_spring.services.BookServices;
import com.compasso.rest_spring.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@Tag(name = "Books", description = "End-points for managing books")
public class BookController {

    @Autowired
    private BookServices bookServices;

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}
    )
    @Operation(
            summary = "Finds all Books",
            description = "Finds all Books",
            tags = {
                    "Books"
            },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = BookVO.class)
                                    )
                            )
                    ),
                    @ApiResponse(
                            description = "Bad request",
                            responseCode = "400",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    public List<BookVO> findAll() {
        return bookServices.findAll();
    }


    @GetMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}
    )
    @Operation(
            summary = "Finds a Book",
            description = "Finds a Book",
            tags = {
                    "Books"
            },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BookVO.class)
                            )
                    ),
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Bad request",
                            responseCode = "400",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    public BookVO findById(@PathVariable(value = "id") Long id) {
        return bookServices.findById(id);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}
    )
    @Operation(
            summary = "Adds a new Book",
            description = "Adds a new Book by passing in a JSON, XML or YML representation of a Book",
            tags = {
                    "Books"
            },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BookVO.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Bad request",
                            responseCode = "400",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    public BookVO create(@RequestBody BookVO book) {
        return bookServices.create(book);
    }

    @PutMapping(
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}
    )
    @Operation(
            summary = "Updates a Book",
            description = "Updates a Book by passing in a JSON, XML or YML representation of a Book",
            tags = {
                    "Books"
            },
            responses = {
                    @ApiResponse(
                            description = "Updated",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BookVO.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Bad request",
                            responseCode = "400",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    public BookVO update(@RequestBody BookVO book) {
        return bookServices.update(book);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(
            summary = "Deletes a Book",
            description = "Deletes a Book by passing in a JSON, XML or YML representation of a Book",
            tags = {
                    "Books"
            },
            responses = {
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Bad request",
                            responseCode = "400",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Unauthorized",
                            responseCode = "401",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Not Found",
                            responseCode = "404",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content
                    )
            }
    )
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        bookServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
