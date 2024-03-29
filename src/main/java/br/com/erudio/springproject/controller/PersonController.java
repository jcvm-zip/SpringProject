package br.com.erudio.springproject.controller;

import br.com.erudio.springproject.data.vo.v1.PersonVO;
import br.com.erudio.springproject.service.PersonServices;
import br.com.erudio.springproject.util.MediaType;
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
@RequestMapping("api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {

    @Autowired
    private PersonServices services;


    @GetMapping(
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds all People", description = "Finds all People",
            tags = {"People"},
            responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = {
                        @Content(
                                mediaType = "application/json",
                                array = @ArraySchema( schema = @Schema(implementation = PersonVO.class))
                        )
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public List<PersonVO> findAll() {
        return services.findall();
    }

    @GetMapping(value="/{id}")
    @Operation(summary = "Finds a People", description = "Finds a People",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))
                    ),

                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public PersonVO findById(@PathVariable("id") Long id) throws Exception {
        return services.findById(id);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Create a People", description = "Create a People",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))
                    ),

                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public PersonVO create(@RequestBody PersonVO person) throws Exception {
        return services.create(person);
    }

    @PutMapping(value="/{id}", consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Update a People", description = "Update a People",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))
                    ),

                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public PersonVO update(@RequestBody PersonVO person) throws Exception {
        return services.create(person);
    }

    @DeleteMapping(value="/{id}")
    @Operation(summary = "Delete a People", description = "Delete a People", tags = {"People"}, responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content(schema = @Schema(implementation = PersonVO.class))),

                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)}
    )
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
        services.delete(id);
        return ResponseEntity.noContent().build();
    }

}