package com.nata.empresa.controller;

import com.nata.empresa.data.dto.EmployeeDTO;
import com.nata.empresa.model.Employee;
import com.nata.empresa.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/v1")
@Tag(name = "Employee", description = "Endpoints for Mapping employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping(
            value = "/save",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
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
    @Transactional
    public ResponseEntity<EmployeeDTO> create(@RequestBody @Valid EmployeeDTO employeeDTO){
        var createEmployee = service.create(employeeDTO);
        addHateoasLink(null, createEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createEmployee);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeeDTO>> findAll(){
        var employees = service.findAll();
        for (EmployeeDTO employeeDTO : employees) {
            addHateoasLink(null, employeeDTO);
        }
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> findById(@PathVariable("id") Long id){
        EmployeeDTO employeeDTO = service.findById(id);
        addHateoasLink(id, employeeDTO);
        return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);
    }



    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity<EmployeeDTO> update(@PathVariable("id") Long id,
                                              @RequestBody @Valid EmployeeDTO employeeDTO){
        var findEmployee = service.findById(id);
        var updateEmployee = service.update(id, employeeDTO);
        addHateoasLink(id, updateEmployee);
        return ResponseEntity.status(HttpStatus.OK).body(updateEmployee);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }



    private void addHateoasLink(Long id, EmployeeDTO employeeDTO) {
        employeeDTO.add(linkTo(methodOn(EmployeeController.class).findById(id)).withSelfRel().withType("GET"));
        employeeDTO.add(linkTo(methodOn(EmployeeController.class).delete(id)).withRel("delete").withType("DELETE"));
        employeeDTO.add(linkTo(methodOn(EmployeeController.class).update(id, employeeDTO)).withRel("update").withType("PUT"));
        employeeDTO.add(linkTo(methodOn(EmployeeController.class).findAll()).withRel("findAll").withType("GET"));
        employeeDTO.add(linkTo(methodOn(EmployeeController.class).create(employeeDTO)).withRel("create").withType("POST"));
    }


}
