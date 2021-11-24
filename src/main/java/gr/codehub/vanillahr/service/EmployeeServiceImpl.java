package gr.codehub.vanillahr.service;

import gr.codehub.vanillahr.model.Department;
import gr.codehub.vanillahr.model.Employee;
import gr.codehub.vanillahr.repository.Repository;

public class EmployeeServiceImpl implements EmployeeService{

    private Repository<Employee> employeeRepository;

    public EmployeeServiceImpl( Repository<Employee> employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee enroll(Employee employee) {
        return employeeRepository.create(employee);
    }

    @Override
    public Employee dismiss(Employee employee) {
        return null;
    }

    @Override
    public Employee find(int key) {
        return employeeRepository.read(key).get();
    }

    @Override
    public void assignEmployeeToDepartment(Employee employee, Department department) {

    }


}
