package com.nata.empresa.services;


import com.nata.empresa.data.dto.EmployeeDTO;
import com.nata.empresa.mapper.EmployeeMapper;
import com.nata.empresa.model.Employee;
import com.nata.empresa.repository.RepositoryEmployee;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private RepositoryEmployee repository;

    @Autowired
    EmployeeMapper mapper;


    public List<EmployeeDTO> findAll(){
        return mapper.convertEntityListToDTO(repository.findAll());
    }

    public EmployeeDTO findById(Long id){
        var employee = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee with id " + id + " not found"));
        return mapper.convertEntityToDTO(employee);
    }

    public EmployeeDTO create(EmployeeDTO employeeDTO) {
        Employee employee = mapper.convertDtoToEntity(employeeDTO);
        Employee saveEmployee = repository.save(employee);
        return  mapper.convertEntityToDTO(saveEmployee);
    }


}
