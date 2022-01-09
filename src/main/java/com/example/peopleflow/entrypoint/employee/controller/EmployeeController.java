package com.example.peopleflow.entrypoint.employee.controller;


import com.example.peopleflow.entrypoint.employee.controller.request.EmployeeRequestDTO;
import com.example.peopleflow.entrypoint.employee.controller.request.EmployeeStatusRequestDTO;
import com.example.peopleflow.entrypoint.employee.controller.response.EmployeeResponseDTO;
import com.example.peopleflow.entrypoint.employee.facade.EmployeeFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = EmployeeController.ROOT_PATH)
@AllArgsConstructor
@CrossOrigin(exposedHeaders = HttpHeaders.LOCATION)
public class EmployeeController {

    public static final String ROOT_PATH = "/api/v1/employee";
    public static final String ID_PATH = "/{id}";

    private final EmployeeFacade employeeFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponseDTO createEmployee(final @RequestBody EmployeeRequestDTO employeeRequestDTO) {

        return employeeFacade.createEmployee(employeeRequestDTO);
    }

    @PatchMapping(path = ID_PATH)
    @ResponseStatus(HttpStatus.OK)
    public EmployeeResponseDTO changeEmployeeStatus(final @RequestBody EmployeeStatusRequestDTO employeeStatusRequestDTO,
        @PathVariable final String id) {

        return employeeFacade.changeEmployeeStatus(employeeStatusRequestDTO, id);
    }

}
