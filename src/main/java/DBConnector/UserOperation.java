package DBConnector;

import static DBConnector.DBConnector.connectionMySQL;
import static DBConnector.LogInChecker.userInfo;

import com.mysql.cj.jdbc.result.ResultSetImpl;
import com.mysql.cj.protocol.ResultsetRows;
import ContentManagement.ArticleManagement;
import UserDashBoard.UserDashBoard;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.sql.*;

public class UserOperation {
    static Scanner wantOperation = new Scanner(System.in);
    static List<String> categoryName = new ArrayList<>();
    static List<String> articleName = new ArrayList<>();
    static List<String> myArticle = new ArrayList<>();
    static PreparedStatement prepAllArticle;
    static PreparedStatement prepMyArticle;
    static ResultsetRows allRowsArticles;
    static ResultsetRows allMyArticle;
    static ResultSet resultAllArticle;
    static ResultSet resultMyArticle;
    static String wantOperationInput;
    static Statement statementUser;
    static int allArt;
    static int myArt;

    public static void seeArticles() throws SQLException {
        statementUser = connectionMySQL.createStatement();
        prepAllArticle = connectionMySQL.prepareStatement("select art.TITLE as Articles,cat.TITLE as Category FROM article art\n" +
                "JOIN category cat on cat.ID = art.CATEGORY_ID WHERE art.IS_PUBLISHED = '1'\n" +
                "order by art.CATEGORY_ID ASC ;");
        resultAllArticle = prepAllArticle.executeQuery();
        allRowsArticles = ((ResultSetImpl) resultAllArticle).getRows();
        System.out.println("Category Name \t Article Name ");
        for (allArt = 0; allArt < allRowsArticles.size(); allArt++) {
            if (resultAllArticle.next()) {
                articleName.add((allArt), resultAllArticle.getString("Articles"));
                categoryName.add((allArt), resultAllArticle.getString("Category"));
                System.out.println((allArt + 1) + ": " + categoryName.get(allArt) + "\t \t \t" + articleName.get(allArt));
            } else {
                System.out.println("There's No Article In Library Do You Want Creat Article? y/n ");
                wantOperationInput = wantOperation.next();
                switch (wantOperationInput) {
                    case "y":
                        ArticleManagement.creatArticle();
                        break;
                    case "n":
                        System.out.println("Automatically We Send You In Your DashBoard ");
                        break;
                }
            }
        }
        UserDashBoard.dashBoard();
    }

    public static void seeMyArticle() throws SQLException {
        statementUser = connectionMySQL.createStatement();
        prepMyArticle = connectionMySQL.prepareStatement("select TITLE From article WHERE USER_ID = '" + userInfo.get(2) + "'");
        resultMyArticle = prepMyArticle.executeQuery();
        allMyArticle = ((ResultSetImpl) resultMyArticle).getRows();
        for (myArt = 0; myArt < allMyArticle.size(); myArt++) {
            if (resultMyArticle.next()) {
                myArticle.add((myArt), resultMyArticle.getString("TITLE"));
                System.out.println((myArt + 1) + ": " + myArticle.get(myArt));
            } else {
                System.out.println("Sorry You Dont Have Article Do You Want Creat Your First Article? y/n ");
                wantOperationInput = wantOperation.next();
                switch (wantOperationInput) {
                    case "y":
                        ArticleManagement.creatArticle();
                        break;
                    case "n":
                        System.out.println("Automatically We Send You In Your DashBoard ");
                        break;
                }
            }
        }
    }
}
