package TransitUsers;

import Main.TransitSystem;
import FareSystem.Card;
import TransitUsers.CardHolder;

import java.io.Serializable;
import java.util.ArrayList;

public class CardHolderList implements Serializable {

    private ArrayList<CardHolder> allCardHolders;

    public CardHolderList(){
        this.allCardHolders = new ArrayList<>();
    }

    /**
     * This parameter creats a new CardHolder and adds it to this TransitSystem's cardholders
     * and returns true if everything goes right
     *
     * @param name name of a new cardholder
     * @param email email of the new cardholder
     * @return returns true if everything goes right
     */
    public boolean addCardHolder(String name, String email, TransitSystem ts){
        for (CardHolder ch: this.allCardHolders){
            if (ch.getEmail().equals(email)){
                System.out.println("User email is already in system!");
                return false;
            }
        }
        CardHolder tempCardholder = new CardHolder(name, email, ts);
        this.allCardHolders.add(tempCardholder);
        return true;
    }

    /**
     * Returns the CardHolder with the given email
     * @param chEmail email of the CardHolder
     * @return returns a the CardHolder that has the email passed to the method
     */
    public CardHolder findCardHolder(String chEmail){
        for (CardHolder ch: this.allCardHolders) {
            if (ch.getEmail().equals(chEmail)){
                return ch;
            }
        }
        return null;
    }

    /**
     * Returns the Card with the given ID
     * @param cID ID of the Card
     * @return returns the Card with the ID that was passed to the method
     */
    public Card findCard(int cID){
        for(CardHolder ch: this.allCardHolders){
            for(Card card: ch.getCards()){
                if (card.getCardID() == cID){
                    return card;
                }
            }
        }
        return null;
    }

}
