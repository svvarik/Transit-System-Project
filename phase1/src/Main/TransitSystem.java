package Main;

import TransitSide.Station;
import TransitSide.SubwayStation;
import TransitSide.TransitManager;
import UserSide.CardHolder;
import UserSide.Trip;

import java.util.ArrayList;
import java.util.Date;

public class TransitSystem {

    private TransitManager tm = new TransitManager();

    private ArrayList<CardHolder> transitCardHolders;
    private ArrayList<Station> stations;
    private Date opened;
    private Date closed;
    private ArrayList<Trip> allTrips;


    public TransitSystem(){
        this.transitCardHolders = new ArrayList<>();
        this.stations = new ArrayList<>();
        this.opened = new Date();
    }

    public TransitSystem(ArrayList<Station> stations, ArrayList<CardHolder> transitCardHolders){
        this.stations = stations;
        this.transitCardHolders = transitCardHolders;
    }

    public TransitManager getTransitManager(){
        return this.tm;
    }

    public void addCardHolder(String name, String email){
        CardHolder tempCardholder = new CardHolder(name, email);
        this.transitCardHolders.add(tempCardholder);
    }

    public void addStation(Station station) {
        this.stations.add(station);
    }

    public void addTrip(Trip t){
        this.allTrips.add(t);
    }


    public void exitSystem(){
        this.closed = new Date();
        // Write to events.txt
    }
}
