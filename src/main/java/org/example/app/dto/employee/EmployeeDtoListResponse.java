package org.example.app.dto.employee;

import org.example.app.entity.employee.Employee;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

public record EmployeeDtoListResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        List<Employee> employeeList) {

    public static final String SUCCESS_MESSAGE = "Employee list has been fetched successfully.";
    public static final String FAILURE_MESSAGE = "Employee list has not been found!";

    public static EmployeeDtoListResponse of(boolean isEmployeeListEmpty, List<Employee> list) {
        return isEmployeeListEmpty ?
                new EmployeeDtoListResponse(
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        false, FAILURE_MESSAGE, Collections.emptyList()) :
                new EmployeeDtoListResponse(
                        HttpStatus.OK.value(),
                        HttpStatus.OK.getReasonPhrase(),
                        true, SUCCESS_MESSAGE, list);
    }
}
