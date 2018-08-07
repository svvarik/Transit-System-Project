package GUI.GeneralControllerClass;

import GUI.HelperClasses.ControllerHelper;
import Main.TransitSystem;
import Main.TransitSystemInteractions;

public class GeneralControllerScreen {
    private TransitSystem ts;
    private TransitSystemInteractions transitSystemInteractions;
    private ControllerHelper controllerHelper;

    public GeneralControllerScreen(){
        this.controllerHelper = new ControllerHelper();
        this.transitSystemInteractions = new TransitSystemInteractions();
    }

    public void setTransitSystem(TransitSystem ts) {
        this.ts = ts;
    }

    public TransitSystem getTransitSystem() {
        return this.ts;
    }

    public TransitSystemInteractions getTransitSystemInteractions() {
        return transitSystemInteractions;
    }

    public ControllerHelper getControllerHelper(){
        return this.controllerHelper;
    }

    public void setUpController(Object object){}

    public void setUpController(){}
}
