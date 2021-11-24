package gr.codehub.vanillahr;

import gr.codehub.vanillahr.db.DbService;
import gr.codehub.vanillahr.exceptions.BusinessException;
import gr.codehub.vanillahr.files.EmployeeFiles;
import gr.codehub.vanillahr.model.Department;
import gr.codehub.vanillahr.model.Employee;
import gr.codehub.vanillahr.model.Speciality;
import gr.codehub.vanillahr.repository.EmployeeRepositoryImpl;
import gr.codehub.vanillahr.repository.Repository;
import gr.codehub.vanillahr.service.EmployeeService;
import gr.codehub.vanillahr.service.EmployeeServiceImpl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UseCase {


    private static Logger logger = Logger.getLogger(UseCase.class.getName());

    public void testUseCase() {

// needed classes

        DbService database = new DbService();
        Repository<Employee> employeeRepository =
                new EmployeeRepositoryImpl(database);
        EmployeeService employeeService =
                new EmployeeServiceImpl(employeeRepository);

// initialization create tables
        try {
            database.createTableEmployees();
            database.createTableDepartments();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // data tranfer objects  dto
        Employee employee = new Employee();
        employee.setName("Dimitris Dimitriou");
        employee.setAddress("Athens");
        employee.setDateOfBirth(new Date(100, 11, 2));
        employee.setSpeciality(Speciality.DEVELOPER);

        Department department = new Department();
        department.setName("Human Resources");
        department.setLocation("First Floor");

        EmployeeFiles employeeFiles = new EmployeeFiles();

        List<Employee> employeesList = employeeFiles.readFromFile();
        employeeService.saveBatch(employeesList);


//Use case
        try {
            employeeService.enroll(employee);

            employeeService.establishDepartment(department);

            Employee employeeDb = employeeService.find(1);

            if (employeeDb != null)
                logger.log(Level.INFO, employeeDb.toString());


            Department departmentdb = employeeService.findDepartment(1);
            employeeService.assignEmployeeToDepartment(employeeDb, departmentdb);

        } catch (BusinessException businessException) {
            businessException.printStackTrace();
        }


        database.closeConnection();
    }
}
