package gr.codehub.vanillahr.service;

import gr.codehub.vanillahr.exceptions.BusinessException;
import gr.codehub.vanillahr.model.Department;
import gr.codehub.vanillahr.model.Employee;
import gr.codehub.vanillahr.repository.Repository;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{

    private Repository<Employee> employeeRepository;

    public EmployeeServiceImpl( Repository<Employee> employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee enroll(Employee employee) throws BusinessException {
        //validations
        if (employee== null)
                throw new BusinessException("null employee");
        if ("Athens".equals(employee.getAddress()))
            throw new BusinessException("Athens not permitted");
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
    public boolean saveBatch(List<Employee> employees) {

if (employees==null) return false;
        for (Employee employee:employees)
            employeeRepository.create(employee);

          return true;
    }

    @Override
    public void assignEmployeeToDepartment(Employee employee, Department department) {

    }


    public Department establishDepartment(Department department){
        return null;
    }
    @Override
    public Department findDepartment(int id) {
        return null;
    }


}
