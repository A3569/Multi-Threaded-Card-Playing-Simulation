import org.junit.*;
import static org.junit.Assert.*;

public class CardGameTest {

    private CardGame game;

    @Before
    public void setup() {
        // creates a valid card pack file for initialization
        String validDeckPath = "test_card_pack.txt";
        createTestCardPack(validDeckPath, 2);
        game = new CardGame(2, validDeckPath);
    }

    @Test
    public void testGameInitialization() {
    	// tests set up game initialization
        assertFalse(game.finished());
        assertEquals(-1, game.getWinnerId());
    }

    @Test
    public void testReadCardPack() {
    	// tests readCardPack() to read the pack correctly
        String validDeckPath = "valid_deck.txt";
        createTestCardPack(validDeckPath, 3);
        CardGame testGame = new CardGame(3, validDeckPath);
        assertNotNull(testGame);
    }

    @Test
    public void testGamePlaySimplified() {
    	// fast test for whole game play
        game.finished(1);
        assertTrue(game.finished());
        assertEquals(1, game.getWinnerId());
    }
    
    @Test
    public void testFinishedMethod() {
    	// tests finish() method to end the game
        game.finished(1);
        assertTrue(game.finished());
        assertEquals(1, game.getWinnerId());
    }
    
    @Test
    public void testInvalidNumberOfPlayers() {
    	// tests invalid number of players
        CardGame game = new CardGame(1, "test_card_pack.txt");
        assertTrue(game.finished());
    }
    
    @Test
    public void testCardPackFileNotFound() {
    	// tests no card pack file found
        String nonExistentFile = "non_existent_file.txt";
        CardGame game = new CardGame(2, nonExistentFile);
        assertTrue(game.finished());
    }
    
    @Test
    public void testInvalidCardValues() {
    	// tests invalid card values in the pack
        String invalidValuesPath = "invalid_values_deck.txt";
        try (java.io.PrintWriter writer = new java.io.PrintWriter(invalidValuesPath)) {
            writer.println("-1");
            writer.println("0");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        CardGame game = new CardGame(2, invalidValuesPath);
        assertTrue(game.finished());
    }

    @After
    public void teardown() {
        // cleanups test files
        deleteTestFiles();
    }

    private void createTestCardPack(String path, int players) {
    	// creates test data
        try (java.io.PrintWriter writer = new java.io.PrintWriter(path)) {
            for (int i = 0; i < players * 8; i++) {
                writer.println(i + 1);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteTestFiles() {
    	// deletes test files
        new java.io.File("test_card_pack.txt").delete();
        new java.io.File("valid_deck.txt").delete();
        new java.io.File("invalid_deck.txt").delete();
    }
}
