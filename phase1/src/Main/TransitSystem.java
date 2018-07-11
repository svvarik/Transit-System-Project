package Main;

import TransitSide.FareManager;
import TransitSide.Station;
import UserSide.CardHolder;
import UserSide.Trip;

import java.util.ArrayList;
import java.util.Date;

public class TransitSystem {

    /**
     * a FareManager Specific to this TransitSystem
     */
    private FareManager tm = new FareManager();

    private ArrayList<CardHolder> transitCardHolders;
    private ArrayList<Station> stations;
    private Date opened;
    private Date closed;
    private ArrayList<Trip> allTrips;

    /**
     * Constructs a new TransitSystem
     */
    public TransitSystem(){
        this.transitCardHolders = new ArrayList<>();
        this.stations = new ArrayList<>();
        this.opened = new Date();
    }

    /**
     * Constructs a TransitSystem with an ArrayList of the TransitSystem's stations, cardholders
     * @param stations the ArrayList of this TransitSystem's stations
     * @param transitCardHolders the ArrayList of this TransitSystem's cardholders
     */
    public TransitSystem(ArrayList<Station> stations, ArrayList<CardHolder> transitCardHolders){
        this.stations = stations;
        this.transitCardHolders = transitCardHolders;
    }

    /**
     * this method returns this TransitSystem's FareManager
     * @return this TransitSystem's FareManager
     */

    public FareManager getTransitManager(){
        return this.tm;
    }

    /**
     * This parameter creats a new CardHolder and adds it to this TransitSystem's cardholders and returns true if everything goes right
     * @param name name of a new cardholder
     * @param email email of the new cardholder
     * @return returns true if everything goes right
     */
    public boolean  addCardHolder(String name, String email){
        for (CardHolder ch: this.transitCardHolders){
            if (ch.getEmail().equals(email)){
                return false;
            }
        }
        CardHolder tempCardholder = new CardHolder(name, email);
        this.transitCardHolders.add(tempCardholder);
        return true;
    }

    /**
     * adds a station to this TransitSystem's stations
     * @param station Station to be added to this objects stations
     */
    public void addStation(Station station) {
        this.stations.add(station);
    }

    /**
     * adds a Trip to this TransitSystem's trips
     * @param t  to be added to this objects trips
     */
    public void addTrip(Trip t){
        this.allTrips.add(t);
    }

    /**
     * exits the system
     */

    public void exitSystem(){
        this.closed = new Date();
        // Write to events.txt
    }
}
