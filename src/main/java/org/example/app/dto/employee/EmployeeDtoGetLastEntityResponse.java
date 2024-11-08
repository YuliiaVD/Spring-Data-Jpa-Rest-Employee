package org.example.app.dto.employee;

import org.example.app.entity.employee.Employee;
import org.springframework.http.HttpStatus;

public record EmployeeDtoGetLastEntityResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message,
        Employee employee) {

    public static final String SUCCESS_MESSAGE = "Employee has been fetched successfully.";
    public static final String FAILURE_MESSAGE = "Employee has not been found!";

    public static EmployeeDtoGetLastEntityResponse of(boolean isEmployeeFound, Employee employee) {
        return isEmployeeFound ?
                new EmployeeDtoGetLastEntityResponse(
                        HttpStatus.OK.value(),
                        HttpStatus.OK.getReasonPhrase(),
                        true, SUCCESS_MESSAGE, employee) :
                new EmployeeDtoGetLastEntityResponse(
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        false, FAILURE_MESSAGE, null);
    }
}
