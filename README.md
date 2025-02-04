This is a client-side implementation of the Acey-Deucey card game. The program communicates with a dealer server to play the game by sending and receiving commands via a socket connection. The main functionality of the program involves determining the optimal bet based on the cards dealt and the current game state, including pot value, stack value, and card history.

Features:
Connects to the dealer via socket communication.
Calculates bets based on various conditions:
Middle play: Calculates a bet based on the difference between two cards.
High or Low play: Determines whether the next play should be high or low based on the card values.
High play bet: Calculates the bet when the play is "high," taking into account how many times a specific card has been played.
Low play bet: Calculates the bet when the play is "low," also factoring in the number of times the card has appeared.
Reads commands from the dealer, processes them, and sends appropriate responses based on the game logic.

How It Works:
Communication with Dealer: The program connects to the dealer server using the provided IP address and port number.
Bet Calculation:
Mid: The program calculates a bet based on the difference between two dealt cards.
High/Low: Based on the first card's value, the program decides whether to place a "high" or "low" bet and calculates the bet accordingly.
Game Loop: After receiving commands from the dealer (e.g., play, login), the program processes them, calculates the bet, and sends the response back to the dealer.

Requirements:
A dealer server that supports the protocol used by this program (Socket communication).
