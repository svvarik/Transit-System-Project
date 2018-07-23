package UserSide;

import Data.TransitData;

import java.io.Serializable;
import java.util.ArrayList;

/** This admin is used to analyze the buisness statistics
 *
 * This class is also responsible for a daily report for the governement to analyze the TTC statistics */
public class AdminUser implements Serializable {

    private String name;
    private String email;

    /**
     * constructs a new instance of AdminUser
     */
    public AdminUser (String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public double getTotalRevenue(TransitData td) {
        return td.totalFareAmount();
    }
}

