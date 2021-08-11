package DBConnector;

/**
 * connectionMySQL Have All Information Of Connector TO DB And This Information Are
 * DBConnector Class In That Class connectionMySQL Is public And Here We
 * Chose Connection
 *
 * @author Amir
 * @version 1.0.0
 * @since August 2021
 */
import static DBConnector.DBConnector.connectionMySQL;

import LogIn.LogIn;
import UserDashBoard.UserDashBoard;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 * This Class First Declare Static Variables Because This Variables
 * Works In All Methods In This Class So One Time Declare And Any Time Chose It
 *
 * @author Amir
 * @version 1.0.0
 * @since August 2021
 */
public class LogInChecker {
    public static List<String> userInfo = new ArrayList<>();
    static List<String> resultQuery = new ArrayList<>();
    static PreparedStatement prepArticleFind;
    static PreparedStatement prepUserFind;
    static ResultSet resultSetArticle;
    static ResultSet resultSetUser;
    static int columnCount;
    static boolean isExist;
    static int elements;

    /**
     * Class CheckExist With Params And Connection To DB Can Know User Name In DB Table Users
     * Has Exist Or Not Exist And After Called Other Method For Check Password This Method
     * Return Boolean If userNameInput Cant Find Return False
     *
     * @param logInPassword
     * @param userNameInput
     * @throws SQLException
     * @return isExist
     * @author Amir
     * @version 1.0.0
     * @since August 2021
     */
    public static boolean checkExist(String userNameInput, String logInPassword) throws SQLException {
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
            checkPassword(logInPassword);
            isExist = true;
        }
        return isExist;
    }

    /**
     * Class CheckPassword With logInPassword Param And Connection To DB Can Know logInPassword
     * Is True For Current User Or False And After If HAs True Called Other Method dashBoard
     * Return False If Password Dose Not Match
     *
     * @param logInPassword
     * @throws SQLException
     * @return
     * @author Amir
     * @version 1.0.0
     * @since August 2021
     */
    public static boolean checkPassword(String logInPassword) throws SQLException {
        if (resultSetUser.getString("PASSWORD").equals(logInPassword)) {
            prepArticleFind = connectionMySQL.prepareStatement("select count(*) as countArticles,USER_ID from article WHERE USER_ID = '" + resultSetUser.getString("ID") + "';");
            resultSetArticle = prepArticleFind.executeQuery();
            resultSetArticle.next();
            userInfo.add(resultSetUser.getString("USER_NAME"));
            userInfo.add(resultSetArticle.getString("countArticles"));
            userInfo.add(resultSetUser.getString("ID"));
            UserDashBoard.dashBoard();
            return true;
        } else {
            System.out.println("Your Password Is Incorrect");
            LogIn.logIn();
            return false;
        }
    }

    /**
     * Class CheckPasswordDashboard Until User Was LogIn Check Input Password With Here
     * Password And Return True If Password HAs Match
     *
     * @throws SQLException
     * @return
     * @author Amir
     * @version 1.0.0
     * @since August 2021
     */
    public static boolean checkPasswordDashboard(String userInput) throws SQLException {
        if (resultSetUser.getString("PASSWORD").equals(userInput)) {
            return true;
        }
        return false;
    }
}
