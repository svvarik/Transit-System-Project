package TransitSide;

import UserSide.Card;

import java.util.Date;

public class FareManager {
  private double flatFare = 2;
  private double tripFare = 0.5;
  private double capFare = 6;
  private double timeLimit = 7200000;

  private long startTimeNum;
  private String startTimeString;
  private Date date;

    /**
     * constructs a new instance of FareManager
     */
  public FareManager() {
    this.date = new Date();
    this.startTimeNum = date.getTime();
    this.startTimeString = date.toString();
  }
  /** setters for the TransitSide.FareManager's fare values */

    /**
     * sets flatFare for this FareManager
     * @param fare amount of flatFare
     */
  public void setFlatFare(double fare) {
    this.flatFare = fare;
  }

    /**
     * sets tripFare for this FareManager
     * @param fare amount of tripFare
     */
  public void setTripFare(double fare) {
    this.tripFare = fare;
  }

    /**
     * sets capFare for this FareManager
     * @param fare amount of capFare
     */
  public void setCapFare(double fare) {
    this.capFare = fare;
  }

    /**
     * sets timeLimit for this FareManager
     * @param limit amount of timeLimit
     */
  public void setTimeLimit(double limit) {
    this.timeLimit = limit;
  }

  /** getters for this TransitSide.FareManager's fare values */

    /**
     * returns this FareManager's flatFare
     * @return this FareManager's flatFare
     */
  public double getFlatFare() {
    return this.flatFare;
  }

    /**
     * returns this FareManager's tripFare
     * @return this FareManager's tripFare
     */
  public double getTripFare() {
    return this.tripFare;
  }

    /**
     * returns this FareManager's capFare
     * @return this FareManager's capFare
     */
  public double getCapFare() {
    return this.capFare;
  }

    /**
     * returns this FareManager's timeLimit
     * @return this FareManager's timeLimit
     */
  public double getTimeLimit() {
    return this.timeLimit;
  }

  /** getter for the TransitSide.FareManager start time in both numericals and String */

    /**
     * returns this TransitManger starTime in numbers
     * @return this TransitManger starTime in numbers
     */
  public long getStartTimeNum() {
    return this.startTimeNum;
  }

    /**
     * returns the String representation of this FareManager's start date
     * @return the String representation of this FareManager's start date
     */
  public String getStartTimeString() {
    return this.startTimeString;
  }

    /**
     * returns this FareManager's running time in numbers
     * @return this FareManager's running time in numbers
     */
  public long getRunningTime() {
    Date d = new Date();
    return d.getTime() - this.startTimeNum;
  }

    /**
     * calculates the bus fare for a trip
     * @param card the tapped card
     * @param cm the CardMachine on which the card was tapped
     * @return the amount of fare
     */
  public double calcBusFare(Card card, CardMachine cm){
    if(card.isWithinTimeLimit() && !isDisjoint(card, cm)){
      double fare =  Math.min(getFlatFare(), getCapFare() - card.getAmountSinceLastEffectiveTap());
      card.addAmountSinceLastEffectiveTap(fare);
      return fare;
    } else {
      card.resetLastEffective();
      return getFlatFare();
    }
  }

    /**
     * calculates the subway fare for a trip
     * @param card the tapped card
     * @param cm the CardMachine on which the card was tapped
     * @return the amount of fare
     */
  public double calcSubwayFare(Card card, CardMachine cm){
    double fare = Math.abs(cm.getStation().getX() - card.getLastCardMachineTapped().getStation().getX()) * getTripFare();
    if(card.isWithinTimeLimit()){
      card.addAmountSinceLastEffectiveTap(fare);
      return Math.min(fare, getCapFare() - card.getAmountSinceLastEffectiveTap());
    } else {
      card.resetLastEffective();
      return fare;
    }
  }

    /**
     * returns true if a trip has been Disjoint
     * @param card the tapped card
     * @param cm the CardMachine on which the card was tapped
     * @return returns true if a trip has been Disjoint
     */
  public boolean isDisjoint(Card card, CardMachine cm){
    return !(cm.getStation().getX() == card.getLastCardMachineTapped().getStation().getX())
            && (cm.getStation().getY() == card.getLastCardMachineTapped().getStation().getY());
  }
}
