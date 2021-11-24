package gr.codehub.vanillahr.service;

import gr.codehub.vanillahr.model.Department;
import gr.codehub.vanillahr.model.Employee;


/**
 * Service gives the signatures of the methods that
 * define the business requirements
 */
public interface EmployeeService {

    Employee enroll(Employee employee);
    Employee dismiss(Employee employee);

    Employee find(int key);

    void assignEmployeeToDepartment(Employee employee, Department department);

}
