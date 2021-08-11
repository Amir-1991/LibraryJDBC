package ContentManagement;

/**
 * userInfo Have All Information Of User And This Information Are
 * LogInChecker Class In That Class userInfo Is public And Here We
 * Chose Values
 *
 * @author Amir
 * @version 1.0.0
 * @since August 2021
 */

import static DBConnector.LogInChecker.userInfo;

import DBConnector.ContentManagementDB;
import UserDashBoard.UserDashBoard;
import Articles.Articles;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.List;

/**
 * This Class First Declare Static Variables Because This Variables
 * Works In All Methods In This Class So One Time Declare And Any Time Chose It
 *
 * @author Amir
 * @version 1.0.0
 * @since August 2021
 */
public class ArticleManagement {
    static List<String> articleInformation = new ArrayList<>();
    static Scanner inputUser = new Scanner(System.in);
    static Articles articles = new Articles();
    static String inputArticle;
    static int randomID;
    static int catId;

    /**
     * This Enum Variable Has Title Of All Information For
     * Creat A Article And With This creatArticle Can Handle
     * Any Information Although This Variable Help Us For Expansion Of Program
     * Just Enough Other Information Add In This After Add Your Operations
     * To switch We Care It For You
     *
     * @author Amir
     * @version 1.0.0
     * @since August 2021
     */
    enum InputMsg {id, title, brief, content, creatDate, isPublished, userId, categoryId, save}

    static ArticleManagement.InputMsg[] enums = ArticleManagement.InputMsg.values();

    /**
     * First Step For Creat A Article Is This Method With This Method Gives
     * Any Information In User And Many Information Set By System For Example
     * ID Always Generate And Set By System Automatic
     *
     * @throws SQLException
     * @author Amir
     * @version 1.0.0
     * @since August 2021
     */
    public static void creatArticle() throws SQLException {
        for (ArticleManagement.InputMsg inputMsg : enums) {
            if (inputMsg.equals(InputMsg.save)) {
                ContentManagementDB.creatArticle(articleInformation);
                System.out.println("Congratulations Your Article Now Is Accessibility ");
                UserDashBoard.dashBoard();
            }
            if (inputMsg.equals(InputMsg.creatDate)) {
                articleInformation.add(String.valueOf(LocalDate.now()));
            }
            if (inputMsg.equals(InputMsg.userId)) {
                articleInformation.add(userInfo.get(2));
            }
            showNewArticleMessage(inputMsg);
            switch (inputMsg) {
                case id:
                    Random idInput = new Random();
                    randomID = idInput.nextInt(1000 + 1);
                    articleInformation.add(0, String.valueOf(randomID));
                    break;
                case title:
                    inputArticle = inputUser.next();
                    articles.setTitle(inputArticle);
                    articleInformation.add(articles.getTitle());
                    break;
                case brief:
                    inputArticle = inputUser.next();
                    articles.setBrief(inputArticle);
                    articleInformation.add(articles.getBrief());
                    break;
                case content:
                    inputArticle = inputUser.next();
                    articles.setContent(inputArticle);
                    articleInformation.add(articles.getContent());
                    break;
                case isPublished:
                    System.out.println("isPublished Means You Want Other Users See Your Articles \n" +
                            "NOTE: Default isPublished Is False Means Just You Can See This Article \n" +
                            "For Watching AnyOne Press 1 Else 0 \n");
                    inputArticle = inputUser.next();
                    articles.setPublished(Integer.valueOf(inputArticle));
                    if (inputArticle.equals("1")) {
                        articleInformation.add(String.valueOf(1));
                    } else if (inputArticle.equals("0")) {
                        articleInformation.add(String.valueOf(0));
                    }
                    break;
                case categoryId:
                    ContentManagementDB.seeAllCategory();
                    System.out.println("Please Type Your Category: \n" +
                            "If Your Category Dose Not Exist Here Can Creat Category \n" +
                            "Do You Want Creat Category ? y/n ");
                    inputArticle = inputUser.next();
                    switch (inputArticle) {
                        case "y":
                            CategoryManagement.creatCategory();
                            break;
                        case "n":
                            System.out.println("GOOD LUCK " + userInfo.get(0) + "");
                            UserDashBoard.dashBoardMenu();
                    }
                    catId = ContentManagementDB.findCategory(inputArticle);
                    articles.setCategoryId(catId);
                    articleInformation.add(String.valueOf(catId));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * For Better Running A Dynamic Program We Needs Method Can Generate Messages
     * Automatically And Show Us This Method Can Do This And Show Any Message We Want
     *
     * @param inputMsg
     * @author Amir
     * @versoin 1.0.0
     * @since August 2021
     */
    public static void showNewArticleMessage(InputMsg inputMsg) {
        if (!inputMsg.equals(InputMsg.id)) {
            if (!inputMsg.equals(InputMsg.creatDate)) {
                if (!inputMsg.equals(InputMsg.save)) {
                    if (!inputMsg.equals(InputMsg.userId)) {
                        System.out.println("Please Enter Your " + inputMsg);
                    }
                }
            }
        }
    }
}
