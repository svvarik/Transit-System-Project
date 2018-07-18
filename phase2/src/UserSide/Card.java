package UserSide;

import java.util.ArrayList;
import java.util.Date;

import TransitSide.CardMachine;
import TransitSide.Station;
import TransitSide.SubwayStation;
import TransitSide.BusStation;
import Main.TransitSystem;

public class Card {

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
        }
    }

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
        }
    }

//  public boolean tapCard(CardMachine cm) {
//    if (!suspended) {
//      if (cm.isEntrance() && this.balance <= 0) {
//        return false;
//      }else{
//        if (cm.getStation() instanceof BusStation) { // if bus:
//          if (firstTap) {
//            resetLastEffective();
//            Trip newTrip = new Trip();
//            newTrip.setStart(cm);
//            this.addTrip(newTrip);
//            deductValue(owner.getTs().getFareManager().getFlatFare());
//            addAmountSinceLastEffectiveTap(owner.getTs().getFareManager().getFlatFare());
//            firstTap = false;
//            return true;
//          } else {
//            tapBusStation(cm);
//            return true;
//          }
//        } else if (cm.getStation() instanceof SubwayStation) {
//          if (firstTap) {
//            Trip newTrip = new Trip();
//            newTrip.setStart(cm);
//            this.addTrip(newTrip);
//            firstTap = false;
//            return true;
//          } else {
//            tapSubwayStation(cm);
//            return true;
//          }
//        } else {
//          // TransitSide.CardMachine either has not been initialized properly or is an invalid
//          // cardmachine
//          System.out.println("There is an error with the Card Machine");
//          return false;
//        }
//      }
//    }else{
//        System.out.println("This card is suspended");
//        return false;
//    }
//  }
//
//  private void tapBusStation(CardMachine cm){
//      if (cm.isEntrance()) { // if entrance:
//          double fare = this.owner.getTs().getFareManager().calcBusFare(this, cm);// Calculate fare
//          if(getLastCardMachineTapped().isEntrance()){ //checks if the card was tapped last at an entrance
//              deductValue(this.owner.getTs().getFareManager().getCapFare());
//              this.allTrips.get(Math.max(this.allTrips.size()-1, 0)).setEnd(cm);
//          }
//          Trip newTrip = new Trip();
//          newTrip.setStart(cm);
//          this.addTrip(newTrip);
//          deductValue(fare);// Deduct fare from this card
//      } else { // is exit so we end trip
//          if(!getLastCardMachineTapped().isEntrance()){ //checks if the card was tapped last at an exit
//              deductValue(this.owner.getTs().getFareManager().getCapFare());
//          } else {
//              this.allTrips.get(Math.max(this.allTrips.size()-1, 0)).setEnd(cm);
//          }
//      }
//  }
//
//  private void tapSubwayStation(CardMachine cm){
//      if (cm.isEntrance()) {
//          if(this.owner.getTs().getFareManager().isDisjoint(this, cm)){
//              resetLastEffective();
//          }
//          if(getLastCardMachineTapped().isEntrance()){
//              deductValue(this.owner.getTs().getFareManager().getCapFare());
//              this.allTrips.get(Math.max(this.allTrips.size()-1, 0)).setEnd(cm);
//          }
//          Trip newTrip = new Trip();
//          newTrip.setStart(cm);
//          this.addTrip(newTrip);
//      } else { // is exist so we end trip and calc fare
//          double fare = this.owner.getTs().getFareManager().calcSubwayFare(this, cm);// Calculate fare
//          if(!getLastCardMachineTapped().isEntrance()){ //checks if the card was tapped last at an exit
//              deductValue(this.owner.getTs().getFareManager().getCapFare());
//          } else {
//              this.allTrips.get(Math.max(this.allTrips.size()-1, 0)).setEnd(cm);
//          }
//          deductValue(fare);// Deduct fare from this card
//      }
//  }


    public TapManager getTm() {
        return tm;
    }

    public void tapCard(){

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
