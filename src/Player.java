import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Player implements Runnable {
  // Private attribute
  private final int id;            // identifier for players
  private ArrayList<Card> card;    // holds the current hand of the player throughout the game
  private FileWriter fw;           // filewriter for logging player actions

  // Constructor
  public Player(int id, CardDeck drawDeck, CardDeck discardDeck, ArrayList<Card> card, CardGame cardgame) {
    this.id = id;
    this.drawDeck = drawDeck;
    this.discardDeck = discardDeck;
    this.card = new ArrayList<>(card);
    this.cardgame = cardgame;
  }

  // gets player's ID
  public int getID() {
    return id;
  }

  // returns the deck the player draws cards from
  public Deck getDrawDeck() {
    return drawDeck;
  }

  // returns the deck the player discards cards to
  public Deck getDiscardDeck() {
    return discardDeck;
  }

  //
  public ArrayList<Card> getPlayerCards(){
        return card;
    }
  
  // check if a player win
  // get rid of unwanted card 
  
  // creates, initializes, updates, and closes the player's outputfile
  public synchronized void writeOutputfile(String operation, int winnerID) {
    try{
      if ("create".equals(operation)) {
        // create player's output file
        File file = new File("./output/player" + id + "_output.txt");
        if (file != null) {
          System.out.println("File is created");
        }
      } else if ("initialize".equals(operation)) {
        // write initial player's hand
        fw.write("player" + id + "initial hand: " + "\n");
        System.out.println("player" + id + "initial hand: ");
        
      } else if ("update".equals(operation)) {
        // updates player's hand
        fw.write("player " + id + " draws " + " from deck " + "\n");
        System.out.println("player " + id + " draws " + " from deck ");
        
        fw.write("player " + id + " discards a " + " to deck " + "\n");
        System.out.println("player " + id + " discards a " + " to deck ");
        
        fw.write("player " + id + " current hand is " + "\n");
        System.out.println("player " + id + " current hand is ");
        
      } else if ("finalize".equals(operation)) {
        // write the final hand and close the writer
        if (winnerID != id) {
        fw.write("player " + id + " wins");
        System.out.println("player " + id + " wins");
        } else {
          fw.write("player " + winnerID + " has informed player" + id + " that player " + winnerID + " has won\n");
          System.out.println("player " + winnerID + " wins");
        }
        
        fw.write("player " + id + " exits\n");
        fw.write(card.getFinalDeck());
        fw.close();
      } else {
        System.out.println("Invalid operation type.");
      }
    } catch (IOException e) {
      System.out.println("Error occurred while handling the outputfile.");
      e.printStackTrace();
    }
}
