package GUI.GeneralControllerClass;

import GUI.HelperClasses.ControllerHelper;
import Main.TransitSystem;
import Main.TransitSystemInteractions;

public class GeneralControllerScreen {
    private TransitSystem ts;
    private TransitSystemInteractions tsIO;
    private ControllerHelper controllerHelper;

    public GeneralControllerScreen(){
        this.controllerHelper = new ControllerHelper();
    }
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

    public void setUpController(Object object){}

    public void setUpController(){}
}
