package SignUp;

import DBConnector.UsersManagement;
import LogIn.LogIn;
import Users.Users;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.time.*;

/**
 * This Class First Declare Static Variables Because This Variables
 * Works In All Methods In This Class So One Time Declare And Any Time Chose It
 *
 * @author Amir
 * @version 1.0.0
 * @since August 2021
 */
public class SignUp {
    static List<String> userInformation = new ArrayList<>();
    static Scanner inputUsers = new Scanner(System.in);
    static String regexPattern = "\\d{10}";
    static Users newUser = new Users();
    public static boolean isFirstTime; //isFirstTime Called Here For All Class Has Access
    public static String generateID; //generateID Called Here For All Class Has Access
    static LocalDate birthDay;
    static String inputUser;
    static int randomID;

    enum InputMsg {id, userName, nationalCode, birthDay, save}

    static InputMsg[] enums = InputMsg.values();

    /**
     * This Method Give Any Information For Sign Up Users And After This
     * Called Method Creat and Send Parameters For Creat
     *
     * @throws SQLException
     */
    public static void signUp() throws SQLException {
        for (SignUp.InputMsg inputMsg : enums) {
            if (inputMsg.equals(InputMsg.save)) {
                generateID = userInformation.get(0);
                UsersManagement.creatUser(userInformation);
                System.out.println("Congratulations Your Register Account Has Successful Please LohIn In Your Account ");
                isFirstTime = true;
                LogIn.logIn();
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
                    birthDay = LocalDate.parse(inputUser);
                    newUser.setBirthDay(birthDay);
                    userInformation.add(String.valueOf(newUser.getBirthDay()));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * List Of Enums Parameter To This Method And Here Can Output Dynamical
     * Information About LogIn Users
     *
     * @param inputMsg
     */
    public static void showSignInMessage(InputMsg inputMsg) {
        if (!inputMsg.equals(InputMsg.id)) {
            System.out.println("Please Enter Your " + inputMsg);
        }
    }
}
