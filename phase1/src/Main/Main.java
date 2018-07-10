package Main;

import TransitSide.BusStation;
import TransitSide.SubwayStation;
import TransitSide.TransitManager;

import java.util.ArrayList;

public class Main {
    private TransitSystem ts;

    //main loop would be here somewhere

    //Instantiates everything
    public void start(){
        ts = new TransitSystem();


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



        ts.addStation(ss1);
        ts.addStation(ss2);
        ts.addStation(ss3);
        ts.addStation(ss4);
        ts.addStation(ss5);
        ts.addStation(ss6);
        ts.addStation(ss7);
        ts.addStation(ss8);

        ts.addStation(bs1);
        ts.addStation(bs2);
        ts.addStation(bs3);
        ts.addStation(bs4);
        ts.addStation(bs5);
        ts.addStation(bs6);
        ts.addStation(bs7);
        ts.addStation(bs8);

    }

    //Methods that need to be called every tick are put in here
    public void run(){
    }
}
