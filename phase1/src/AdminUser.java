import java.util.ArrayList;

/** This admin compares overall revenues to the operating costs
 *
 * This class is responsible for a daily report for the governement to analyze the TTC statistics */
public class AdminUser{

    int overallRevenue = 0;
    int operatingCosts;

    public AdminUser (int costs) {
        operatingCosts = costs;
    }

    public int dailyProfits () {
        return overallRevenue - operatingCosts;
    }

    public void addRevenue(int fare) {
        overallRevenue += fare;
    }
}

