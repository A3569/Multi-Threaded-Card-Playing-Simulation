import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Deck {
    private int id;
    private ArrayList<Card> cards = new ArrayList<Card>();

    // Get Methods
    public int getID(){
        return id;
    }
    public ArrayList<Card> getCards(){
        return cards;
    }

    // Constructor
    public Deck(int id){
        this.id = id;
    }

    // Method to set the initial cards of the deck
    public void setDeck(ArrayList<Card> cards){
        this.cards = cards;
    }

    // Synchronized method to add a single card to the deck
    public synchronized void addCard(Card card){
        cards.add(card);
    }
    // Synchronized method to remove the top card from the deck
    public synchronized Card removeCard(){
        // Check to make sure the deck is not empty
        if(cards.isEmpty()){
            return null;
        }
        return cards.remove(0);
    }

    // Method to create a file for the deck and writes the deck's final values to the file
    public synchronized void writeOutput(){
        try{
            // Generates new file
            File newFile = new File("./outputs/deck/deck"+id+"_output.txt");
            if(newFile.createNewFile()){
                System.out.println("File created: "+ newFile.getName());
            } else {
                System.out.println("File already exists: " + newFile.getName());
            }
            FileWriter fw = new FileWriter("./outputs/deck/deck"+id+"_output.txt");
            // Writes the deck's contents to the file and then closes the file writer
            fw.write(this.getDeck());
            fw.close();
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Method that returns the contents of the deck as a string
    public String getDeck(){
        String deckString = "deck " + id + " contents: ";
        for(Card card: cards){
            deckString += " " + card.getRank();
        }
        return deckString;
    }
}