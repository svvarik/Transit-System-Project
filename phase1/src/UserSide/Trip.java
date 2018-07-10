package UserSide;

import java.util.Comparator;
import java.util.Date;
import TransitSide.CardMachine;

public class Trip implements Comparator<Trip>, Comparable<Trip> {

    private CardMachine start = null;
    private CardMachine end = null;

    private Date starDate = null;
    private Date endDate = null;

    public Trip(){}

    public void setStart(CardMachine cardMachine){
        this.start = cardMachine;
        Date d = new Date();
        this.starDate = d;
    }

    public void setEnd(CardMachine cardMachine){
        this.end = cardMachine;
        Date d = new Date();
        this.endDate = d;
    }

    public void endTrip(){
        // pass
    }

    public CardMachine getEnd() { return end; }
    public CardMachine getStart() { return start; }

    public Date getEndDate() { return endDate; }
    public Date getStarDate() { return starDate; }

    // Overriding the compareTo method
    public int compareTo(Trip t) {
        return (this.endDate).compareTo(t.endDate);
    }

    // Overriding the compare method to sort the age
    public int compare(Trip t1, Trip t2) {
        return (int)(t1.endDate.getTime() / 10000) - (int)(t2.endDate.getTime() / 10000);
    }
}
