package SignUp;

import LogIn.LogIn;
import Users.Users;
import com.github.eloyzone.jalalicalendar.DateConverter;

import java.sql.SQLException;
import java.time.*;
import java.util.Scanner;

public class SignUp {

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

    enum InputMsg {userName, nationalCode, birthDay}

    static InputMsg[] enums = InputMsg.values();

    public static void signUp() throws SQLException {
        for (SignUp.InputMsg inputMsg : enums) {
            showSignInMessage(inputMsg);
            switch (inputMsg) {
                case userName:
                    inputUser = inputUsers.next();
                    newUser.setUserName(inputUser);
                    break;
                case nationalCode:
                    while (!inputUser.matches(regexPattern)) {
                        inputUser = inputUsers.next();
                        if (inputUser.matches(regexPattern)) {
                            newUser.setNationalCode(inputUser);
                            newUser.setPassword(inputUser);
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
                    if (LocalDate.now().compareTo(LocalDate.parse(inputUser)) > illegalAge) {
                        birthDay = LocalDate.parse(inputUser);
                        newUser.setBirthDay(birthDay);
                        break;
                    } else {
                        userAge = LocalDate.now().compareTo(LocalDate.parse(inputUser)) / 365;
                        System.out.println("You Are " + userAge + " Years Old");
                    }
                default:
                    System.out.println("Congratulations Your Register Account Has Successful Please LohIn In Your Account ");
                    LogIn.logIn(isFirstTime);
                    break;
            }
        }
    }

    public static void showSignInMessage(InputMsg inputMsg) {
        System.out.println("Please Enter Your " + inputMsg);
        switch (inputMsg) {
            case birthDay:
                System.out.println("Date Format Is : yyyy-MM-dd Like 1991-11-10 \n" +
                        "NOTE: Your Age Must Be Upper Than 18 Years Old \n" +
                        "Good Luck ");
        }
    }

//    public static boolean isUnique(String inputUser) throws SQLException {
//        DBConnector.checkUnique(inputUser);
//
//        if (inputUser.equals("1")) {
//        }
//        return true;
//    }
}
