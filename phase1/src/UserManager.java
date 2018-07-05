import java.util.ArrayList;

public class UserManager {

    public UserManager () {

    }

  public int viewBalance(CardHolder user) {
    int balance = 0;
    ArrayList<Card> userCards = user.getCards();
    for (int i = 0; i < userCards.size(); i++) {
      balance += ((Card) userCards.get(i)).getBalance();
    }
    return balance;
  }

    public void changeName(CardHolder user, String newname) {
        user.setName(newname);
    }

    public String[][] viewTrips(CardHolder user) {
        return user.getTrips();
    }

    public void addCard(CardHolder user, Card newcard) {
        user.addCard(newcard);
    }

    public void removeCard(CardHolder user, int deadcardid) {
        user.removeCard(deadcardid);
    }
}