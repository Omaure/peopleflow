package com.example.peopleflow.core.application.employee.usacase.impl;

import com.example.peopleflow.core.application.employee.UseCase;
import com.example.peopleflow.core.application.employee.converter.EmployeeResponseModelConverter;
import com.example.peopleflow.core.application.employee.repository.EmployeeRepository;
import com.example.peopleflow.core.application.employee.usacase.EmployeeUseCase;
import com.example.peopleflow.core.application.employee.usacase.request.EmployeeCreationRequestModel;
import com.example.peopleflow.core.application.employee.usacase.request.EmployeeStatusRequestModel;
import com.example.peopleflow.core.application.employee.usacase.response.EmployeeResponseModel;
import com.example.peopleflow.core.domain.employee.EmployeeFactory;
import com.google.common.base.Preconditions;
import java.text.MessageFormat;
import java.util.Objects;
import javax.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class EmployeeUseCaseImpl implements EmployeeUseCase {

    final String errorMessage = "Employee request model should not be null.";
    private static final String ERROR_EMPLOYEE_NOT_FOUND_BY_ID = "Employee not found by ID [{0}].";

    private final EmployeeRepository employeeRepository;
    private final EmployeeResponseModelConverter employeeResponseModelConverter;

    @Transactional
    @Override
    public EmployeeResponseModel createEmployee(final EmployeeCreationRequestModel employeeRequestModel) {

        Preconditions.checkArgument(Objects.nonNull(employeeRequestModel), errorMessage);

        final var newEmployee = EmployeeFactory.create(employeeRequestModel);

        final var employee = employeeRepository.upsert(newEmployee);

        return employeeResponseModelConverter.convert(employee);
    }

    @Override
    public EmployeeResponseModel changeEmployeeStatus(final EmployeeStatusRequestModel employeeStatusRequestModel) {

        Preconditions.checkArgument(Objects.nonNull(employeeStatusRequestModel), errorMessage);

        final var employee = employeeRepository.findById(employeeStatusRequestModel.getId())
            .orElseThrow(() -> new EntityNotFoundException(
                MessageFormat.format(ERROR_EMPLOYEE_NOT_FOUND_BY_ID, employeeStatusRequestModel.getId())));

        employee.updateStatus(employee, employeeStatusRequestModel.getStatus());

        employeeRepository.upsert(employee);

        return employeeResponseModelConverter.convert(employee);
    }

}
