package org.example.app.service.employee;

import org.example.app.dto.employee.EmployeeDtoRequest;
import org.example.app.entity.employee.Employee;
import org.example.app.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService extends BaseService<Employee, EmployeeDtoRequest> {
    Employee create(EmployeeDtoRequest request);
    Optional<List<Employee>> getAll();
    Employee getById(Long id);
    Employee updateById(Long id, EmployeeDtoRequest request);
    boolean deleteById(Long id);
    Employee findFirstByOrderByIdDesc();
    List<Employee> findByFirstName(String firstName);
    List<Employee> findByLastName(String lastName);
}
