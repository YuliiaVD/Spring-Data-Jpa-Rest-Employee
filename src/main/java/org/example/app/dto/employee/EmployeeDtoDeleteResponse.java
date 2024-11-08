package org.example.app.dto.employee;

import org.springframework.http.HttpStatus;

public record EmployeeDtoDeleteResponse(
        int statusCode,
        String reasonPhrase,
        boolean success,
        String message) {

    public static final String SUCCESS_MESSAGE = "Employee with id %s has been deleted successfully.";
    public static final String FAILURE_MESSAGE = "Employee with id %s has not been found!";

    public static EmployeeDtoDeleteResponse of(Long id, boolean isEmployeeFound) {
        return isEmployeeFound ?
                new EmployeeDtoDeleteResponse(
                        HttpStatus.OK.value(),
                        HttpStatus.OK.getReasonPhrase(),
                        true, SUCCESS_MESSAGE.formatted(id)) :
                new EmployeeDtoDeleteResponse(
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        false, FAILURE_MESSAGE.formatted(id));
    }
}
