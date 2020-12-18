package dao;

import DBConnection.DBConnection;
import models.Exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExerciseDao {
    public Exercise create(Exercise exercise) {
        try {
            Connection connection = new DBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO Programming_school.exercise(title, description) VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, exercise.getTitile());
            preparedStatement.setString(2, exercise.getDescription());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.next()){
                exercise.setId(resultSet.getInt(1));
            }
        return exercise;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}