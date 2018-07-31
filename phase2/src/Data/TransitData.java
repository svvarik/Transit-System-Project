package Data;

import Main.TransitSystem;
import TransitUsers.Trip;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * TransitData class keeps track of the trips taken within a TransitSystem. It also saves the total fare collected
 */

public class TransitData implements Serializable {

    private static final long serialVersionUID = 473658;

    private ArrayList<Trip> allTrips;
    private CardIDCounter cardIDCounter;

    private ArrayList<Double> allFaresCollected;

    /**
     * Constructs a new TransitData Object
     */
    public TransitData(TransitSystem ts){
        this.allFaresCollected = new ArrayList<>();
        this.allTrips = new ArrayList<>();
        this.cardIDCounter = new CardIDCounter();
    }

    public CardIDCounter getCardIDCounter(){
        return this.cardIDCounter;
    }

    public ArrayList<Trip> getAllTrips(){
        return this.allTrips;
    }

    /**
     * adds a trip's fare to the total collected fare of this TransitDate object
     * @param fare first parameter, a double value
     */
    public void addFareCollected(double fare){
        allFaresCollected.add(fare);
    }

    /**
     * Returns the summation of all fares collected.
     *
     * @return the sum of all fares
     */
    public double totalFareAmount(){
        double totalFare = 0;
        for(Trip t: this.allTrips){
            totalFare += t.getFare();
        }
        return totalFare;
    }

    public double getMonthlyFareAmount(int month, int year){
        double totalFare = 0;
        for (Trip t: this.allTrips){
            if((t.getStarDate().getMonth() == month) && (t.getStarDate().getYear() == year)){
                totalFare = totalFare + t.getFare();
            }
        }
        return totalFare;
    }

    /**
     * Return the number of stations reached.
     *
     */
    public int stationsReached(){
        int numStationsReached = 0;
        for (Trip t: allTrips){
            if(t.getStart() != null){
                numStationsReached++;
            }
            if(t.getEnd() != null){
                numStationsReached++;
            }
        }
        return numStationsReached;
    }

    public void dailyReport(){
        System.out.println("Total fare collected: " + this.totalFareAmount());
        System.out.println("Total stations reached: " + this.stationsReached());
    }
}
