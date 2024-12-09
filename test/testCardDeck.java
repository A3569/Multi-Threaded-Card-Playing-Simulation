import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.File;

public class TestCardDeck {
    private CardDeck carddeck;

    @Before
    public void setUp() {
    	// Create a deck with ID 1
    	carddeck = new CardDeck(1); 
    }

    @Test
    public void testAddCard() {
        Card card = new Card(5);
        carddeck.addCard(card);
        assertEquals("Deck 1 contents: 5", carddeck.getFinalDeck());
    }

    @Test
    public void testRemoveCard() {
        Card card1 = new Card(3);
        Card card2 = new Card(2);
        carddeck.addCard(card1);
        carddeck.addCard(card2);
        assertEquals(3, carddeck.removeCard().getNumber());
        assertEquals("Deck 1 contents: 2", carddeck.getFinalDeck());
    }

    @Test
    public void testGetFinalDeck() {
        Card card1 = new Card(1);
        Card card2 = new Card(2);
        Card card3 = new Card(3);
        carddeck.addCard(card1);
        carddeck.addCard(card2);
        carddeck.addCard(card3);
        assertEquals("Deck 1 contents: 1 2 3", carddeck.getFinalDeck());
    }
    
    @Test
    public void testWriteOutputFile() {
        Card card1 = new Card(3);
        Card card2 = new Card(1);
        carddeck.addCard(card1);
        carddeck.addCard(card2);
        carddeck.writeOutputFile();
        File outputFile = new File("./output/deck1_output.txt");
        assertTrue(outputFile.exists());
        assertTrue(outputFile.length() > 0);
    }
}
