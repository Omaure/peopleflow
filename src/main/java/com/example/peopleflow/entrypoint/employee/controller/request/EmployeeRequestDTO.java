package com.example.peopleflow.entrypoint.employee.controller.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmployeeRequestDTO {

    private String name;
    private String age;
    private String phoneNumber;
    private String email;

}
