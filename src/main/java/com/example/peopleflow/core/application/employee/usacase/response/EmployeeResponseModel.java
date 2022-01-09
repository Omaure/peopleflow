package com.example.peopleflow.core.application.employee.usacase.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EmployeeResponseModel {

    private Long id;
    private String status;

}
