package UserSide;

import TransitSide.BusStation;
import TransitSide.CardMachine;
import TransitSide.SubwayStation;

import java.util.Date;

public class TapManager {

    private Card c;
    public boolean suspended;
    private Date lastEffectiveTap;
    private double amountSinceLastEffectiveTap;
    private boolean firstTap;

    public TapManager(Card c){
        this.c = c;
        this.suspended = false;
        this.amountSinceLastEffectiveTap = 0;
        this.lastEffectiveTap = null;
        this.firstTap = true;
    }

    public Boolean toContinue(CardMachine cm) {
        if(firstTap) {
            System.out.println("First Tap");
            if(cm.getStation() instanceof BusStation){
                resetLastEffective();
                Trip newTrip = new Trip();
                newTrip.setStart(cm);
                this.c.getTrips().add(newTrip);
                this.c.deductValue(c.getOwner().getTs().getFareManager().getFlatFare());
                addAmountSinceLastEffectiveTap(c.getOwner().getTs().getFareManager().getFlatFare());
                this.firstTap = false;
            }
            if(cm.getStation() instanceof SubwayStation){
                Trip newTrip = new Trip();
                newTrip.setStart(cm);
                c.getTrips().add(newTrip);
                this.firstTap = false;
            }
            return false;
        }
        if(suspended){
            System.out.println("Suspended");
            return false;
        }
        if(c.getBalance() < 0){
            System.out.println("Balance less than 0");
            return false;
        }
        if(c.getLastCardMachineTapped().isEntrance() && cm.isEntrance()){
            System.out.println("double entrance");
            c.deductValue(c.getOwner().getTs().getFareManager().getCapFare());
            c.getTrips().get(Math.max(c.getTrips().size()-1, 0)).setEnd(cm);
            return true;
        }
        if(!c.getLastCardMachineTapped().isEntrance() && !cm.isEntrance()){
            System.out.println("Double Exit");
            if(cm.getStation() instanceof SubwayStation){
                c.deductValue(c.getOwner().getTs().getFareManager().getCapFare());
                return true;
            }
            if(cm.getStation() instanceof BusStation){
                c.deductValue(c.getOwner().getTs().getFareManager().getCapFare());
                return false;
            }
        }
        System.out.println("no errors");
        return true;
    }

    /**
     * returns the lasttime the Card was effectively tapped
     * @return the lasttime the Card was effectively tapped
     */
    public Date getLastEffectiveTap() {
        return lastEffectiveTap;
    }

    /**
     * sets a new lastEffectiveTap
     * @param lastEffectiveTap the Date of the lastEffectiveTap
     */
    public void setLastEffectiveTap(Date lastEffectiveTap) {
        this.lastEffectiveTap = lastEffectiveTap;
    }

    /**
     * returns the amount of fare calculated since the lastEffectiveTap
     * @return the amount of fare calculated since the lastEffectiveTap
     */
    public double getAmountSinceLastEffectiveTap() {
        return amountSinceLastEffectiveTap;
    }

    /**
     * sets the amount to the amountSinceLastEffectiveTap
     * @param amountSinceLastEffectiveTap the amount to be set
     */
    public void setAmountSinceLastEffectiveTap(double amountSinceLastEffectiveTap) {
        this.amountSinceLastEffectiveTap = amountSinceLastEffectiveTap;
    }


    /**
     * adds the amount to the amountSinceLastEffectiveTap
     * @param amountToAdd the amount to be added
     */
    public void addAmountSinceLastEffectiveTap(double amountToAdd) {
        if(this.amountSinceLastEffectiveTap + amountToAdd >=6){
            this.amountSinceLastEffectiveTap = 6;
        }else{
            this.amountSinceLastEffectiveTap = this.amountSinceLastEffectiveTap + amountToAdd;
        }
    }

    /**
     * returns true if the tap is within the two hours time period since the lastEffectiveTape
     * @return true if the tap is within the two hours time period since the lastEffectiveTape
     */
    public boolean isWithinTimeLimit(){
        Date d = new Date();
        if (this.lastEffectiveTap != null)
            return (Math.abs(this.lastEffectiveTap.getTime() - d.getTime()) < 7200000);
        return false;
    }

    /**
     * resets the lastEffectiveTap
     */
    public void resetLastEffective(){
        setLastEffectiveTap(new Date());
        setAmountSinceLastEffectiveTap(0);
    }

    /**
     * supspends the Card
     */
    public void suspendCard() {
        this.suspended = true;
    }

    public boolean isSuspended() {
        return suspended;
    }
}
