package UserDashBoard;

import static DBConnector.LogInChecker.userInfo;
import static SignUp.SignUp.isFirstTime;
import ContentManagement.CategoryManagement;
import ContentManagement.ArticleManagement;
import DBConnector.ContentManagementDB;
import DBConnector.UsersManagement;
import DBConnector.UserOperation;
import DBConnector.LogInChecker;
import LogIn.LogIn;

import java.sql.SQLException;
import java.util.Scanner;

public class UserDashBoard {
    static String regexPatternPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
    static Scanner scanner = new Scanner(System.in);
    static int userChoiceOperation;
    static boolean isCorrect;
    static String userInput;

    public static void dashBoard() throws SQLException {
        if (isFirstTime) {
            System.out.println("You Are New User Please Insert New Password: \n" +
                    "NOTE: Password Is Words And Digit Numbers Min 8 And max Is 20 Character");
            do {
                userInput = scanner.next();
            }while(!userInput.matches(regexPatternPassword));
            UsersManagement.updateUserPassword(userInput);
            isFirstTime = false;
            LogIn.logIn();
        }
        System.out.println("Welcome User " + userInfo.get(0) + " Dear \n" +
                "Your Articles In Here Is " + userInfo.get(1) + " Number \n" +
                "You Are At Home Choice Anyone And Do Anything :) ");
        dashBoardMenu();
    }

    public static void dashBoardMenu() throws SQLException {
        System.out.println("1: See All Article \n" +
                "2: Creat New Category \n" +
                "3: Creat New Article \n" +
                "4: See Your Articles \n" +
                "5: Setting ");
        userChoiceOperation = scanner.nextInt();
        switch (userChoiceOperation) {
            case 1:
                UserOperation.seeArticles();
                break;
            case 2:
                CategoryManagement.creatCategory();
                break;
            case 3:
                ArticleManagement.creatArticle();
                break;
            case 4:
                UserOperation.seeMyArticle();
                break;
            case 5:
                userManagement();
            default:
                break;
        }
    }

    public static void userManagement() throws SQLException {
        System.out.println("1: Change User Name \n" +
                "2: Change Password \n" +
                "3: Remove My Article \n" +
                "4: Go Back \n ");
        userChoiceOperation = scanner.nextInt();
        switch (userChoiceOperation) {
            case 1:
                System.out.println("Please Enter New User Name: ");
                userInput = scanner.next();
                UsersManagement.updateUserName(userInput);
                dashBoardMenu();
                break;
            case 2:
                System.out.println("Please Enter OLd Password First:");
                userInput = scanner.next();
                isCorrect = LogInChecker.checkPasswordDashboard(userInput);
                if (isCorrect) {
                    System.out.println("Please Enter New Password Now:");
                    userInput = scanner.next();
                    do {
                        userInput = scanner.next();
                    }while(!userInput.matches(regexPatternPassword));
                    UsersManagement.updateUserPassword(userInput);
                    dashBoardMenu();
                } else {
                    System.out.println("Your Password Dose Not Match Please For Change Password Go Back Again");
                    dashBoardMenu();
                }
                break;
            case 3:
                UserOperation.seeMyArticle();
                System.out.println("For Remove Please Enter Article Name: ");
                userInput = scanner.next();
                ContentManagementDB.removeArticle(userInput);
                dashBoardMenu();
                break;
            case 4:
                dashBoardMenu();
                break;
        }
    }
}
