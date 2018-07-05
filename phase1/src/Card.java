import java.util.ArrayList;
import java.util.Date;

public class Card {

    private boolean tapOn;

    private static int cardId = 0;

    private double balance;

    private double amountSinceLastEffectiveTap;

    private ArrayList<Date> tapDates;

    private Station lastTap;

    private boolean suspended;

    private TransitNetwork transitNetwork;

    private Date lastEffectiveTap;

    //the card has an amount
    //boolean value tapOn
    //methods for setting and getting the amount
    //a timer for tapOn
    //everytime the user wants to tap on, the timer checks to see if the card has been tapOn for an unusual time
    //if it, it charges a flat rate, then turns the tap on again with the timer reset to zero
    //otherwise it just behaves regulary.
    public Card(TransitNetwork tn) {
        balance = 19;
        tapOn = false;
        cardId++;
        suspended = false;
        this.transitNetwork = tn;
    }

    public int getId() {
        return cardId;
    }

    public double getBalance() {
        return balance;
    }

    public void tap(CardMachine cardmachine) {
        if (suspended == false) {
            this.transitNetwork.calcFare(this, cardmachine);
            this.lastTap = cardmachine.getStation();
            Date tempDate = new Date();
            this.tapDates.add(tempDate);
        }
        else {
            System.out.println("Card is Suspended!");
        }
    }

    public boolean isWithinTimeLimit(){
        Date d = new Date();
        return (Math.abs(this.lastEffectiveTap.getTime() - d.getTime()) < transitNetwork.getTimeLimit());
    }

    public void addValue(double value) {
        if (value == 10 | value == 20 | value == 50) {
            this.balance += value;
        }
        else
        {
            System.out.println("Not a valid value. Cardholders can only add $10, $20, or $50 to their card balance.");
        }

        if (this.balance > 0) {
            this.suspended = false;
        }

    }

    public void deductFare(double fare) {
        this.balance -= fare;
        this.amountSinceLastEffectiveTap += fare;
        if (this.balance < 0) {
            this.suspended = true;
        }
    }


    public void reportStolen() {
        this.suspended = true;
    }

    public void setLastTap(Station lastTap) {
        this.lastTap = lastTap;
    }

    public void setLastEffectiveTap(){
        this.amountSinceLastEffectiveTap = 0;
        Date d = new Date();
        this.lastEffectiveTap = d;
    }

    public void setTapOn() {
        this.tapOn = true;
    }
    public void setTapOff() {this.tapOn = false;}

    public boolean isTapOn() {return this.tapOn;}

    public Station getLastStation(){return this.lastTap;}
    public Date getLastTapDate(){return this.tapDates.get(this.tapDates.size() - 1);}
    public Date getLastTapOn(){return this.lastEffectiveTap;}
    public double getAmountSinceLastEffectiveTap(){return this.amountSinceLastEffectiveTap;}

}
