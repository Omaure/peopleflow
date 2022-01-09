package com.example.peopleflow.core.domain.employee;

import com.example.peopleflow.core.application.employee.usacase.request.EmployeeCreationRequestModel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmployeeFactory {

    public static Employee create(final EmployeeCreationRequestModel employeeCreationRequestModel) {

        return Employee
            .builder()
            .status(EmployeeStatus.ADDED)
            .email(employeeCreationRequestModel.getEmail())
            .name(employeeCreationRequestModel.getName())
            .phoneNumber(employeeCreationRequestModel.getPhoneNumber())
            .age(employeeCreationRequestModel.getAge())
            .build();
    }

}
