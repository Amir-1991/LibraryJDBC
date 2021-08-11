package DBConnector;

/**
 * userInfo And generateID And isFirstTime Have All Information Of User And This Information Are
 * LogInChecker And SignUp Class In That Classes userInfo And generateID And isFirstTime Is public And Here We
 * Chose Values
 *
 * @author Amir
 * @version 1.0.0
 * @since August 2021
 */

import static DBConnector.LogInChecker.userInfo;
import static SignUp.SignUp.generateID;
import static SignUp.SignUp.isFirstTime;

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
public class UsersManagement {
    static Connection connectionMySQL = DBConnector.connectionToDB();
    static PreparedStatement prepAllUserInfo;
    static Statement statementUser;
    static int resultAllCategory;

    /**
     * This Method Give List Of User Information In User With userInformation Param And Creat A Statement
     * With Statement And Using A Query Running A ExecuteUpdate And Creat New User
     *
     * @param userInformation
     * @throws SQLException
     * @author Amir
     * @version 1.0.0
     * @since August 2021
     */
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

    /**
     * This Method Give String Of User userInput In User With userInput Param And Creat A Statement
     * With Statement And Using A Query Running A ExecuteUpdate And Update User Name
     *
     * @param userInput
     * @throws SQLException
     * @author Amir
     * @version 1.0.0
     * @since August 2021
     */
    public static void updateUserName(String userInput) throws SQLException {
        statementUser = connectionMySQL.createStatement();
        prepAllUserInfo = connectionMySQL.prepareStatement("UPDATE user SET USER_NAME = (?) WHERE ID = (?);");
        prepAllUserInfo.setString(1, userInput);
        prepAllUserInfo.setString(2, userInfo.get(2));
        resultAllCategory = prepAllUserInfo.executeUpdate();
        System.out.println("User Name Has Ben Changed Now We Called You As: " + userInput + " ");
    }

    /**
     * This Method Give String Of Password In User With newPassword Param And Creat A Statement
     * With Statement And Using A Query Running A ExecuteUpdate And Update User Password
     *
     * @param newPassword
     * @throws SQLException
     * @author Amir
     * @version 1.0.0
     * @since August 2021
     */
    public static void updateUserPassword(String newPassword) throws SQLException {
        statementUser = connectionMySQL.createStatement();
        prepAllUserInfo = connectionMySQL.prepareStatement("UPDATE user SET PASSWORD = (?) WHERE ID = (?);");
        prepAllUserInfo.setString(1, newPassword);
        if (isFirstTime) {
            prepAllUserInfo.setString(2, generateID);
        } else {
            prepAllUserInfo.setString(2, userInfo.get(2));
        }
        resultAllCategory = prepAllUserInfo.executeUpdate();
        System.out.println("Your Password Now Is Update");
    }
}


