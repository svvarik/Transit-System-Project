package UserSide;

import TransitSide.BusStation;
import TransitSide.CardMachine;
import TransitSide.SubwayStation;

import java.io.Serializable;
import java.util.Date;

public class TapManager implements Serializable {

    public TapManager(){}

    /**
     * Checks any errors when the card taps
     * @param cm card machine that was tapped by the card
     * @return returns whether or not to continue to calculate fare
     */
    public Boolean toContinue(Card c, CardMachine cm) {
        if(c.isFirstTap()) {//checks whether this is the card's first tap
            if(cm.getStation().isFlatRate()){
                c.resetLastEffective();
                Trip newTrip = new Trip();
                newTrip.setStart(cm);
                c.addTrip(newTrip);
                c.deductValue(c.getOwner().getTs().getFareManager().getFlatFare());
                c.addAmountSinceLastEffectiveTap(c.getOwner().getTs().getFareManager().getFlatFare());
                c.setFirstTap(false);
            }
            if(!cm.getStation().isFlatRate()){
                Trip newTrip = new Trip();
                newTrip.setStart(cm);
                c.addTrip(newTrip);
                c.setFirstTap(false);
            }
            return false;
        }
        if(c.isSuspended()){//checks if the card is suspended
            return false;
        }
        if(c.getBalance() < 0){//checks if the card's balance is less than 0
            return false;
        }
        if(c.getLastCardMachineTapped().isEntrance() && cm.isEntrance()){//checks if the card has a double entrance
            c.deductValue(c.getOwner().getTs().getFareManager().getCapFare());
            c.getAllTrips().get(Math.max(c.getAllTrips().size()-1, 0)).setEnd(cm);
            return true;
        }
        if(!c.getLastCardMachineTapped().isEntrance() && !cm.isEntrance()){//checks if the card has a double exit
            if(!cm.getStation().isFlatRate()){
                c.deductValue(c.getOwner().getTs().getFareManager().getCapFare());
                return true;
            }
            if(cm.getStation().isFlatRate()){
                c.deductValue(c.getOwner().getTs().getFareManager().getCapFare());
                return false;
            }
        }
        return true;
    }

    /**
     * Initializes or ends a trip depending on user location, and deducts fare if required.
     *
     * <p>This method will handle exceptions such as incomplete previous trips or current trips as
     * specified by incomplete trip handlers.
     *
     * @param cm the cardmachine this card is tapped on
     */
    public boolean tapCard(Card c, CardMachine cm){
        Boolean contd = toContinue(c, cm);
        if(contd){
            if(!cm.getStation().isFlatRate()){
                tapDaynamicStation(c, cm);
            }
            if(cm.getStation().isFlatRate()){
                tapFlatStation(c, cm);
            }
        }else{
            return true;
        }
        return true;
    }

    /**
     * Deducts fare when tapped at a SubwayStation
     * @param cm CardMachine that was tapped at
     */
    private void tapDaynamicStation(Card c, CardMachine cm){
        if (cm.isEntrance()) {
            if(c.getOwner().getTs().getFareManager().isDisjoint(c, cm)){
                c.resetLastEffective();
            }
            Trip newTrip = new Trip();
            newTrip.setStart(cm);
            c.addTrip(newTrip);
        }else{ // is exist so we end trip and calc fare
            double fare = c.getOwner().getTs().getFareManager().calcDynamicFare(c, cm);// Calculate fare
            c.getAllTrips().get(Math.max(c.getAllTrips().size()-1, 0)).setEnd(cm);
            c.deductValue(fare);// Deduct fare from this card
        }
    }

    /**
     * Deducts fare when tapped at a SubwayStation
     * @param cm CardMachine that was tapped at
     */
    private void tapFlatStation(Card c, CardMachine cm){
        if (cm.isEntrance()) { // if entrance:
            if(c.getOwner().getTs().getFareManager().isDisjoint(c, cm)){
                c.resetLastEffective();
            }
            double fare = c.getOwner().getTs().getFareManager().calcFlatFare(c, cm);// Calculate fare
            Trip newTrip = new Trip();
            newTrip.setStart(cm);
            c.addTrip(newTrip);
            c.deductValue(fare);// Deduct fare from this card
        }else{ // is exit so we end trip
            c.getAllTrips().get(Math.max(c.getAllTrips().size()-1, 0)).setEnd(cm);
        }
    }

}
