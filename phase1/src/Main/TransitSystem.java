package Main;

import TransitSide.Station;
import TransitSide.SubwayStation;
import UserSide.CardHolder;

import java.util.ArrayList;
import java.util.Date;

public class TransitSystem {

    private ArrayList<CardHolder> transitCardHolders;
    private ArrayList<Station> stations;
    private int closeHour = 22;
    private int openHour = 5;

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

    public static int calDistance(SubwayStation s1, SubwayStation s2){
        return Math.abs(s1.getX() - s2.getX());
    }

    public void nightCheck(){
        Date date = new Date();
        String currentHourString = String.format("%tk", date );

        int currentHour = Integer.parseInt(currentHourString);

        if((currentHour >= this.closeHour) && (currentHour <= this.openHour)){
            for (int i = 0; i <= this.transitCardHolders.size() - 1; i++){
                for(int j = 0; j <= this.transitCardHolders.get(i).getCards().size()-1; j++){
                    //this.transitCardHolders.get(i).getCards().get(j).setLastTap(null);
                    //this.transitCardHolders.get(i).getCards().get(j).setTapOff();
                }
            }
        }
    }
}
