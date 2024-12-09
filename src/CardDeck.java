import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class CardDeck {
    // Attributes
    private final ArrayList<Card> cards;
    private final int id;

    // Constructors
    public CardDeck(int id) {
        this.id = id;
        this.cards = new ArrayList<>();
    }

    // gets deck's ID
    public int getID() {
        return id;
    }

    // gets the player's cards
    public ArrayList<Card> getCards() {
        return cards;
    }

    // adds a card to the deck
    public synchronized void addCard(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("Card cannot be null.");
        }
        cards.add(card);
    }

    // removes the top card from the deck
    public synchronized Card removeCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("The deck " + id + " is empty.");
        }
        return cards.remove(0);
    }

    // writes the deck's final values to the file
    public synchronized void writeOutputFile() {
        try{
        	File outputDir = new File("./output");
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            File newFile = new File("./output/deck"+id+"_output.txt");
        	if(newFile.createNewFile()){
                System.out.println("File is created");
            } else {
                System.out.println("File is already exists");
            }

        	try (FileWriter fw = new FileWriter(newFile)) {
                fw.write(getFinalDeck());
        	}
        	} catch (IOException e) {
                System.err.println("Error writing for deck " + id);
                e.printStackTrace();
                }
        }

    // contains the contents of the deck at the end of the game
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
