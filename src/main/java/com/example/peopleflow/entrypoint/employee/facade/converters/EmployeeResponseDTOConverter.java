package com.example.peopleflow.entrypoint.employee.facade.converters;

import com.example.peopleflow.core.application.employee.usacase.response.EmployeeResponseModel;
import com.example.peopleflow.entrypoint.employee.controller.response.EmployeeResponseDTO;
import com.google.common.base.Preconditions;
import java.util.Objects;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmployeeResponseDTOConverter implements Converter<EmployeeResponseModel, EmployeeResponseDTO> {

    private static final String ERROR_RESPONSE_MODEL_IS_NULL = "Response Model should not be null.";

    @Override
    public EmployeeResponseDTO convert(final EmployeeResponseModel employeeResponseModel) {

        Preconditions.checkArgument(Objects.nonNull(employeeResponseModel), ERROR_RESPONSE_MODEL_IS_NULL);

        return EmployeeResponseDTO.builder()
            .id(employeeResponseModel.getId().toString())
            .status(employeeResponseModel.getStatus())
            .build();
    }

}
