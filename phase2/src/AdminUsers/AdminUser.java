package AdminUsers;

import Data.TransitData;

import java.io.Serializable;

/** This admin is used to analyze the buisness statistics
 *
 * This class is also responsible for a daily report for the governement to analyze the TTC statistics */
public class AdminUser implements Serializable {

    private static final long serialVersionUID = 364732;
    private String name;
    private String email;
    private String password;

    /**
     * constructs a new instance of AdminUser
     */
    public AdminUser (String name, String email) {
        this.name = name;
        this.email = email;
    }

    public AdminUser (String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }


    public String getPassword() {
        return password;
    }

    public void setEmail(String newemail) {
        email = newemail;
    }

    public void setName(String newname) {
        email = newname;
    }

    public void setPassword(String newpassword) {
        password = newpassword;
    }


    public double getTotalRevenue(TransitData td) {
        return td.totalFareAmount();
    }

    public boolean isPassCorrect(String pass){
        if (this.password != null) {
            return (this.password.equals(pass)); }
        return false;
    }
}

