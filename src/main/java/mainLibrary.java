import SignUp.SignUp;
import LogIn.LogIn;

import java.io.IOException;
import java.util.Scanner;

public class mainLibrary extends IOException {
    static int firsOperation;
    static String exception;

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        System.out.println("Welcome To Biggest Article Library In The World :D \n" +
                "1: SignUp \n" +
                "2: LogIn \n" +
                "3: Exit ");
        Scanner scanner = new Scanner(System.in);
        firsOperation = scanner.nextInt();
        try {
            switch (firsOperation) {
                case 1:
                    SignUp.signUp();
                    break;
                case 2:
                    LogIn.logIn();
                    break;
                case 3:
                    System.out.println("Thanks For Watching Me :) \n" +
                            "GOODBYE Dear ");
                default:
                    System.out.println(exception);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

