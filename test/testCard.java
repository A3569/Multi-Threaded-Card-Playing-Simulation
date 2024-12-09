import org.junit.Test;
import static org.junit.Assert.*;

public class testCard {
	@Test
    public void testCardInitialization() {
        // Test that the card initializes correctly
        Card card = new Card(5);
        assertEquals(5, card.getNumber());
    }

    @Test
    public void testCardWithZero() {
        // Test initializing card with zero
        Card card = new Card(0);
        assertEquals(0, card.getNumber());
    }

    @Test
    public void testCardWithNegativeNumber() {
        // Test initializing card with a negative number
        Card card = new Card(-1);
        assertEquals(-1, card.getNumber());
    }

    @Test
    public void testCardWithLargeNumber() {
        // Test initializing card with a large number
        int largeNumber = Integer.MAX_VALUE;
        Card card = new Card(largeNumber);
        assertEquals(largeNumber, card.getNumber());
    }
}
