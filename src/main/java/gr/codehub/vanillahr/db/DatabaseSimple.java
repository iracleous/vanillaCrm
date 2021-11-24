package gr.codehub.vanillahr.db;

import org.h2.tools.Server;

import java.sql.*;

public class DatabaseSimple {
    private static final String DB_CONNECTION_URL_MEMORY_MODE = "jdbc:h2:mem:sample";
    private static final String DB_CONNECTION_URL_FILE_MODE = "jdbc:h2:./sample";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "";

    private static Server server;

    public static void main(String[] args) throws SQLException {
        server = Server.createTcpServer("-tcpAllowOthers", "-tcpDaemon");
        server.start();
        System.out.println("Server started... " + server.getStatus());

        org.h2.Driver.load();
        // Class.forName("org.h2.Driver");
        System.out.println("Driver loaded...");

        Connection connection = DriverManager.getConnection(DB_CONNECTION_URL_MEMORY_MODE, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        int rowsAffected = statement.executeUpdate("create table if not exists registration(" +
                "id integer primary key, " +
                "first varchar(20), " +
                "last varchar(50), " +
                "age integer)");
        System.out.println("Successful:" + rowsAffected);

        String sql = "insert into registration values(101, 'John', 'Smith', 18)";
        rowsAffected = statement.executeUpdate(sql);
        System.out.println("Successful:" + rowsAffected);

        sql = "select * from registration";
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            System.out.print(resultSet.getString("id") + ", ");
            System.out.print(resultSet.getString("first") + ", ");
            System.out.print(resultSet.getString("last") + ", ");
            System.out.println(resultSet.getString("age"));
            System.out.println(resultSet.getInt(4));
        }

        statement.close();
        connection.close();
        server.stop();
        server.shutdown();

    }
}