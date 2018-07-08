import java.util.Date;

public class TransitNetwork {
  private double flatFare = 2;
  private double tripFare = 0.5;
  private double capFare = 6;
  private double timeLimit = 7200000;

  private long startTimeNum;
  private String startTimeString;
  private Date date;

  public TransitNetwork() {
    this.date = new Date();
    this.startTimeNum = date.getTime();
    this.startTimeString = date.toString();
  }
  /** setters for the TransitNetwork's fare values */
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

  /** getters for this TransitNetwork's fare values */
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

  /** getter for the TransitNetwork start time in both numericals and String */
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
      return Math.min(getFlatFare(), getCapFare() - card.getAmountSinceLastEffectiveTap());
    } else {
      card.setLastEffectiveTap();
      return getFlatFare();
    }
  }

  public double calcSubwayFare(Card card, CardMachine cm){
    double fare = Math.abs(cm.getStation().getX() - card.getLastStation().getX()) * getTripFare();
    if(card.isWithinTimeLimit()){
      return Math.min(fare, getCapFare() - card.getAmountSinceLastEffectiveTap());
    } else {
      card.setLastEffectiveTap();
      return fare;
    }
  }

  public boolean isDisjoint(Card card, CardMachine cm){
    return (cm.getStation().getX() == card.getLastStation().getX())
            && (cm.getStation().getY() == card.getLastStation().getY());
  }

//  public double calcFare(Card card, CardMachine cardmachine) {
//    if (card.isWithinTimeLimit() && (cardmachine.getStation().getX() == card.getLastStation().getX())
//                                 && (cardmachine.getStation().getY() == card.getLastStation().getY())){
//      if (card.getAmountSinceLastEffectiveTap() < getCapFare()) {
//        if (cardmachine.isEntrance()) {
//          if (cardmachine.getStation().isFlatRate()) {
//            return Math.min(getCapFare(), getCapFare() - card.getAmountSinceLastEffectiveTap());
//          } else {
//          }
//        } else {
//          if (cardmachine.getStation().isFlatRate()) {
//
//          } else {
//            double rate =
//                Math.abs(cardmachine.getStation().getX() - card.getLastStation().getX())
//                    * getTripFare();
//            return Math.min(rate, getCapFare() - rate);
//          }
//        }
//      } else {
//        return 0;
//      }
//    }
//
//    if (!card.isWithinTimeLimit() || ((cardmachine.getStation().getX() == card.getLastStation().getX())
//            && (cardmachine.getStation().getY() == card.getLastStation().getY()))) {
//      card.setAmountSinceLastEffectiveTap(0);
//      card.setLastEffectiveTap();
//      if (cardmachine.isEntrance()) {
//        if (cardmachine.getStation().isFlatRate()) {
//          return getFlatFare();
//        } else {
//        }
//      } else {
//        if (cardmachine.getStation().isFlatRate()) {
//
//        } else {
//          double rate =
//              Math.abs(cardmachine.getStation().getX() - card.getLastStation().getX())
//                  * this.tripFare;
//          return rate;
//        }
//      }
//    }
//    return 0;
//  }

}
