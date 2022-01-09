package com.example.peopleflow.infrastructure.database.employee.repository.impl;

import com.example.peopleflow.core.application.employee.repository.EmployeeRepository;
import com.example.peopleflow.core.domain.employee.Employee;
import com.example.peopleflow.infrastructure.database.employee.repository.EmployeeJPARepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EmployeeJPARepository employeeJPARepository;

    @Override
    public Employee upsert(final Employee employee) {

        return employeeJPARepository.save(employee);
    }

    @Override
    public Optional<Employee> findById(final Long id) {

        return employeeJPARepository.findById(id);

    }

}
