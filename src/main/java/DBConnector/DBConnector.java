package DBConnector;

import java.sql.*;

public class DBConnector extends SQLException {
    static Connection connectionMySQL;
    static String urlDB = "jdbc:mysql://localhost:3306/library";
    static String userNameDB = "root";
    static String passwordDB = "Amir0013981064";

    public static Connection connectionToDB() {
        try {
            connectionMySQL = DriverManager.getConnection(urlDB, userNameDB, passwordDB);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connectionMySQL;
    }
}
