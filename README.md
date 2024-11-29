1. cards.jar (executable and include both the .class and .java of your submission)
2. Report (minimum 2cm margins and 11-point text)
   - Max 2 page: detailing your design choice and reasons with respect to both your production code, and any known performance issues
   - Max 3 page: detailing the design choice and reasons with respect to your tests. You may use either of JUnit 4.x or 5.x frameworks, explicitly detail which framework you are using in your document
   - Max 1 page: development log includes date, time and duration of pair programming sessions, and which roles developers took in these sessions, with each log entry signed by both members

3. cardTest.zip
- src
  - class Card.java  (Thread-safe)
  - class Player.java  (Thread-safe)
  - class CardDeck.java
  - class CardGame.java (executable. Should distribute the hands to the players, fill the decks and start the required threads for the players. If the pack is invalid, the progran should inform the user of this, and request a valid pack file)
  - four.txt (contains 8n cards)
- test file
  - testCard.java
  - testPlayer.java
  - testCardDeck.java
- output file
  - player1_output.txt  (every action of a player processing his hand should be printed in here)
  - player2_output.txt
  - player3_output.txt
  - player4_output.txt
  - Deck1_output.txt  (single line of text detailing the contents of the deck at the end of the game)
  - Deck2_output.txt
  - Deck3_output.txt
  - Deck4_output.txt
- README.md (detailing how to run your test suite)
  
### Example of running CardGame.java
![image](https://github.com/user-attachments/assets/ce91e140-67ac-416b-9b32-79778638e592)

### Example of output file:
~~~
    player 1 initial hand 1123
    player 1 draws a 4 from deck 1
    player 1 discards a 3 to deck 2
    player 1 current handis 1124
    play 1 wins / player 3 has informed player 1 that player 3 has won
    play 1 exits
    play 1 final hand: 1111 / 2235

# There should be a message printed to the terminal window (as is the case when a player wins immediately), i.e. if the 4^{th} player wins, then
    player 4 wins
should be printed to the screen.
~~~

## Attention
1. A play can win with four cards which are all the same value at the start of the game, even it is not their preferred denomination. And then they should immediately printing "Player 1 wins", that player thread should notify the other threads, and exit
2. Game progresses: each player picks a card from the top of the deck to their left and discards one to the bottom of the deck to their right
3. A player must not hold onto a non-preferred denomination card indefinitely
4. Don't need to handle two or more players have four cards with the same value at the start of the game
5. By multi-threading, players should not play the game sequentially, i.e., Not in a way that, when one player finishes actions another player starts
6. The combination of a card draw, and a discard should be treated as a single atomic action. Therefore, at the end of the game every player should hold four cards
