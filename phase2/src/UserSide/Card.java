package UserSide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import TransitSide.CardMachine;
import TransitSide.Station;
import TransitSide.SubwayStation;
import TransitSide.BusStation;
import Main.TransitSystem;

public class Card implements Serializable {

    private static int idAssigner = 0;

    private int cardId;
    private double balance;
    private ArrayList<Trip> allTrips = new ArrayList<>();
    private CardHolder owner;
    private TapManager tm;


    /**
     * constructs a new Card
     * @param owner this Card's cardHolder
     */
    public Card(CardHolder owner) {
        this.balance = 19;
        this.owner = owner;
        this.cardId = idAssigner;
        Card.idAssigner += 1;
        this.tm = new TapManager(this);
    }

    /**
     * returns all the Trips taken by this Card
     * @return all the Trips taken by this Card
     */
    public ArrayList<Trip> getTrips() { return allTrips; }


    /**
     * returns the last Station the card was tapped at
     * @return the last Station the card was tapped at
     */
    public Station getLastStation(){
        return this.allTrips.get(this.allTrips.size()-1).getEnd().getStation();
    }

    /**
     * return this Card's id
     * @return this Card's id
     */
    public int getCardID() {
        return cardId;
    }

    /**
     * returns this Card's balance
     * @return this Card's balance
     */
    public double getBalance() {
        return balance;
    }


    /**
     * returns this Card's owner
     * @return this Card's CardHolder
     */
    public CardHolder getOwner(){
        return this.owner;
    }

    /**
     * returns the last CardMachine this Card tapped
     * @return the last CardMachine this Card tapped
     */
    public CardMachine getLastCardMachineTapped(){
        ArrayList<CardMachine> tempCMArray = new ArrayList <>();
        if (!this.allTrips.isEmpty()) {
            for (int i = 0; i <= this.allTrips.size() - 1; i++) {
                if (this.allTrips.get(i).getStart() != null) {
                    tempCMArray.add(this.allTrips.get(i).getStart());
                }
                if (this.allTrips.get(i).getEnd() != null) {
                    tempCMArray.add(this.allTrips.get(i).getEnd());
                }
            }
            return tempCMArray.get(tempCMArray.size() - 1);
        }
        return null;
    }

    /**
     * Adds one of three dollar amounts - 10, 20, 50 - to the card's
     * balance.
     *
     * @param value the value proposed to be added
     *
     */
    public boolean addValue(double value) {
        if (this.tm.isSuspended()) {
            System.out.println("This card is suspended");
            return false;
        } else {
            if (value == 10 | value == 20 | value == 50) {
                this.balance += value;
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * deducts the fare from the Card's balance and suspends the Card if the balance goes negative
     * @param fare the amount to be deducted
     */
    public void deductValue(double fare){
        this.balance -= fare;
        this.owner.getTs().getTransitData().addFareCollected(fare);
        this.owner.addMonthlyFareData(fare);
    }
    /**
     * Initializes or ends a trip depending on user location, and deducts fare if required.
     *
     * <p>This method will handle exceptions such as incomplete previous trips or current trips as
     * specified by incomplete trip handlers.
     *
     * @param cm the cardmachine this card is tapped on
     */
    public boolean tapCard(CardMachine cm){
        Boolean contd = tm.toContinue(cm);
        if(contd){
            if(cm.getStation() instanceof SubwayStation){
                tapSubwayStation(cm);
            }
            if(cm.getStation() instanceof  BusStation){
                tapBusStation(cm);
            }
        }else{
            return true;
        }
        return true;
    }

    /**
     * Deducts fare when tapped at a SubwayStation
     * @param cm CardMachine that was tapped at
     */
    private void tapSubwayStation(CardMachine cm){
        if (cm.isEntrance()) {
            if(this.owner.getTs().getFareManager().isDisjoint(this, cm)){
                this.tm.resetLastEffective();
            }
            Trip newTrip = new Trip();
            newTrip.setStart(cm);
            this.addTrip(newTrip);
        }else{ // is exist so we end trip and calc fare
            double fare = this.owner.getTs().getFareManager().calcSubwayFare(this, cm);// Calculate fare
            this.allTrips.get(Math.max(this.allTrips.size()-1, 0)).setEnd(cm);
            System.out.println(fare);
            deductValue(fare);// Deduct fare from this card
            this.owner.addTrip(this.allTrips.get(Math.max(this.allTrips.size()-1, 0)));
        }
    }

    /**
     * Deducts fare when tapped at a SubwayStation
     * @param cm CardMachine that was tapped at
     */
    private void tapBusStation(CardMachine cm){
        if (cm.isEntrance()) { // if entrance:
            if(this.owner.getTs().getFareManager().isDisjoint(this, cm)){
                this.tm.resetLastEffective();
            }
            double fare = this.owner.getTs().getFareManager().calcBusFare(this, cm);// Calculate fare
            Trip newTrip = new Trip();
            newTrip.setStart(cm);
            this.addTrip(newTrip);
            deductValue(fare);// Deduct fare from this card
        }else{ // is exit so we end trip
            this.allTrips.get(Math.max(this.allTrips.size()-1, 0)).setEnd(cm);
            this.owner.addTrip(this.allTrips.get(Math.max(this.allTrips.size()-1, 0)));
        }
    }

    /**
     * Returns the TapManager
     * @return returns the TapManager
     */
    public TapManager getTm() {
        return tm;
    }

    /**
     * returns a String representation of this Card
     * @return String representation of this Card
     */
    public String toString(){
        return "Card ID: " + this.cardId + ", Owner: " + owner.getName() + ", Balance: " + this.balance;
    }

    /**
     * adds a Trip to this Card's allTrips and calls the Card's owner's addTrip to add the Trip to their allTrips
     * @param t the trip to be added
     */
    public void addTrip(Trip t){
        this.allTrips.add(t);
        this.owner.addTrip(t);
    }
}
