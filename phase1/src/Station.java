public class Station {

    private static int idCount = 000001;
    /** location for this station */

    private int x;
    private int y;

    private boolean FlatRate = false;

    private int id;

    public Station(){}{
        this.id = idCount;
        idCount++;
    }

    public Station(int id, int x, int y){
        this.id =idCount;
        idCount++;
        this.x = x;
        this.y = y;
    }

    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getId(){ return this.id;}

    public int getX(){return this.x;}

    public int getY(){return this.y;}

    public boolean isFlatRate(){
        return FlatRate;
    };
}
