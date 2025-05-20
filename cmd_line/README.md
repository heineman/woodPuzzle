# Command-line implementation of Wood Puzzle.

## How to build

Type `make` to build the executables. There are five of them:

* start [JSON file]  -- which initiates the puzzle
* show -- shows the state of the puzzle to the screen
* move -- either moves a piece directed by user or the first in solved solution.
* solve -- solve the current puzzle state and store solution
* check -- check to see if puzzle state is solved

## `./start` launches the application

It creates a `PUZZLE` file to store the state of the puzzle. If given an optional JSON file (based on the schema) it will create a puzzle from its information.

If exit code is 0 then success. It might core dump if given a non-JSON file as input, but this is the problem of the cJSON.c file.

## `./show` shows puzzle state

Outputs the state of the PUZZLE

## `./move` moves desired piece

Move the piece using WASD directions. If the move is not valid, application exits with an error code of 1.

## `./solve` solves the current puzzle

Solves the current PUZZLE and stores moves in MOVES file.

## `./check` confirms whether puzzle is solved

If solved then it exits with 1 otherwise it exits with 0

## JSON puzzles

To load up a different puzzle, execute with the name of the file as an argument, like `./puzzle extra.json`.

## Dependencies

JSON parsing accomplished using Dave Gamble's cJSON library, available
from https://github.com/DaveGamble/cJSON under an MIT License.
