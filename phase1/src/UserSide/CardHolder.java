package UserSide;

import UserSide.Card;

import java.util.ArrayList;
import java.util.Collections;

public class CardHolder {
  private String name;
  private String email;
  private ArrayList<Card> cards;
  private int totalCost;

  public CardHolder(String name, String email) {
    this.name = name;
    this.email = email;
    this.cards = new ArrayList<Card>();
    this.totalCost = 0;
  }

  public ArrayList<Card> getCards() {
    return this.cards;
  }

  public void addCard(Card card) {
    this.cards.add(card);
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public void removeCard(int cardID) {
    for (int i = 0; i < this.cards.size(); i++) {
      if (cardID == this.cards.get(i).getCardID()) {
        this.cards.remove(i);
      }
    }
  }

  public void addTotalCost(int fare) {
    this.totalCost += fare;
  }

  public void modifyCardBalance(int cardID, int value) {
    for (int i = 0; i < this.cards.size(); i++) {
      if (cardID == this.cards.get(i).getCardID()) {
        this.cards.get(i).addValue(value);
      }
    }
  }

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
}