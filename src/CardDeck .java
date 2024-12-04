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
  public removeCard(Card cards) {
    cards.remove(cards);
  }
  
  // Writes the deck's final values to the file and closes it
  public writeOutputfile() {
    try{
      FileWriter fw = new FileWriter("./outputfile/deck" + id + "_output.txt");
    fw.write("deck" + id + "contents: " + this.initialCards());
    fw.close();
  } catch (Exception e){
            e.printStackTrace();
        }
}
