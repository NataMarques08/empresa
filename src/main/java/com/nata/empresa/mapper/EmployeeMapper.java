package com.nata.empresa.mapper;

import com.nata.empresa.data.dto.EmployeeDTO;
import com.nata.empresa.model.Employee;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

     EmployeeDTO convertDtoToEntity(Employee employee);
     List<EmployeeDTO> convertEntityListToDTO(List<Employee> person);
}
