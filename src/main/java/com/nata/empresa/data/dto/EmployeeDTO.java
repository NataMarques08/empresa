package com.nata.empresa.data.dto;

import com.nata.empresa.model.types.Gender;
import com.nata.empresa.model.types.Occupation;

import java.util.Date;
import java.util.UUID;

public record EmployeeDTO(
        String name,
        String lastname,
        Occupation occupation,
        Double salary,
        Date admission,
        Date termination,
        Gender gender
) {
}
