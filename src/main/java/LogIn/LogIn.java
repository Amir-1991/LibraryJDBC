package LogIn;

import DBConnector.LogInChecker;
import static SignUp.SignUp.isFirstTime;
import java.sql.SQLException;
import java.util.Scanner;

public class LogIn {
    static Scanner logInUsers = new Scanner(System.in);
    static String logInPassword;
    static String userNameInput;
    static String logInUser;
    static boolean isExist;

    enum LogInMsg {userName, password}

    static LogIn.LogInMsg[] enums = LogIn.LogInMsg.values();

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
        isUser(userNameInput,logInPassword);
    }

    public static void isFirst(boolean isFirstTime) {
        if (isFirstTime) {
            System.out.println("By Default Your Password Is Your NationalCode Please Change It \n" +
                    "Thanks Library Administrator");
        }
    }

    public static void isUser(String userNameInput, String logInPassword) throws SQLException {
        isExist = LogInChecker.checkExist(userNameInput,logInPassword);
        if (!isExist) {
            System.out.println("This User Not Available !!!");
        }
    }

    public static void showLogInMessage(LogIn.LogInMsg logInMsg) {
        System.out.println("Please Enter Your " + logInMsg);
    }
}
