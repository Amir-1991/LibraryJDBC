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

import java.util.ArrayList;
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
public class ContentManagementDB {
    static List<String> resultCategory = new ArrayList<>();
    static PreparedStatement prepManegeContent;
    static ResultsetRows categoryCountRow;
    static ResultSet resultSetCategory;
    static Statement statementUser;
    static int resultAllCategory;
    static int categoryCount;
    static int elements;
    static int catID;

    /**
     * This Method Give Article Name In User With userInput Param And Creat A Statement
     * With Statement And Using A Query Running A ExecuteUpdate And Delete Article If
     * This Articles Owner IS Same Current User
     *
     * @param userInput
     * @author Amir
     * @version 1.0.0
     * @since August 2021
     */
    public static void removeArticle(String userInput) throws SQLException {
        statementUser = connectionMySQL.createStatement();
        prepManegeContent = connectionMySQL.prepareStatement("DELETE FROM article WHERE TITLE =(?) AND USER_ID = (?);");
        prepManegeContent.setString(1, userInput);
        prepManegeContent.setString(2, userInfo.get(2));
        resultAllCategory = prepManegeContent.executeUpdate();
    }

    /**
     * This Method Give List Of String An User With categoryInformation Param And Creat A Statement
     * With Statement And Using A Query Running A ExecuteUpdate And Insert Category
     *
     * @param categoryInformation
     * @author Amir
     * @version 1.0.0
     * @since August 2021
     */
    public static void creatCategory(List<String> categoryInformation) throws SQLException {
        statementUser = connectionMySQL.createStatement();
        prepManegeContent = connectionMySQL.prepareStatement("INSERT INTO category(ID, TITLE, DESCRIPTIONS, CREAT_BY) VALUES (?,?,?,?)");
        prepManegeContent.setString(1, categoryInformation.get(0));
        prepManegeContent.setString(2, categoryInformation.get(1));
        prepManegeContent.setString(3, categoryInformation.get(2));
        prepManegeContent.setString(4, categoryInformation.get(3));
        resultAllCategory = prepManegeContent.executeUpdate();
    }

    /**
     * This Method Give List Of String An User With articleInformation Param And Creat A Statement
     * With Statement And Using A Query Running A ExecuteUpdate And Insert New Article
     *
     * @param articleInformation
     * @author Amir
     * @version 1.0.0
     * @since August 2021
     */
    public static void creatArticle(List<String> articleInformation) throws SQLException {
        statementUser = connectionMySQL.createStatement();
        prepManegeContent = connectionMySQL.prepareStatement("INSERT INTO article(ID, TITLE, BRIEF, CONTENT, CREAT_DATE, IS_PUBLISHED, USER_ID, CATEGORY_ID) VALUES (?,?,?,?,?,?,?,?);");
        prepManegeContent.setString(1, articleInformation.get(0));
        prepManegeContent.setString(2, articleInformation.get(1));
        prepManegeContent.setString(3, articleInformation.get(2));
        prepManegeContent.setString(4, articleInformation.get(3));
        prepManegeContent.setString(5, articleInformation.get(4));
        prepManegeContent.setString(6, articleInformation.get(5));
        prepManegeContent.setString(7, articleInformation.get(6));
        prepManegeContent.setString(8, articleInformation.get(7));
        resultAllCategory = prepManegeContent.executeUpdate();
    }

    /**
     * This Method With A Statement And Using A Query Running
     * A ExecuteQuery Show All Categories
     *
     * @author Amir
     * @version 1.0.0
     * @since August 2021
     */
    public static void seeAllCategory() throws SQLException {
        statementUser = connectionMySQL.createStatement();
        prepManegeContent = connectionMySQL.prepareStatement("SELECT TITLE,ID FROM category;");
        resultSetCategory = prepManegeContent.executeQuery();
        categoryCountRow = ((ResultSetImpl) resultSetCategory).getRows();
        resultSetCategory.next();
        for (elements = 0; elements <= categoryCountRow.size(); elements++) {
            resultCategory.add((elements), resultSetCategory.getString("TITLE"));
            System.out.println((elements + 1) + ": " + resultCategory.get(elements));
        }
    }

    /**
     * This Method Give Category Title An User With inputArticle Param And Creat A Statement
     * With Statement And Using A Query Running A ExecuteQuery And Select Find Intended Category
     *
     * @param inputArticle
     * @author Amir
     * @version 1.0.0
     * @since August 2021
     */
    public static int findCategory(String inputArticle) throws SQLException {
        statementUser = connectionMySQL.createStatement();
        prepManegeContent = connectionMySQL.prepareStatement("SELECT TITLE,ID FROM category WHERE TITLE = '" + inputArticle + "';");
        resultSetCategory = prepManegeContent.executeQuery();
        categoryCount = resultSetCategory.getMetaData().getColumnCount();
        if (resultSetCategory.next()) {
            catID = (resultSetCategory.getInt("ID"));
        } else {
            System.out.println("This Category Dose Not Exist");
        }
        return catID;
    }
}
