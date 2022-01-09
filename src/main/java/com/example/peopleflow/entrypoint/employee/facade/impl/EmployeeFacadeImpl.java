package com.example.peopleflow.entrypoint.employee.facade.impl;

import com.example.peopleflow.core.application.employee.usacase.EmployeeUseCase;
import com.example.peopleflow.core.application.employee.usacase.request.EmployeeCreationRequestModel;
import com.example.peopleflow.core.application.employee.usacase.request.EmployeeStatusRequestModel;
import com.example.peopleflow.entrypoint.employee.controller.request.EmployeeRequestDTO;
import com.example.peopleflow.entrypoint.employee.controller.request.EmployeeStatusRequestDTO;
import com.example.peopleflow.entrypoint.employee.controller.response.EmployeeResponseDTO;
import com.example.peopleflow.entrypoint.employee.facade.EmployeeFacade;
import com.example.peopleflow.entrypoint.employee.facade.converters.EmployeeResponseDTOConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class EmployeeFacadeImpl implements EmployeeFacade {

    private final EmployeeUseCase employeeUseCase;
    private final EmployeeResponseDTOConverter employeeResponseDTOConverter;

    @Override
    public EmployeeResponseDTO createEmployee(final EmployeeRequestDTO employeeRequestDTO) {

        final var employeeCreationRequestModel = constructEmployeeCreationRequestModel(employeeRequestDTO);

        final var newEmployee = employeeUseCase.createEmployee(employeeCreationRequestModel);

        return employeeResponseDTOConverter.convert(newEmployee);

    }

    @Override
    public EmployeeResponseDTO changeEmployeeStatus(final EmployeeStatusRequestDTO employeeStatusRequestDTO, final String id) {

        final var employeeStatusRequestModel = constructEmployeeStatusRequestModel(employeeStatusRequestDTO, id);

        final var updatedEmployee = employeeUseCase.changeEmployeeStatus(employeeStatusRequestModel);

        return employeeResponseDTOConverter.convert(updatedEmployee);

    }

    private EmployeeStatusRequestModel constructEmployeeStatusRequestModel(final EmployeeStatusRequestDTO employeeStatusRequestDTO, final String id) {

        return EmployeeStatusRequestModel
            .builder()
            .status(employeeStatusRequestDTO.getStatus())
            .id(Long.parseLong(id))
            .build();
    }

    private EmployeeCreationRequestModel constructEmployeeCreationRequestModel(final EmployeeRequestDTO employeeRequestDTO) {

        return EmployeeCreationRequestModel
            .builder()
            .name(employeeRequestDTO.getName())
            .age(employeeRequestDTO.getAge())
            .email(employeeRequestDTO.getEmail())
            .phoneNumber(employeeRequestDTO.getPhoneNumber())
            .build();
    }

}
