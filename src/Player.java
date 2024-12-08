import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Player implements Runnable {
  // Private attribute
  private final int id;
  private final ArrayList<Card> cards;
  private final CardDeck drawDeck;
  private final CardDeck discardDeck;
  private final CardGame cardGame;
  private FileWriter fw; 

  // Constructor
  public Player(int id, ArrayList<Card> cards, CardDeck drawDeck, CardDeck discardDeck, CardGame cardGame) {
    this.id = id;
    this.card = new ArrayList<>(cards);
    this.drawDeck = drawDeck;
    this.discardDeck = discardDeck;
    this.cardGame = cardGame;
  }
  
  // check if a player win
  public synchronized boolean checkWin() {
    if (cards.size() != 4) return false;
    int firstRank = cards.get(0).getNumber();
    if (cards.stream().allMatch(card -> card.getNumber() == firstRank)) {
      cardGame.finished(id);
      return true;
    }
    return false;
  }

  // handles the player's turn by discarding and drawing cards
  public synchronized boolean playing() {
    if (Thread.currentThread().isInterrupted()) return false;
    while (drawDeck.getCards().isEmpty()) {
        if (Thread.currentThread().isInterrupted()) return false;
    }
    for(int i = 0; i < cards.size(); i++) {
            if(cards.get(i).getNumber() != id){
                Card cardToDiscard = cards.remove(i);
                discardDeck.addCard(cardToDiscard);
                Card newCard = drawDeck.removeCard();
                cards.add(newCard);
                writeOutputfile("update", drawDeck, newCard, -1);
                break;
            }
        }
        return true;
    }

  // creates, updates, and closes the player's outputfile
  public synchronized void writeOutputfile(String operation, CardDeck drawDeck, Card newCard, int winnerID) {
    try{
      // create player's output file
      if (fw == null) {
        fw = new FileWriter("./output/player" + id + "_output.txt", true);
      }
      // write initial player's hand
      if ("create".equals(operation)) {
        fw.write(getInitialCard() + "\n");
        System.out.println(getInitialCard());
      } else if ("update".equals(operation)) {
          // updates player's hand
          fw.write("player " + id + " draws " + newCard.getNumber() + " from deck " + drawDeck.getID() + "\n");
          System.out.println("player " + id + " draws " + newCard.getNumber() + " from deck " + drawDeck.getID());
       
          fw.write("player " + id + " discards a " + newCard.getNumber() + " to deck " + discardDeck.getID() + "\n");
          System.out.println("player " + id + " discards a " + newCard.getNumber() + " to deck " + discardDeck.getID());
        
          fw.write(getCurrentCard() + "\n");
          System.out.println(getCurrentCard());
        } else if ("finalize".equals(operation)) {
          // write the final hand and close the writer
            if (winnerID == id) {
              fw.write("player " + id + " wins\n");
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
