import java.util.Date;

public class test {

    public static void main (String[] args) {
        CardHolder aleashea = new CardHolder("Aleashea", "aleashea.valentine@mail.utoronto.ca");
        Card card1 = new Card(aleashea);
        Card card2 = new Card(aleashea);
        Card card3 = new Card(aleashea);
        aleashea.addCard(card1);
    }

}
