package gr.codehub.vanillahr.db;

import gr.codehub.vanillahr.model.Employee;
import gr.codehub.vanillahr.model.Speciality;
import org.h2.tools.Server;

import java.sql.*;

public class DbService {
    private static final String DB_CONNECTION_URL_MEMORY_MODE = "jdbc:h2:mem:sample";
    private static final String DB_CONNECTION_URL_FILE_MODE = "jdbc:h2:./sample";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "";

    private static Server server;

    private Connection connection = null;
;
    private Connection getConnection(){
        if (connection != null) return connection;
        try {
            server = Server.createTcpServer("-tcpAllowOthers", "-tcpDaemon");
            server.start();
        } catch (SQLException e) {
            return null;
        }

        System.out.println("Server started... " + server.getStatus());

        org.h2.Driver.load();
        // Class.forName("org.h2.Driver");
        System.out.println("Driver loaded...");

        try {
              connection = DriverManager.getConnection(DB_CONNECTION_URL_MEMORY_MODE, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            return null;
        }
        return connection;
    }


    public boolean createTableEmployees( ) throws SQLException{
        Connection connection = getConnection();

        String sql ="create table Employee(\n" +
                "        id integer   PRIMARY KEY AUTO_INCREMENT,\n" +
                "        name VARCHAR(50),\n" +
                "        address VARCHAR(50),\n" +
                "        specialityString   VARCHAR(50),\n" +
                "        specialityInt integer,\n" +
                "        dateOfBirth date,\n" +
                "        belongingDepartmentId integer,\n" +
                "        managingProjectId integer,\n" +
                "        directorDepartmentId integer\n" +
                "     );";

        Statement statement = connection.createStatement();
        int rowsAffected = statement.executeUpdate(sql);


        return true;
    }


    public int saveEmployee( Employee employee){


        Connection connection = getConnection();
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
        Connection connection = getConnection();
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


    public void closeConnection(){
        Connection connection = getConnection();
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
