public class BusStation extends Station {

    public BusStation(){
        super(true);
    }

    public BusStation(int id, int x, int y) {
        super(id, x, y, true);
    }
}
