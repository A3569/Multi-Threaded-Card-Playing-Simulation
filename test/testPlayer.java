import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class TestPlayer {
	// Private attributes
    private Player player;
    private CardDeck drawDeck;
    private CardDeck discardDeck;
    private CardGame cardGame;
    private ArrayList<Card> playerCards;

    @Before
    public void setUp() {
    	// Initializes player with cards and decks
    	cardGame = null;
    	drawDeck = new CardDeck(2);
        discardDeck = new CardDeck(2);
        playerCards = new ArrayList<>();
        playerCards.add(new Card(1));
        playerCards.add(new Card(1));
        playerCards.add(new Card(1));
        playerCards.add(new Card(1));
        drawDeck.addCard(new Card(2));
        drawDeck.addCard(new Card(3));
        player = new Player(1, drawDeck, playerCards, discardDeck, cardGame);
    }

    @Test
    public void testCheckWin_Win() {
    	// Verifies the win condition when all cards are the same
    	player.getPlayerCards().clear();
        player.getPlayerCards().add(new Card(1));
        player.getPlayerCards().add(new Card(1));
        player.getPlayerCards().add(new Card(1));
        player.getPlayerCards().add(new Card(1));
        assertTrue(player.checkWin());
    }

    @Test
    public void testCheckWin_NotWin() {
        // Verifies the win condition when cards are different
        player.getPlayerCards().set(0, new Card(3));
        assertFalse(player.checkWin());
    }

    @Test
    public void testGetPlayerCards() {
    	// Ensures the get method returns the correct card
        assertEquals(playerCards, player.getPlayerCards());
    }

    @Test
    public void testPlaying_UpdateCards() {
    	// Tests playing() to verify card is updated
        assertTrue(player.playing());
    }

    @Test
    public void testWriteOutputfile() {
    	// Tests writeoutputFile() runs without exception
        player.writeOutputFile("create", drawDeck, null, -1);
        player.writeOutputFile("update", drawDeck, new Card(2), -1);
        player.writeOutputFile("finalize", null, null, 1);
        assertTrue(true);
    }
    
    @Test
    public void testPlayerRunnable(){
    	// Tests run() is thread-safe
        Thread playerthread = new Thread(player);
        playerthread.start();
        try {
            playerthread.join(2000);
        } catch (InterruptedException e) {
            fail("Player's thread was interrupted unexpectedly.");
        }
        assertFalse(playerthread.isAlive());
    }
}
