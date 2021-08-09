package DBConnector;

import com.mysql.cj.jdbc.result.ResultSetImpl;
import com.mysql.cj.protocol.ResultsetRows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserOperation {
    static Statement statementUser;
    static PreparedStatement prepAllCategory;
    static PreparedStatement prepAllArticle;
    static PreparedStatement prepMyArticle;
    static ResultSet resultAllCategory;
    static ResultSet resultAllArticle;
    static ResultSet resultMyArticle;
    static ResultsetRows allRowsCategory;
    static ResultsetRows allRowsArticles;
    static ResultsetRows allMyArticle;
    static List<String> categoryName = new ArrayList<>();
    static List<String> articleName = new ArrayList<>();
    static List<String> myArticle = new ArrayList<>();
    static int cat;
    static int art;
    static int myArt;
    static int catID;

    public static void seeArticles(Connection connectionMySQL) throws SQLException {
        statementUser = connectionMySQL.createStatement();
        prepAllArticle = connectionMySQL.prepareStatement("select art.TITLE as Articles,art.CATEGORY_ID as catID FROM article art order by art.CATEGORY_ID ASC ;");
        prepAllCategory = connectionMySQL.prepareStatement("select cat.TITLE as Category, cat.ID as catID FROM category cat ;");
        resultAllArticle = prepAllArticle.executeQuery();
        resultAllCategory = prepAllCategory.executeQuery();
        allRowsCategory = ((ResultSetImpl) resultAllCategory).getRows();
        allRowsArticles = ((ResultSetImpl) resultAllArticle).getRows();
        for (cat = 0; cat <= allRowsCategory.size(); cat++) {
            resultAllCategory.next();
            categoryName.add(cat, resultAllCategory.getString("Category"));
            System.out.println(categoryName.get(cat));
            for (art = 0; art <= allRowsArticles.size(); art++) {
                resultAllArticle.next();
                catID = resultAllArticle.getInt("catID");
                while ((cat + 1) == catID) {
                    articleName.add(art, resultAllArticle.getString("Articles"));
                    System.out.println(art + ": " + articleName.get(art));
                }
                break;
            }
        }
    }

    public static void creatCategory(Connection connectionMySQL) throws SQLException {

    }

    public static void creatArticle(Connection connectionMySQL) throws SQLException {
    }

    public static void seeMyArticle(List<String> userInfo, Connection connectionMySQL) throws SQLException {
        statementUser = connectionMySQL.createStatement();
        prepMyArticle = connectionMySQL.prepareStatement("select TITLE From article WHERE USER_ID = '" + userInfo.get(2) + "'");
        resultMyArticle = prepMyArticle.executeQuery();
        allMyArticle = ((ResultSetImpl) resultMyArticle).getRows();
        for (myArt = 0; myArt < allMyArticle.size(); myArt++) {
            resultMyArticle.next();
            myArticle.add((myArt), resultMyArticle.getString("TITLE"));
            System.out.println((myArt + 1) + ": " + myArticle.get(myArt));
        }
    }
}
