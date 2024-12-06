import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;
import java.io.StringBuilder;

public class CardDeck {
  // Private attribute
  private final ArrayList<Card> cards;
  private final int id;
  
  // Constructor
  public CardDeck(int id) {
    this.id = id;
    this.cards = new ArrayList<>();
  }

  // Get the ID of the deck
  public int getID() {
    return id;
  }
  
  // Adds a card to the deck
  public synchronized void addCard(Card card) {
    if (card == null) {
      throw new IllegalArgumentException("Card cannot be null.");
    }
    cards.add(card);
  }
  
  // Removes the top card from the deck
  public synchronized Card removeCard() {
    if (cards.isEmpty()) {
            throw new IllegalStateException("The deck " + id + " is empty.");
        }
        return cards.remove(0);
    }
  }

  // Writes the deck's final values to the file and closes it
  public synchronized writeOutputfile() {
    try{
      FileWriter fw = new FileWriter("./output/deck" + id + "_output.txt");
      fw.write(this.getFinalDeck());
    } catch (Exception e){
            System.err.println("Error writing for deck " + id);
            e.printStackTrace();
        }
  }

  // contain the contents of the deck at the end of the game
  public synchronized String getFinalDeck() {
    StringBuilder finalDeck = new StringBuilder("Deck ")
        .append(id)
        .append(" contents:");
    for (Card card : cards) {
      finalDeck.append(" ").append(card.getNumber());
    }
    return finalDeck.toString();
  }
}
