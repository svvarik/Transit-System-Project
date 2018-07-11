package UserSide;

import java.util.Comparator;
import java.util.Date;
import TransitSide.CardMachine;

public class Trip implements Comparator<Trip>, Comparable<Trip> {

    private CardMachine start = null;
    private CardMachine end = null;

    private Date starDate = null;
    private Date endDate = null;

    /**
     * constructs a new Trip
     */
    public Trip(){}

    /**
     * returns a String representation of this Trip
     * @return a String representation of this Trip
     */
    public String toString() {
        String stringRep;
        stringRep = "Start: " + this.start.toString() + " on " + this.starDate +
                "\n" + "End: " + this.end.toString() + " on " + this.endDate +  "\n";
        return stringRep;
    }

    /**
     * sets the stating point of a trip
     * @param cardMachine the CardMachine starting of the a trip
     */
    public void setStart(CardMachine cardMachine){
        this.start = cardMachine;
        Date d = new Date();
        this.starDate = d;
    }
    /**
     * sets the ending point of a trip
     * @param cardMachine the CardMachine ending of the a trip
     */

    public void setEnd(CardMachine cardMachine){
        this.end = cardMachine;
        Date d = new Date();
        this.endDate = d;
    }

    /**
     * ends a trip
     */
    public void endTrip(){
        // pass
    }

    /**
     * returns this trip CardMachine exit
     * @return this trip CardMachine exit
     */
    public CardMachine getEnd() { return end; }

    /**
     * returns this trip CardMachine entrance
     * @return this trip CardMachine entrance
     */
    public CardMachine getStart() { return start; }

    /**
     * returns this trip end Date
     * @return this trip end Date
     */
    public Date getEndDate() { return endDate; }

    /**
     * returns this trip Start Date
     * @return this trip Start Date
     */
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
