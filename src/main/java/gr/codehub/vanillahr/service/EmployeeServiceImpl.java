package gr.codehub.vanillahr.service;

import gr.codehub.vanillahr.model.Employee;
import gr.codehub.vanillahr.repository.EmployeeRepository;

public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee enroll(Employee employee) {
        return null;
    }

    @Override
    public Employee dismiss(Employee employee) {
        return null;
    }
}
