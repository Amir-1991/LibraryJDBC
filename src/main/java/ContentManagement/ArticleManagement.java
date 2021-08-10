package ContentManagement;

import DBConnector.ContentManagementDB;
import UserDashBoard.UserDashBoard;
import Articles.Articles;

import java.sql.SQLException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.List;

public class ArticleManagement {
    static List<String> articleInformation = new ArrayList<>();
    static Scanner inputUser = new Scanner(System.in);
    static Articles articles = new Articles();
    static String inputArticle;
    static int randomID;
    static int catId;

    enum InputMsg {id, title, brief, content, creatDate, isPublished, userId, categoryId, save}

    static ArticleManagement.InputMsg[] enums = ArticleManagement.InputMsg.values();

    public static void creatArticle(List<String> userInfo, Connection connectionMySQL) throws SQLException {
        for (ArticleManagement.InputMsg inputMsg : enums) {
            if (inputMsg.equals(InputMsg.save)) {
                ContentManagementDB.creatArticle(articleInformation);
                System.out.println("Congratulations Your Category Now Is Accessibility ");
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
                    articles.setPublished(Integer.parseInt(inputArticle));
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
                            CategoryManagement.creatCategory(userInfo);
                            break;
                        case "n":
                            System.out.println("GOOD LUCK " + userInfo.get(0) + "");
                            UserDashBoard.dashBoardMenu(userInfo, connectionMySQL);
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

    public static void showNewArticleMessage(ArticleManagement.InputMsg inputMsg) {
        if (!inputMsg.equals(ArticleManagement.InputMsg.id)) {
            if (!inputMsg.equals(InputMsg.creatDate)) {
                if (!inputMsg.equals(ArticleManagement.InputMsg.save)) {
                    System.out.println("Please Enter Your " + inputMsg);
                }
            }
        }
    }
}
