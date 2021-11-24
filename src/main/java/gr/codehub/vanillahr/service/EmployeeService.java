package gr.codehub.vanillahr.service;

import gr.codehub.vanillahr.model.Employee;
import gr.codehub.vanillahr.repository.EmployeeRepository;

/**
 * Service gives the signatures of the methods that
 * define the business requirements
 */
public interface EmployeeService {

    Employee enroll(Employee employee);
    Employee dismiss(Employee employee);

}
