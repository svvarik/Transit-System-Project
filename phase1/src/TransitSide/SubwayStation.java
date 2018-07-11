package TransitSide;

public class SubwayStation extends Station {

    /**
     * creates a new SubwayStation , not flatRated
     */
    public SubwayStation(){
        super(false);
    }

    /**
     * creates a new SubwayStation, not flatRated
     * @param x this SubwayStation's first coordinate
     * @param y this SubwayStation's second coordinate
     * @param name this SubwayStation's name
     */
    public SubwayStation(int x, int y, String name) {
        super(x, y, false, name);
    }

}
