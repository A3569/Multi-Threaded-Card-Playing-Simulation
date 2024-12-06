import java.util.Scanner;

// public class CardGame {
//   // Main method to run the game
//   public static void main (String[] args){
//     System.out.println("Welcome to the Card Game!");
//     System.out.println("Initializing the game...");
    
//     try{
      
//     } catch (Exception e){
//             e.printStackTrace();
//         }
//   }
  // Starts and runs the game

public class Main {                                             // when trying to run on VSC, keeps coming up that Main could not be loaded? Cuz there are two main method that creates ambiguity
  public static void main(String[]args) {
      Scanner beginGame = new Scanner(System.in); 
      System.out.println("Welcome to the Card Game!");
      System.out.printIn("Please enter the number of players in the group:");

      int playersNumber = beginGame.nextInt();                           // get user input of players
      System.out.printIn = ("Number of players: " + playersNumber);      // print results 

      System.out.printIn("Please enter location of card deck to load:");

      String deckLocation = beginGame.nextInt();                         // get location of deck of cards
      System.out.printIn = ("Location of deck: " + deckLocation);        // print results 
      
      try{
        
      } catch (Exception e){
        e.printStackTrace();
      }
  }
}
  
  // Sets up the card game
  // Ends to the game
}
