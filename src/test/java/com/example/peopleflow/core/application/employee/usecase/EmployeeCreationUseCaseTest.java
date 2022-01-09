package com.example.peopleflow.core.application.employee.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.peopleflow.core.application.employee.converter.EmployeeResponseModelConverter;
import com.example.peopleflow.core.application.employee.repository.EmployeeRepository;
import com.example.peopleflow.core.application.employee.usacase.EmployeeUseCase;
import com.example.peopleflow.core.application.employee.usacase.impl.EmployeeUseCaseImpl;
import com.example.peopleflow.core.application.employee.usacase.request.EmployeeCreationRequestModel;
import com.example.peopleflow.core.application.employee.usacase.response.EmployeeResponseModel;
import com.example.peopleflow.core.domain.employee.Employee;
import com.example.peopleflow.core.domain.employee.EmployeeFactory;
import com.example.peopleflow.core.domain.employee.EmployeeStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.internal.verification.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = {EmployeeUseCaseImpl.class})
public class EmployeeCreationUseCaseTest {

    @Autowired
    private EmployeeUseCase employeeUseCase;

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private EmployeeResponseModelConverter employeeResponseModelConverter;

    @Test
    @DisplayName("Creating an employee with valid payload return employee status and id")
    public void employeeCreation_withValidRequest_createsEmployeeWithAddedStatus() {

        //GIVEN
        final var expectedId = 1;
        final var expectedStatus = EmployeeStatus.ADDED.toString();

        final var employeeCreationRequestModel = constructRequestModel();

        final var employeeResponseModel = EmployeeResponseModel.builder()
            .id(1L).status(EmployeeStatus.ADDED.toString()).build();

        final var employee = EmployeeFactory.create(employeeCreationRequestModel);

        final ArgumentCaptor<Employee> employeeModelCapture = ArgumentCaptor.forClass(Employee.class);
        final ArgumentCaptor<Employee> employeeConverterCapture = ArgumentCaptor.forClass(Employee.class);

        when(employeeRepository.upsert(any())).thenReturn(employee);
        when(employeeResponseModelConverter.convert(any())).thenReturn(employeeResponseModel);

        // WHEN
        final var response = employeeUseCase.createEmployee(employeeCreationRequestModel);

        // THEN
        verify(employeeRepository, new Times(1)).upsert(employeeModelCapture.capture());
        verify(employeeResponseModelConverter, new Times(1)).convert(employeeConverterCapture.capture());

        final var employeeOnRepo = employeeModelCapture.getValue();
        final var employeeOnConverter = employeeConverterCapture.getValue();

        assertThat(employeeOnRepo.getStatus()).isEqualTo(EmployeeStatus.ADDED);
        assertThat(employeeOnRepo.getAge()).isEqualTo(employeeCreationRequestModel.getAge());
        assertThat(employeeOnRepo.getName()).isEqualTo(employeeCreationRequestModel.getName());
        assertThat(employeeOnRepo.getPhoneNumber()).isEqualTo(employeeCreationRequestModel.getPhoneNumber());
        assertThat(employeeOnRepo.getEmail()).isEqualTo(employeeCreationRequestModel.getEmail());

        assertThat(employeeOnConverter.getStatus()).isEqualTo(EmployeeStatus.ADDED);
        assertThat(employeeOnRepo.getAge()).isEqualTo(employeeOnRepo.getAge());
        assertThat(employeeOnRepo.getName()).isEqualTo(employeeOnRepo.getName());
        assertThat(employeeOnRepo.getPhoneNumber()).isEqualTo(employeeOnRepo.getPhoneNumber());
        assertThat(employeeOnRepo.getEmail()).isEqualTo(employeeOnRepo.getEmail());

        assertThat(response.getId()).isEqualTo(expectedId);
        assertThat(response.getStatus()).isEqualTo(expectedStatus);

    }

    private EmployeeCreationRequestModel constructRequestModel() {

        return EmployeeCreationRequestModel
            .builder()
            .email("test@mail")
            .age("11")
            .phoneNumber("01111")
            .name("omar")
            .build();
    }

}
