# woodPuzzle
Multiple implementations of Sliding Wood Block Puzzle

![Actual Puzzle](images/actual_puzzle.png?raw=true "Actual Puzzle")

This repository contains standalone applications that enable a user to slide wooden tiles in a 5x4 rectangular puzzle to ultimately release one of the tiles from the board through an opening at the bottom of the board. These applications are developed using different languages and technologies.

When you start your application, the puzzle will be in its initial state. 

![Abstract Representation](images/rendition.png?raw=true "Abstract Representation")

From this initial state, the user can quit the application or reset the puzzle to its original configuration. Moving a piece is a two-step operation. First, the user selects a piece. Next the user requests to move the piece either up, down, left, or right one square. Naturally only valid moves should be allowed.

When the large 2x2 piece (highlighted in red) has left the board through the slot on the board of the board, the user has won the game, and should be congratulated! During game play, the total number of moves should be shown to the user.

