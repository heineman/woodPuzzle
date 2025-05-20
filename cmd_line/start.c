#include <stdio.h>
#include <stdlib.h>

#include "cJSON.h"
#include "load.h"
#include "model.h"
#include "share.h"
#include "boundary.h"

// initial sample puzzle, if none provided on command linen
static char init_board[5][4] = {
  { 'A', 'B', 'B', 'C'},
  { 'A', 'B', 'B', 'C'},
  { 'D', 'E', 'F', 'G'},
  { 'D', 'H', 'I', 'G'},
  { ' ', 'J', 'J', ' '}
};

void setup_board(char from[5][4], BOARD_PTR bd) {
  for (int r = 0; r < bd->rows; r++) {
    for (int c = 0; c < bd->cols; c++) {
      char ch = from[r][c];
      bd->contents[r*bd->cols + c] = ch;
    }
  }
}

//--------------------------------------------------------
// FUNCTION main
//--------------------------------------------------------
int main(int argc, char **argv)
{
  // the board to represent the puzzle
  PUZZLE_PTR puzzle = NULL;

  if (argc > 1) {

    puzzle = json_parse(argv[1]);   // load up board from JSON

    if (puzzle == NULL) {
      printf("Cannot load file\n");
      exit (1);
    }
  }

  if (puzzle == NULL) {             // load up default...
    puzzle = make_puzzle("default", 5, 4,
			'B', 3, 1, 1, 2, DOWN);
    setup_board(init_board, puzzle->board);
  }

  if (puzzle == NULL) {
    exit (1);
  }

  output_puzzle(puzzle, 1);
  exit(0);
}
