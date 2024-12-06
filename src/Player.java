import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringBuilder;

public class Player implements Runnable {
  // Private attribute
  private final int id;            // identifier for players
  private ArrayList<Card> card;    // holds the current hand of the player throughout the game
  private CardDeck drawDeck;       // Decks used for drawing cards
  private CardDeck discardDeck;    // Decks used for discarding cards
  private CardGame cardGame;

  // Constructor
  public Player(int id, ArrayList<Card> card, CardDeck drawDeck, CardDeck discardDeck) {
    this.id = id;
    this.card = new ArrayList<>(card);
    this.drawDeck = drawDeck;
    this.discardDeck = discardDeck;
  }

  // gets player's ID
  public int getID() {
    return id;
  }

  //
  public ArrayList<Card> getPlayerCards(){
        return card;
    }

   // returns the deck the player draws cards from
  public Deck getDrawDeck() {
    return drawDeck;
  }

  // returns the deck the player discards cards to
  public Deck getDiscardDeck() {
    return discardDeck;
  }
  
  // check if a player win
  public synchronized boolean checkWin() {
    if(cards.size() != 4){
            return false;
    }
    
    int firstRank = cards.get(0).getNumber();
    boolean allSame = cards.stream().allMatch(card -> card.getNumber() == firstRank);
    if (allSame) {
      cardGame.finished(id);
      return true;
    }
    return false;
  }

  // handles the player's turn by discarding and drawing cards
  public synchronized boolean playing() {
    if (Thread.currentThread().isInterrupted()) {
        return false;
    }

    while (drawDeck.getCards().isEmpty()) {
        if (Thread.currentThread().isInterrupted()) {
            return false;
        }
    }

    for(int i=0; i < cards.size(); i++){
            if(cards.get(i).getNumber() != id){
                Card drawDeck = cards.get(i);
                discordDeck.addCard(cards.remove(i));
                Card newCard = drawDeck.removeCard();
                cards.add(newCard);

                writeOutputfile(drawDeck, newCard);
                break;
            }
        }
        return true;
    }

  // creates, updates, and closes the player's outputfile
  public synchronized void writeOutputfile(String operation, Card drawDeck, Card newCard, int winnerID) {
    try{
      if ("create".equals(operation)) {
        // create player's output file
        File file = new File("./output/player" + id + "_output.txt");
        if (file.createNewFile()) {
          System.out.println("File is created");
        }
        
        // write initial player's hand
        fw.write(this.getInitialCard() + "\n");
        System.out.println(this.getInitialCard());
        }
      } else if ("update".equals(operation)) {
        // updates player's hand
        fw.write("player " + id + " draws " + newCard.getNumber() + " from deck " + drawDeck.getID() + "\n");
        System.out.println("player " + id + " draws " + newCard.getNumber() + " from deck " + drawDeck.getID());
        
        fw.write("player " + id + " discards a " + drawDeck.getNumber() + " to deck " + discordDeck.getID() + "\n");
        System.out.println("player " + id + " discards a " + drawDeck.getNumber() + " to deck " + discordDeck.getID());
        
        fw.write(this.getCurrentCard() + "\n");
        System.out.println(this.getCurrentCard());
        
      } else if ("finalize".equals(operation)) {
        // write the final hand and close the writer
        if (winnerID != id) {
        fw.write("player " + id + " wins" + "\n");
        System.out.println("player " + id + " wins");
        } else {
          fw.write("player " + winnerID + " has informed player" + id + " that player " + winnerID + " has won \n");
          System.out.println("player " + winnerID + " wins");
        }
        
        fw.write("player " + id + " exits");
        fw.write(this.getFinalCard());
        fw.close();
      } else {
        System.out.println("Invalid operation type.");
      }
    } catch (IOException e) {
      System.out.println("Error occurred while handling the outputfile.");
      e.printStackTrace();
    }

    // Creates a string for the player's initial hand
    public String getInitialCard() {
    StringBuilder initialCard = new StringBuilder("player ")
        .append(id)
        .append(" intial hand:");
      for (Card card : cards) {
        initialCard.append(" ").append(card.getNumber());
      }
      return initialCard.toString();
    }

    // Creates a string for the player's current hand
    public String getCurrentCard() {
    StringBuilder currentCard = new StringBuilder("player ")
        .append(id)
        .append(" current hand:");
      for (Card card : cards) {
        currentCard.append(" ").append(card.getNumber());
      }
      return currentCard.toString();
    }

    // Creates a string for the player's final hand
    public String getFinalCard() {
    StringBuilder finalCard = new StringBuilder("player ")
        .append(id)
        .append(" final hand:");
      for (Card card : cards) {
        finalCard.append(" ").append(card.getNumber());
      }
      return finalCard.toString();
    }
}
