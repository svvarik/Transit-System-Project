package TransitSide;

public class SubwayStation extends Station {

    public SubwayStation(){
        super(false);
    }

    public SubwayStation(int x, int y, String name) {
        super(x, y, false, name);
    }

}
