package DBConnector;

import static DBConnector.DBConnector.connectionMySQL;
import static DBConnector.LogInChecker.userInfo;

import ContentManagement.ArticleManagement;
import ContentManagement.CategoryManagement;
import com.mysql.cj.jdbc.result.ResultSetImpl;
import com.mysql.cj.protocol.ResultsetRows;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.sql.*;

public class UserOperation {
    static List<String> categoryName = new ArrayList<>();
    static List<String> articleName = new ArrayList<>();
    static List<String> myArticle = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static PreparedStatement prepAllCategory;
    static PreparedStatement prepAllArticle;
    static PreparedStatement prepMyArticle;
    static ResultSet resultAllCategory;
    static ResultSet resultAllArticle;
    static ResultSet resultMyArticle;
    static Statement statementUser;
    static ResultsetRows allRowsCategory;
    static ResultsetRows allRowsArticles;
    static ResultsetRows allMyArticle;
    static String scannerInput;
    static int myArt;
    static int catID;
    static int cat;
    static int art;

    public static void seeArticles() throws SQLException {
        statementUser = connectionMySQL.createStatement();
        prepAllArticle = connectionMySQL.prepareStatement("select art.TITLE as Articles,art.CATEGORY_ID as catID FROM article art order by art.CATEGORY_ID ASC ;");
        prepAllCategory = connectionMySQL.prepareStatement("select cat.TITLE as Category, cat.ID as catID FROM category cat ;");
        resultAllArticle = prepAllArticle.executeQuery();
        resultAllCategory = prepAllCategory.executeQuery();
        allRowsCategory = ((ResultSetImpl) resultAllCategory).getRows();
        allRowsArticles = ((ResultSetImpl) resultAllArticle).getRows();
        for (cat = 0; cat <= allRowsCategory.size(); cat++) {
            if (resultAllCategory.next()) {
                categoryName.add(cat, resultAllCategory.getString("Category"));
                System.out.println(categoryName.get(cat));
                for (art = 0; art <= allRowsArticles.size(); art++) {
                    if (resultAllArticle.next()) {
                        catID = resultAllArticle.getInt("catID");
                        while ((cat + 1) == catID) {
                            articleName.add(art, resultAllArticle.getString("Articles"));
                            System.out.println(art + ": " + articleName.get(art));
                        }
                        break;
                    } else {
                        System.out.println("No Article In Library Do You Want Creat New Library? y");
                        scannerInput = scanner.next();
                        if (scannerInput.equals("y")) {
                            ArticleManagement.creatArticle();
                            break;
                        }
                    }
                }
            } else {
                System.out.println("No Category In Library Do You Want Creat New Category? y");
                scannerInput = scanner.next();
                if (scannerInput.equals("y")) {
                    CategoryManagement.creatCategory();
                }
            }
        }
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
                System.out.println("Sorry You Dont Have Article");
            }
        }
    }
}
