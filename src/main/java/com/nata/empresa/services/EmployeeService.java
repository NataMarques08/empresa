package com.nata.empresa.services;


import com.nata.empresa.data.dto.EmployeeDTO;
import com.nata.empresa.mapper.EmployeeMapper;
import com.nata.empresa.model.Employee;
import com.nata.empresa.repository.RepositoryEmployee;
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

    public EmployeeDTO create(EmployeeDTO employee) {
        return mapper.convertDtoToEntity(repository.save(new Employee()));
    }


}
