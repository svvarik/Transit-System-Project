package Data;

import Main.TransitSystem;
import UserSide.Trip;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * TransitData class keeps track of the trips taken within a TransitSystem. It also saves the total fare collected
 */

public class TransitData implements Serializable {
    
    private ArrayList<Double> allFaresCollected;
    private TransitSystem ts;

    /**
     * Constructs a new TransitData Object
     */
    public TransitData(TransitSystem ts){
        allFaresCollected = new ArrayList<>();
        this.ts = ts;
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
        return this.ts.getAllTrips().size() * 2;
    }

    public void dailyReport(){
        System.out.println("Total fare collected: " + this.totalFareAmount());
        System.out.println("Total stations reached: " + this.stationsReached());
    }
}
