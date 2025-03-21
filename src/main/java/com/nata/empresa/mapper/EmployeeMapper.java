package com.nata.empresa.mapper;

import com.nata.empresa.data.dto.EmployeeDTO;
import com.nata.empresa.model.Employee;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

@Mapper
public interface EmployeeMapper extends Converter<Employee, EmployeeDTO> {
}
