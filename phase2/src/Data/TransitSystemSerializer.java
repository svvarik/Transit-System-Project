package Data;


import Main.TransitSystem;
import UserSide.CardHolder;

import java.io.*;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransitSystemSerializer {

    private final static Logger logger  = Logger.getLogger(TransitSystem.class.getName());

    private static final Handler consoleHandler = new ConsoleHandler();

    public TransitSystemSerializer() throws ClassNotFoundException, IOException {
        // Associate the handler with the logger.
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
        }

    public TransitSystem readFromFile(String path) throws ClassNotFoundException {

        try {
            InputStream file = new FileInputStream(path);
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            //deserialize the Map
            System.out.println("Does this work?");
            TransitSystem ts = (TransitSystem) input.readObject();
            input.close();
            return ts;
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Cannot read from input.", ex);
            return null;
        }
    }

    public void saveToFile(String filePath, TransitSystem ts) throws IOException {

        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        // serialize the Map
        output.writeObject(ts);
        output.close();
    }
}

