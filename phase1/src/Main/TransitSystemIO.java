package Main;

import TransitSide.CardMachine;
import UserSide.Card;
import UserSide.CardHolder;
import UserSide.Trip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
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
            File file = new File(filename);
            FileReader fileReader1 = new FileReader(file.getAbsolutePath());
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
        if (thisCard == null){
            System.out.println("This card is invalid.");
        } else if (thisCM == null){
            System.out.println("This card machine is invalid.");
        } else {
            if(thisCard.tapCard(thisCM)){
                System.out.println(thisCard.toString() + "enters" + thisCM.toString());
            } else {
                System.out.println("Tap was not successful.");
            }
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
    private void exitStation(String cID, String cmID) {
        int cardID = Integer.parseInt(cID);
        int exit = Integer.parseInt(cmID);
        Card thisCard = ts.findCard(cardID);
        CardMachine thisCM = ts.findExit(exit);
        if (thisCard == null){
            System.out.println("This card is invalid.");
        } else if (thisCM == null){
            System.out.println("This card machine is invalid.");
        } else {
            if(thisCard.tapCard(thisCM)){
                System.out.println(thisCard.toString() + "exits" + thisCM.toString());
            } else {
                System.out.println("Tap was not successful.");
            }
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
    private void addNewCard(String ch){
        CardHolder thisCH = ts.findCardHolder(ch);
        if(thisCH != null){
            thisCH.addCard(new Card(thisCH));
            System.out.println("Card added to " + thisCH.toString());
        } else {
            System.out.println("Could not find Card Holder.");
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
    private void removeCard(String ch, String cID){
        int cardID = Integer.parseInt(cID);
        CardHolder cardHolder = ts.findCardHolder(ch);
        Card card = ts.findCard(cardID);
        if (cardHolder == null) {
            System.out.println("Could not find this Card Holder.");
        } else if (card == null){
            System.out.println("This card is invalid");
        } else {
            cardHolder.removeCard(cardID);
            System.out.println(card.toString() + "was removed succesfully from " +  cardHolder.toString());
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
    private void addToBalance(String cID, String amount){
        int cardID = Integer.parseInt(cID);
        int addedAmount = Integer.parseInt(amount);
        Card card = ts.findCard(cardID);
        if (card != null){
            if (card.addValue(addedAmount)) {
                System.out.println("Amount added successfully.");
            } else {
                System.out.println("Please add $10, $20, or $50 at a time.");
            }
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
            System.out.println("Name for user " + cardHolder.toString() + "changed successfully");
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
            ArrayList<Trip> trips = cardHolder.viewRecentTrips();
            for (Trip t: trips ) {
                System.out.println(t.toString());
            }
        } else {
            System.out.println("Card holder could not be found in system.");
        }
    }

}