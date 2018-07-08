import java.util.ArrayList;

/** This admin is used to analyze the buisness statistics
 * For example it compares overall revenues to the operating costs
 *
 * This class is also responsible for a daily report for the governement to analyze the TTC statistics */
public class AdminUser{

    private int overallRevenue = 0;
    private int operatingCosts;
    private ArrayList<Trip> allTrips;

    public AdminUser (int costs) {
        operatingCosts = costs;
        ArrayList<Trip> allTrips = new ArrayList<>();
    }

    public int dailyProfits () {
        return overallRevenue - operatingCosts;
    }

    public void addRevenue(int fare) {
        overallRevenue += fare;
    }

    public void reset() {
        overallRevenue = 0;
        ArrayList<Trip> allTrips = new ArrayList<>();
    }

    public void addTrip(Trip newtrip) {
        allTrips.add(newtrip);
    }

    public void calculateOperatingCosts(){}

}

