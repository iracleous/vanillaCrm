package gr.codehub.vanillahr.service;

import gr.codehub.vanillahr.db.DbService;
import gr.codehub.vanillahr.exceptions.BusinessException;
import gr.codehub.vanillahr.model.Employee;
import gr.codehub.vanillahr.model.Speciality;
import gr.codehub.vanillahr.repository.EmployeeRepositoryImpl;
import gr.codehub.vanillahr.repository.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    private EmployeeService employeeService;
    Employee employee = new Employee();


    @BeforeEach
    void initialize(){

        DbService database = new DbService();
        Repository<Employee> employeeRepository =
                new EmployeeRepositoryImpl(database);

          employeeService =
                new EmployeeServiceImpl(employeeRepository);

        // initialization create tables
        try {
            database.createTableEmployees();
            database.createTableDepartments();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    @Test
    void enroll() throws BusinessException {

        employee.setName("Dimitris Dimitriou");
        employee.setAddress("Athens");
        employee.setDateOfBirth(new Date(100,11, 2));
        employee.setSpeciality(Speciality.DEVELOPER);

        assertEquals(0, employee.getId());

        employeeService.enroll(employee);
        assertEquals(1, employee.getId());

    }

    @Test
    void enrollNullEmployee() throws BusinessException {

        employee.setName(null);
        employee.setAddress(null);
        employee.setDateOfBirth(null);
        employee.setSpeciality(null);

        assertEquals(0, employee.getId());
        employeeService.enroll(employee);
        assertEquals(0, employee.getId());

    }


}