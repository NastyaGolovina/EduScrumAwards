package UPT_SQ.EduScrumAwards.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseCreator {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "yfcnz212006"; // your password

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {


            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS EduScrumAwards");
            System.out.println("Database created!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

