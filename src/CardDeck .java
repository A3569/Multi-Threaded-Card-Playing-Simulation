import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;

public class CardDeck {
  // Private attribute
  private final ArrayList<Card> cards = new ArrayList<>();
  private final int id;
  
  // Constructor
  public CardDeck(int id) {
    this.id = id;
  }

  // Get method for ID
  public int getID() {
    return id;
  }
  
  // Sets the initial cards of the deck
  public void initialCards() {
    this.cards = cards;
  }
  
  // Adds a card to the deck
  public void addCard(Card card) {
    cards.add(card);
  }
  
  // Removes the top card from the deck
  public Card removeCard() {
    if (cards.isEmpty()) {
            throw new IllegalStateException("The deck is empty.");
        }
        return cards.remove(0);
    }
  }

  // Writes the deck's final values to the file and closes it
  public writeOutputfile() {
    try{
      FileWriter fw = new FileWriter("./output/deck" + id + "_output.txt");
      fw.write(this.getFinalDeck());
      fw.close();
    } catch (Exception e){
            e.printStackTrace();
        }
  }

  // contain the contents of the deck at the end of the game
  public String getFinalDeck() {
    String finalDeck = "Deck " + id + " contents:";
    for (Card card : cards) {
        finalDeck.append(" ").append(card.getNumber());
    }
    return finalDeck;
  }
}
