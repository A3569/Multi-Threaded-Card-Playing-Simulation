Within the source code, the four main folders of Card, CardDeck, CardGame and Player were created. 

1. The main code, CardGame, acts as the central controller for the game to manage the overall state of the game, which is also serving as the entry point to set up and start the game.
2. Card represents an individual card in the game, we separated it out from the main code for clarity and reusability.
3. CardDeck represents a deck in the card game to manage cards and operations like add card and remove card; we separated it out from the main code for the interaction and modularity.
4. Player represents a player in the game to manage the player's hand and handle game logic specific to players; we separated it out, similarly, for clarity and modularity.

## 1. CardGame.java (represents a card game involving players, decks and cards)
CardGame imports all necessary information into the file before declaring the players and decks as lists and their locations. 

From the start of the game, it is immediately checked if one of the players has been dealt a winning hand, if not, the game continues dealing the cards. Players are dealt cards and the picking up and discarding begins, if the card is not the same number as the player, it is discarded. All cards are also checked to ensure that they are positive integers. 

At the end of the game, the players’ output files are accessed, and their moves that they made throughout the game are recorded and the file updates. These all use information from the functions that initialise cards and create the strings for the players’ current and final hands. 
- **main()** as the entry point of the card game
- **readCardPack()** to read a card pack file, validate correct card numbers, and ensures the file has the exact number of cards needed
- **play()** to start the game by creating threads for each player. Then, joining the threads after execution, ensuring the game completes before proceeding. when the threads are finished, it will announce the winner and call writeGameSummary() to log the final game state
- **finished()** to check if the game is finished and retrieve the winner's ID

## 2. Card.java (represents a card in the card game)
Calls on the constructor for the cards and gets the number of each card when they are called in the other sections of the game

## 3. CardDeck.java (represents a deck of cards in the card game)
A card deck is created for the game, an ID is assigned to it to allow for the game to call on it. When cards are added to the deck, they are first checked to make sure that they are valid, this then allows players to remove cards from the top of the deck when the game is playing. 

When the game ends, the contents of the deck is then written into the output file, if one has been created already a new one will not be. At the end of all the final deck will be created to represent the final numbers. 
- **addCard()** to add a card to the deck
- **removeCard()** to remove a card to the top of the deck
- **writeOutputFile()** to write the deck’s contents at the end of the game to the output file
- **getFinalDeck()** to construct a string representation of the deck's contents

## 4. Player.java (represents a player in the card game)
The player function is initialised to ensure that all players can interact with the card deck, as well as the discard and drawing piles. We also implemented the Runnable interface to allow each player in the game to run as a separate thread, so that multiple players can act concurrently and interact with card decks. 

All players are assigned an ID to allow for easy interaction as well as to match it to their cards for the game. In order to check if a player has won the game, a function initially checks that they only have the required 4 cards, before checking that all 4 cards are matching the ID number of the player. If the player satisfies these requirements, the game is ended, output files are updated and another game can begin.
- **checkWin()** to check a player's hand has four cards and all have same number, then, notifies the CardGame.finished() if the player wins
- **playing()** to ensure players to wait if the draw deck is empty. Discarding a card from the hand that doesn't match the player's ID and draws a new card. Returning the player's action to writeOutputFile() to record it.
- **writeOutputFile()** to create a empty output file, keeps updating the player's action while the game is player, and finally record the final hand of the player and inform the player who is the winner
- **getInitialCard()**, getCurrentCard(), and getFinalCard() to construct readable strings for the player's hand at different stages of the game
- **run()** to continuously check for a win and takes turns playing() until a player wins or the thread is interrupted
