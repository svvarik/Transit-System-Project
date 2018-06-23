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
        this.startTimeString date.toString();
    }
    /** setters for the TransitNetwork's fare values */
    public void setFlatFare(double fare){ this.flatFare = fare;}
    public void setTripFare(double fare){this.tripFare = fare;}



    /** getters for this TransitNetwork's fare values */
    public double getFlatFare(){ return this.flatFare;}
    public double getTripFare(){return this.tripFare;}

    /**getter for the TransitNetwork start time in both numericals and String */
    public long getStartTimeNum(){return this.startTimeNum;}
    public String getStartTimeString(){return this.startTimeString;}

    /** getRunningTime returns the systems runingtime in ms */
    public long getRunningTime(){return this.date.getTime() - this.startTimeNum;}
}
