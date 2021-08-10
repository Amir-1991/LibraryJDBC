package DBConnector;

import UserDashBoard.UserDashBoard;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class LogInChecker {
    static Connection connectionMySQL = DBConnector.connectionToDB();
    static List<String> resultQuery = new ArrayList<>();
    static List<String> userInfo = new ArrayList<>();
    static PreparedStatement prepArticleFind;
    static PreparedStatement prepUserFind;
    static ResultSet resultSetArticle;
    static ResultSet resultSetUser;
    static Statement statement;
    static int columnCount;
    static boolean isExist;
    static int elements;

    public static boolean checkExist(String userNameInput, String logInPassword, boolean isFirstTime) throws SQLException {
        statement = connectionMySQL.createStatement();
        prepUserFind = connectionMySQL.prepareStatement("select * from user WHERE USER_NAME='" + userNameInput + "' ;");
        resultSetUser = prepUserFind.executeQuery();
        columnCount = resultSetUser.getMetaData().getColumnCount();
        resultSetUser.next();
        if (resultSetUser.getString("ID") == null) {
            isExist = false;
        } else {
            for (elements = 1; elements <= columnCount; elements++) {
                resultQuery.add(resultSetUser.getString(elements));
            }
            checkPassword(logInPassword, connectionMySQL, isFirstTime);
            isExist = true;
        }
        connectionMySQL.close();
        resultSetUser.close();
        statement.close();
        return isExist;
    }

    public static boolean checkPassword(String logInPassword, Connection connectionMySQL, boolean isFirstTime) throws SQLException {
        if (resultSetUser.getString("PASSWORD").equals(logInPassword)) {
            prepArticleFind = connectionMySQL.prepareStatement("select count(*) as countArticles,USER_ID from article WHERE USER_ID = '" + resultSetUser.getString("ID") + "';");
            resultSetArticle = prepArticleFind.executeQuery();
            resultSetArticle.next();
            userInfo.add(resultSetUser.getString("USER_NAME"));
            userInfo.add(resultSetArticle.getString("countArticles"));
            userInfo.add(String.valueOf(resultSetArticle.getInt("USER_ID")));
            UserDashBoard.dashBoard(userInfo, connectionMySQL, isFirstTime);
            return true;
        } else {
            System.out.println("Your Password Is Incorrect");
            return false;
        }
    }

    public static boolean checkPasswordDashboard(String userInput) throws SQLException {
        if (resultSetUser.getString("PASSWORD").equals(userInput)) {
            return true;
        }
        return false;
    }
}
