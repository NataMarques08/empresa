package com.nata.empresa.mapper;

import com.nata.empresa.data.dto.EmployeeDTO;
import com.nata.empresa.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

     @Mapping(target = "id", ignore = true)
     Employee convertDtoToEntity(EmployeeDTO employee);
     EmployeeDTO convertEntityToDTO(Employee employee);
     List<EmployeeDTO> convertEntityListToDTO(List<Employee> person);
}
