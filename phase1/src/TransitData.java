import java.util.ArrayList;

public class TransitData {

    private ArrayList<Trip> allCompletedTrips;
    
    private ArrayList<Double> allFaresCollected;

    public TransitData(){
        allCompletedTrips = new ArrayList<>();
        allFaresCollected = new ArrayList<>();
    }

    public void addCompletedTrip(Trip t){
        allCompletedTrips.add(t);
    }

    public void addFareCollected(double fare){
        allFaresCollected.add(fare);
    }

    /**
     * Returns the number of stations reached in the entire transit system.
     * The number of stations reached is currently just one per trip.
     *
     * @return the total number of stations
     */
    // TODO: figure out proper stations reached number, currently only calculates based on num of trips
    // TODO: Figure out how to do this based on dates
    // Does a station reached mean a station tapped on, or only a station where a trip is completed?
    public int numStationsReached(){
        return allCompletedTrips.size();
    }

    /**
     * Returns the summation of all fares collected.
     *
     * @return the sum of all fares
     */
    public double totalFareAmount(){
        double totalFare = 0;
        for (Double d : this.allFaresCollected) {
            totalFare += d;
        }
        return totalFare;
    }
}
