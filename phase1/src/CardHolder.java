import java.util.ArrayList;

public class CardHolder {
    private String name;
    private String email;
    private ArrayList<Card> cards;
    private String[][] trips = new String[2][2];
    private int totalCost;

    public CardHolder(String name, String email){
        this.name = name;
        this.email = email;
        this.cards = new ArrayList <Card>();
        this.totalCost = 0;
    }

    public ArrayList <Card> getCards() {
        return this.cards;
    }

    public void addCard(Card card){
        this.cards.add(card);
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() { return email;}

    public String[][] getTrips() { return trips; }

    public void removeCard(int cardID){
        for (int i = 0; i < this.cards.size(); i++){
            if(cardID == this.cards.get(i).getId()){
                this.cards.remove(i);
            }
        }
    }

    public void addTotalCost(int fare){
        this.totalCost += fare;
    }

    public void modifyCardBalance(int cardID, int value){
        for (int i = 0; i < this.cards.size(); i++){
            if(cardID == this.cards.get(i).getId()){
                this.cards.get(i).addValue(value);
            }
        }
    }
}
