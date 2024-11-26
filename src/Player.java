import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Player implements Runnable{
    // Private Variables
    private int id;
    private Deck lastDeck;
    private Deck nextDeck;
    private ArrayList<Card> cards;
    private Board board;
    private FileWriter fw;

    // Get Methods
    public int getID(){
        return id;
    }
    public Deck getLastDeck(){
        return lastDeck;
    }
    public Deck getNextDeck(){
        return nextDeck;
    }
    public synchronized ArrayList<Card> getPlayerCards(){
        return cards;
    }

    // Returns a string of the player's hand
    public String getCards(){
        String currentCards = "player " + id +" current hand: ";
        for(Card card: cards){
            currentCards += " " + card.getRank();
        }
        return currentCards;
    }
    // Returns a string of the player's final hand
    public String finalCards(){
        String currentCards = "player " + id +" final hand: ";
        for(Card card: cards){
            currentCards += " " + card.getRank();
        }
        return currentCards;
    }

    // Implemented thread run method
    public void run(){
        while(!Thread.currentThread().isInterrupted()){
            if(checkWin()){
                // Interrupt
                Thread.currentThread().interrupt();
            } else {
                deal();
            }
        }
    }

    // Constructor
    public Player(int id, Deck lastDeck, ArrayList<Card> cards, Deck nextDeck, Board board){
        this.id = id;
        this.lastDeck = lastDeck;
        this.nextDeck = nextDeck;
        this.cards = cards;
        this.board = board;

        // Creates a string to hold the player's initial hand
        String startingCards = "Player " + id + " intial hand: ";
        for(Card card: cards){
            startingCards += " " + card.getRank();
        }
        // Creates the player's file
        createPlayerFile(startingCards);
    }

    public synchronized boolean deal(){
        // Checks if Deck is empty
        while(lastDeck.getCards().isEmpty()){
            // Interrupts currentThread
            if(Thread.currentThread().isInterrupted()){
                return false;
            }
        }
        // Gives Player a tendancy to keep cards of the same rank as their player ID
        for(int i=0; i < cards.size(); i++){
            if(cards.get(i).getRank() != id){
                Card lastCard = cards.get(i);
                nextDeck.addCard(cards.remove(i));
                Card newCard = lastDeck.removeCard();
                cards.add(newCard);

                writeHand(lastCard, newCard);
                break;
            }
        }
        return true;
    }


    public synchronized boolean checkWin(){
        cards.stream();
        // Ensures 4 cards are in the player's hand
        if(cards.size() != 4){
            return false;
        }
        // Checks that all cards are the same
        for(int i=0 ; i < 4; i++){
            if(cards.get(i).getRank() != cards.get(0).getRank()){
                return false;
            }
        }
        // Notifies game that the player has won
        board.finished(id);
        return true;
    }

    // Creates a new file for the player's log, and writes to it the initial starting cards
    public void createPlayerFile(String startingCards){
        try {
            File newFile = new File("./outputs/player/player" + id + "_output.txt");
            if (newFile.createNewFile()){
                System.out.println("File created: " + newFile.getName());
            } else {
                System.out.println("File already exists!");
            }
            fw = new FileWriter("./outputs/player/player"+ id + "_output.txt");
            fw.write(startingCards+"\n");
            System.out.println(startingCards);
        } catch(IOException e){
            System.out.println("Error occurred while creating a new file");
            e.printStackTrace();
        }
    }

    // Writes to the player's file to update the actions being performed
    public void writeHand(Card lastCard, Card newCard){
        try {
            fw.write("player "+id+" discards a "+ lastCard.getRank() + " to deck " + nextDeck.getID()+"\n");
            System.out.println("player "+id+" discards a "+ lastCard.getRank() + " to deck " + nextDeck.getID());

            fw.write("player "+id+" draws "+ newCard.getRank() + " from deck " + lastDeck.getID()+"\n");
            System.out.println("player "+id+" draws "+ newCard.getRank() + " from deck " + lastDeck.getID());

            fw.write(getCards()+"\n");

        } catch (IOException e){
            System.out.println("Error occured whilst writing to a file");
            e.printStackTrace();
        }
    }

    // Writes to the file to record the final actions and then closes the file writer
    public synchronized void writeFinalHand(int winnerID){
        try{
            if(winnerID != id){
                fw.write("player "+ winnerID +" has informed player" + id + " that player "
                        + winnerID + " has won\n");
            } else {
                fw.write("player " +id+ " wins\n");
                System.out.println("player " +id+ " wins");
            }

            System.out.println(finalCards());
            // Finishes writing exit message to the file and closes the file writer
            fw.write("player "+id+" exits\n");
            fw.write(finalCards()+"\n");
            fw.close();
        } catch (IOException e){
            System.out.println("Error occurred whilst writing to a file");
            e.printStackTrace();
        }
    }

}