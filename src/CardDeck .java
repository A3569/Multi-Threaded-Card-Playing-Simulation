public class CardDeck {
  // Private attribute
  private final <Card> cards = new <Card>();
  private final int id;
  
  // Creates a constructor
  public Deck(int id) {
    this.id = id;
  }

  // Get method
  public int getID() {
    reture id;
  }
  
  // Sets the initial cards of the deck
  public initialCards() {
    this.cards = cards;
  }
  
  // Adds a card to the deck
  public addCard(Card cards) {
    cards.add(cards);
  }
  
  // Removes the top card from the deck
  public Card removeCard() {
    if (!cards.isEmpty()) {
            if (cards.isEmpty()) {
            throw new IllegalStateException("The deck is empty.");
        }
        return cards.remove(0);
    }
  }

  // contain the contents of the deck at the end of the game
  public String getFinalDeck() {
    String finaldeck = "deck" + id + "contents: ";
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
