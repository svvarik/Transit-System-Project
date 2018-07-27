package TransitUsers;

import java.io.Serializable;
import java.util.Date;
import FareSystem.CardMachine;

public class Trip implements Serializable {

    private static final long serialVersionUID = 295624;
    private CardMachine start = null;
    private CardMachine end = null;

    private Date starDate = null;
    private Date endDate = null;

    private double fare;


    /**
     * constructs a new Trip
     */
    public Trip(){
        this.fare = 0;
    }

    /**
     * returns a String representation of this Trip
     * @return a String representation of this Trip
     */
    public String toString() {
        if(this.end == null){//checks if start is null
            return "Start: " + this.start.toString() + " on " + this.starDate +
                    "\n" + "End: trip did not end ";
        }
        if(this.start == null) {//checks if end is null
           return "Start: Trip did not start " +
                    "\n" + "End: " + this.end.toString() + " on " + this.endDate + "\n";
        }
        return "Start: " + this.start.toString() + " on " + this.starDate +
                "\n" + "End: " + this.end.toString() + " on " + this.endDate + "\n";
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

    public void addFare(Double fare){
        this.fare += fare;
    }

    public double getFare() {
        return fare;
    }
}
