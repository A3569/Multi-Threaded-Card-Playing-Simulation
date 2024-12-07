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

public class CardGame {                                             // when trying to run on VSC, keeps coming up that Main could not be loaded? Cuz there are two main method that creates ambiguity
  public static void main(String[]args) {
      Scanner beginGame = new Scanner(System.in); 
      System.out.println("Welcome to the Card Game!");
      System.out.println("Please enter the number of players in the group:");

      int playersNumber = beginGame.nextInt();                           // get user input of players
      System.out.println("Number of players: " + playersNumber);      // print results 

      System.out.println("Please enter location of card deck to load:");

      String deckLocation = beginGame.nextInt();                         // get location of deck of cards
      System.out.println("Location of deck: " + deckLocation);        // print results 
      
      try{
        
      } catch (Exception e){
        e.printStackTrace();
      }
  }
}
  
  // Sets up the card game
  // read the input pack
  // if the pack is invalid, notify user and request new pack

  // distribute the hands to the players

  // fill the decks

  // start required threads

  // Ends to the game
}
