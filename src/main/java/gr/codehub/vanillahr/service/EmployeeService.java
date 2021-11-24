package gr.codehub.vanillahr.service;

import gr.codehub.vanillahr.exceptions.BusinessException;
import gr.codehub.vanillahr.model.Department;
import gr.codehub.vanillahr.model.Employee;

import java.util.List;


/**
 * Service gives the signatures of the methods that
 * define the business requirements
 */
public interface EmployeeService {

    Employee enroll(Employee employee) throws BusinessException;
    Employee dismiss(Employee employee);

    Employee find(int key);


    boolean saveBatch(List<Employee> employee);

    void assignEmployeeToDepartment(Employee employee, Department department);

    Department findDepartment(int id);
    Department establishDepartment(Department department);


}
