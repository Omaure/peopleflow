package com.example.peopleflow.entrypoint.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.peopleflow.core.application.employee.usacase.EmployeeUseCase;
import com.example.peopleflow.core.domain.employee.EmployeeStatus;
import com.example.peopleflow.entrypoint.employee.controller.EmployeeController;
import com.example.peopleflow.entrypoint.employee.controller.response.EmployeeResponseDTO;
import com.example.peopleflow.entrypoint.employee.facade.EmployeeFacade;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EmployeeController.class)
public class EmployeeControllerTest {

    private static final String ROOT_PATH = "/api/v1/employee";
    private static final String ID_PATH = "/{id}";
    private static final String ID_EXPRESSION = "$.id";
    private static final String STATUS_EXPRESSION = "$.status";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeFacade employeeFacade;

    @MockBean
    private EmployeeUseCase employeeUseCase;

    @Test
    @DisplayName("Creating an employee with valid payload return employee status and id")
    public void employeeCreation_withValidRequest_createsEmployeeWithAddedStatus() throws Exception {

        //GIVEN
        final JSONObject requestPayload = constructValidPayload();

        final var expectedId = "1";
        final var expectedStatus = EmployeeStatus.ADDED.toString();
        final var employeeResponse = constructEmployeeResponse(expectedId, expectedStatus);

        when(employeeFacade.createEmployee(any())).thenReturn(employeeResponse);

        //When
        final var requestResult = makePostRequest(requestPayload, "");

        // THEN
        assertPostResponse(requestResult, expectedId, expectedStatus);

    }

    @Test
    @DisplayName("Updating employee status to any of the the enums works and returns the new status")
    public void changeEmployeeStatus_withValidRequest_returnsEmployeeWithUpdatedStatus() throws Exception {

        // GIVEN
        final var expectedId = "1";
        final var expectedStatus = EmployeeStatus.APPROVED;

        final var payload = new JSONObject();
        payload.put("status", expectedStatus.toString());

        final var employeeResponse = constructEmployeeResponse(expectedId, expectedStatus.toString());

        when(employeeFacade.changeEmployeeStatus(any(), any())).thenReturn(employeeResponse);

        // WHEN
        final ResultActions requestResult = makePatchRequest(payload, ID_PATH.replace("{id}", expectedId));

        // THEN
        assertPatchResponse(requestResult, expectedId, expectedStatus.toString());

    }

    private JSONObject constructValidPayload() throws JSONException {

        final var requestPayload = new JSONObject();
        requestPayload.put("name", "omar");
        requestPayload.put("age", "26");
        requestPayload.put("email", "omar@mail.com");
        requestPayload.put("phoneNumber", "01111");

        return requestPayload;
    }

    private EmployeeResponseDTO constructEmployeeResponse(final String id, final String status) {

        return EmployeeResponseDTO
            .builder()
            .id(id)
            .status(status)
            .build();
    }

    private ResultActions makePostRequest(final JSONObject payload, final String path) throws Exception {

        return mvc.perform(MockMvcRequestBuilders
            .post(ROOT_PATH + path)
            .contentType(MediaType.APPLICATION_JSON)
            .content(payload.toString()));
    }

    private ResultActions makePatchRequest(final JSONObject payload, final String path) throws Exception {

        return mvc.perform(MockMvcRequestBuilders
            .patch(ROOT_PATH + path)
            .content(payload.toString())
            .contentType(MediaType.APPLICATION_JSON));
    }

    private void assertPostResponse(final ResultActions requestResult, final String expectedId, final String expectedStatus)
        throws Exception {

        requestResult.andExpect(status().isCreated())
            .andExpect(jsonPath(ID_EXPRESSION).value(expectedId))
            .andExpect(jsonPath(STATUS_EXPRESSION).value(expectedStatus));
    }

    private void assertPatchResponse(final ResultActions requestResult, final String expectedId, final String expectedStatus)
        throws Exception {

        requestResult.andExpect(status().isOk())
            .andExpect(jsonPath(ID_EXPRESSION).value(expectedId))
            .andExpect(jsonPath(STATUS_EXPRESSION).value(expectedStatus));
    }

}
