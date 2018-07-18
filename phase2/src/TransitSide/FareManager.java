package TransitSide;

import UserSide.Card;

public class FareManager {
  private double flatFare;
  private double tripFare;
  private double capFare;
  private double timeLimit; // 2 hours

  /**
   * Constructs a new FareManager object with specific fares inputted as required.
   *
   * @param flatFare the fee for vehicles that charge a flat fee
   * @param tripFare the fee for vehicles that have a flexible fee
   * @param capFare the maximum fee one can pay in two hours on a continuous trip
   * @param timeLimit in milliseconds, the time one spend on a continuous trip
   */
  public FareManager(double flatFare, double tripFare, double capFare, double timeLimit) {
    this.flatFare = flatFare;
    this.tripFare = tripFare;
    this.capFare = capFare;
    this.timeLimit = timeLimit;
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
   * calculates the bus fare for a trip
   * @param card the tapped card
   * @param cm the CardMachine on which the card was tapped
   * @return the amount of fare
   */
  public double calcBusFare(Card card, CardMachine cm){
    if(card.getTm().isWithinTimeLimit() && !isDisjoint(card, cm)){
      double fare =  Math.min(getFlatFare(), getCapFare() - card.getTm().getAmountSinceLastEffectiveTap());
      card.getTm().addAmountSinceLastEffectiveTap(fare);
      return fare;
    } else {
      card.getTm().resetLastEffective();
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
    if(card.getTm().isWithinTimeLimit()){
      card.getTm().addAmountSinceLastEffectiveTap(fare);
      return Math.min(fare, getCapFare() - card.getTm().getAmountSinceLastEffectiveTap());
    } else {
      card.getTm().resetLastEffective();
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
