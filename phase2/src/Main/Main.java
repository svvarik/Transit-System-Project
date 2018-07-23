package Main;

import Data.TransitSystemSerializer;
import TransitSide.BusStation;
import TransitSide.SubwayStation;
import UserSide.*;

import java.io.IOException;

public class Main {

    private static String filepath = "./serializedTransitSystem.ser";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        TransitSystemSerializer saveFileHelper = new TransitSystemSerializer();

        TransitSystem ts = Main.start();

        // Handling Events (To be Replaced by GUI)
        TransitSystemIO tsIO = new TransitSystemIO(ts);
        tsIO.readFile("../events.txt");

        // Save the log to file
        saveFileHelper.saveToFile(filepath, ts);
        System.exit(0);

    }

    //Instantiates everything
    private static TransitSystem start() throws IOException, ClassNotFoundException {
        // INSTANTIATE EVERYTHING ELSE, CARDHOLDERS, CARDMACHINES, ETC ETC ETC
        TransitSystemSerializer tsLog = new TransitSystemSerializer();
        TransitSystem newTS = tsLog.readFromFile(filepath);

//        newTS.addCardHolder("HAL", "HAL@mail.com");
//        newTS.addCardHolder("Dave", "Dave@mail.com");
//        newTS.findCardHolder("HAL@mail.com").addCard(new Card(newTS.findCardHolder("HAL@mail.com"), newTS.getTapManager()));
//        newTS.findCardHolder("Dave@mail.com").addCard(new Card(newTS.findCardHolder("Dave@mail.com"), newTS.getTapManager()));
//        newTS.addAdminUser("Sai", "sai@sai.com");
//
//        SubwayStation ss1 = new SubwayStation(0, 2, "ossington");
//        ss1.addEntrace();
//        ss1.addExits();
//        SubwayStation ss2 = new SubwayStation(1, 2, "christie");
//        ss2.addEntrace();
//        ss2.addExits();
//        SubwayStation ss3 = new SubwayStation(2, 2, "bathurst");
//        ss3.addEntrace();
//        ss3.addExits();
//        SubwayStation ss4 = new SubwayStation(3, 2, "spadina");
//        ss4.addEntrace();
//        ss4.addExits();
//        SubwayStation ss5 = new SubwayStation(4, 2, "st. george");
//        ss5.addEntrace();
//        ss5.addExits();
//
//        BusStation bs1 = new BusStation(2,0, "a");
//        bs1.addEntrace();
//        bs1.addExits();
//        BusStation bs2 = new BusStation(2,2, "b");
//        bs2.addEntrace();
//        bs2.addExits();
//        BusStation bs3 = new BusStation(2,3, "c");
//        bs3.addEntrace();
//        bs3.addExits();
//        BusStation bs4 = new BusStation(2,4, "d");
//        bs4.addEntrace();
//        bs4.addExits();
//
//        newTS.addStation(ss1);
//        newTS.addStation(ss2);
//        newTS.addStation(ss3);
//        newTS.addStation(ss4);
//        newTS.addStation(ss5);
//
//        newTS.addStation(bs1);
//        newTS.addStation(bs2);
//        newTS.addStation(bs3);
//        newTS.addStation(bs4);

        return newTS;
    }
}
