package com.nata.empresa.docs;

import com.nata.empresa.controller.EmployeeController;
import com.nata.empresa.data.dto.EmployeeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public interface EmployeeControllerDOCS {

    @Operation(
            summary = "Create a new employee",
            description = "This method saves a new employee and return the employee",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
            }
    )

    ResponseEntity<EmployeeDTO> create(@RequestBody @Valid EmployeeDTO employeeDTO);


    @Operation(
            summary = "List all employees in the company",
            description = "This method lists all employees and returns a list of employees.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EmployeeDTO.class)))),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
            }
    )
    ResponseEntity<Page<EmployeeDTO>> findAll(
        @RequestParam(value = "page",defaultValue = "0") Integer page, 
        @RequestParam(value = "size",defaultValue = "12") Integer size 
    );


    @Operation(
            summary = "Show employee in the company by id",
            description = "This method show employee and returns employee data by ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
            }
    )
    ResponseEntity<EmployeeDTO> findById(@PathVariable("id") Long id);


    @Operation(
            summary = "Update an employee",
            description = "This method update an employee by ID and return the employee",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
            }
    )

    ResponseEntity<EmployeeDTO> update(@PathVariable("id") Long id,
                                       @RequestBody @Valid EmployeeDTO employeeDTO);


    @Operation(
            summary = "Delete employee",
            description = "This method just delete an employee and return nothing",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
            }
    )

    ResponseEntity<Void> delete(@PathVariable("id") Long id);

    default void addHateoasLink(Long id, EmployeeDTO employeeDTO) {
        employeeDTO.add(linkTo(methodOn(EmployeeController.class).findById(id)).withSelfRel().withType("GET"));
        employeeDTO.add(linkTo(methodOn(EmployeeController.class).delete(id)).withRel("delete").withType("DELETE"));
        employeeDTO.add(linkTo(methodOn(EmployeeController.class).update(id, employeeDTO)).withRel("update").withType("PUT"));
        employeeDTO.add(linkTo(methodOn(EmployeeController.class).findAll()).withRel("findAll").withType("GET"));
        employeeDTO.add(linkTo(methodOn(EmployeeController.class).create(employeeDTO)).withRel("create").withType("POST"));
    }
}
