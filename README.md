~~~
Multi-Threaded-Card-Playing-Simulation/
├── src/
│   ├── Card.java
│   ├── CardDeck.java
│   ├── Player.java
│   ├── CardGame.java
├── test/
│   ├── CardTest.java
│   ├── CardDeckTest.java
│   ├── PlayerTest.java
│   ├── CardGameTest.java
├── bin/
│   ├── Card.class
│   ├── CardDeck.class
│   ├── Player.class
│   ├── CardGame.class
│   ├── CardTest.class
│   ├── CardDeckTest.class
│   ├── PlayerTest.class
│   ├── CardGameTest.class
├── output/
│   ├── player1_output.txt
│   ├── deck1_output.txt
├── README.md
├── cards.txt
├── Game.jar
~~~

# Game rules
The game has 4 players and 4 decks of cards, again. Each player will hold a hand of 4 cards. Both these hands and the decks will be drawn from a pack which contains 8n cards. Each card has a face value of a non-negative integer 1.

At the start of the game, each player will be distributed four cards in a round-robin fashion, from the top of the pack, starting by giving one card to player1, then one card to player2, etc. The decks and players will form a ring topology. After the hands have been distributed, the decks will then be filled from the remaining cards in the pack, again in a round-robin fashion. Each player picks a card from the top of the deck to their left and discards one to the bottom of the deck to their right. This process continues until the first player declares that they have four cards of the same value, at which point the game ends.

## Win conditions
To win the game, **A player needs four cards of the same value in their hand**.

## Situations
- If a player is given four cards which are all the same value at the start of the game, the winner then notify the other players that he has won and exit the game.
- If two player are given four cards which are all the same value at the start of the game, then restarts the game.
- If the game is not won immediately, then the game continuous.

## Example of running Game.jar
~~~
Enter the number of players: 
4
Please enter location of pack to load:
cards.txt
~~~
