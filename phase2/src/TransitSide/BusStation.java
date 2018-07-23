package TransitSide;

import java.io.Serializable;

public class BusStation extends Station implements Serializable {

    /**
     * Constructs a new BusStation with location and name.
     *
     * @param x x coordinate of this station
     * @param y y coordinate of this station
     * @param name name of this station
     */
    public BusStation(int x, int y, String name) {
        super(x, y, true, name);
    }
}
