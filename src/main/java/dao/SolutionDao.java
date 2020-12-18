package dao;

import DBConnection.DBConnection;
import models.Solution;

import java.sql.*;

public class SolutionDao {
    public Solution create(Solution solution) {
        try {
            Connection connection = new DBConnection().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO Programming_school.solution(created, updated, description, user_id, exercise_id) VALUES (?,?,?,?,?)",
                            PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setTimestamp(1, solution.getCreated());
            preparedStatement.setTimestamp(2, solution.getUpdated());
            preparedStatement.setString(3, solution.getDescription());
            preparedStatement.setInt(4, solution.getUserId());
            preparedStatement.setInt(5, solution.getExerciseId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                solution.setId(resultSet.getInt(1));
            }
            return solution;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Solution read(int solutionId) {
        try {
            Connection connection = new DBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM Programming_school.solution WHERE id = ?");
            preparedStatement.setInt(1, solutionId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setExerciseId(resultSet.getInt("exercise_id"));
                solution.setUserId(resultSet.getInt("user_id"));
                solution.setDescription(resultSet.getString("description"));
                solution.setCreated(resultSet.getTimestamp("created"));
                solution.setUpdated(resultSet.getTimestamp("updated"));
                return solution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Solution solution) {
        try {
            Connection connection = new DBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("UPDATE Programming_school.solution SET user_id = ?, exercise_id = ?, created = ?, updated = ?, description = ? WHERE id = ?");
            preparedStatement.setInt(1, solution.getUserId());
            preparedStatement.setInt(2, solution.getExerciseId());
            preparedStatement.setTimestamp(3, solution.getCreated());
            preparedStatement.setTimestamp(4, solution.getUpdated());
            preparedStatement.setString(5, solution.getDescription());
            preparedStatement.setInt(6, solution.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
