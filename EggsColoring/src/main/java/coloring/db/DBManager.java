package coloring.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum DBManager {
    INSTANCE();

    private static final String DB_HOSTNAME = "localhost";
    private static final int DB_PORT = 3306;
    private static final String DB_NAME = "test3";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    private Connection connection;

     DBManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Sorry, driver not found. Please check your dependencies!");
        }
        this.connection = createConnection();
    }

    private Connection createConnection(){
        try {
            return DriverManager.getConnection("jdbc:mysql://"+ DB_HOSTNAME +":"+ DB_PORT +"/" + DB_NAME, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error connecting to database. Check your credentials!");
            return null;
        }
    }

    public Connection getConnection() throws SQLException {
        if(connection.isClosed()){
            connection = createConnection();
        }
        return connection;
    }
}
