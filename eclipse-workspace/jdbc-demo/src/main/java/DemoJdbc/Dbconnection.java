package DemoJdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnection {
    public static Connection getConnection() throws SQLException {
        String dburl = "jdbc:mysql://localhost:3306/jdbc";
        String username = "root";
        String password = "root123";

        return DriverManager.getConnection(dburl, username, password);
    }
}