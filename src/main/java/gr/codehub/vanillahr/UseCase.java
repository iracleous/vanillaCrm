package gr.codehub.vanillahr;

import gr.codehub.vanillahr.db.DbService;
import gr.codehub.vanillahr.model.Department;
import gr.codehub.vanillahr.model.Employee;
import gr.codehub.vanillahr.model.Speciality;
import gr.codehub.vanillahr.repository.EmployeeRepositoryImpl;
import gr.codehub.vanillahr.repository.Repository;
import gr.codehub.vanillahr.service.EmployeeService;
import gr.codehub.vanillahr.service.EmployeeServiceImpl;

import java.sql.SQLException;
import java.util.Date;

public class UseCase {

    public void testUseCase(){

// needed classes
        DbService dbService = new DbService();
        Repository<Employee> employeeRepository =
                new EmployeeRepositoryImpl(dbService);
        EmployeeService employeeService =
                new EmployeeServiceImpl(employeeRepository);




  // data tranfer objects  dto
        Employee employee = new Employee();
        employee.setName("Dimitris Dimitriou");
        employee.setAddress("Athens");
        employee.setDateOfBirth(new Date(100,11, 2));
        employee.setSpeciality(Speciality.DEVELOPER);

        Department department = new Department();
        department.setName("Human Resources");
        department.setLocation("First Floor");

// initialization create tables
        try {
            dbService.createTableEmployees();
            dbService.createTableDepartments();
        } catch (SQLException e) {
            e.printStackTrace();
        }

//Use case

        employeeService.enroll(employee);



        Employee employeeDb = employeeService.find( 1);

        System.out.println(employeeDb);

        Department departmentdb = employeeService.findDepartment( 1);

        employeeService.assignEmployeeToDepartment(employeeDb, departmentdb);


        dbService.closeConnection();


    }
}
