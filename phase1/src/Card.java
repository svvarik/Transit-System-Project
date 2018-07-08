import java.util.ArrayList;
import java.util.Date;

public class Card {

    private static int idAssigner = 0;

    private int cardId;
    private double balance;
    private ArrayList<Trip> allTrips = new ArrayList<>();
    private CardHolder owner;
    private boolean suspended;


    public Card(CardHolder owner) {
        this.balance = 19;
        this.suspended = false;
        this.owner = owner;
        this.cardId = idAssigner;
        Card.idAssigner += 1;
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

    public int addValue(double value) {
        if (this.suspended) {
            // Return some other exception
            return 1;
        } else {
            if (value == 10 | value == 20 | value == 50) {
                this.balance += value;
                return 0;
            } else {
                // Return some null exception or error
                return 1;
            }
        }
    }

    // TODO: Handle exceptions - not enough balance left etc.
    public void deductValue(double fare){
        this.balance -= fare;
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

}
