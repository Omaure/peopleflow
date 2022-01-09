package com.example.peopleflow.infrastructure.database.employee.repository;

import com.example.peopleflow.core.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeJPARepository extends JpaRepository<Employee, Long> {

}
