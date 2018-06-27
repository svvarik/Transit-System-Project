import java.util.ArrayList;

public class TransitSystem {

    private ArrayList<CardHolder> transitCardHolders;
    private ArrayList<Station> stations;

    public TransitSystem(){

    }

    public TransitSystem(ArrayList<Station> stations, ArrayList<CardHolder> transitCardHolders){
        this.stations = stations;
        this.transitCardHolders = transitCardHolders;
    }

    public void addCardHolder(String name, String email){
        CardHolder tempCardholder = new CardHolder(name, email);
        this.transitCardHolders.add(tempCardholder);
    }

    public void addStation(Station station){this.stations.add(station);}


    public int calDistanc(Station s1, Station s2){
        return Math.abs(s1.getX() - s2.getX());
    }

    public void nightCheck(){}

}
