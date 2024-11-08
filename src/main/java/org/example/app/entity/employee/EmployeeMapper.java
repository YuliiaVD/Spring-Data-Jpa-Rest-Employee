package org.example.app.entity.employee;

import org.example.app.dto.employee.EmployeeDtoRequest;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee dtoCreateToEntity(EmployeeDtoRequest request) {
        Employee employee = new Employee();

        Long id = request.id();
        if (id != null) employee.setId(id);

        String firstName = request.firstName();
        if (firstName != null) {
            if (!firstName.isBlank())
                employee.setFirstName(firstName);
        }

        String lastName = request.lastName();
        if (lastName != null) {
            if (!lastName.isBlank())
                employee.setLastName(lastName);
        }

        String position = request.position();
        if (position != null) {
            if (!position.isBlank())
                employee.setPosition(position);
        }

        String phone = request.phone();
        if (phone != null) {
            if (!phone.isBlank())
                employee.setPhone(phone);
        }

        return employee;
    }

    public Employee dtoUpdateToEntity(Long id, EmployeeDtoRequest request,
                                      Employee employeeToUpdate) {
        if (id != null) employeeToUpdate.setId(id);

        String firstName = request.firstName();
        if (firstName != null) {
            if (!firstName.isBlank())
                employeeToUpdate.setFirstName(firstName);
        }

        String lastName = request.lastName();
        if (lastName != null) {
            if (!lastName.isBlank())
                employeeToUpdate.setLastName(lastName);
        }

        String position = request.position();
        if (position != null) {
            if (!position.isBlank())
                employeeToUpdate.setPosition(position);
        }

        return employeeToUpdate;
    }
}
