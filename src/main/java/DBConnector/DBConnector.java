package DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnector extends SQLException {
    static Connection connectionMySQL;
    static List<String> resultQuery = new ArrayList<>();
    static int columnCount;

    public static boolean checkExist(String loginUser) throws SQLException {
        connectionMySQL();
        Statement st = connectionMySQL.createStatement();
        PreparedStatement prep = connectionMySQL.prepareStatement("select * from users WHERE USER_NAME='" + loginUser + "' ;");
        ResultSet rs = prep.executeQuery();
        columnCount = rs.getMetaData().getColumnCount();
        rs.next();
        for (int elements = 1; elements <= columnCount; elements++) {
            resultQuery.add(rs.getString(elements));
            System.out.println(rs.getString(elements));
        }
        rs.close();
        st.close();
        return true;
    }

    public static void connectionMySQL() {
        try {
            connectionMySQL = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Amir0013981064");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
