package SignUp;

import DBConnector.UsersManagement;
import LogIn.LogIn;
import Users.Users;
import com.github.eloyzone.jalalicalendar.DateConverter;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SignUp {
    static Connection connectionMySQL;
    static Users newUser = new Users();
    static Scanner inputUsers = new Scanner(System.in);
    static String inputUser;
    static LocalDate birthDay;
    static String regexPattern = "\\d{10}";
    static boolean isFirstTime = true;
    static int illegalAge = 6570;
    static int userAge;
    static LocalDate birthDayCompare;
    static DateConverter dateConverter = new DateConverter();
    static List<String> userInformation = new ArrayList<>();
    static int randomID;

    enum InputMsg {id, userName, nationalCode, birthDay, save}

    static InputMsg[] enums = InputMsg.values();

    public static void signUp() throws SQLException {
        for (SignUp.InputMsg inputMsg : enums) {
            if (inputMsg.equals(InputMsg.save)) {
                UsersManagement.creatUser(userInformation);
                System.out.println("Congratulations Your Register Account Has Successful Please LohIn In Your Account ");
                LogIn.logIn(isFirstTime);
            }
            showSignInMessage(inputMsg);
            switch (inputMsg) {
                case id:
                    Random idInput = new Random();
                    randomID = idInput.nextInt(1000 + 1);
                    userInformation.add(0, String.valueOf(randomID));
                    break;
                case userName:
                    inputUser = inputUsers.next();
                    newUser.setUserName(inputUser);
                    userInformation.add(newUser.getUserName());
                    break;
                case nationalCode:
                    while (!inputUser.matches(regexPattern)) {
                        inputUser = inputUsers.next();
                        if (inputUser.matches(regexPattern)) {
                            newUser.setNationalCode(inputUser);
                            newUser.setPassword(inputUser);
                            userInformation.add(newUser.getNationalCode());
                            userInformation.add(newUser.getPassword());
                            break;
                        } else {
                            System.out.println("Please Enter Valid National Code \n" +
                                    "NOTE: NationalCode Must Be Number 10 Character Without Letters You Enter: \n" +
                                    "" + inputUser + " And " + inputUser.length() + " Character ");
                        }
                    }
                    break;
                case birthDay:
                    inputUser = inputUsers.next();
//                    if (LocalDate.now().compareTo(LocalDate.parse(inputUser)) > illegalAge) {
                    birthDay = LocalDate.parse(inputUser);
                    newUser.setBirthDay(birthDay);
                    userInformation.add(String.valueOf(newUser.getBirthDay()));
                    break;
//                    } else {
//                        userAge = LocalDate.now().compareTo(LocalDate.parse(inputUser)) / 365;
//                        System.out.println("You Are " + userAge + " Years Old");
//                    }
//                    break;
                default:
                    break;

            }

        }
    }

    public static void showSignInMessage(InputMsg inputMsg) {
        if (!inputMsg.equals(InputMsg.id)) {
            System.out.println("Please Enter Your " + inputMsg);
        }
//        switch (inputMsg) {
//            case birthDay:
//                System.out.println("Date Format Is : yyyy-MM-dd Like 1991-11-10 \n" +
//                        "NOTE: Your Age Must Be Upper Than 18 Years Old \n" +
//                        "Good Luck ");
//        }
    }

//    public static boolean isUnique(String inputUser) throws SQLException {
//        DBConnector.checkUnique(inputUser);
//
//        if (inputUser.equals("1")) {
//        }
//        return true;
//    }
}
