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
    private boolean suspended;
    private Date lastEffectiveTap;
    private double amountSinceLastEffectiveTap;
    private boolean firstTap;

    /**
     * constructs a new Card
     * @param owner this Card's cardHolder
     */
    public Card(CardHolder owner) {
        this.balance = 19;
        this.suspended = false;
        this.owner = owner;
        this.cardId = idAssigner;
        this.amountSinceLastEffectiveTap = 0;
        this.lastEffectiveTap = null;
        Card.idAssigner += 1;
        this.firstTap = true;
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
        if(this.amountSinceLastEffectiveTap + amountToAdd >=6){
            this.amountSinceLastEffectiveTap = 6;
        }else{
            this.amountSinceLastEffectiveTap = this.amountSinceLastEffectiveTap + amountToAdd;
        }
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
        if (this.suspended) {
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
    }

  /**
   * Initializes or ends a trip depending on user location, and deducts fare if required.
   *
   * <p>This method will handle exceptions such as incomplete previous trips or current trips as
   * specified by incomplete trip handlers.
   *
   * @param cm the cardmachine this card is tapped on
   */
  public boolean tapCard(CardMachine cm) {
    if (!suspended) {
      if (cm.isEntrance() && this.balance <= 0) {
        return false;
      }else{
        if (cm.getStation() instanceof BusStation) { // if bus:
          if (firstTap) {
            resetLastEffective();
            Trip newTrip = new Trip();
            newTrip.setStart(cm);
            this.addTrip(newTrip);
            deductValue(owner.getTs().getFareManager().getFlatFare());
            addAmountSinceLastEffectiveTap(owner.getTs().getFareManager().getFlatFare());
            firstTap = false;
            return true;
          } else {
            tapBusStation(cm);
            return true;
          }
        } else if (cm.getStation() instanceof SubwayStation) {
          if (firstTap) {
            Trip newTrip = new Trip();
            newTrip.setStart(cm);
            this.addTrip(newTrip);
            firstTap = false;
            return true;
          } else {
            tapSubwayStation(cm);
            return true;
          }
        } else {
          // TransitSide.CardMachine either has not been initialized properly or is an invalid
          // cardmachine
          System.out.println("There is an error with the Card Machine");
          return false;
        }
      }
    }else{
        System.out.println("This card is suspended");
        return false;
    }
  }

  private void tapBusStation(CardMachine cm){
      if (cm.isEntrance()) { // if entrance:
          double fare = this.owner.getTs().getFareManager().calcBusFare(this, cm);// Calculate fare
          if(getLastCardMachineTapped().isEntrance()){ //checks if the card was tapped last at an entrance
              deductValue(this.owner.getTs().getFareManager().getCapFare());
              this.allTrips.get(Math.max(this.allTrips.size()-1, 0)).setEnd(cm);
          }
          Trip newTrip = new Trip();
          newTrip.setStart(cm);
          this.addTrip(newTrip);
          deductValue(fare);// Deduct fare from this card
      } else { // is exit so we end trip
          if(!getLastCardMachineTapped().isEntrance()){ //checks if the card was tapped last at an exit
              deductValue(this.owner.getTs().getFareManager().getCapFare());
          } else {
              this.allTrips.get(Math.max(this.allTrips.size()-1, 0)).setEnd(cm);
          }
      }
  }

  private void tapSubwayStation(CardMachine cm){
      if (cm.isEntrance()) {
          if(this.owner.getTs().getFareManager().isDisjoint(this, cm)){
              resetLastEffective();
          }
          if(getLastCardMachineTapped().isEntrance()){
              deductValue(this.owner.getTs().getFareManager().getCapFare());
              this.allTrips.get(Math.max(this.allTrips.size()-1, 0)).setEnd(cm);
          }
          Trip newTrip = new Trip();
          newTrip.setStart(cm);
          this.addTrip(newTrip);
      } else { // is exist so we end trip and calc fare
          double fare = this.owner.getTs().getFareManager().calcSubwayFare(this, cm);// Calculate fare
          if(!getLastCardMachineTapped().isEntrance()){ //checks if the card was tapped last at an exit
              deductValue(this.owner.getTs().getFareManager().getCapFare());
          } else {
              this.allTrips.get(Math.max(this.allTrips.size()-1, 0)).setEnd(cm);
          }
          deductValue(fare);// Deduct fare from this card
      }
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
