package com.nata.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nata.empresa.model.employee.Employee;



public interface RepositoryEmployee extends JpaRepository<Employee, Long> {
}
