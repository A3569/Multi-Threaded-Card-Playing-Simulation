import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class testPlayer {
	// Private attributes
    private Player player;
    private CardDeck drawDeck;
    private CardDeck discardDeck;
    private CardGame cardGame;
    private ArrayList<Card> playerCards;

    @Before
    public void setUp() {
    	// Initializes player with cards and decks
        drawDeck = new CardDeck(1);
        discardDeck = new CardDeck(2);
        cardGame = new CardGame();
        playerCards = new ArrayList<>();
        playerCards.add(new Card(1));
        playerCards.add(new Card(1));
        playerCards.add(new Card(1));
        playerCards.add(new Card(1));
        drawDeck.addCard(new Card(2));
        drawDeck.addCard(new Card(3));
        player = new Player(1, playerCards, drawDeck, discardDeck, cardGame);
    }

    @Test
    public void testCheckWin_Win() {
    	// Verifies the win condition when all cards are the same
        assertTrue(player.checkWin());
    }

    @Test
    public void testCheckWin_NotWin() {
        // Verifies the win condition when cards are different
        player.getPlayerCards().set(0, new Card(2));
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
    	// Tests writeoutputfile() runs without exception
        player.writeOutputfile("create", drawDeck, null, -1);
        player.writeOutputfile("update", drawDeck, new Card(5), -1);
        player.writeOutputfile("finalize", null, null, 1);
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
