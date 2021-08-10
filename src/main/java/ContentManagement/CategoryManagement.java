package ContentManagement;

import DBConnector.ContentManagementDB;
import Category.Category;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.List;

public class CategoryManagement {
    static List<String> categoryInformation = new ArrayList<>();
    static Scanner inputUser = new Scanner(System.in);
    static Category category = new Category();
    static String inputCategory;
    static int randomID;

    enum InputMsg {id, title, description, creatBy, save}

    static CategoryManagement.InputMsg[] enums = CategoryManagement.InputMsg.values();

    public static void creatCategory(List<String> userInfo) throws SQLException {
        for (CategoryManagement.InputMsg inputMsg : enums) {
            if (inputMsg.equals(CategoryManagement.InputMsg.save)) {
                ContentManagementDB.creatCategory(categoryInformation);
                System.out.println("Congratulations Your Category Now Is Accessibility ");
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
