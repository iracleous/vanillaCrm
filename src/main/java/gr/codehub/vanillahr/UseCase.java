package gr.codehub.vanillahr;

import gr.codehub.vanillahr.db.DbService;
import gr.codehub.vanillahr.model.Employee;
import gr.codehub.vanillahr.model.Speciality;
import gr.codehub.vanillahr.repository.EmployeeRepository;
import gr.codehub.vanillahr.repository.EmployeeRepositoryImpl;
import gr.codehub.vanillahr.service.EmployeeService;
import gr.codehub.vanillahr.service.EmployeeServiceImpl;

import java.sql.SQLException;
import java.util.Date;

public class UseCase {

    public void testUseCase(){

        Employee employee = new Employee();
        employee.setName("Dimitris Dimitriou");
        employee.setAddress("Athens");
        employee.setDateOfBirth(new Date(100,11, 2));
        employee.setSpeciality(Speciality.DEVELOPER);


        DbService dbService = new DbService();


        try {
            dbService.createTableEmployees();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        EmployeeRepository employeeRepository =
                new EmployeeRepositoryImpl(dbService);
        EmployeeService employeeService =
                new EmployeeServiceImpl(employeeRepository);




        employeeService.enroll(employee);
        System.out.println(employee.getId());
    }
}
