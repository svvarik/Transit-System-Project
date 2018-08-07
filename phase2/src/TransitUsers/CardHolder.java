package TransitUsers;

import Main.TransitSystem;
import FareSystem.Card;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class CardHolder implements Serializable {
  private String name;
  private String email;
  private String password;
  private ArrayList<Card> cards;
  private TransitSystem ts;
  private ArrayList<Trip> trips;
  private Double giftMoney;
  private static final long serialVersionUID = 291745;

  public CardHolder(String name, String email, String password, TransitSystem ts) {
    this.password = password;
    this.name = name;
    this.email = email;
    this.cards = new ArrayList<Card>();
    this.trips = new ArrayList <>();
    this.ts = ts;
  }

  /**
   * constructs a new CardHolder
   * @param name this CardHolder's name
   * @param email this CardHolder's email
   */

  public CardHolder(String name, String email, TransitSystem ts) {
    this.name = name;
    this.email = email;
    this.cards = new ArrayList<Card>();
    this.trips = new ArrayList <>();
    this.ts = ts;
  }

  /**
   * return this CardHolder's cards
   * @return the Cards of this CardHolder
   */
  public ArrayList<Card> getCards() {
    return this.cards;
  }

  public double getAverageMonthlyFare() {
    Calendar currentDate = Calendar.getInstance();
    int currentMonth = currentDate.get(Calendar.MONTH);
    int counter = 0;
    double fareTotal = 0;
    for(Trip t: this.trips){
      if(t.getStarDate().get(Calendar.MONTH) == currentMonth){
        fareTotal = fareTotal + t.getFare();
        if(t.getEnd() != null){
          counter+=1;
        }
        if(t.getStart() != null){
          counter+=1;
        }
      }
    }
    if(counter == 0){
      return fareTotal;
    }else{
      return fareTotal/counter;
    }
  }

  /**
     * Returns TransitSystem
     * @return returns TransitSystem
     */
    public TransitSystem getTs() {
        return ts;
    }

    /**
   * adds a Card this CardHolder's cards
   * @param card the Card to be added
   */

  public void addCard(Card card) {
    this.cards.add(card);
  }

  /**
   * sets this CardHolder's name
   * @param name the name to be set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * returns this CardHolder's name
   * @return this CardHolder's name
   */
  public String getName() {
    return name;
  }

  /**
   * returns this CardHolder's email
   * @return this CardHolder's email
   */
  public String getEmail() {
    return email;
  }

  /**
   * removes a Card by id
   * @param cardID the id of the Card to be removed
   */
  public void removeCard(int cardID) {
    for (int i = 0; i < this.cards.size(); i++) {
      if (cardID == this.cards.get(i).getCardID()) {
        this.cards.remove(i);
      }
    }
  }

  /**
   * returns this CardHolder's 3 recent trips
   * @return this CardHolder's 3 recent trips
   */
  public ArrayList<Trip> viewRecentTrips() {
    ArrayList<Trip> recentTrips = new ArrayList<Trip>();

    if(this.trips.size() > 0){
      recentTrips.add(this.trips.get(this.trips.size() - 1));
    }
    if(this.trips.size() > 1){
      recentTrips.add(this.trips.get(this.trips.size() - 2));
    }
    if(this.trips.size() > 2){
      recentTrips.add(this.trips.get(this.trips.size() - 3));
    }
    return recentTrips;
  }

  /**
   * returns a String representation of this CardHolder
   * @return  a String representation of this CardHolder
   */
  public String toString(){
    return this.name + ", " + this.email;
  }

  /**
   * adds a trip to this Cardholder's Trips, calls this Cardholder's TransitSystem's addTrip to add the Trip also in there
   * @param t the trip to be added
   */
  public void addTrip(Trip t){
    this.trips.add(t);
    this.ts.addTrip(t);
  }

  public ArrayList<Trip> getTrips() {
    return trips;
  }

  public ObservableList<Trip> getObservableTrip(){
    if(this.trips.size()<30){
        return FXCollections.observableArrayList(this.trips);
    }else {
        return FXCollections.observableArrayList(this.trips.subList(this.trips.size() - 30, this.trips.size() - 1));
    }
  }

  public ObservableList<Card> getObservableCards(){
    return FXCollections.observableArrayList(this.cards);
  }

  public double getDailyFare(int day, int month, int year){
      double fare = 0;
      for(Trip t: this.trips){
          if((t.getStarDate().get(Calendar.DATE) == day) && (t.getStarDate()
          .get(Calendar.MONTH) == month) && (t.getStarDate().get(Calendar.YEAR) == year)){
              fare+= t.getFare();
          }
      }
      return fare;
  }
  public void addGiftMoney(Double money){
    this.giftMoney += money;
  }

  public Card getCard(int cardID){
    Card cd = null;
    for(Card card: this.cards){
      if(card.getCardID() == cardID){
        cd = card;
      }
    }
    return cd;
  }

  public boolean sendGiftMoney(String email, int cardID, double money){
    if(this.ts.getCardHolders().findCardHolder(email) != null){
      CardHolder recepient = this.ts.getCardHolders().findCardHolder(email);
      if(this.getCard(cardID) != null){
        Card giftingCard = this.getCard(cardID);
        if(giftingCard.getBalance() < money){
          System.out.println("Not enough money!");
          return false;
        }
        else{
          giftingCard.deductValue(money);
          recepient.addGiftMoney(money);
          System.out.println(this.getName() +" Successfully sent money to "+ recepient.getName());
          return true;
        }
      }
    }
    return false;
  }

  public boolean allocateGiftMoney(int cardID, double money){
    if(this.getCard(cardID) != null){
      Card card = this.getCard(cardID);
      if(this.giftMoney < money){
        System.out.println("You do not have enough money in your gift basket to allocate to your card");
        return false;
      }
      else{
        this.giftMoney -= money;
        card.addValue(money);
        System.out.println(this.name + " successfully allocated money to " + card.toString());
        return true;
      }

    }
    return false;
  }

  public boolean allocateGiftMoney(int cardID){
    return allocateGiftMoney(cardID, this.giftMoney);
  }

  public void setPassword(String password){
    this.password = password;
  }

  public boolean isPassCorrect(String pass){
    if (this.password != null) {
      return (this.password.equals(pass)); }
    return false;
  }
}