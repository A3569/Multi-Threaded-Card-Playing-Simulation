import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Board {
    // Private Attributes
    private ArrayList<Card> cards;
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Deck> decks = new ArrayList<Deck>();
    private ArrayList<Thread> threads = new ArrayList<Thread>();
    private int playerIDCounter = 1;
    private int deckIDCounter = 1;
    private int winner;

    // Get Methods
    public ArrayList<Card> getCards(){
        return cards;
    }
    public ArrayList<Player> getPlayers(){
        return players;
    }
    public ArrayList<Thread> getThreads(){
        return threads;
    }
    public ArrayList<Deck> getDecks(){
        return decks;
    }
    public int getWinnerID(){
        return winner;
    }

    // Void method that starts and runs the game
    public void play(){
        String path;
        int totalPlayers;
        Scanner input = new Scanner(System.in);
        try{
            System.out.println("Enter number of players: ");
            totalPlayers = Integer.parseInt(input.nextLine());
            System.out.println("Enter filename of pack: ");
            path = input.nextLine();
            System.out.println(path);
            // Sets up the game
            this.setUpGame(path, totalPlayers);
            // Starts the game
            this.startGame();
        } catch (NumberFormatException e){
            System.out.println("Not an integer");
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            // Closes the Scanner
            input.close();
        }
    }

    // Reads a file containing a pack of cards
    public boolean readPack(String path, int totalPlayers){
        cards = new ArrayList<Card>();
        Scanner fileReader = null;
        try{
            File myObj = new File(path);
            fileReader = new Scanner(myObj);
            // Reads out the entire file and adds contents to a card
            while(fileReader.hasNextLine()){
                int fileCard = fileReader.nextInt();
                if(fileCard <= 0){
                    throw new Exception("Pack of Cards contain an invalid Rank");
                }
                // Adds a card from the file to an arrayList of cards
                cards.add(new Card(fileCard));
            }
            // Closes the file reader
            fileReader.close();
        } catch (FileNotFoundException e){
            System.out.println("File does not exist");
            return false;
        } catch (InputMismatchException e){
            System.out.println("File contains non-integers");
            return false;
        } catch (NoSuchElementException e){
            System.out.println("File missing element");
            return false;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        } finally {
            // Makes sure that the file reader is empty and then closes the file reader
            if(fileReader != null){
                fileReader.close();
            }
        }

        // Checks that the total number of cards read from the file is within the range of 8n
        if(cards.size() == (8*totalPlayers)){
            return true;
        } else if (cards.size() > (8*totalPlayers)){
            System.out.println("Pack contains too many cards: INVALID");
        } else if (cards.size() < (8*totalPlayers)){
            System.out.println("Pack contains too few cards: INVALID");
        }
        return false;
    }

    // Method to deal the cards among the players and decks
    public ArrayList<ArrayList<Card>> dealCards(int totalPlayers){
        ArrayList<ArrayList<Card>> hands = new ArrayList<ArrayList<Card>>();
        // Generates the potential hands and decks
        for(int i=0; i < totalPlayers; i++){
            hands.add(new ArrayList<Card>());
            decks.add(new Deck(deckIDCounter++));
        }
        // Deals amongst the potential hands and decks
        for(int i=0; i < 4; i++){
            for(ArrayList<Card> card: hands){
                card.add(0,cards.remove(0));
            }
            for(Deck deck: decks){
                deck.getCards().add(0,cards.remove(0));
            }
        }

        return hands;
    }

    // Method to set up the card game
    public boolean setUpGame(String path, int totalPlayers){
        // Checks there are enough players and that the pack of cards have been successfully read
        if(totalPlayers <= 0){
            return false;
        }
        if(!readPack(path,totalPlayers)){
            return false;
        }
        ArrayList<ArrayList<Card>> hands;
        hands = dealCards(totalPlayers);
        // Generates the players and their respective cards
        for(int i=0; i < totalPlayers; i++){
            try{
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
            // Adds threads to the thread arrayList
            threads.add(new Thread(player));
        }
        return true;
    }

    // Method to end the game
    public void finished(int id){
        // Interrupts the threads
        for(Thread thread: threads){
            thread.interrupt();
        }
        // Calls the players to finish with the player writeFinalHand() class which writes and closes the player's file
        for(Player player : players){
            player.writeFinalHand(id);
        }
        // Calls the decks to reveal their deck
        for(Deck deck: decks){
            deck.writeOutput();
        }
        // Stores the victorious player's id
        winner = id;
    }

    // Method to start the game
    public void startGame(){
        // Begins threads
        for(Thread thread: threads){
            thread.start();
        }
    }

    public static void main (String[] args){
        // Main method to create a board and run the game on the board
        try{
            Board game = new Board();
            game.play();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}