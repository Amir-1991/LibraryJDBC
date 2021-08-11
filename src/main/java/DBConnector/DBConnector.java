package DBConnector;

import java.sql.*;

/**
 * In This Class We Can Connect To DB And After Connecting With Declare
 * public Variables Any Class Want To Connect Just Enough import Above Class
 *
 * @author Amir
 * @version 1.0.0
 * @since August 2021
 */
public class DBConnector extends SQLException {
    public static Connection connectionMySQL = DBConnector.connectionToDB();

    /**
     * All Information's Needs For Connect To DB Declare Here
     * For Connect Is Necessary Know UrlDB For Connection
     * userDB And passwordDB For LogIn To DB
     *
     * @author Amir
     * @version 1.0.0
     * @since August 2021
     */
    static String UrlDB = "jdbc:mysql://localhost:3306/library";
    static String userDB = "root";
    static String passwordDB = "Amir0013981064";

    /**
     * This Method With Necessary Information Connect To DB And Return Connection For
     * Used In Any Class In Program Any Time We Called This Method Give Connection Us
     *
     * @return Connection
     * @author Amir
     * @version 1.0.0
     * @since August 2021
     */
    public static Connection connectionToDB() {
        try {
            connectionMySQL = DriverManager.getConnection(UrlDB, userDB, passwordDB);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connectionMySQL;
    }
}
