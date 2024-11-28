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
  - class CardGame.java (executable) 
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
- four.txt (contains 8n cards) //Inside the pack file ???
- README.md (detailing how to run your test suite)
  
### Example of running CardGame.jar
![image](https://github.com/user-attachments/assets/ce91e140-67ac-416b-9b32-79778638e592)

## Attention
1. The combination of a card draw, and a discard should be treated as a single atomic action. Therefore, at the end of the game every player should hold four cards
2. A play can win with four cards which are all the same value at the start of the game, even it is not their preferred denomination. And then they should immediately printing "Player 1 wins", that player thread should notify the other threads, and exit
3. Game progresses: each player picks a card from the top of the deck to their left and discards one to the bottom of the deck to their right
4. 
