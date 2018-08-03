package GUI.GeneralControllerClass;

import Main.TransitSystem;
import Main.TransitSystemIO;

public abstract class GeneralControllerScreen {
    private TransitSystem ts;
    private TransitSystemIO tsIO;

    public GeneralControllerScreen(){}
    public GeneralControllerScreen(TransitSystem ts, TransitSystemIO tsIO){
        this.ts = ts;
        this.tsIO = tsIO;
    }
    public void setTs(TransitSystem ts) {
        this.ts = ts;
    }

    public TransitSystem getTs() {
        return this.ts;
    }

    public void setTsIO(TransitSystemIO tsIO) {
        this.tsIO = tsIO;
    }

    public TransitSystemIO getTsIO() {
        return tsIO;
    }

    public abstract void setUpController(Object object);
}
