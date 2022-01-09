package com.example.peopleflow.core.domain.employee;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.peopleflow.core.application.employee.usacase.request.EmployeeCreationRequestModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EmployeeFactoryTest {

    @Test
    @DisplayName("Employee domain model is created successfully")
    public void create_withValidEmployeeRequestModel_returnEmployeeModel() {

        // GIVEN
        final EmployeeCreationRequestModel employeeCreationRequestModel = EmployeeCreationRequestModel
            .builder()
            .email("test@mail")
            .age("11")
            .phoneNumber("01111")
            .name("omar")
            .build();

        // WHEN
        final Employee employee = EmployeeFactory.create(employeeCreationRequestModel);

        // THEN
        assertThat(employee).isNotNull();
        assertThat(employee.getName()).isEqualTo(employeeCreationRequestModel.getName());
        assertThat(employee.getPhoneNumber()).isEqualTo(employeeCreationRequestModel.getPhoneNumber());
        assertThat(employee.getEmail()).isEqualTo(employeeCreationRequestModel.getEmail());
        assertThat(employee.getAge()).isEqualTo(employeeCreationRequestModel.getAge());
        assertThat(employee.getStatus()).isEqualTo(EmployeeStatus.ADDED);
    }

}
