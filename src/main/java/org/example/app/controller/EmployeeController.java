package org.example.app.controller;

import org.example.app.dto.employee.*;
import org.example.app.entity.employee.Employee;
import org.example.app.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(@Qualifier("employeeServiceImpl") EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDtoCreateResponse> createEmployee(
            @RequestBody EmployeeDtoRequest request) {
        Employee employee = employeeService.create(request);
        // ternary operator usage
        return (employee != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(EmployeeDtoCreateResponse.of(true,
                                employee)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(EmployeeDtoCreateResponse.of(false,
                                null));
    }

    @GetMapping
    public ResponseEntity<EmployeeDtoListResponse> getAllEmployees() {
        Optional<List<Employee>> optional = employeeService.getAll();
        if (optional.isPresent()) {
            List<Employee> list = optional.get();
            return (!list.isEmpty()) ?
                    ResponseEntity.status(HttpStatus.OK)
                            .body(EmployeeDtoListResponse.of(false,
                                    list)) :
                    ResponseEntity.status(HttpStatus.OK)
                            .body(EmployeeDtoListResponse.of(true,
                                    Collections.emptyList()));
        } else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(EmployeeDtoListResponse.of(true,
                            Collections.emptyList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDtoGetByIdResponse> getEmployeeById(
            @PathVariable("id") Long id) {
        Employee employee = employeeService.getById(id);
        return (employee != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(EmployeeDtoGetByIdResponse.of(id, true,
                                employee)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(EmployeeDtoGetByIdResponse.of(id, false,
                                null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDtoUpdateResponse> updateEmployeeById(
            @PathVariable("id") Long id,
            @RequestBody EmployeeDtoRequest request) {
        return (employeeService.getById(id) != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(EmployeeDtoUpdateResponse.of(id, true,
                                employeeService.updateById(id, request))) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(EmployeeDtoUpdateResponse.of(id, false,
                                null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeDtoDeleteResponse> deleteEmployeeById(
            @PathVariable(value = "id") Long id) {
        return (employeeService.deleteById(id)) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(EmployeeDtoDeleteResponse.of(id, true)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(EmployeeDtoDeleteResponse.of(id, false));
    }

    @GetMapping("/last-entity")
    public ResponseEntity<EmployeeDtoGetLastEntityResponse> getLastEntity() {
        Employee employee = employeeService.findFirstByOrderByIdDesc();
        return employee != null ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(EmployeeDtoGetLastEntityResponse.of(true,
                                employee)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(EmployeeDtoGetLastEntityResponse.of(false,
                                null));
    }

    @GetMapping("/first-name/{firstName}")
    public ResponseEntity<EmployeeDtoListResponse> getByFirstName(
            @PathVariable(value = "firstName") String firstName) {
        List<Employee> list = employeeService.findByFirstName(firstName);
        return (!list.isEmpty()) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(EmployeeDtoListResponse.of(false,
                                list)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(EmployeeDtoListResponse.of(true,
                                Collections.emptyList()));
    }

    @GetMapping("/last-name/{lastName}")
    public ResponseEntity<EmployeeDtoListResponse> getByLastName(
            @PathVariable(value = "lastName") String lastName) {
        List<Employee> list = employeeService.findByLastName(lastName);
        return (!list.isEmpty()) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(EmployeeDtoListResponse.of(false,
                                list)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(EmployeeDtoListResponse.of(true,
                                Collections.emptyList()));
    }
}
