package com.nata.empresa.repository;

import com.nata.empresa.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RepositoryEmployee extends JpaRepository<Employee, Long> {
}
