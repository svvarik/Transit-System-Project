public class Station {

    private static int idCount = 000001;
    /** location for this station */

    private int x;
    private int y;

    private boolean flatRate;

    private int id;

    public Station(boolean flatRate){}{
        this.id = idCount;
        idCount++;
        this.flatRate = flatRate;
    }

    public Station(int x, int y, boolean flatRate){
        this.id =idCount;
        idCount++;
        this.x = x;
        this.y = y;
        this.flatRate = flatRate;
    }

    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getId(){ return this.id;}

    public int getX(){return this.x;}

    public int getY(){return this.y;}

    public boolean isFlatRate(){
        return this.flatRate;
    };
}
