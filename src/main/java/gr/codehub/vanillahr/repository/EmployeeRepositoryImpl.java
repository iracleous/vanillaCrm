package gr.codehub.vanillahr.repository;

import gr.codehub.vanillahr.model.Employee;

import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository{
    @Override
    public Employee createEmployee(Employee e) {
        return null;
    }

    @Override
    public Optional<Employee> readEmployee(int employeeId) {
        return Optional.empty();
    }

    @Override
    public List<Employee> readEmployee() {
        return null;
    }

    @Override
    public Employee updateEmployee(int employeeId, Employee e) {
        return null;
    }

    @Override
    public boolean deleteEmployee(int employeeId) {
        return false;
    }
}