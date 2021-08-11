import SignUp.SignUp;
import LogIn.LogIn;

/**
 * connectionMySQL Have Connector TO DB This Information Are In
 * DBConnector Class In That Class connectionMySQL Is public And Here We
 * Chose Connection
 *
 * @author Amir
 * @version 1.0.0
 * @since August 2021
 */

import static DBConnector.DBConnector.connectionMySQL;

import java.sql.SQLException;
import java.io.IOException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * This Class First Declare Static Variables Because This Variables
 * Works In All Methods In This Class So One Time Declare And Any Time Chose It
 *
 * @author Amir
 * @version 1.0.0
 * @since August 2021
 */
public class mainLibrary extends IOException {
    static int firsOperation;
    static String exception;
    static Statement statement;

    /**
     * main Is First Step To Running Program And Here Is
     * Declare Connection And Close Connection Finally Here
     *
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        statement = connectionMySQL.createStatement();
        menu();
        connectionMySQL.close();
        statement.close();
    }

    /**
     * The First Thing The User Sees Is menu User Can Choice SignUp Or LogIn
     */
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

