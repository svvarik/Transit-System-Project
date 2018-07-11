package TransitSide;

public class BusStation extends Station {

    /**
     * constructs a new instance of BusStation
     */
    public BusStation(){
        super(true);
    }

    /**
     * constructs a new BusStation with location and name
     * @param x x coordinate of this station
     * @param y y coordinate of this station
     * @param name name of this station
     */
    public BusStation(int x, int y, boolean flatrate, String name) {
        super(x, y, true, name);
    }
}
