import org.junit.Test;
import static org.junit.Assert.*;

public class CardTest {
	@Test
    public void testCardInitialization() {
        // tests that the card initializes correctly
        Card card = new Card(3);
        assertEquals(3, card.getNumber());
    }

    @Test
    public void testCardWithZero() {
        // tests initializing card with zero
        Card card = new Card(0);
        assertEquals(0, card.getNumber());
    }

    @Test
    public void testCardWithNegativeNumber() {
        // tests initializing card with a negative number
        Card card = new Card(-1);
        assertEquals(-1, card.getNumber());
    }

    @Test
    public void testCardWithLargeNumber() {
        // tests initializing card with a large number
        int largeNumber = Integer.MAX_VALUE;
        Card card = new Card(largeNumber);
        assertEquals(largeNumber, card.getNumber());
    }
}