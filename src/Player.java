import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Player implements Runnable {
  // Private attribute
  private int id;
  private ArrayList<Card> card;
  
  // Represents a player in the game, manages their hand, and handles game logic specific to players
  // Player's hand
  // Checks that all cards are the same
  // get rid of unwanted card 
  // Player's final hand
  
  // Updates the player's outputfile
  public synchronized void writeOutputfile(String operation, int winnerID) {
    try{
      if ("initialize".equals(operation)) {
        // write initial player's hand
        FileWriter fw = new FileWriter("./output/player" + id + "_output.txt");
        fw.write("player" + id + "initial hand: " + startingCards + "\n");
        System.out.println(startingCards);
        
      } else if ("update".equals(operation)) {
        // updates player's hand
        fw.write("player " + id + " draws " + " from deck " + "\n");
        System.out.println("player " + id + " draws " + " from deck ");
        
        fw.write("player " + id + " discards a " + " to deck " + "\n");
        System.out.println("player " + id + " discards a " + " to deck " );
        
        fw.write("player " + id + " current hand is " + "\n");
        System.out.println("player " + id + " current hand is ");
        
        fw.write( + "\n");
        
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
