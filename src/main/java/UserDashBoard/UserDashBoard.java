package UserDashBoard;

import ContentManagement.ArticleManagement;
import ContentManagement.CategoryManagement;
import DBConnector.ContentManagementDB;
import DBConnector.UsersManagement;
import DBConnector.UserOperation;
import DBConnector.LogInChecker;
import LogIn.LogIn;

import java.sql.SQLException;
import java.sql.Connection;
import java.util.Scanner;
import java.util.List;

public class UserDashBoard {
    static String regexPatternPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
    static Scanner scanNewPassword = new Scanner(System.in);
    static Scanner scanner = new Scanner(System.in);
    static int userChoiceOperation;
    static String newPassword ;
    static boolean isCorrect;
    static String userInput;

    public static void dashBoard(List<String> userInfo, Connection connectionMySQL, boolean isFirstTime) throws SQLException {
        if (isFirstTime) {
            System.out.println("You Are New User Please Insert New Password: \n" +
                    "NOTE: Password Is Words And Digit Numbers Min 8 And max Is 20 Character");
            do {
                newPassword = scanNewPassword.next();
            }while(!newPassword.matches(regexPatternPassword));
            UsersManagement.updateUserPassword(userInfo, newPassword);
            isFirstTime = false;
            LogIn.logIn(isFirstTime);
        }
        System.out.println("Welcome User " + userInfo.get(0) + " Dear \n" +
                "Your Articles In Here IS " + userInfo.get(1) + " Number \n" +
                "You Are At Home Choice Anyone And Do Anything :) ");
        dashBoardMenu(userInfo, connectionMySQL);
    }

    public static void dashBoardMenu(List<String> userInfo, Connection connectionMySQL) throws SQLException {
        System.out.println("1: See All Article \n" +
                "2: Creat New Category \n" +
                "3: Creat New Article \n" +
                "4: See Your Articles \n" +
                "5: Setting ");
        userChoiceOperation = scanner.nextInt();
        switch (userChoiceOperation) {
            case 1:
                UserOperation.seeArticles(connectionMySQL);
                break;
            case 2:
                CategoryManagement.creatCategory(userInfo);
                break;
            case 3:
                ArticleManagement.creatArticle(userInfo,connectionMySQL);
                break;
            case 4:
                UserOperation.seeMyArticle(userInfo, connectionMySQL);
                break;
            case 5:
                userManagement(userInfo, connectionMySQL);
            default:
                break;
        }
    }

    public static void userManagement(List<String> userInfo, Connection connectionMySQL) throws SQLException {
        System.out.println("1: Change User Name \n" +
                "2: Change Password \n" +
                "3: Remove My Article \n" +
                "4: Go Back \n ");
        userChoiceOperation = scanner.nextInt();
        switch (userChoiceOperation) {
            case 1:
                System.out.println("Please Enter New User Name: ");
                userInput = scanner.next();
                UsersManagement.updateUserName(userInfo, userInput);
                dashBoardMenu(userInfo, connectionMySQL);
                break;
            case 2:
                System.out.println("Please Enter OLd Password First:");
                userInput = scanner.next();
                isCorrect = LogInChecker.checkPasswordDashboard(userInput);
                if (isCorrect) {
                    System.out.println("Please Enter New Password Now:");
                    userInput = scanner.next();
                    UsersManagement.updateUserPassword(userInfo, userInput);
                    dashBoardMenu(userInfo, connectionMySQL);
                } else {
                    System.out.println("Your Password Dose Not Match Please For Change Password Go Back Again");
                    dashBoardMenu(userInfo, connectionMySQL);
                }
                break;
            case 3:
                UserOperation.seeMyArticle(userInfo, connectionMySQL);
                System.out.println("For Remove Please Enter Article Name: ");
                userInput = scanner.next();
                ContentManagementDB.removeArticle(userInfo, userInput);
                dashBoardMenu(userInfo, connectionMySQL);
                break;
            case 4:
                dashBoardMenu(userInfo, connectionMySQL);
                break;
        }
    }
}
