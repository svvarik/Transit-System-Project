package UserSide;

import Main.TransitSystem;
import UserSide.Card;

import java.util.ArrayList;
import java.util.Collections;

public class CardHolder {
  private String name;
  private String email;
  private ArrayList<Card> cards;
  private int totalCost;
  private TransitSystem ts;

  /**
   * constructs a new CardHolder
   * @param name this CardHolder's name
   * @param email this CardHolder's email
   */
  public CardHolder(String name, String email) {
    this.name = name;
    this.email = email;
    this.cards = new ArrayList<Card>();
    this.totalCost = 0;
  }

  /**
   * return this CardHolder's cards
   * @return the Cards of this CardHolder
   */
  public ArrayList<Card> getCards() {
    return this.cards;
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
   * adds to this CardHolder's total cost
   * @param fare the amount to be added
   */
  public void addTotalCost(int fare) {
    this.totalCost += fare;
  }

  /**
   * modfies a card's balance
   * @param cardID the card's id
   * @param value the amount to be added to the card
   */
  public void modifyCardBalance(int cardID, int value) {
    for (int i = 0; i < this.cards.size(); i++) {
      if (cardID == this.cards.get(i).getCardID()) {
        this.cards.get(i).addValue(value);
      }
    }
  }

  /**
   * returns this CardHolder's recent trips
   * @return this CardHolder's recent trips
   */
  public ArrayList<Trip> viewRecentTrips() {
      ArrayList<Trip> Usertrips = new ArrayList<Trip>();
    for (int i = 0; i < cards.size(); i++) {
        ArrayList<Trip> cardTrips = this.cards.get(i).getTrips();
        for (int y = 0; y < cardTrips.size(); y++) {
            Usertrips.add(cardTrips.get(i));
        }
    }
    Collections.sort(Usertrips);
    ArrayList<Trip> lastThreeTrips = new ArrayList<Trip>();
   for (int i = 0; i < 3; i++)
       lastThreeTrips.add(Usertrips.get(i));
   
    return lastThreeTrips;
  }

  /**
   * returns a String representation of this CardHolder
   * @return  a String representation of this CardHolder
   */
  public String toString(){
    return this.name + " has " + this.cards.size() + " cards." + "\n" + this.email;
  }
}