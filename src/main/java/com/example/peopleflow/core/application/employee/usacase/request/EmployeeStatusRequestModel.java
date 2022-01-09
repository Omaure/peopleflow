package com.example.peopleflow.core.application.employee.usacase.request;

import com.example.peopleflow.core.domain.employee.EmployeeStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmployeeStatusRequestModel {

    private EmployeeStatus status;
    private Long id;

}
