package TransitSide;

import UserSide.Card;

import java.util.Date;

public class TransitManager {
  private double flatFare = 2;
  private double tripFare = 0.5;
  private double capFare = 6;
  private double timeLimit = 7200000;

  private long startTimeNum;
  private String startTimeString;
  private Date date;

    /**
     * constructs a new instance of TransitManager
     */
  public TransitManager() {
    this.date = new Date();
    this.startTimeNum = date.getTime();
    this.startTimeString = date.toString();
  }
  /** setters for the TransitSide.TransitManager's fare values */

    /**
     * sets flatFare for this TransitManager
     * @param fare amount of flatFare
     */
  public void setFlatFare(double fare) {
    this.flatFare = fare;
  }

    /**
     * sets tripFare for this TransitManager
     * @param fare amount of tripFare
     */
  public void setTripFare(double fare) {
    this.tripFare = fare;
  }

    /**
     * sets capFare for this TransitManager
     * @param fare amount of capFare
     */
  public void setCapFare(double fare) {
    this.capFare = fare;
  }

    /**
     * sets timeLimit for this TransitManager
     * @param limit amount of timeLimit
     */
  public void setTimeLimit(double limit) {
    this.timeLimit = limit;
  }

  /** getters for this TransitSide.TransitManager's fare values */

    /**
     * returns this TransitManager's flatFare
     * @return this TransitManager's flatFare
     */
  public double getFlatFare() {
    return this.flatFare;
  }

    /**
     * returns this TransitManager's tripFare
     * @return this TransitManager's tripFare
     */
  public double getTripFare() {
    return this.tripFare;
  }

    /**
     * returns this TransitManager's capFare
     * @return this TransitManager's capFare
     */
  public double getCapFare() {
    return this.capFare;
  }

    /**
     * returns this TransitManager's timeLimit
     * @return this TransitManager's timeLimit
     */
  public double getTimeLimit() {
    return this.timeLimit;
  }

  /** getter for the TransitSide.TransitManager start time in both numericals and String */

    /**
     * returns this TransitManger starTime in numbers
     * @return this TransitManger starTime in numbers
     */
  public long getStartTimeNum() {
    return this.startTimeNum;
  }

    /**
     * returns the String representation of this TransitManager's start date
     * @return the String representation of this TransitManager's start date
     */
  public String getStartTimeString() {
    return this.startTimeString;
  }

    /**
     * returns this TransitManager's running time in numbers
     * @return this TransitManager's running time in numbers
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
    } else {
      card.resetLastEffective();
      return getFlatFare();
    }
    return 0;
  }

    /**
     * calculates the subway fare for a trip
     * @param card the tapped card
     * @param cm the CardMachine on which the card was tapped
     * @return the amount of fare
     */
  public double calcSubwayFare(Card card, CardMachine cm){
    double fare = Math.abs(cm.getStation().getX() - card.getLastStation().getX()) * getTripFare();
    if(card.isWithinTimeLimit()){
      card.addAmountSinceLastEffectiveTap(fare);
      return fare;
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
    return (cm.getStation().getX() == card.getLastStation().getX())
            && (cm.getStation().getY() == card.getLastStation().getY());
  }
}
