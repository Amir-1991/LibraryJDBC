package DBConnector;

import static DBConnector.LogInChecker.userInfo;
import static SignUp.SignUp.generateID;
import static SignUp.SignUp.isFirstTime;

import java.util.List;
import java.sql.*;

public class UsersManagement {
    static Connection connectionMySQL = DBConnector.connectionToDB();
    static PreparedStatement prepAllUserInfo;
    static Statement statementUser;
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
    }

    public static void updateUserName(String userInput) throws SQLException {
        statementUser = connectionMySQL.createStatement();
        prepAllUserInfo = connectionMySQL.prepareStatement("UPDATE user SET USER_NAME = (?) WHERE ID = (?);");
        prepAllUserInfo.setString(1, userInput);
        prepAllUserInfo.setString(2, userInfo.get(2));
        resultAllCategory = prepAllUserInfo.executeUpdate();
        System.out.println("User Name Has Ben Changed Now We Called You As: " + userInput + " ");
    }

    public static void updateUserPassword(String newPassword) throws SQLException {
        statementUser = connectionMySQL.createStatement();
        prepAllUserInfo = connectionMySQL.prepareStatement("UPDATE user SET PASSWORD = (?) WHERE ID = (?);");
        prepAllUserInfo.setString(1, newPassword);
        if (isFirstTime){
            prepAllUserInfo.setString(2, generateID);
        }else {
            prepAllUserInfo.setString(2, userInfo.get(2));
        }
        resultAllCategory = prepAllUserInfo.executeUpdate();
        System.out.println("Your Password Now Is Update");
    }
}


