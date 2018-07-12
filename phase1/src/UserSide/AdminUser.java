package UserSide;

import Data.TransitData;

import java.util.ArrayList;

/** This admin is used to analyze the buisness statistics
 *
 * This class is also responsible for a daily report for the governement to analyze the TTC statistics */
public class AdminUser{

    /**
     * constructs a new instance of AdminUser
     */
    public AdminUser () {
    }

    public double getTotalRevenue(TransitData td) {
        return td.totalFareAmount();
    }
}

