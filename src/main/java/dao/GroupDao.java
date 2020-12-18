package dao;

import DBConnection.DBConnection;
import models.Group;

import java.sql.*;

public class GroupDao {


    public Group create(Group group) {
        Connection connection;

        {
            try {
                connection = new DBConnection().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement
                        ("INSERT INTO Programming_school.user_group(name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, group.getName());
                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();

                if (resultSet.next()) {
                    group.setId(resultSet.getInt(1));
                    return group;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Group read(int groupId) {
        try {
            Connection connection = new DBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM Programming_school.user_group WHERE id = ?");
            preparedStatement.setInt(1, groupId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                return group;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}