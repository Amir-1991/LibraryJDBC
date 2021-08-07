package SignUp;

import Users.Users;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SignUp {

    static Users newUser = new Users();
    static Scanner inputUsers = new Scanner(System.in);
    static String inputUser;
    static boolean isUnique;
    static LocalDate birthDay;
    static String regexPattern = "\\d*^{10}";

    enum inputMsg {userName, nationalCode, birthDay}

    static inputMsg[] enums = inputMsg.values();

    public static void signUp() {
        for (SignUp.inputMsg inputMsg : enums) {
            showSignInMessage(inputMsg);
            inputUser = inputUsers.next();
            switch (inputMsg) {
                case userName:
                    isUnique = isUnique(inputUser);
                    if (isUnique == true) {
                    newUser.setUserName(inputUser);
                    }
                    break;
                case nationalCode:
                    if (inputUser.matches(regexPattern)) {
                        newUser.setNationalCode(inputUsers.next());
                        newUser.setPassword(inputUsers.next());
                    } else {
                        System.out.println("Please Enter Valid National Code");
                    }
                    break;
                case birthDay:
                    birthDay = LocalDate.parse(inputUsers.next());
                    newUser.setBirthDay(birthDay);
                    break;
                default:
                    break;
            }
        }
    }

    public static void showSignInMessage(inputMsg inputMsg) {
        System.out.println("Please Enter Your " + inputMsg);
    }

    public static boolean isUnique(String inputUser) {

        return true;
    }
}
