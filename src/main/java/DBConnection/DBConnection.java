package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection implements AutoCloseable {

    private Connection connection;

    public DBConnection() throws SQLException {
        String URL =
        "jdbc:mysql://localhost:3306/Programming_school?useSSL=false&characterEncoding=utf8";
        String USER = "root";
        String PASSWORD = "coderslab";

        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);

    }

    public Connection getConnection() {
        return connection;
    }


    public void close() throws Exception {

    }
}
