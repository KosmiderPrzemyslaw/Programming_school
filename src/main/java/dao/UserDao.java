package dao;

import DBConnection.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDao {
     Connection dbConnection;

    {
        try {
            dbConnection = new DBConnection().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
