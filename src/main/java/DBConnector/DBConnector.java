package DBConnector;

import java.sql.*;

public class DBConnector extends SQLException {
    static Connection connectionMySQL;

    public static void checkUnique(String inputUser) throws SQLException {
        connectionMySQL();
        Statement st = connectionMySQL.createStatement();
        PreparedStatement prep = connectionMySQL.prepareStatement("select count(*) as isExist from users WHERE USER_NAME='" + inputUser + "' ;");
        ResultSet rs = prep.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt("isExist"));
        }
        rs.close();
        st.close();
    }

    public static void connectionMySQL() {
        try {
            connectionMySQL = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Amir0013981064");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
