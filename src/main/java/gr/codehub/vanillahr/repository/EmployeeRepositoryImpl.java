package gr.codehub.vanillahr.repository;

import gr.codehub.vanillahr.db.DbService;
import gr.codehub.vanillahr.model.Employee;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository{

private DbService dbservice;

    public EmployeeRepositoryImpl(DbService dbservice) {
        this.dbservice = dbservice;
    }

    @Override
    public Employee createEmployee(Employee employee) {

        int key = dbservice.saveEmployee(employee);
        employee.setId(key);
        return employee;

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
