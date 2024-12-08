import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CardGame {
  // Attributes
  private final ArrayList<Player> players;
  private final ArrayList<CardDeck> decks;
  private boolean gameFinished = false;
  private int winnerID = -1;

  // Constructors
  public CardGame(int playersNumber, String deckLocation) {
    players = new ArrayList<>();
    decks = new ArrayList<>();

    // reads the card pack
    ArrayList<Card> cardPack = readCardPack(deckLocation, playersNumber);
    if (cardPack == null) {
      System.out.println("Failed to initialize game");
      return;
    }
    // creates decks
    for (int i = 1; i <= playersNumber; i++) {
      decks.add(new Deck(i));
    }
    // distributes cards to players
    for (int i = 0; i < playersNumber; i++) {
      ArrayList<Card> hand = new ArrayList<>(cardPack.subList(i * 4, i * 4 + 4));
      CardDeck drawDeck = (i == 0) ? decks.get(decks.size() - 1) : decks.get(i - 1);
      CardDeck discardDeck = decks.get(i);
      players.add(new Player(i + 1, drawDeck, hand, discardDeck, this));
    }
  }
    
  // main method as the program's entry point
  public static void main(String[] args) {
    try {
      Scanner beginGame = new Scanner(System.in);
      System.out.println("Enter the number of players: ");
      int playersNumber = Integer.parseInt(scanner.nextLine());
      System.out.println("Please enter location of pack to load:");
      String deckLocation = scanner.nextInt();
      CardGame game = new CardGame(playersNumber, deckLocation);
      game.play();
    } catch (NumberFormatException e) {
      System.out.println("Invalid input. Please enter valid number of players.");
    } catch (Exception e){
      System.out.println("An error occurred: " + e.getMessage());
      e.printStackTrace();
    }
  }

  // reads a card pack from a file
  private ArrayList<Card> readCardPack(String deckLocation, int playersNumber) {
    ArrayList<Card> cards = new ArrayList<>();
    try (Scanner scanner = new Scanner(new File(deckLocation))) {
      while (scanner.hasNextInt()) {
        int number = scanner.nextInt();
        if (number <= 0) {
          throw new IllegalArgumentException("Invalid card number");
        }
        cards.add(new Card(number));
      }
    } catch (FileNotFoundException e) {
      System.out.println("Card pack file not found: " + e.getMessage());
      return null;
    } catch (Exception e) {
      System.out.println("Error reading card pack: " + e.getMessage());
      return null;
    }
    if (cards.size() != playersNumber * 8) {
      System.out.println("Invalid card pack size.");
      return null;
    }
    return cards;
  }
  
  // main game logic
  public void play() {
    ArrayList<Thread> playerThreads = new ArrayList<>();
    for (Player player : players) {
      Thread thread = new Thread(player);
      playerThreads.add(thread);
      thread.start();
    }
    for (Thread thread : playerThreads) {
      try {
        thread.join();
      } catch (InterruptedException e) {
        System.out.println("Thread interrupted: " + e.getMessage());
      }
    }
    System.out.println("Player " + winnerID + "has won");
    writeGameSummary();
  }
  
  // notifies the game is finished
  public synchronized void finished(int playerID) {
    if (!gameFinished) {
      gameFinished = true;
      winnerID = playerID;
    }
  }
  
  // writes final game summary to the output file
  public void writeGameSummary() {
    for(Player player : players) {
      player.writeOutputfile(winnerID);
    }
    for(Deck deck: decks) {
      cardDeck.writeOutputFile();
    }
  }
}
