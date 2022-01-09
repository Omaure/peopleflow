package com.example.peopleflow.core.application.employee.converter;

import com.example.peopleflow.core.application.employee.usacase.response.EmployeeResponseModel;
import com.example.peopleflow.core.domain.employee.Employee;
import com.google.common.base.Preconditions;
import java.util.Objects;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmployeeResponseModelConverter implements Converter<Employee, EmployeeResponseModel> {

    private static final String ERROR_EMPLOYEE_MODEL_IS_NULL = "Employee model should not be null";

    @Override
    public EmployeeResponseModel convert(final Employee employee) {

        Preconditions.checkArgument(Objects.nonNull(employee), ERROR_EMPLOYEE_MODEL_IS_NULL);

        return EmployeeResponseModel
            .builder()
            .id(employee.getId())
            .status(employee.getStatus().toString())
            .build();
    }

}
