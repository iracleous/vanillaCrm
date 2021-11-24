package gr.codehub.vanillahr.db;


import org.h2.tools.Server;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DatabasesNoException {

    private static final String DB_CONNECTION_URL_MEMORY_MODE = "jdbc:h2:mem:sample";
    private static final String DB_CONNECTION_URL_FILE_MODE = "jdbc:h2:./sample";
    private static final String DB_USERNAME="sa";
    private static final String DB_PASSWORD="";

    private static Server server;
    private static final Properties sqlCommands = new Properties();

    public static void main(String[] args) throws SQLException, IOException {
        DatabasesNoException dbne = new DatabasesNoException();
        dbne.startH2Server();
        dbne.loadDBDriver();
        Connection connection = dbne.getConnection();
        dbne.loadSQLCommands();
        dbne.createTable(connection);
        dbne.insertData(connection);
        dbne.insertMoreData(connection);
        dbne.insertLiveData(connection);
        dbne.readData(connection);
        dbne.stopH2Server();
    }

    private void readData(Connection connection) throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlCommands.getProperty("select.table.000"));

        while (resultSet.next()){
            System.out.print(resultSet.getString("id") + ", ");
            System.out.print(resultSet.getString("first") + ", ");
            System.out.print(resultSet.getString("last") + ", ");
            System.out.println(resultSet.getString("age"));
        }
    }

    private void insertLiveData(Connection connection) throws SQLException {
//        private void insertLiveData(Connection connection, int code,String name.....) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlCommands.getProperty("insert.table.000"));

        preparedStatement.setInt(1, 1001); // (1, code)
        preparedStatement.setString(2, "AAAAAAA");  // (2, name)
        preparedStatement.setString(3, "BBBBBBB");
        preparedStatement.setInt(4, 25);

        int rowsAffected = preparedStatement.executeUpdate();
        System.out.println("Successful:" + rowsAffected);
    }


    private void insertMoreData(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.addBatch(sqlCommands.getProperty("insert.table.002"));
        statement.addBatch(sqlCommands.getProperty("insert.table.003"));
        statement.addBatch(sqlCommands.getProperty("insert.table.004"));
        statement.addBatch(sqlCommands.getProperty("insert.table.005"));
        statement.addBatch(sqlCommands.getProperty("insert.table.006"));

        int[] rowsAffected = statement.executeBatch();
    }

    private void insertData(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        // for....
        int rowsAffected = statement.executeUpdate(sqlCommands.getProperty("insert.table.001"));
        System.out.println("Successful:" + rowsAffected);
    }


    private void createTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        int rowsAffected = statement.executeUpdate(sqlCommands.getProperty("create.table"));
        System.out.println("Successful:" + rowsAffected);
    }

    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_CONNECTION_URL_MEMORY_MODE, DB_USERNAME, DB_PASSWORD);
        return connection;
    }

    private void loadSQLCommands() throws IOException {
        InputStream inputStream = DatabasesNoException.class.getClassLoader().getResourceAsStream("sql.properties");
        sqlCommands.load(inputStream);
    }

    private void stopH2Server() {
        if (server == null) return;
        if (server.isRunning(true)) {
            server.stop();
            server.shutdown();
            System.out.println("Server stopped...");
        }
        server = null;
    }

    public void startH2Server() throws SQLException {
        server = Server.createTcpServer("-tcpAllowOthers", "-tcpDaemon");
        server.start();
        System.out.println("Server started... " + server.getStatus());
    }

    public void loadDBDriver(){
        org.h2.Driver.load();
        // Class.forName("org.h2.Driver");
        System.out.println("Driver loaded...");
    }

}