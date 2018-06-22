import java.util.ArrayList;

public class Card {

    private boolean tapOn;

    private static int cardId = 0;

    private int balance;

    private ArrayList timer;

    private boolean suspended;

    //the card has an amount
    //boolean value tapOn
    //methods for setting and getting the amount
    //a timer for tapOn
    //everytime the user wants to tap on, the timer checks to see if the card has been tapOn for an unusual time
    //if it, it charges a flat rate, then turns the tap on again with the timer reset to zero
    //otherwise it just behaves regulary.
    public Card() {
        balance = 19;
        tapOn = false;
        cardId++;
        timer = new ArrayList();
        suspended = false;
    }

    public int getId() {
        return cardId;
    }

    public int getBalance() {
        return balance;
    }

    public void tap(Station station) {
        if (suspended == false) {
            tapOn = true;
            timer.add(System.currentTimeMillis());
        }
        else {
            System.out.println("Card is Suspended!");
        }
    }

    public void addValue(int value) {
        if (value == 10 | value == 20 | value == 50) {
            this.balance += value;
        }
        else
        {
            System.out.println("Not a valid value. Cardholders can only add $10, $20, or $50 to their card balance.");
        }

    }

    public void deductFare(int fare) {
        this.balance -= fare;
        if (this.balance < 0) {
            this.suspended = true;
        }
    }

}
