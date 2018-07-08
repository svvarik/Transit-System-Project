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

    private Date lastEffectiveTap = null;

    private CardHolder owner;

    private ArrayList<Trip> allTrips = new ArrayList<>();

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

    public int getCardID() {
        return cardId;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * Initializes or ends a trip depending on user location, and
     * deducts fare if required.
     *
     * This method will handle exceptions such as incomplete previous trips or
     * current trips as specified by incomplete trip handlers.
     *
     * @param cm the cardmachine this card is tapped on
     */
    // TODO: HANDLE EXCEPTIONS
    // Exception where last trip has not ended, but we are tapping on a cm.entrance
    // Exception where first trip never started but we are tapping on a cm.exit
    // Deal with two hour transfers
    // TODO: Figure out proper return type
    // If card is suspended, what should we return?
    public void tapCard(CardMachine cm) {
        // if bus:
        if (cm.getStation() instanceof BusStation){
            if(cm.isEntrance()){
                Trip newTrip = new Trip();
                this.allTrips.add(newTrip);
                // Calculate fare
                // Deduct fare from this card
            } else { // is exit so we end trip
                this.allTrips.get(allTrips.size()-1).endTrip();
            }
        } else if (cm.getStation() instanceof SubwayStation) {
            if (cm.isEntrance()) {
                Trip newTrip = new Trip();
                this.allTrips.add(newTrip);
            } else { //is exist so we end trip and calc fare
                this.allTrips.get(allTrips.size()-1).endTrip();
                // Calculate fare
                // Deduct fare from this card
            }
        } else {
            // CardMachine either has not been initialized properly or is an invalid cardmachine
            System.out.println("Error");
        }
    }


    public boolean isWithinTimeLimit(){
        Date d = new Date();
        if (this.lastEffectiveTap != null)
            return (Math.abs(this.lastEffectiveTap.getTime() - d.getTime()) < transitNetwork.getTimeLimit());
        return false;
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

    public void setTapOn() { this.tapOn = true; }
    public void setTapOff() {this.tapOn = false;}
    public void setOwner(CardHolder owner) {this.owner = owner;}
    public void setAmountSinceLastEffectiveTap(double amount){this.amountSinceLastEffectiveTap = amount;}

    public boolean isTapOn() {return this.tapOn;}

    public Station getLastStation(){return this.lastTap;}
    public Date getLastTapDate(){return this.tapDates.get(this.tapDates.size() - 1);}
    public Date getLastTapOn(){return this.lastEffectiveTap;}
    public double getAmountSinceLastEffectiveTap(){return this.amountSinceLastEffectiveTap;}
    public CardHolder getOwner(){return this.owner;}

}
