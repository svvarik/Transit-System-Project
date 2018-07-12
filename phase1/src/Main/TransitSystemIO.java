package Main;

import TransitSide.CardMachine;
import UserSide.Card;
import UserSide.CardHolder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

/*
Enter station - enter; card id; cardmachine id

Exit station - exit; card id; cardmachine id

User add card - addNewCard; userEmail

User remove card - removeCard; cardID; userEmail

User add balance to card - addBalance; cardID; amount

User change name - changeName; userEmail; newName

User view recent trips - viewRecentTrips; userEmail;

*/

public class TransitSystemIO {

    private TransitSystem ts;

    public TransitSystemIO(TransitSystem ts){
        this.ts = ts;
    }

    /**
     * Reads events to be processed by the Transit System, from a specified text file.
     *
     * Events must be formatted as specified in program ReadMe in order for
     * function to properly parse events.
     *
     * @param filename the file name to be read from
     */
    public void readFile(String filename) {
        try {
            FileReader fileReader1 = new FileReader(filename);
            BufferedReader fileReader = new BufferedReader(fileReader1);
            String newLine;

            while ((newLine = fileReader.readLine()) != null) {
                // Read input and split it into required arguments
                String[] arguments = newLine.split(";");
                String event = arguments[0].replaceAll("\\s", "");

                // Get a separate array of the required arguments for each event
                String[] parameters = Arrays.copyOfRange(arguments, 1, arguments.length);
                for(int i = 0; i < parameters.length; i += 1){
                    parameters[i] = parameters[i].replaceAll("\\s", "");
                    System.out.println(parameters[i]);
                }

                this.processEvent(event, parameters);
            }
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Processes a string event and its arguments, formatted as specified in
     * the Readme.
     *
     * @param event the event that is occurring
     * @param args the required arguments of the event
     */
    public void processEvent(String event, String[] args){
        switch(event){
            case "enter":
                enterStation(args[0], args[1]);
                break;
            case "exit":
                exitStation(args[0], args[1]);
                break;
            case "addNewCard":
                addNewCard(args[0]);
                break;
            case "removeCard":
                removeCard(args[0], args[1]);
                break;
            case "addToBalance":
                addToBalance(args[0], args[1]);
                break;
            case "changeName":
                changeName(args[0], args[1]);
                break;
            case "viewRecentTrips":
                viewRecentTrips(args[0]);
                break;
            default:
                System.out.println("Incorrect argument");
        }

    }

    /**
     * Attempts to tap a Card onto an entry CardMachine.
     *
     * This function attempts to search for the Card and CardMachine
     * in the TransitSystem using find methods. If the Card or the CardMachine
     * cannot be found, a statement is outputted to the screen.
     *
     * @param cID the Card ID being tapped
     * @param cmID the CardMachine ID that the Card is tapping on
     */
    public void enterStation(String cID, String cmID) {
        int cardID = Integer.parseInt(cID);
        int entrance = Integer.parseInt(cmID);
        Card thisCard = ts.findCard(cardID);
        CardMachine thisCM = ts.findEntrance(entrance);
        if(thisCard != null && thisCM != null){
            thisCard.tapCard(thisCM);
        } else {
            System.out.println("Invalid Card or Card Machine");
        }
    }


    /**
     * Attempts to tap a Card onto an exit CardMachine.
     *
     * This function attempts to search for the Card and CardMachine
     * in the TransitSystem using find methods. If the Card or the CardMachine
     * cannot be found, a statement is outputted to the screen.
     *
     * @param cID the Card ID being tapped
     * @param cmID the CardMachine ID that the Card is tapping on
     */
    public void exitStation(String cID, String cmID) {
        int cardID = Integer.parseInt(cID);
        int exit = Integer.parseInt(cmID);
        Card thisCard = ts.findCard(cardID);
        CardMachine thisCM = ts.findExit(exit);
        if(thisCard != null && thisCM != null){
            thisCard.tapCard(thisCM);
        } else {
            System.out.println("Invalid Card or Card Machine");
        }
    }

    /**
     * Creates and adds a new Card to a specified User, based on their email.
     *
     * This function attempts to search for the User in the TransitSystem
     * using a find method. If the User does not exist, a statement is outputted
     * to screen.
     *
     * @param ch the CardHolder
     */
    public void addNewCard(String ch){
        CardHolder thisCH = ts.findCardHolder(ch);
        if(thisCH != null){
            thisCH.addCard(new Card(thisCH, ts));
        }
    }

    /**
     * Removes a Card from a specified User, based on their email.
     *
     * This function attempts to search for the User in the TransitSystem
     * using a find method. If the User does not exist, a statement is outputted
     * to screen.
     *
     * @param ch the CardHolder
     * @param cID the CardID being removed
     */
    public void removeCard(String ch, String cID){
        int cardID = Integer.parseInt(cID);
        CardHolder cardHolder = ts.findCardHolder(ch);
        Card card = ts.findCard(cardID);
        if (cardHolder != null && card != null){
            cardHolder.removeCard(cardID);
        } else {
            System.out.println("Invalid card or card holder");
        }
    }

    /**
     * Adds a specified amount to a specified Card, based on the CardID.
     *
     * This function attempts to search for the Card in the TransitSystem
     * using a find method. If the Card does not exist, a statement is outputted
     * to screen.
     *
     * @param cID the CardID
     * @param amount the amount being added
     */
    public void addToBalance(String cID, String amount){
        int cardID = Integer.parseInt(cID);
        int addedAmount = Integer.parseInt(amount);
        Card card = ts.findCard(cardID);
        if (card != null){
            card.addValue(addedAmount);
        } else {
            System.out.println("Could not find card");
        }
    }

    /**
     * Modifies a CardHolder's name, changing it to an inputted name.
     *
     * This function attempts to search for the CardHolder in the TransitSystem
     * using a find method. If the User does not exist, a statement is outputted
     * to screen.
     *
     * @param ch the CardHolder
     * @param newName the name requested for change
     */
    public void changeName(String ch, String newName){
        CardHolder cardHolder = ts.findCardHolder(ch);
        if(cardHolder != null){
            cardHolder.setName(newName);
        } else {
            System.out.println("Card holder could not be found in system.");
        }

    }

    /**
     * Outputs to screen the last three trips this CardHolder took.
     *
     * This function attempts to search for the CardHolder in the TransitSystem
     * using a find method. If the CardHolder does not exist, a statement is outputted
     * to screen.
     *
     * @param ch the CardHolder
     */
    public void viewRecentTrips(String ch){
        CardHolder cardHolder = ts.findCardHolder(ch);
        if(cardHolder != null){
            cardHolder.viewRecentTrips();
        } else {
            System.out.println("Card holder could not be found in system.");
        }
    }

}