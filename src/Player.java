import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Player implements Runnable {
    // Attributes
    private final int id;
    private final CardDeck drawDeck;
    private final ArrayList<Card> cards;
    private final CardDeck discardDeck;
    private final CardGame cardGame;
    private FileWriter fw;

    // Constructors
    public Player(int id, CardDeck drawDeck, ArrayList<Card> cards, CardDeck discardDeck, CardGame cardGame) {
        this.id = id;
        this.drawDeck = drawDeck;
        this.cards = new ArrayList<>(cards);
        this.discardDeck = discardDeck;
        this.cardGame = cardGame;
    }

    // allows interaction with the player's ID
    public int getID(){
        return id;
    }

    // allows interaction with drawing a deck
    public CardDeck getDrawDeck(){
        return drawDeck;
    }

    // allows interaction with discarding a deck
    public CardDeck getDiscardDeck(){
        return discardDeck;
    }

    // allows interaction with the player's card
    public synchronized ArrayList<Card> getPlayerCards(){
        return cards;
    }

    // checks if a player win
    public synchronized boolean checkWin() {
        if (cards.size() != 4) return false;
        int firstRank = cards.get(0).getNumber();
        if (cards.stream().allMatch(card -> card.getNumber() == firstRank)) {
            if (cardGame != null) {
                cardGame.finished(id);
            }
            return true;
        }
        return false;
    }

    // handles the player's turn by discarding and drawing cards
    public synchronized boolean playing() {
        if (Thread.currentThread().isInterrupted()) return false;
        while (drawDeck.getCards().isEmpty()) {
            if (Thread.currentThread().isInterrupted()) return false;
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        for(int i = 0; i < cards.size(); i++) {
            if(cards.get(i).getNumber() != id){
                Card cardToDiscard = cards.remove(i);
                discardDeck.addCard(cardToDiscard);
                Card newCard = drawDeck.removeCard();
                cards.add(newCard);
                writeOutputFile("update", drawDeck, newCard, -1);
                break;
            }
        }
        return true;
    }

    // resets, creates, updates, and closes the player's output file
    public synchronized void writeOutputFile(String operation, CardDeck drawDeck, Card newCard, int winnerID) {
        try{
            // create player's output file
            if (fw == null) {
                // Clear the file by writing nothing
                fw = new FileWriter("./output/player" + id + "_output.txt", false);
                fw.write("");
                fw.close();
                fw = new FileWriter("./output/player" + id + "_output.txt", true);
            }
            // write initial player's hand
            if ("create".equals(operation)) {
                fw.write(getInitialCard() + "\n");
                System.out.println(getInitialCard());
            } else if ("update".equals(operation)) {
                // updates player's hand
                fw.write("player " + id + " draws a " + newCard.getNumber() + " from deck " + drawDeck.getID() + "\n");
                System.out.println("player " + id + " draws a " + newCard.getNumber() + " from deck " + drawDeck.getID());

                fw.write("player " + id + " discards a " + newCard.getNumber() + " to deck " + discardDeck.getID() + "\n");
                System.out.println("player " + id + " discards a " + newCard.getNumber() + " to deck " + discardDeck.getID());

                fw.write(getCurrentCard() + "\n");
                System.out.println(getCurrentCard());
            } else if ("finalize".equals(operation)) {
                // write the final hand and close the writer
                if (winnerID == id) {
                    fw.write("player " + id + " wins\n");
                    System.out.println("player " + id + " wins");
                } else {
                    fw.write("player " + winnerID + " has informed player" + id + " that player " + winnerID + " has won\n");
                    System.out.println("player " + winnerID + " wins");
                }
                fw.write("player " + id + " exits" + "\n");
                fw.write(getFinalCard() + "\n");
                fw.close();
            }
        } catch (IOException e) {
            System.err.println("Error writing output for player " + id);
        }
    }

    // creates a string for the player's initial hand
    public String getInitialCard() {
        StringBuilder initialCard = new StringBuilder("player ").append(id).append(" initial hand:");
        for (Card card : cards) {
            initialCard.append(" ").append(card.getNumber());
        }
        return initialCard.toString();
    }

    // creates a string for the player's current hand
    public String getCurrentCard() {
        StringBuilder currentCard = new StringBuilder("player ").append(id).append(" current hand:");
        for (Card card : cards) {
            currentCard.append(" ").append(card.getNumber());
        }
        return currentCard.toString();
    }

    // creates a string for the player's final hand
    public String getFinalCard() {
        StringBuilder finalCard = new StringBuilder("player ").append(id).append(" final hand:");
        for (Card card : cards) {
            finalCard.append(" ").append(card.getNumber());
        }
        return finalCard.toString();
    }

    // implements threads
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted() && !checkWin()) {
                if (!playing()) break;
            }
        } catch (Exception e) {
            System.err.println("Error during player " + id + "'s turn.");
        }
    }
}