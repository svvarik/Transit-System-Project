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

  public TransitManager() {
    this.date = new Date();
    this.startTimeNum = date.getTime();
    this.startTimeString = date.toString();
  }
  /** setters for the TransitSide.TransitManager's fare values */
  public void setFlatFare(double fare) {
    this.flatFare = fare;
  }

  public void setTripFare(double fare) {
    this.tripFare = fare;
  }

  public void setCapFare(double fare) {
    this.capFare = fare;
  }

  public void setTimeLimit(double limit) {
    this.timeLimit = limit;
  }

  /** getters for this TransitSide.TransitManager's fare values */
  public double getFlatFare() {
    return this.flatFare;
  }

  public double getTripFare() {
    return this.tripFare;
  }

  public double getCapFare() {
    return this.capFare;
  }

  public double getTimeLimit() {
    return this.timeLimit;
  }

  /** getter for the TransitSide.TransitManager start time in both numericals and String */
  public long getStartTimeNum() {
    return this.startTimeNum;
  }

  public String getStartTimeString() {
    return this.startTimeString;
  }

  /** getRunningTime returns the systems runingtime in ms */
  public long getRunningTime() {
    Date d = new Date();
    return d.getTime() - this.startTimeNum;
  }

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

  public boolean isDisjoint(Card card, CardMachine cm){
    return (cm.getStation().getX() == card.getLastStation().getX())
            && (cm.getStation().getY() == card.getLastStation().getY());
  }
}
