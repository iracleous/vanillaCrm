package gr.codehub.vanillahr.repository;

import gr.codehub.vanillahr.db.DbService;
import gr.codehub.vanillahr.model.Employee;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl implements Repository<Employee>{

private DbService dbservice;

    public EmployeeRepositoryImpl(DbService dbservice) {
        this.dbservice = dbservice;
    }

    @Override
    public Employee create(Employee employee) {

        int key = dbservice.saveEmployee(employee);
        employee.setId(key);
        return employee;

    }

    @Override
    public Optional<Employee> read(int employeeId) {

            Employee employee = dbservice.getEmployee(employeeId);

         return  (employee==null)? Optional.empty(): Optional.of(employee);

//        if (employee==null)
//            return Optional.empty();
//        return Optional.of(employee);
    }

    @Override
    public List<Employee> read() {
        return null;
    }

    @Override
    public Employee update(int employeeId, Employee e) {
        return null;
    }

    @Override
    public boolean delete(int employeeId) {
        return false;
    }
}
