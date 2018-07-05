import java.util.Date;

public class Trip {

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

    public CardMachine getEnd() { return end; }
    public CardMachine getStart() { return start; }

    public Date getEndDate() { return endDate; }
    public Date getStarDate() { return starDate; }
}
