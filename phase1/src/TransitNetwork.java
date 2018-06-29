import java.util.Date;

public class TransitNetwork {
    private double flatFare = 2;
    private double tripFare = 0.5;

    private long startTimeNum;
    private String startTimeString;
    private Date date;

    public TransitNetwork(){
        this.date = new Date();
        this.startTimeNum = date.getTime();
        this.startTimeString = date.toString();
    }
    /** setters for the TransitNetwork's fare values */
    public void setFlatFare(double fare){ this.flatFare = fare;}
    public void setTripFare(double fare){this.tripFare = fare;}



    /** getters for this TransitNetwork's fare values */
    public double getFlatFare(){ return this.flatFare;}
    public double getTripFare(){ return this.tripFare;}

    /**getter for the TransitNetwork start time in both numericals and String */
    public long getStartTimeNum(){ return this.startTimeNum;}
    public String getStartTimeString(){ return this.startTimeString;}

    /** getRunningTime returns the systems runingtime in ms */
    public long getRunningTime(){
        Date d = new Date();
        return d.getTime() - this.startTimeNum;
    }

    public void calcFare(Card card, Station station){
        if(station.isFlatRate()) {card.deductFare(this.flatFare);}
        else{
            if(!card.isTapOn()) {card.setTapOn(true);}
            else{
                Date d = new Date();
                if(d.getTime() - card.getLastTapDate().getTime() > 7200000){ //if it has been more than 2 hours since the past trip
                    card.deductFare(this.flatFare);
                    card.setTapOn(true);
                }
                else{
                    double tripfare = Math.abs(station.getX() - card.getLastStation().getX()) * this.tripFare;
                    card.deductFare(tripFare);
                    card.setTapOn(false);
                }
            }
        }
    }
}
