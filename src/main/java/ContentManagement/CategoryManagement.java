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

import DBConnector.ContentManagementDB; //In ANy Class We Need Connect To DB This Line Can Do For Us
import Category.Category;
import UserDashBoard.UserDashBoard;

import java.sql.SQLException;
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
public class CategoryManagement {
    static List<String> categoryInformation = new ArrayList<>();
    static Scanner inputUser = new Scanner(System.in);
    static Category category = new Category();
    static String inputCategory;
    static int randomID;

    /**
     * This Enum Variable Has Title Of All Information For
     * Creat A Category And With This creatCategory Can Handle
     * Any Information Although This Variable Help Us For Expansion Of Program
     * Just Enough Other Information Add In This After Add Your Operations
     * To switch We Care It For You
     *
     * @author Amir
     * @version 1.0.0
     * @since August 2021
     */
    enum InputMsg {id, title, description, creatBy, save}

    static CategoryManagement.InputMsg[] enums = CategoryManagement.InputMsg.values();

    /**
     * First Step For Creat A Category Is This Method With This Method Gives
     * Any Information In User And Many Information Set By System For Example
     * ID Always Generate And Set By System Automatic
     *
     * @throws SQLException
     * @author Amir
     * @version 1.0.0
     * @since August 2021
     */
    public static void creatCategory() throws SQLException {
        for (CategoryManagement.InputMsg inputMsg : enums) {
            if (inputMsg.equals(CategoryManagement.InputMsg.save)) {
                ContentManagementDB.creatCategory(categoryInformation);
                System.out.println("Congratulations Your Category Now Is Accessibility ");
                UserDashBoard.dashBoardMenu();
            }
            if (inputMsg.equals(CategoryManagement.InputMsg.creatBy)) {
                categoryInformation.add(userInfo.get(2));
            }
            showNewCategoryMessage(inputMsg);
            switch (inputMsg) {
                case id:
                    Random idInput = new Random();
                    randomID = idInput.nextInt(1000 + 1);
                    categoryInformation.add(0, String.valueOf(randomID));
                    break;
                case title:
                    inputCategory = inputUser.next();
                    category.setTitle(inputCategory);
                    categoryInformation.add(category.getTitle());
                    break;
                case description:
                    inputCategory = inputUser.next();
                    category.setDescription(inputCategory);
                    categoryInformation.add(category.getDescription());
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
    public static void showNewCategoryMessage(InputMsg inputMsg) {
        if (!inputMsg.equals(InputMsg.id)) {
            if (!inputMsg.equals(InputMsg.creatBy)) {
                if (!inputMsg.equals(InputMsg.save)) {
                    System.out.println("Please Enter Your " + inputMsg);
                }
            }
        }
    }
}
