package Data;

import UserSide.Trip;

import java.util.ArrayList;

/**
 * TransitData class keeps track of the trips taken within a TransitSystem. It also saves the total fare collected
 */

public class TransitData {

    private ArrayList<Trip> allCompletedTrips;
    
    private ArrayList<Double> allFaresCollected;

    /**
     * Constructs a new TransitData Object
     */
    public TransitData(){
        allCompletedTrips = new ArrayList<>();
        allFaresCollected = new ArrayList<>();
    }

    /**
     * This method adds a completed trip to the arrayList of allCompletedTrips
     * @param t first parameter, a trip object
     */
    public void addCompletedTrip(Trip t){
        allCompletedTrips.add(t);
    }

    /**
     * adds a trip's fare to the total collected fare of this TransitDate object
     * @param fare first parameter, a double value
     */
    public void addFareCollected(double fare){
        allFaresCollected.add(fare);
    }

    /**
     * Returns the number of stations reached in the entire transit system.
     * The number of stations reached is currently just one per trip.
     *
     * @return the total number of stations
     */

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
