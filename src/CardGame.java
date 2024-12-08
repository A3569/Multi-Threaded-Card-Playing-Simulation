import java.util.Scanner;

public class CardGame {
  // Attributes
  
  // Main method as the program's entry point
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the number of players: ");
            int numPlayers = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter the path to the card pack file: ");
            String packFilePath = scanner.nextLine();

            Board game = new Board(numPlayers, packFilePath);
            game.play();

  public static void main (String[] args) {
    try {
      Scanner beginGame = new Scanner(System.in);
      System.out.println("Enter the number of players: ");
      int playersNumber = beginGame.nextInt();   

      System.out.println("Please enter location of card deck to load:");

      String deckLocation = scanner.nextInt();
      System.out.println("Location of deck: " + deckLocation);
      
      CardGame game = new CardGame(playersNumber, packFilePath);
      game.play();
      
      } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid number of players.");
      } catch (Exception e){
        e.printStackTrace();
      }
  }
  
  // Sets up the card game
  public boolean setUp (int totalPlayers) {
    if(totalPlayers <=0) {
      return false;
    }
    if(!readPack(path,totalPlayers)){
      return false;
    }
    ArrayList<ArrayList<Card>> hands;
    hands = dealCards(totalPlayers);
    for(int i=0; i < totalPlayers; i++) {
      try( 
        players.add(new Player(playerIDCounter,decks.get(i),hands.get(i),decks.get(i+1),this));
      } catch (IndexOutOfBoundsException e) {
        players.add(new Player(playerIDCounter, decks.get(i), hands.get(i), decks.get(0),this));
      }
playerIDCounter++;
        }
        // Checks for win conditions
        for(Player player: players){
            if(player.checkWin()){
                return true;
            }
            threads.add(new Thread(player));
        }
        return true;
    }
    
  // read the input pack
  // if the pack is invalid, notify user and request new pack

  // distribute the hands to the players

  // fill the decks

  // start the threads
  public void startGame(){
    for(Thread thread: threads){
      thread.start();
    }
  }
  
  // Ends the game
  public void finished(int id) {
    for(Thread thread: threads) {
      thread.interrupt();
    }
    for(Player player : players) {
      player.writeOutputfile(id);
    }
    for(Deck deck: decks) {
      cardDeck.writeOutputFile("finalize", null, null, winnerID);
    }
    winnerID = id;
    System.out.println("Player " + id + " has won the game!");
  }
}
