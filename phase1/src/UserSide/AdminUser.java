package UserSide;

import java.util.ArrayList;

/** This admin is used to analyze the buisness statistics
 * For example it compares overall revenues to the operating costs
 *
 * This class is also responsible for a daily report for the governement to analyze the TTC statistics */
public class AdminUser{

    private int overallRevenue = 0;
    private int operatingCosts;
    private ArrayList<Trip> allTrips;

    /**
     * constructs a new instance of AdminUser
     * @param costs this AdminUser's costs
     */
    public AdminUser (int costs) {
        operatingCosts = costs;
        ArrayList<Trip> allTrips = new ArrayList<>();
    }

    /**
     * returns the daily profits
     * @return returns the daily profits
     */
    public int dailyProfits () {
        return overallRevenue - operatingCosts;
    }

    /**
     * adds revenue to this AdminUser
     * @param fare the amount of revenue
     */
    public void addRevenue(int fare) {
        overallRevenue += fare;
    }

    /**
     * resets this AdminUser
     */
    public void reset() {
        overallRevenue = 0;
        ArrayList<Trip> allTrips = new ArrayList<>();
    }

    /**
     * adds a trip to this AdminUser trips
     * @param t trip to be added to this AdminUser's allTrips
     */
    public void addTrip(Trip t) {
        allTrips.add(t);
    }

    /**
     * calculates the operating costs for this AdminUser
     */
    public void calculateOperatingCosts(){}

}

