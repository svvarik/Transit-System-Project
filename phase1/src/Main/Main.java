package Main;

import TransitSide.BusStation;
import TransitSide.SubwayStation;

import java.util.Scanner;

public class Main {



    public static void main(String[] args) {

        TransitSystem ts = Main.start();
        Scanner scan = new Scanner(System.in);

        while (!(scan.next().equals("exit"))){
            // Run Program
        }
        // Save everything
        System.exit(0);
    }

    //main loop would be here somewhere

    //Instantiates everything
    private static TransitSystem start(){
        TransitSystem ts = new TransitSystem();

        SubwayStation ss1 = new SubwayStation(0, 2, "ossington");
        SubwayStation ss2 = new SubwayStation(1, 2, "christie");
        SubwayStation ss3 = new SubwayStation(2, 2, "bathurst");
        SubwayStation ss4 = new SubwayStation(3, 2, "spadina");
        SubwayStation ss5 = new SubwayStation(4, 2, "st. George");
        SubwayStation ss6 = new SubwayStation(5, 2, "Bay");
        SubwayStation ss7 = new SubwayStation(6, 2, "Yonge");
        SubwayStation ss8 = new SubwayStation(7, 2, "Sherbourne");

        BusStation bs1 = new BusStation(2,0, "a");
        BusStation bs2 = new BusStation(2,2, "b");
        BusStation bs3 = new BusStation(2,3, "c");
        BusStation bs4 = new BusStation(2,4, "d");
        BusStation bs5 = new BusStation(5,0, "e");
        BusStation bs6 = new BusStation(5,1, "f");
        BusStation bs7 = new BusStation(5,2, "g");
        BusStation bs8 = new BusStation(5,4, "h");

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

        return ts;
    }
}
