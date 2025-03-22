package com.nata.empresa.controller;

import com.nata.empresa.data.dto.EmployeeDTO;
import com.nata.empresa.services.EmployeeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping("/save")
    @Transactional
    public ResponseEntity<EmployeeDTO> create(@RequestBody @Valid EmployeeDTO employeeDTO){
        System.out.println("NATÃ SEE THE DTO INPUT HERE ------------- "+employeeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(employeeDTO));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeeDTO>> findAll(){
        var results = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

}
