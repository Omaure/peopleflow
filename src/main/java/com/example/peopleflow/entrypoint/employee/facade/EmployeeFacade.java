package com.example.peopleflow.entrypoint.employee.facade;

import com.example.peopleflow.entrypoint.employee.controller.request.EmployeeRequestDTO;
import com.example.peopleflow.entrypoint.employee.controller.request.EmployeeStatusRequestDTO;
import com.example.peopleflow.entrypoint.employee.controller.response.EmployeeResponseDTO;

public interface EmployeeFacade {

    EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO);

    EmployeeResponseDTO changeEmployeeStatus(EmployeeStatusRequestDTO employeeStatusRequestDTO, String id);

}
