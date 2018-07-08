import java.util.ArrayList;

public class Main {
    private ArrayList<Station> stations;
    private ArrayList<CardHolder> cardHolders;
    private TransitSystem ts;
    private TransitNetwork tn;
    //main loop would be here somewhere

    //Instantiates everything
    public void start(){


        SubwayStation ss1 = new SubwayStation(0, 2);
        SubwayStation ss2 = new SubwayStation(1, 2);
        SubwayStation ss3 = new SubwayStation(2, 2);
        SubwayStation ss4 = new SubwayStation(3, 2);
        SubwayStation ss5 = new SubwayStation(4, 2);
        SubwayStation ss6 = new SubwayStation(5, 2);
        SubwayStation ss7 = new SubwayStation(6, 2);
        SubwayStation ss8 = new SubwayStation(7, 2);

        BusStation bs1 = new BusStation(2,0);
        BusStation bs2 = new BusStation(2,2);
        BusStation bs3 = new BusStation(2,3);
        BusStation bs4 = new BusStation(2,4);
        BusStation bs5 = new BusStation(5,0);
        BusStation bs6 = new BusStation(5,1);
        BusStation bs7 = new BusStation(5,2);
        BusStation bs8 = new BusStation(5,4);

        stations = new ArrayList <>();

        stations.add(ss1);
        stations.add(ss2);
        stations.add(ss3);
        stations.add(ss4);
        stations.add(ss5);
        stations.add(ss6);
        stations.add(ss7);
        stations.add(ss8);

        stations.add(bs1);
        stations.add(bs2);
        stations.add(bs3);
        stations.add(bs4);
        stations.add(bs5);
        stations.add(bs6);
        stations.add(bs7);
        stations.add(bs8);

        cardHolders = new ArrayList <>();

        ts = new TransitSystem(stations,cardHolders);
        tn = new TransitNetwork();
    }

    //Methods that need to be called every tick are put in here
    public void run(){
    }
}
