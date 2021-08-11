package LogIn;

/**
 * isFirstTime Has A Boolean Variable And This Information Are
 * SignUp Class In That Class isFirstTime Is public And Here We
 * Chose Values AnyOne Want Sign Up In Library isFirstTime Value Has Ture
 * Means This User Is New And After Sign Up Must Be SignIn And Change Password
 *
 * @author Amir
 * @version 1.0.0
 * @since August 2021
 */

import static SignUp.SignUp.isFirstTime;

import DBConnector.LogInChecker;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * This Class First Declare Static Variables Because This Variables
 * Works In All Methods In This Class So One Time Declare And Any Time Chose It
 *
 * @author Amir
 * @version 1.0.0
 * @since August 2021
 */
public class LogIn {
    static Scanner logInUsers = new Scanner(System.in);
    static String logInPassword;
    static String userNameInput;
    static String logInUser;
    static boolean isExist;

    enum LogInMsg {userName, password}

    static LogIn.LogInMsg[] enums = LogIn.LogInMsg.values();

    /**
     * This Class Give User Name And Password An User For LogIn
     *
     * @throws SQLException
     */
    public static void logIn() throws SQLException {
        for (LogIn.LogInMsg logInMsg : enums) {
            isFirst(isFirstTime);
            showLogInMessage(logInMsg);
            logInUser = logInUsers.next();
            switch (logInMsg) {
                case userName:
                    userNameInput = logInUser;
                    break;
                case password:
                    logInPassword = logInUser;
                    break;
                default:
                    break;
            }
        }
        isUser(userNameInput, logInPassword);
    }

    /**
     * In LogIn Time If User Want LogIn For First Time See Message
     * About System Set National Code For Password By Default
     *
     * @param isFirstTime
     */
    public static void isFirst(boolean isFirstTime) {
        if (isFirstTime) {
            System.out.println("By Default Your Password Is Your NationalCode Please Change It \n" +
                    "Thanks Library Administrator");
        }
    }

    /**
     * Until AnyOne Want LogIn To Library And System Cant Find User Name Input In DB
     * This Method Called And User See Message About This Exception
     *
     * @param userNameInput
     * @param logInPassword
     * @throws SQLException
     */
    public static void isUser(String userNameInput, String logInPassword) throws SQLException {
        isExist = LogInChecker.checkExist(userNameInput, logInPassword);
        if (!isExist) {
            System.out.println("This User Not Available !!!");
        }
    }

    /**
     * List Of Enums Parameter To This Method And Here Can Output Dynamical
     * Information About LogIn Users
     *
     * @param logInMsg
     */
    public static void showLogInMessage(LogIn.LogInMsg logInMsg) {
        System.out.println("Please Enter Your " + logInMsg);
    }
}
