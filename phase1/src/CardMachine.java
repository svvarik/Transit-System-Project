public class CardMachine {

    private boolean entrance;

    private Station station;

    private int id;


    public CardMachine(boolean entrance, Station station, int id){
        this.id = id;
        this.entrance = entrance;
        this.station = station;
    }

    public Station getStation(){return this.station;}
    public int getId(){return this.id;}
    public boolean isEntrance(){return this.entrance;}
}
