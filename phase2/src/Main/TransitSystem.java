package Main;

import Data.TransitData;
import TransitSide.CardMachine;
import TransitSide.FareManager;
import TransitSide.Station;
import UserSide.*;

import java.util.ArrayList;

public class TransitSystem {

    /**
     * a FareManager Specific to this TransitSystem
     */
    private FareManager tm = new FareManager(2, 0.5, 6, 7200000);
    private TapManager tapManager = new TapManager();

    private ArrayList<AdminUser> adminUsers;
    private ArrayList<CardHolder> transitCardHolders;
    private ArrayList<Station> stations;
    private ArrayList<Trip> allTrips;
    private TransitData transitData;

    /**
     * Constructs a new TransitSystem
     */
    public TransitSystem(){
        this.transitCardHolders = new ArrayList<>();
        this.stations = new ArrayList<>();
        this.allTrips = new ArrayList <>();
        this.transitData = new TransitData(this);
        this.adminUsers = new ArrayList <>();
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
     * this method returns this TransitSystem's TransitData
     * @return this TransitSystem's TransitData
     */
    public TransitData getTransitData() {
        return transitData;
    }

    /**
     * This parameter creats a new CardHolder and adds it to this TransitSystem's cardholders
     * and returns true if everything goes right
     *
     * @param name name of a new cardholder
     * @param email email of the new cardholder
     * @return returns true if everything goes right
     */
    public boolean addCardHolder(String name, String email){
        for (CardHolder ch: this.transitCardHolders){
            if (ch.getEmail().equals(email)){
                System.out.println("User email is already in system!");
                return false;
            }
        }
        CardHolder tempCardholder = new CardHolder(name, email, this);
        this.transitCardHolders.add(tempCardholder);
        return true;
    }

    /**
     * Add admin user to list of admin users
     * @param name
     * @param email
     * @return true if successful, false if fails
     */
    public boolean addAdminUser(String name, String email){
        for (AdminUser au: this.adminUsers){
            if (au.getEmail().equals(email)){
                System.out.println("User email is already in system!");
                return false;
            }
        }
        this.adminUsers.add(new AdminUser(name, email));
        return true;
    }

    /**
     * Returns admin user with given email
     * @param email
     * @return admin user with given email
     */
    public AdminUser findAdminUser(String email){
        for (AdminUser au: this.adminUsers) {
            if (au.getEmail().equals(email)){
                return au;
            }
        }
        return null;
    }

    /**
     * Adds a station to this TransitSystem's stations
     *
     * @param station Station to be added to this objects stations
     */
    public void addStation(Station station) {
        this.stations.add(station);
    }

    /**
     * Adds a Trip to this TransitSystem's trips
     * @param t  to be added to this objects trips
     */
    public void addTrip(Trip t){
        this.allTrips.add(t);
    }

    /**
     * Returns the CardHolder with the given email
     * @param chEmail email of the CardHolder
     * @return returns a the CardHolder that has the email passed to the method
     */
    public CardHolder findCardHolder(String chEmail){
        for (CardHolder ch: this.transitCardHolders) {
            if (ch.getEmail().equals(chEmail)){
                return ch;
            }
        }
        return null;
    }

    /**
     * Returns the Card with the given ID
     * @param cID ID of the Card
     * @return returns the Card with the ID that was passed to the method
     */
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

    /**
     * Returns the entrance CardMachine with the given ID
     * @param cmID ID of the CardMachine
     * @return returns the entrance CardMachine with the ID that was passed to the method
     */
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

    /**
     * Returns the exit CardMachine with the given ID
     * @param cmID ID if the CarMachine
     * @return returns the exit CardMachine with the ID that was passed to the method
     */
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

    public ArrayList <Trip> getAllTrips() {
        return allTrips;
    }

    public TapManager getTapManager(){
        return this.tapManager;
    }
}
