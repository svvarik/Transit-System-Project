package Main;

import TransitSide.CardMachine;
import TransitSide.FareManager;
import TransitSide.Station;
import UserSide.Card;
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
        this.allTrips = new ArrayList <>();
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

    public FareManager getFareManager(){
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
        CardHolder tempCardholder = new CardHolder(name, email, this);
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

    public CardHolder findCardHolder(String chEmail){
        for (CardHolder ch: this.transitCardHolders) {
            if (ch.getEmail().equals(chEmail)){
                return ch;
            }
        }
        return null;
    }

    public Card findCard(int cID){
        for(CardHolder ch: this.transitCardHolders){
            for(Card card: ch.getCards()){
                if (card.getCardID() == cID){
                    return card;
                }
            }
        }
        return null;
    }

    public CardMachine findEntrance(int cmID){
        for(Station s: this.stations){
            for(CardMachine cm: s.getEntrances()){
                if (cm.getId() == cmID){
                    return cm;
                }
            }
        }
        return null;
    }

    public CardMachine findExit(int cmID){
        for(Station s: this.stations){
            for(CardMachine cm: s.getExits()){
                if (cm.getId() == cmID){
                    return cm;
                }
            }
        }
        return null;
    }

    /**
     * exits the system
     */

    public void exitSystem(){
        this.closed = new Date();
        // Write to events.txt
    }
}
