package ru.learnup.javaqa.PedometerManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    private Connection connection; //для создания соединения с базой данных с помощью jdbc

    public DBHelper(String dbUrl, String dbUser, String dbPassword) {
        try {
            this.connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword); //создаем соединение
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Day> getAllPos() {
        try {
            List<Day> listOfDays = new ArrayList<>();
            final Statement statement = (Statement) connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM pedometer_manager ORDER BY day ASC;"
            ); //объект результата
            while (resultSet.next()) {
                final int day = resultSet.getInt("day");
                final int steps = resultSet.getInt("steps");
                listOfDays.add(
                        new Day(day, steps)
                );
            }
            return listOfDays;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getDay(Day note) {
        try {
            int result = -1;
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM pedometer_manager;"
            );
            while (resultSet.next()) {
                if (resultSet.getInt("day") == note.getDay()) {
                    result = resultSet.getInt("steps");
                    break;
                }
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getMaxDay(Day note) {
        try {
            int result = -1;
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(
                    "SELECT *  from pedometer_manager ORDER BY steps DESC;"
            );
            while (resultSet.next()) {
                result = resultSet.getInt("day");
                break;
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean addDay(Day note) {
        try {
            final PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO pedometer_manager(steps) VALUES(?);");
            statement.setInt(1, note.getSteps()); //вместо первого ? ставим кол-во шагов
            final int modifiedCount = statement.executeUpdate();
            return modifiedCount > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDay(Day note) {
        try {
            final PreparedStatement statement = connection.prepareStatement(
                    "UPDATE pedometer_manager SET steps = steps + "
                            + note.getSteps() + " WHERE day = " + note.getDay() + ";");
            final int modifiedCount = statement.executeUpdate();
            return modifiedCount > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
