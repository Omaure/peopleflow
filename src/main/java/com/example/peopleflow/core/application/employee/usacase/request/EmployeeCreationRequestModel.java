package com.example.peopleflow.core.application.employee.usacase.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmployeeCreationRequestModel {

    private String name;
    private String age;
    private String phoneNumber;
    private String email;

}
