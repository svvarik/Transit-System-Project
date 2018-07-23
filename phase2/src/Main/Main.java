package Main;

import Data.TransitSystemLog;
import TransitSide.BusStation;
import TransitSide.SubwayStation;
import UserSide.*;
import org.omg.CORBA.TRANSACTION_MODE;

import java.io.IOException;

public class Main {

    private static final long serialVersionUID = 546374;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String filepath = "./serializedTransitSystem.ser";
        TransitSystemLog tsLog = new TransitSystemLog(filepath);
        tsLog.readFromFile(filepath);
        // TransitSystemLog ts = Main.start();

        //Allow start to modify the Transit System in the Transit Log
        tsLog.setTransitSystem(Main.start(tsLog));
        tsLog.saveToFile(filepath);
        // Handling Events (To be Replaced by GUI)
        TransitSystemIO tsIO = new TransitSystemIO(tsLog.getTransitSystem());
        tsIO.readFile("events.txt");

        // Save the log to file

        System.exit(0);

    }

    //Instantiates everything
    private static TransitSystem start(TransitSystemLog tsLog) throws IOException, ClassNotFoundException {
        // INSTANTIATE EVERYTHING ELSE, CARDHOLDERS, CARDMACHINES, ETC ETC ETC

        tsLog.addCardHolder("HAL", "HAL@mail.com");
        tsLog.addCardHolder("Dave", "Dave@mail.com");
        TransitSystem ts = tsLog.getTransitSystem();
        ts.findCardHolder("HAL@mail.com").addCard(new Card(ts.findCardHolder("HAL@mail.com"), ts.getTapManager()));
        ts.findCardHolder("Dave@mail.com").addCard(new Card(ts.findCardHolder("Dave@mail.com"), ts.getTapManager()));
        ts.addCardHolder("HAL", "HAL@mail.com");
        ts.addCardHolder("Dave", "Dave@mail.com");
        ts.addAdminUser("Sai", "sai@sai.com");

        SubwayStation ss1 = new SubwayStation(0, 2, "ossington");
        ss1.addEntrace();
        ss1.addExits();
        SubwayStation ss2 = new SubwayStation(1, 2, "christie");
        ss2.addEntrace();
        ss2.addExits();
        SubwayStation ss3 = new SubwayStation(2, 2, "bathurst");
        ss3.addEntrace();
        ss3.addExits();
        SubwayStation ss4 = new SubwayStation(3, 2, "spadina");
        ss4.addEntrace();
        ss4.addExits();
        SubwayStation ss5 = new SubwayStation(4, 2, "st. george");
        ss5.addEntrace();
        ss5.addExits();

        BusStation bs1 = new BusStation(2,0, "a");
        bs1.addEntrace();
        bs1.addExits();
        BusStation bs2 = new BusStation(2,2, "b");
        bs2.addEntrace();
        bs2.addExits();
        BusStation bs3 = new BusStation(2,3, "c");
        bs3.addEntrace();
        bs3.addExits();
        BusStation bs4 = new BusStation(2,4, "d");
        bs4.addEntrace();
        bs4.addExits();

        ts.addStation(ss1);
        ts.addStation(ss2);
        ts.addStation(ss3);
        ts.addStation(ss4);
        ts.addStation(ss5);

        ts.addStation(bs1);
        ts.addStation(bs2);
        ts.addStation(bs3);
        ts.addStation(bs4);

        return ts;
    }
}
