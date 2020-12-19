package dao;

import DBConnection.DBConnection;
import models.Solution;
import models.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public List<Solution> findAllSolutionsByUserId(int userId) {
        try {
            List<Solution> solutions = new ArrayList<>();
            Connection connection = new DBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM Programming_school.solution where user_id = ?");
            preparedStatement.setInt(1, userId);
            return getSolutions(preparedStatement, solutions);
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
            preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setString(5, solution.getDescription());
            preparedStatement.setInt(6, solution.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int solutionId) {
        try {
            Connection connection = new DBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("DELETE Programming_school.solution FROM Programming_school.solution where id = ?");
            preparedStatement.setInt(1, solutionId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Solution> findAll() {
        try {
            List<Solution> solutions = new ArrayList<>();
            Connection connection = new DBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM Programming_school.solution");
            return getSolutions(preparedStatement, solutions);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Solution> findAllByUserId(int userId) {
        try {
            Connection connection = new DBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM Programming_school.solution WHERE user_id = ?");
            preparedStatement.setInt(1, userId);
            List<Solution> solutionList = new ArrayList<>();
            return getSolutions(preparedStatement, solutionList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Solution> findAllByExerciseId(int exerciseId) {
        try {
            Connection connection = new DBConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM Programming_school.solution where exercise_id = ? ORDER BY created desc");
            preparedStatement.setInt(1, exerciseId);
            List<Solution> solutionList = new ArrayList<>();
            return getSolutions(preparedStatement, solutionList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Solution> getSolutions(PreparedStatement preparedStatement, List<Solution> solutionList) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Solution solution = new Solution();
            solution.setId(resultSet.getInt("id"));
            solution.setUserId(resultSet.getInt("user_id"));
            solution.setExerciseId(resultSet.getInt("exercise_id"));
            solution.setCreated(resultSet.getTimestamp("created"));
            solution.setUpdated(resultSet.getTimestamp("updated"));
            solution.setDescription(resultSet.getString("description"));
            solutionList.add(solution);
        }
        return solutionList;
    }
}
