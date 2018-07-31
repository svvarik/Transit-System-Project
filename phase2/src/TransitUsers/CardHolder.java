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
  private ArrayList<Card> cards;
  private TransitSystem ts;
  private ArrayList<Trip> trips;
  private static final long serialVersionUID = 291745;

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
      if(t.getStarDate().get(Calendar.DATE) == currentMonth){
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
    return FXCollections.observableArrayList(this.trips);
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
}