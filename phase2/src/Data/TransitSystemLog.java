package Data;


import Main.TransitSystem;
import UserSide.CardHolder;

import java.io.*;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransitSystemLog {

    private final static Logger logger  = Logger.getLogger(TransitSystem.class.getName());

    private static final Handler consoleHandler = new ConsoleHandler();

    private TransitSystem transitSystem;

    public TransitSystemLog(String filePath) throws ClassNotFoundException, IOException {
        transitSystem = new TransitSystem();
        // Associate the handler with the logger.
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);

        // Reads serializable objects from file.
        // Populates the record list using stored data, if it exists.
        File file = new File(filePath);
        if (file.exists()) {
            readFromFile(filePath);
        } else {
            file.createNewFile();

        }
    }

    public TransitSystem getTransitSystem() {
        return transitSystem;
    }

    public void readFromFile(String path) throws ClassNotFoundException {

        try {
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            //deserialize the Map
            System.out.println("Does this work?");
            transitSystem = (TransitSystem) input.readObject();
            input.close();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Cannot read from input.", ex);
        }
    }

    public void saveToFile(String filePath) throws IOException {

        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        // serialize the Map
        output.writeObject(transitSystem);
        output.close();
    }

    public void addCardHolder(String name, String email) {
        transitSystem.addCardHolder(name, email);

        // Log the addition of a CardHolder.
        logger.log(Level.FINE, "Added a new CardHolder " + name);
    }

    public void setTransitSystem(TransitSystem transitSystem) {
        this.transitSystem = transitSystem;
    }
}

