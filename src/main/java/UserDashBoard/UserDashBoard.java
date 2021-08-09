package UserDashBoard;

import DBConnector.UserOperation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserDashBoard {
    static int userChoiceOperation;
    static Scanner scanner = new Scanner(System.in);

    public static void dashBoard(List<String> userInfo, Connection connectionMySQL) throws SQLException {
        System.out.println("Welcome User " + userInfo.get(0) + " Dear \n" +
                "Your Articles In Here IS " + userInfo.get(1) + " Number \n" +
                "You Are At Home Choice Anyone And Do Anything :) ");
        dashBoardMenu(userInfo,connectionMySQL);
    }

    private static void dashBoardMenu(List<String> userInfo,Connection connectionMySQL) throws SQLException {
        System.out.println("1: See All Article \n" +
                "2: Creat New Category \n" +
                "3: Creat New Article \n" +
                "4: See Your Articles");

        userChoiceOperation = scanner.nextInt();
        switch (userChoiceOperation) {
            case 1:
                UserOperation.seeArticles(connectionMySQL);
                break;
            case 2:
                UserOperation.creatCategory(connectionMySQL);
                break;
            case 3:
                UserOperation.creatArticle(connectionMySQL);
                break;
            case 4:
                UserOperation.seeMyArticle(userInfo,connectionMySQL);
                break;
            default: break;
        }
    }
}
