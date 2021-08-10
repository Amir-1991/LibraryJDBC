package DBConnector;

import java.sql.*;

public class DBConnector extends SQLException {
    public static Connection connectionMySQL = DBConnector.connectionToDB();

    public static Connection connectionToDB() {
        try {
            connectionMySQL = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Amir0013981064");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connectionMySQL;
    }
}
