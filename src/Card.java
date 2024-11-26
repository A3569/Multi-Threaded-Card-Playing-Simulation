public class Card extends Thread{
    // Private attribute
    private final int rank;

    // Constructor
    public Card(int rank){
        this.rank = rank;
    }

    // Get method for the card's rank
    public synchronized int getRank(){
        return rank;
    }
}