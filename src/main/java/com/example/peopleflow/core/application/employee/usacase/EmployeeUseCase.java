package com.example.peopleflow.core.application.employee.usacase;

import com.example.peopleflow.core.application.employee.usacase.request.EmployeeCreationRequestModel;
import com.example.peopleflow.core.application.employee.usacase.request.EmployeeStatusRequestModel;
import com.example.peopleflow.core.application.employee.usacase.response.EmployeeResponseModel;

public interface EmployeeUseCase {

    EmployeeResponseModel createEmployee(EmployeeCreationRequestModel employeeRequestModel);

    EmployeeResponseModel changeEmployeeStatus(EmployeeStatusRequestModel employeeStatusRequestModel);

}
