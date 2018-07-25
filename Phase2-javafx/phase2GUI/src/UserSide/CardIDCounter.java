package UserSide;

import java.io.Serializable;

public class CardIDCounter implements Serializable {
    private int idCounter = 0;

    public void updateCounter(){
        idCounter += 1;
    }

    public int getIdCounter(){
        return this.idCounter;
    }
}
