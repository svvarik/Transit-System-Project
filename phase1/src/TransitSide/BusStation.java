package TransitSide;

public class BusStation extends Station {

    public BusStation(){
        super(true);
    }

    public BusStation(int x, int y, String name) {
        super(x, y, true, name);
    }
}
