package com.example.peopleflow.core.application.employee.repository;

import com.example.peopleflow.core.domain.employee.Employee;
import java.util.Optional;

public interface EmployeeRepository {

    Employee upsert(Employee employee);

    Optional<Employee> findById(Long id);

}
