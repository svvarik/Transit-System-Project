package Main;

import AdminUsers.AdminUser;
import FareSystem.Card;
import FareSystem.CardMachine;
import TransitUsers.CardHolder;
import TransitUsers.Trip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

/*
Enter station - enter; card id; cardmachine id

Exit station - exit; card id; cardmachine id

Add User - addUser; userName; userEmail

User add card - addNewCard; userEmail

User remove card - removeCard; cardID; userEmail

User add balance to card - addBalance; cardID; amount

User change name - changeName; userEmail; newName

User view recent trips - viewRecentTrips; userEmail

Admin User's daily report - adminView; email;

*/

public class TransitSystemInteractions {

    private TransitSystem ts;

    public TransitSystemInteractions(TransitSystem ts){
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
            FileReader fileReader1 = new FileReader(file);
            BufferedReader fileReader = new BufferedReader(fileReader1);
            String newLine;

            while ((newLine = fileReader.readLine()) != null) {
                // Read input and split it into required arguments
                String[] arguments = newLine.split(";");
                String event = arguments[0].replaceAll("\\s", "");

            // Get a separate array of the required arguments for each event
            if (arguments.length > 1) {
                String[] parameters = Arrays.copyOfRange(arguments, 1, arguments.length);
                for (int i = 0; i < parameters.length; i += 1) {
                parameters[i] = parameters[i].replaceAll("\\s", "");
                }
                this.processEvent(event, parameters);
            } else { // No parameters arg needed
                this.processEvent(event, new String[1]);
                }
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
            case "addUser":
                addUser(args[0], args[1]);
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
            case "adminView":
                adminView(args[0]);
                break;
            case "exitProgram":
                this.ts.getTransitData().dailyReport();
                System.exit(0);
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
     * @return true if the user can enter, false otherwise
     */
    public boolean enterStation(String cID, String cmID) {
        int cardID = Integer.parseInt(cID);
        int entrance = Integer.parseInt(cmID);
        Card thisCard = ts.getCardHolders().findCard(cardID);
        CardMachine thisCM = ts.getStations().findEntrance(entrance);
        if (thisCard == null){
            System.out.println("This card is invalid.");
            return false;
        } else if (thisCM == null){
            System.out.println("This card machine is invalid.");
            return false;
        } else {
            if(thisCard.tapCard(thisCM)){
                System.out.println(thisCard.toString() + " enters " + thisCM.toString());
                return true;
            } else {
                System.out.println("Tap was not successful.");
                return false;
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
        Card thisCard = ts.getCardHolders().findCard(cardID);
        CardMachine thisCM = ts.getStations().findExit(exit);
        if (thisCard == null){
            System.out.println("This card is invalid.");
        } else if (thisCM == null){
            System.out.println("This card machine is invalid.");
        } else {
            if(thisCard.tapCard(thisCM)){
                System.out.println(thisCard.toString() + " exits " + thisCM.toString());
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
     * @param name the CardHolder's name
     * @param email the CardHolder's email
     */
    public void addUser(String name, String email){
        if(ts.getCardHolders().addCardHolder(name, email, ts)){
            System.out.println("User added successfully");
        } else {
            System.out.println("User could not be added.");
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
        CardHolder thisCH = ts.getCardHolders().findCardHolder(ch);
        if(thisCH != null){
            Card newCard = new Card(thisCH, ts.getTapManager());
            thisCH.addCard(newCard);
            System.out.println("New card added to " + thisCH.toString());
            System.out.println("Card " + newCard.toString());
        } else {
            System.out.println("Could not find Card Holder.");
        }
    }

    /***
     * This function adds a new Card to the passed in CardHolder.
     *
     * There does not need to be a check for the CardHolder, this is assumed
     * to have been done in a prior check.
     *
     * @param cardHolder the passed in CardHolder.
     */
    public void addNewCard(CardHolder cardHolder){
        Card newCard = new Card(cardHolder, ts.getTapManager());
        cardHolder.addCard(newCard);
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
        CardHolder cardHolder = ts.getCardHolders().findCardHolder(ch);
        Card card = ts.getCardHolders().findCard(cardID);
        if (cardHolder == null) {
            System.out.println("Could not find this Card Holder.");
        } else if (card == null){
            System.out.println("This card is invalid");
        } else {
            cardHolder.removeCard(cardID);
            System.out.println(card.toString() + " was removed succesfully from " +  cardHolder.toString());
        }
    }

    /**
     * This method removes a Card from a CardHolder given the two objects.
     *
     * It does not do checks for existence, operating on the assumption they have been done prior.
     *
     * @param cardHolder the CardHolder who is having the Card removed
     * @param card the Card object being removed from the CardHolder
     */
    public void removeCard(CardHolder cardHolder, Card card){
        cardHolder.removeCard(card.getCardID());
    }

    /**
     * Suspend a given Card object.
     *
     * @param card the Card being suspended.
     */
    public void suspendCard(Card card) {
        card.suspendCard();
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
        Card card = ts.getCardHolders().findCard(cardID);
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
     * This method adds a specific value to a given Card.
     *
     * This method is more flexible, and can add any amount.
     *
     * @param amount the amount being added
     */
    public void addToBalance(Card card, int amount){
        card.addValue(amount);
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
        CardHolder cardHolder = ts.getCardHolders().findCardHolder(ch);
        if(cardHolder != null){
            cardHolder.setName(newName);
            System.out.println("Name for user " + cardHolder.toString() + " changed successfully");
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
        CardHolder cardHolder = ts.getCardHolders().findCardHolder(ch);
        if(cardHolder != null){
            ArrayList<Trip> trips = cardHolder.viewRecentTrips();
            for (Trip t: trips ) {
                System.out.println(t.toString());
            }
        } else {
            System.out.println("Card holder could not be found in system.");
        }
    }

  public void adminView(String email) {
    AdminUser au = ts.getAdminUsers().findAdminUser(email);
    if (au != null) {
      ts.getTransitData().dailyReport();
    } else {
      System.out.println("This admin user does not exist in the system. Who are you hacker???");
    }
        }

    public boolean loginAdmin(String email, String password){
    AdminUser au = ts.getAdminUsers().findAdminUser(email);
    if (au!= null){
        return true;
    } else {
        return false;
    }

    }
}