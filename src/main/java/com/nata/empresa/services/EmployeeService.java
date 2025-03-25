package com.nata.empresa.services;


import com.nata.empresa.data.dto.EmployeeDTO;
import com.nata.empresa.mapper.EmployeeMapper;
import com.nata.empresa.model.Employee;
import com.nata.empresa.repository.RepositoryEmployee;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private RepositoryEmployee repository;

    @Autowired
    EmployeeMapper mapper;


    public Page<EmployeeDTO> findAll(Pageable pageable) {
            return repository.findAll(pageable).map(mapper::convertEntityToDTO);
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

    public EmployeeDTO update(Long id, EmployeeDTO employeeDTO) {
        Optional<Employee> entity = repository.findById(id);
        if (entity.isEmpty()) {
            throw new RuntimeException("Employee not found with ID: " + id); // Or use a custom exception
        }

        Employee employee = entity.get();
        employee.setName(employeeDTO.getName());
        employee.setLastname(employeeDTO.getLastname());
        employee.setOccupation(employeeDTO.getOccupation());
        employee.setSalary(employeeDTO.getSalary());
        employee.setAdmission(employeeDTO.getAdmission());
        employee.setTermination(employeeDTO.getTermination());
        employee.setGender(employeeDTO.getGender());
        return mapper.convertEntityToDTO(repository.save(employee));
    }

    public void delete(Long id) {
        var entity = repository.findById(id);
        repository.delete(entity.orElseThrow(() -> new EntityNotFoundException("Employee with id " + id + " not found")));
    }


}
