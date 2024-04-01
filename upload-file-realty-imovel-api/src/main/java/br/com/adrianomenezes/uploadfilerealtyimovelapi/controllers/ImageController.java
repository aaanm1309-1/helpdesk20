package br.com.adrianomenezes.uploadfilerealtyimovelapi.controllers;


import br.com.adrianomenezes.models.exceptions.StandardError;
import br.com.adrianomenezes.models.requests.CreateImageRequest;
import br.com.adrianomenezes.models.requests.CreateUserRequest;
import br.com.adrianomenezes.models.requests.UpdateUserRequest;
import br.com.adrianomenezes.models.responses.ImageResponse;
import br.com.adrianomenezes.models.responses.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "UserController", description = "Controller responsible for user operations")
@RequestMapping("/api/v1/files")
public interface ImageController {

    @Operation(summary = "Find image by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image found"),
            @ApiResponse(responseCode = "404",
                    description = "Image not found",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class))
            )
    })
    @GetMapping("/{id}")
    ResponseEntity<ImageResponse> findById(
            @Parameter(description = "Image id", required = true, example = "65bc391ab0973863d7ff8cab")
            @PathVariable(name = "id") final Long id
    );

    @Operation(summary = "Find image by fileName")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image found"),
            @ApiResponse(responseCode = "404",
                    description = "Image not found",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class))
            )
    })
    @GetMapping("/byname/{fileName}")
    ResponseEntity<byte[]> findByFileName(
            @Parameter(description = "fileName", required = true, example = "dede.jpg")
            @PathVariable(name = "fileName") final String fileName
    ) throws FileNotFoundException, SQLException;

    @Operation(summary = "Save new image")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Image created"),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class))
            )
    })
    @PostMapping("/upload")
    ResponseEntity<ImageResponse> save(
            @Valid
            @RequestBody final MultipartFile file
    ) throws IOException, SQLException;


    @Operation(summary = "Find all images")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image found",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = StandardError.class)))
            ),
            @ApiResponse(responseCode = "404",
                    description = "Image not found",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class))
            ),
            @ApiResponse(responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class))
            )
    })
    @GetMapping
    ResponseEntity<List<ImageResponse>> findAll(
    );

//    @Operation(summary = "Update image")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Image update",
//                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
//                            schema = @Schema(implementation = UserResponse.class))),
//            @ApiResponse(responseCode = "400",
//                    description = "Bad request",
//                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
//                            schema = @Schema(implementation = StandardError.class))
//            ),
//            @ApiResponse(responseCode = "404",
//                    description = "Image not found",
//                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
//                            schema = @Schema(implementation = StandardError.class))
//            ),
//            @ApiResponse(responseCode = "500",
//                    description = "Internal server error",
//                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
//                            schema = @Schema(implementation = StandardError.class))
//            )
//    })
//    @PutMapping("/{id}")
//    ResponseEntity<UserResponse> update(
//            @Parameter(description = "Image id", required = true, example = "65bc391ab0973863d7ff8cab")
//            @PathVariable(name = "id") final String id,
//            @Valid
//            @RequestBody final UpdateUserRequest updateUserRequest
//    );
}
