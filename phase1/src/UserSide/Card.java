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

    private TransitSystem ts;
    private int cardId;
    private double balance;
    private ArrayList<Trip> allTrips = new ArrayList<>();
    private CardHolder owner;
    private boolean suspended;
    private Date lastEffectiveTap;
    private double amountSinceLastEffectiveTap;

    /**
     * constructs a new Card
     * @param owner this Card's cardHolder
     * @param ts the TransitSystem that this Card belongs to
     */
    public Card(CardHolder owner, TransitSystem ts) {
        this.balance = 19;
        this.suspended = false;
        this.owner = owner;
        this.cardId = idAssigner;
        this.amountSinceLastEffectiveTap = 0;
        this.lastEffectiveTap = null;
        this.ts = ts;
        Card.idAssigner += 1;
    }

    /**
     * returns all the Trips taken by this Card
     * @return all the Trips taken by this Card
     */
    public ArrayList<Trip> getTrips() { return allTrips; }

    /**
     * returns the lasttime the Card was effectively tapped
     * @return the lasttime the Card was effectively tapped
     */
    public Date getLastEffectiveTap() {
        return lastEffectiveTap;
    }

    /**
     * sets a new lastEffectiveTap
     * @param lastEffectiveTap the Date of the lastEffectiveTap
     */
    public void setLastEffectiveTap(Date lastEffectiveTap) {
        this.lastEffectiveTap = lastEffectiveTap;
    }

    /**
     * returns the amount of fare calculated since the lastEffectiveTap
     * @return the amount of fare calculated since the lastEffectiveTap
     */
    public double getAmountSinceLastEffectiveTap() {
        return amountSinceLastEffectiveTap;
    }

    /**
     * sets the amount to the amountSinceLastEffectiveTap
     * @param amountSinceLastEffectiveTap the amount to be set
     */
    public void setAmountSinceLastEffectiveTap(double amountSinceLastEffectiveTap) {
        this.amountSinceLastEffectiveTap = amountSinceLastEffectiveTap;
    }


    /**
     * adds the amount to the amountSinceLastEffectiveTap
     * @param amountToAdd the amount to be added
     */
    public void addAmountSinceLastEffectiveTap(double amountToAdd) {
        this.amountSinceLastEffectiveTap = this.amountSinceLastEffectiveTap + amountToAdd;
    }

    /**
     * returns true if the tap is within the two hours time period since the lastEffectiveTape
     * @return true if the tap is within the two hours time period since the lastEffectiveTape
     */
    public boolean isWithinTimeLimit(){
        Date d = new Date();
        if (this.lastEffectiveTap != null)
            return (Math.abs(this.lastEffectiveTap.getTime() - d.getTime()) < 7200000);
        return false;
    }

    /**
     * returns the last Station the card was tapped at
     * @return the last Station the card was tapped at
     */
    public Station getLastStation(){
        return this.allTrips.get(this.allTrips.size()-1).getEnd().getStation();
    }

    /**
     * resets the lastEffectiveTap
     */
    public void resetLastEffective(){
        setLastEffectiveTap(new Date());
        setAmountSinceLastEffectiveTap(0);
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
     * supspends the Card
     */
    public void suspendCard() {
        this.suspended = true;
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

    /**
     * deducts the fare from the Card's balance and suspends the Card if the balance goes negative
     * @param fare the amount to be deducted
     */
    public void deductValue(double fare){
        this.balance -= fare;
        if(this.balance < 0){
            this.suspendCard();
        }
    }

  /**
   * Initializes or ends a trip depending on user location, and deducts fare if required.
   *
   * <p>This method will handle exceptions such as incomplete previous trips or current trips as
   * specified by incomplete trip handlers.
   *
   * @param cm the cardmachine this card is tapped on
   */
  public void tapCard(CardMachine cm) {
    if (!suspended) {
      if (cm.getStation() instanceof BusStation) { // if bus:
        if (cm.isEntrance()) { // if entrance:
          if(getLastCardMachineTapped().isEntrance()){ //checks if the card was tapped last at an entrance
            deductValue(ts.getTransitManager().getCapFare());
            this.allTrips.get(this.allTrips.size()-1).setEnd(cm);
          }
          Trip newTrip = new Trip();
          newTrip.setStart(cm);
          this.allTrips.add(newTrip);
          double fare = ts.getTransitManager().calcBusFare(this, cm);// Calculate fare
          deductValue(fare);// Deduct fare from this card
        } else { // is exit so we end trip
          if(!getLastCardMachineTapped().isEntrance()){ //checks if the card was tapped last at an exit
            deductValue(ts.getTransitManager().getCapFare());
          } else {
            this.allTrips.get(allTrips.size() - 1).setEnd(cm);
          }
        }
      } else if (cm.getStation() instanceof SubwayStation) {
        if (cm.isEntrance()) {
          if(getLastCardMachineTapped().isEntrance()){
            deductValue(ts.getTransitManager().getCapFare());
            this.allTrips.get(this.allTrips.size()-1).setEnd(cm);
          }
          Trip newTrip = new Trip();
          newTrip.setStart(cm);
          this.allTrips.add(newTrip);
        } else { // is exist so we end trip and calc fare
          if(!getLastCardMachineTapped().isEntrance()){ //checks if the card was tapped last at an exit
              deductValue(ts.getTransitManager().getCapFare());
          } else {
              this.allTrips.get(allTrips.size() - 1).setEnd(cm);
          }
          double fare = ts.getTransitManager().calcSubwayFare(this, cm);// Calculate fare
          deductValue(fare);// Deduct fare from this card
        }
      } else {
        // TransitSide.CardMachine either has not been initialized properly or is an invalid cardmachine
        System.out.println("Error");
      }
    }else{
        System.out.println("UserSide.Card is Suspended");
    }
  }

    /**
     * returns a String representation of this Card
     * @return String representation of this Card
     */
  public String toString(){
      return "Card ID: " + this.cardId + ", Owner: " + owner.getName() + ", Balance: " + this.balance;
  }
}
