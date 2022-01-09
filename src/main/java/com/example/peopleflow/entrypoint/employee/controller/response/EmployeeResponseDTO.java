package com.example.peopleflow.entrypoint.employee.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmployeeResponseDTO {

    private String id;
    private String status;

}
