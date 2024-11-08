package org.example.app.repository;


import org.example.app.entity.employee.Employee;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Qualifier("employeeRepository")
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findFirstByOrderByIdDesc();

    Optional<List<Employee>> findByFirstName(String firstName);

    Optional<List<Employee>> findByLastName(String lastName);
}
