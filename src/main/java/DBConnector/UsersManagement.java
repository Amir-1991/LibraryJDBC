package DBConnector;

import java.sql.*;
import java.util.List;

public class UsersManagement {
    static Connection connectionMySQL = DBConnector.connectionToDB();
    static Statement statementUser;
    static PreparedStatement prepAllUserInfo;
    static int resultAllCategory;

    public static void creatUser(List<String> userInformation) throws SQLException {
        statementUser = connectionMySQL.createStatement();
        prepAllUserInfo = connectionMySQL.prepareStatement("INSERT INTO user(ID,USER_NAME,NATIONAL_CODE,BIRTHDAY,PASSWORD) VALUES (?,?,?,?,?);");
        prepAllUserInfo.setString(1, userInformation.get(0));
        prepAllUserInfo.setString(2, userInformation.get(1));
        prepAllUserInfo.setString(3, userInformation.get(2));
        prepAllUserInfo.setString(4, userInformation.get(4));
        prepAllUserInfo.setString(5, userInformation.get(3));
        resultAllCategory = prepAllUserInfo.executeUpdate();
        System.out.println("success");
    }

    public static void updateUserPassword(){

    }

}
