import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3307/employee_management";
    private static final String USER = "root";
    private static final String PASSWORD = "password"; // Update with your actual MySQL password

    /**
     * Establishes a connection to the MySQL database.
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found. Please add the connector JAR to your classpath.", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
