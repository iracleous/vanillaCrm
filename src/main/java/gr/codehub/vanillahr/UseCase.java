package gr.codehub.vanillahr;

import gr.codehub.vanillahr.model.Employee;
import gr.codehub.vanillahr.repository.EmployeeRepository;
import gr.codehub.vanillahr.repository.EmployeeRepositoryImpl;
import gr.codehub.vanillahr.service.EmployeeService;
import gr.codehub.vanillahr.service.EmployeeServiceImpl;

import java.util.Date;

public class UseCase {

    public void testUseCase(){

        Employee employee = new Employee();
        employee.setName("Dimitris Dimitriou");
        employee.setAddress("Athens");
        employee.setDateOfBirth(new Date(100,11, 2));

        EmployeeRepository employeeRepository =
                new EmployeeRepositoryImpl();
        EmployeeService employeeService =
                new EmployeeServiceImpl(employeeRepository);

        employeeService.enroll(employee);

    }
}
