package gr.codehub.vanillahr.db;

import gr.codehub.vanillahr.model.Department;
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
    public Connection getConnection(){
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



    public void closeConnection(){
        Connection connection = getConnection();
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean createTableEmployees( ) throws SQLException {
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

    public boolean createTableDepartments( ) throws SQLException {
        Connection connection = getConnection();

        String sql ="create table Department(\n" +
                "        id integer   PRIMARY KEY AUTO_INCREMENT,\n" +
                "        name VARCHAR(50),\n" +
                "        location VARCHAR(50),\n" +
                "        director integer\n" +
                "     );";

        Statement statement = connection.createStatement();
        int rowsAffected = statement.executeUpdate(sql);
        return true;
    }


}
