package DBConnector;

import UserDashBoard.UserDashBoard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogInChecker {
    static Connection connectionMySQL = DBConnector.connectionToDB();
    static List<String> resultQuery = new ArrayList<>();
    static int columnCount;
    static PreparedStatement prepUserFind;
    static PreparedStatement prepArticleFind;
    static Statement statement;
    static ResultSet resultSetUser;
    static ResultSet resultSetArticle;
    static boolean isExist;
    static List<String> userInfo = new ArrayList<>();

    public static boolean checkExist(String userNameInput, String logInPassword) throws SQLException {
        statement = connectionMySQL.createStatement();
        prepUserFind = connectionMySQL.prepareStatement("select * from user WHERE USER_NAME='" + userNameInput + "' ;");
        resultSetUser = prepUserFind.executeQuery();
        columnCount = resultSetUser.getMetaData().getColumnCount();
        resultSetUser.next();
        if (resultSetUser.getString("ID") == null) {
            isExist = false;
        } else {
            for (int elements = 1; elements <= columnCount; elements++) {
                resultQuery.add(resultSetUser.getString(elements));
                System.out.println(resultSetUser.getString(elements));
            }
            checkPassword(logInPassword, connectionMySQL);
            isExist = true;
        }
        connectionMySQL.close();
        resultSetUser.close();
        statement.close();
        return isExist;
    }

    public static boolean checkPassword(String logInPassword, Connection connectionMySQL) throws SQLException {
        if (resultSetUser.getString("PASSWORD").equals(logInPassword)) {
            prepArticleFind = connectionMySQL.prepareStatement("select count(*) as countArticles,USER_ID from article WHERE USER_ID = '" + resultSetUser.getString("ID") + "';");
            resultSetArticle = prepArticleFind.executeQuery();
            resultSetArticle.next();
            userInfo.add(resultSetUser.getString("USER_NAME"));
            userInfo.add(resultSetArticle.getString("countArticles"));
            userInfo.add(String.valueOf(resultSetArticle.getInt("USER_ID")));
            UserDashBoard.dashBoard(userInfo, connectionMySQL);
            return true;
        } else {
            System.out.println("Your Password Is Incorrect");
            return false;
        }
    }
}
