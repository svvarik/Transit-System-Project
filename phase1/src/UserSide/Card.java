package UserSide;

import java.util.ArrayList;
import java.util.Date;

import TransitSide.CardMachine;
import TransitSide.Station;
import TransitSide.SubwayStation;
import TransitSide.BusStation;

public class Card {

    private static int idAssigner = 0;

    private int cardId;
    private double balance;
    private ArrayList<Trip> allTrips = new ArrayList<>();
    private CardHolder owner;
    private boolean suspended;
    private Date lastEffectiveTap;
    private double amountSinceLastEffectiveTap;


    public Card(CardHolder owner) {
        this.balance = 19;
        this.suspended = false;
        this.owner = owner;
        this.cardId = idAssigner;
        Card.idAssigner += 1;
        this.amountSinceLastEffectiveTap = 0;
        this.lastEffectiveTap = null;
    }

    public ArrayList<Trip> getTrips() { return allTrips; }

    public Date getLastEffectiveTap() {
        return lastEffectiveTap;
    }

    public void setLastEffectiveTap(Date lastEffectiveTap) {
        this.lastEffectiveTap = lastEffectiveTap;
    }

    public double getAmountSinceLastEffectiveTap() {
        return amountSinceLastEffectiveTap;
    }

    public void setAmountSinceLastEffectiveTap(double amountSinceLastEffectiveTap) {
        this.amountSinceLastEffectiveTap = amountSinceLastEffectiveTap;
    }

    public void addAmountSinceLastEffectiveTap(double amountToAdd) {
        this.amountSinceLastEffectiveTap = amountSinceLastEffectiveTap + amountToAdd;
    }

    public boolean isWithinTimeLimit(){
        Date d = new Date();
        if (this.lastEffectiveTap != null)
            return (Math.abs(this.lastEffectiveTap.getTime() - d.getTime()) < 7200000);
        return false;
    }

    public Station getLastStation(){
        return this.allTrips.get(this.allTrips.size()-1).getEnd().getStation();
    }

    public void resetLastEffective(){
        setLastEffectiveTap(new Date());
        setAmountSinceLastEffectiveTap(0);
    }

    public int getCardID() {
        return cardId;
    }

    public double getBalance() {
        return balance;
    }

    public void suspendCard() {
        this.suspended = true;
    }

    public CardHolder getOwner(){
        return this.owner;
    }

    public CardMachine getLastCardMachineTapped(){return this.allTrips.get(this.allTrips.size()-1).getEnd();}

    /**
     * Adds one of three dollar amounts - 10, 20, 50 - to the card's
     * balance.
     *
     * @param value the value proposed to be added
     *
     */
    public void addValue(double value) {
        if (this.suspended) {
            // Throw some exception
        } else {
            if (value == 10 | value == 20 | value == 50) {
                this.balance += value;
            } else {
                // Didn't work, throw exception
            }
        }
    }

    // TODO: Handle exceptions - not enough balance left etc.
    public void deductValue(double fare){
        this.balance -= fare;
    }

  /**
   * Initializes or ends a trip depending on user location, and deducts fare if required.
   *
   * <p>This method will handle exceptions such as incomplete previous trips or current trips as
   * specified by incomplete trip handlers.
   *
   * @param cm the cardmachine this card is tapped on
   */
  // TODO: Figure out proper return type
  // If card is suspended, what should we return?
  public void tapCard(CardMachine cm) {
    if (!suspended) {
      if (cm.getStation() instanceof BusStation) { // if bus:
        if (cm.isEntrance()) { // if entrance:
          if(getLastCardMachineTapped().isEntrance()){ //checks if the card was tapped last at an entrance
            deductValue(5);
            this.allTrips.get(this.allTrips.size()-1).endTrip();
          }
          Trip newTrip = new Trip();
          this.allTrips.add(newTrip);
          // Calculate fare
          // Deduct fare from this card
        } else { // is exit so we end trip
          if(!getLastCardMachineTapped().isEntrance()){ //checks if the card was tapped last at an exit
            deductValue(5);
          } else {
            this.allTrips.get(allTrips.size() - 1).endTrip();
          }
        }
      } else if (cm.getStation() instanceof SubwayStation) {
        if (cm.isEntrance()) {
          if(getLastCardMachineTapped().isEntrance()){
            deductValue(5);
            this.allTrips.get(this.allTrips.size()-1).endTrip();
          }
          Trip newTrip = new Trip();
          this.allTrips.add(newTrip);
        } else { // is exist so we end trip and calc fare
          if(!getLastCardMachineTapped().isEntrance()){ //checks if the card was tapped last at an exit
              deductValue(5);
          } else {
              this.allTrips.get(allTrips.size() - 1).endTrip();
          }
          // Calculate fare
          // Deduct fare from this card
        }
      } else {
        // TransitSide.CardMachine either has not been initialized properly or is an invalid cardmachine
        System.out.println("Error");
      }
    }else{
        System.out.println("UserSide.Card is Suspended");
    }
  }
}
