### All tests were undertaken on Eclipse using **JUnit 4.x frameworks**, each file for the coding section has a corresponding test file

## 1. TestCard.java
We assigned testing to ensure that it would initialise correctly. This was followed by testing the numbers that it would withstand, such as a negative number, a large number or zero.
- **testCardInitialization()** verifies that a Card object initialised correctly when a number is provided
- **testCardWithZero()** ensures that a Card object can handle initialization with 0
- **testCardWithNegativeNumber()** checks the behavior when a Card object is initialized with a negative number
- **testCardWithLargeNumber()** verifies the handling of very large numbers during Card initialization

## 2. TestCardDeck.java
It was tested on the main components of the actions it must undertake. This included making a basic deck, as well as adding and removing cards correctly. A final deck was also included to ensure the output would be correct, the output files were also tested with a set of card numbers and trying to add them to the lineup.
- **testAddCard()** verifies that the addCard() method adds cards correctly
- **testRemoveCard()** ensures that removeCard() removes the top card
- **testGetFinalDeck()** validates that getFinalDeck() correctly returns the string representation of the deck
- **testWriteOutputFile()** tests that the writeOutputFile() method creates and writes to a file correctly

## 3. TestCardGame.java
It was tested in more ways. The initialisation was tested through creating a valid card pack as well as testing to set up a game. The testing function makes sure that it can read for the card pack properly and a simple input of the game finishing was used to make sure that the whole game could be ended. This was tested again with a finish() method. 

In the occurrence of an invalid number of players or card values, the testing function negative integers or only one player. There is also a call to make sure it prints the correct information when a card pack cannot be found.
- **setup()** sets up a valid CardGame instance with a card pack file before each test
- **testGameInitialization()** verifies that a newly initialized game starts in a non-finished state and has no winner
- **testReadCardPack()** ensures that a valid card pack file is read correctly during game initialization
- **testGamePlaySimplified()** tests a simplified game flow where the game ends and a winner is declared
- **testFinishedMethod()** verifies that the finished() method correctly ends the game and sets the winner
- **testInvalidNumberOfPlayers()** tests game behavior when initialized with an invalid number of players
- **testCardPackFileNotFound()** ensures the game ends gracefully when the card pack file is missing
- **testInvalidCardValues()** tests the behavior when the card pack file contains invalid card values
- **teardown()** cleans up test files created during the tests
- **createTestCardPack()** generates a test card pack file with valid card values for a specified number of players
- **deleteTestFiles()** deletes temporary test files created during testing

## 4. TestPlayer.java
Testing first sets up a hand that the players would use for the testing, before checking that the winning function is working if the player has all the correct cards. This is then followed by the instance of there being different cards. 

For during the game, the get method, the playing method and the output file method are all tested. The output file method is tried to see if it will still run with no exceptions to the data that is given to it. The final test is to ensure that the game is thread safe. 
- **setUp()** initializes a Player object with decks and a predefined set of cards
- **testCheckWin_Win()** verifies that the checkWin() method correctly identifies a winning condition that all cards in the hand are identical
- **testCheckWin_NotWin()** ensures that checkWin() returns false when the player's cards are not identical
- **testGetPlayerCards()** validates that the getPlayerCards() method returns the correct list of cards
- **testPlaying_UpdateCards()** tests the playing() method, which likely simulates a player's turn and updates the cards
- **testWriteOutputfile()** tests the writeOutputFile() method for generating output files based on three actions by player
- **testPlayerRunnable()** verifies that the Player class behaves as expected when run in a thread
