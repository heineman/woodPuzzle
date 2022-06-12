# C implementation of Wood Puzzle.

This project was built using the [ncurses](https://tldp.org/HOWTO/NCURSES-Programming-HOWTO/) library.

## How to build

Type `make` to build the executable. The executable is built using the `-g` option to ensure you can debug the executable to see how it works.

### `./puzzle` launches the application

Instructions to play the application are shown on the screen when running.

The opening screen shows the puzzle to be solved:

![Initial Puzzle](images/initial_screen.png?raw=true "Initial Screen")

You can select a puzzle piece by typing its letter (A-J). Once a piece
has been selected, you choose the direction to move using
standard "WASD" indications -- if your keyboard has a numeric keypad, then you can also use the directional arrows on the "2468" keys.

![Select Direction](images/select_direction.png?raw=true "Select Direction")

If there is space to move the selected piece in the indicated
direction, then you will be brought back to the earlier screen.

You can request to Reset the puzzle to its initial state by entering `R` when prompted to select a piece.

You can request to solve the puzzle using a Breadth First Search algorithm. Once the solution is computed, it appears on the top right.

![Solving Puzzle](images/solving_screen.png?raw=true "Solving Puzzle")

You can either manually perform the moves as indicated (i.e., select a piece and choose the indicated direction to move) or you can just type `X` to automatically make the next move in the solution. In either case, the solution will adjust to show the remaining moves to make.

## JSON puzzles

To load up a different puzzle, execute with the name of the file as an argument, like `./puzzle extra.json`.

## Dependencies

JSON parsing accomplished using Dave Gamble's cJSON library, available
from https://github.com/DaveGamble/cJSON under an MIT License.

