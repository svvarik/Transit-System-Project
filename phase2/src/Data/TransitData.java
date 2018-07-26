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
    private TransitSystem ts;

    /**
     * Constructs a new TransitData Object
     */
    public TransitData(TransitSystem ts){
        this.allFaresCollected = new ArrayList<>();
        this.allTrips = new ArrayList<>();
        this.cardIDCounter = new CardIDCounter();
        this.ts = ts;
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
        for (Double d : this.allFaresCollected) {
            totalFare += d;
        }
        return totalFare;
    }

    /**
     * Return the number of stations reached.
     *
     */
    public int stationsReached(){
        return this.allTrips.size() * 2;
    }

    public void dailyReport(){
        System.out.println("Total fare collected: " + this.totalFareAmount());
        System.out.println("Total stations reached: " + this.stationsReached());
    }
}
