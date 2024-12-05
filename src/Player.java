public class Player implements Runnable {
  // Represents a player in the game, manages their hand, and handles game logic specific to players
  // Player's hand
  // Checks that all cards are the same
  // get rid of unwanted card 
  // Player's final hand
  
  // Updates the player's file
  public synchronized writeOutputfile() {
    try{
      FileWriter fw = new FileWriter("./output/player" + id + "_output.txt");
      fw.write("player" + id + "contents: " + this.());
      fw.close();
  } catch (Exception e){
            e.printStackTrace();
        }
  }
  
  // Writes to the file to record the final cards and closes the file
}
