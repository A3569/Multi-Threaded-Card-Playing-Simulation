import org.junit.Test;
import static org.junit.Assert.*;

public class testCard {
    @Test
    public void testInitialization() {
        // Create a card with a specific number
        int cardNumber = 5;
        Card card = new Card(cardNumber);
        assertEquals(cardNumber, card.getNumber());
    }

    @Test
    public void testCardWithNegativeNumber() {
        // Create a card with a negative number
        int cardNumber = -3;
        Card card = new Card(cardNumber);
        assertEquals(cardNumber, card.getNumber());
    }

    @Test
    public void testCardWithZero() {
        // Create a card with a number of zero
        int cardNumber = 0;
        Card card = new Card(cardNumber);
        assertEquals(cardNumber, card.getNumber());
    }

    @Test
    public void testMistmatchCardNumber(){
        // Test mismatched numbers
        int cardNumber = 4;
        int mismatchedNumber = 3;
        Card card = new Card(cardNumber);
        assertFalse(card.getNumber() == mismatchedNumber);
    }
}
