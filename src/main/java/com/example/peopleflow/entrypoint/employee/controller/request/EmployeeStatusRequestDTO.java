package com.example.peopleflow.entrypoint.employee.controller.request;

import com.example.peopleflow.core.domain.employee.EmployeeStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeStatusRequestDTO {

    private EmployeeStatus status;

}
