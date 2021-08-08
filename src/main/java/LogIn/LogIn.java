package LogIn;

import DBConnector.DBConnector;

import java.sql.SQLException;
import java.util.Scanner;

public class LogIn {
    static Scanner logInUsers = new Scanner(System.in);
    static String logInUser;
    static String logInPassword;
    static String userNameInput;

    enum LogInMsg {userName, password}

    static LogIn.LogInMsg[] enums = LogIn.LogInMsg.values();

    public static void logIn(boolean isFirstTime) throws SQLException {
        for (LogIn.LogInMsg logInMsg : enums) {
            isFirst(isFirstTime);
            showLogInMessage(logInMsg);
            logInUser = logInUsers.next();
            switch (logInMsg){
                case userName:
//                    isUser(logInUser);
                    userNameInput = logInUser;
                    break;
                case password:
                    logInPassword = logInUser;
                    break;
                default:break;
            }
        }
        isUser(userNameInput);
    }

    public static void isFirst(boolean isFirstTime) {
        if (isFirstTime ==true){
            System.out.println("By Default Your Password Is Your NationalCode Please After LogIn Change It \n" +
                    "Thanks Library Administrator");
        }
    }

    public static boolean isUser(String logInUser) throws SQLException {
        DBConnector.checkExist(logInUser);
//        if ()

        return true;
    }

    public static void showLogInMessage(LogIn.LogInMsg logInMsg) {
        System.out.println("Please Enter Your " + logInMsg);
    }
}
