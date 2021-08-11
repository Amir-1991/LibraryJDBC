package Users;

import java.time.LocalDate;

/**
 * This Class Declare All Users Attribute And
 * With Setter And Getter Methods Gives Values
 *
 * @author Amir
 * @version 1.0.0
 * @since August 2021
 */
public class Users {

    private String nationalCode;
    private LocalDate birthDay;
    private String userName;
    private String password;
    private Long id;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
