package gr.codehub.vanillahr.repository;

import gr.codehub.vanillahr.db.DbService;
import gr.codehub.vanillahr.model.Department;
import gr.codehub.vanillahr.model.Employee;
import gr.codehub.vanillahr.model.Speciality;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl implements Repository<Employee>{

private DbService dbservice;

    public EmployeeRepositoryImpl(DbService dbservice) {
        this.dbservice = dbservice;
    }

    @Override
    public Employee create(Employee employee) {

        int key = saveEmployee(employee);
        employee.setId(key);
        return employee;

    }

    @Override
    public Optional<Employee> read(int employeeId) {

            Employee employee = getEmployee(employeeId);

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



    public Department getDepartment(int employeeId){
        return null;
    }







    ////////////////////////




    public int saveEmployee( Employee employee){

        Connection connection = dbservice.getConnection();
        if (connection== null) return 0;

        String sql = "insert into Employee( name,  address ,   specialityString,  specialityInt ,    dateOfBirth  )  values(?,?,?,?, ?) ";

        int key = 0;
        try (PreparedStatement insertEmployee = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS) ){

            insertEmployee.setString(1, employee.getName());
            insertEmployee.setString(2, employee.getAddress());
            insertEmployee.setString(3, employee.getSpeciality().toString());
            insertEmployee.setInt(4, employee.getSpeciality().ordinal());
            insertEmployee.setDate(5, new java.sql.Date(employee.getDateOfBirth().getTime()));

            insertEmployee.executeUpdate();

            ResultSet rs = insertEmployee.getGeneratedKeys();
            if (rs != null && rs.next()) {
                key = rs.getInt(1);
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        finally{
            return key;
        }
    }


    public Employee getEmployee(int id){
        Connection connection = dbservice.getConnection();
        if (connection== null) return null;



        String sql = "select * from Employee  where id = "+ id;
        Statement statement = null;
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            Employee employee = new Employee();
            while (resultSet.next()) {

                employee.setId(resultSet.getInt("id")  );
                employee.setName(resultSet.getString("name")  );
                employee.setAddress(resultSet.getString("address")  );
                employee.setSpeciality(Speciality.getSpeciality(resultSet.getInt("specialityInt")));
                employee.setDateOfBirth(resultSet.getDate("dateOfBirth"));

            }
            return employee;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return new Employee();
    }








}
