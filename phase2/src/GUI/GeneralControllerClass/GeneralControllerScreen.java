package GUI.GeneralControllerClass;

import GUI.ControllerHelper;
import Main.TransitSystem;
import Main.TransitSystemInteractions;

public abstract class GeneralControllerScreen {
    private TransitSystem ts;
    private TransitSystemInteractions tsIO;
    private ControllerHelper controllerHelper;

    public GeneralControllerScreen(){}
    public GeneralControllerScreen(TransitSystem ts, TransitSystemInteractions tsIO){
        this.ts = ts;
        this.tsIO = tsIO;
        this.controllerHelper = new ControllerHelper();
    }

    public ControllerHelper getControllerHelper(){
        return this.controllerHelper;
    }

    public void setTs(TransitSystem ts) {
        this.ts = ts;
    }

    public TransitSystem getTs() {
        return this.ts;
    }

    public void setTsIO(TransitSystemInteractions tsIO) {
        this.tsIO = tsIO;
    }

    public TransitSystemInteractions getTsIO() {
        return tsIO;
    }

    public abstract void setUpController(Object object);

    public abstract void setUpController();
}
