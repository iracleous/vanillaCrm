package gr.codehub.vanillahr.repository;

import gr.codehub.vanillahr.model.Employee;

import java.util.List;
import java.util.Optional;

/**
 * Repository gives the signatures of the methods that
 * communicate with the database
 *
 */
public interface EmployeeRepository {
    //CRUD

    Employee createEmployee(Employee e);
    Optional<Employee> readEmployee(int employeeId);
    List<Employee> readEmployee();
    Employee updateEmployee(int employeeId, Employee e);
    boolean deleteEmployee(int employeeId);
}
