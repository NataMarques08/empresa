package com.nata.empresa.controller;

import com.nata.empresa.data.dto.EmployeeDTO;
import com.nata.empresa.docs.EmployeeControllerDOCS;
import com.nata.empresa.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
@Tag(name = "Employee", description = "Endpoints for Mapping employee")
public class EmployeeController implements EmployeeControllerDOCS {

    @Autowired
    private EmployeeService service;

    @PostMapping(
            value = "/save",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    @Override
    public ResponseEntity<EmployeeDTO> create(@RequestBody @Valid EmployeeDTO employeeDTO){
        var createEmployee = service.create(employeeDTO);
        addHateoasLink(null, createEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createEmployee);
    }

    @GetMapping(
                value = "/getAll",
                produces = MediaType.APPLICATION_JSON_VALUE
        )
        @Override
        public ResponseEntity<Page<EmployeeDTO>> findAll(
            @RequestParam(value = "page",defaultValue = "0") Integer page, 
            @RequestParam(value = "size",defaultValue = "12") Integer size,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy
        ){
            Sort sort = direction.equalsIgnoreCase("DESC") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
            Pageable pageable = PageRequest.of(page, size,sort);
            Page<EmployeeDTO> employees = service.findAll(pageable);
            for (EmployeeDTO employeeDTO : employees) {
                addHateoasLink(null, employeeDTO);
            }
            return ResponseEntity.status(HttpStatus.OK).body(employees);
        }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<EmployeeDTO> findById(@PathVariable("id") Long id){
        EmployeeDTO employeeDTO = service.findById(id);
        addHateoasLink(id, employeeDTO);
        return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);
    }

    @PutMapping(
            value = "/update/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    @Override
    public ResponseEntity<EmployeeDTO> update(@PathVariable("id") Long id,
                                              @RequestBody @Valid EmployeeDTO employeeDTO){
        service.findById(id);
        var updateEmployee = service.update(id, employeeDTO);
        addHateoasLink(id, updateEmployee);
        return ResponseEntity.status(HttpStatus.OK).body(updateEmployee);
    }

    @DeleteMapping("/delete/{id}")
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
    @Transactional
    @Override
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
