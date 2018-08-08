package Main;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

public class Logger implements Serializable{

    private static final long serialVersionUID = 555555;

    ArrayList<String> events;

    public Logger() {
    this.events = new ArrayList<>();
    }

    public void addToLog(String string){
        this.events.add(Calendar.getInstance().getTime().toString() + "  " + string);
    }

    public ArrayList<String> getEvents() {
        return this.events;
    }
}
