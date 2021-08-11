package DBConnector;

/**
 * userInfo And connectionMySQL Have All Information Of User And Connector TO DB This Information Are
 * LogInChecker And DBConnector Class In That Class userInfo And connectionMySQL Is public And Here We
 * Chose Values And Connection
 *
 * @author Amir
 * @version 1.0.0
 * @since August 2021
 */

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

/**
 * This Class First Declare Static Variables Because This Variables
 * Works In All Methods In This Class So One Time Declare And Any Time Chose It
 *
 * @author Amir
 * @version 1.0.0
 * @since August 2021
 */
public class UserOperation {
    static Scanner wantOperation = new Scanner(System.in);
    static List<String> myArticlesCat = new ArrayList<>();
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

    /**
     * This Method Creat A Statement With Statement And Using A Query Running A ExecuteQuery And
     * Export All Articles
     *
     * @throws SQLException
     * @author Amir
     * @version 1.0.0
     * @since August 2021
     */
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

    /**
     * This Method Creat A Statement With Statement And Using A Query Running A ExecuteQuery And
     * Export All Articles Make By Current User
     *
     * @throws SQLException
     * @author Amir
     * @version 1.0.0
     * @since August 2021
     */
    public static void seeMyArticle() throws SQLException {
        statementUser = connectionMySQL.createStatement();
        prepMyArticle = connectionMySQL.prepareStatement("select art.TITLE as Article,cat.TITLE as Category From article art\n" +
                "JOIN category cat on cat.ID = art.CATEGORY_ID\n" +
                "WHERE USER_ID = '" + userInfo.get(2) + "'");
        resultMyArticle = prepMyArticle.executeQuery();
        allMyArticle = ((ResultSetImpl) resultMyArticle).getRows();
        System.out.println("Category Name \t Article Name ");
        for (myArt = 0; myArt < allMyArticle.size(); myArt++) {
            if (resultMyArticle.next()) {
                myArticlesCat.add((myArt), resultMyArticle.getString("Category"));
                myArticle.add((myArt), resultMyArticle.getString("Article"));
                System.out.println((myArt + 1) + ": " + myArticlesCat.get(myArt) + "\t \t \t" + myArticle.get(myArt));
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
