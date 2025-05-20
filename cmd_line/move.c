#include <stdio.h>
#include <stdlib.h>
#include "cJSON.h"
#include "load.h"
#include "share.h"
#include "model.h"

//--------------------------------------------------------
// FUNCTION main
//--------------------------------------------------------
int main(int argc, char **argv)
{
  if (argc != 3 && argc != 2) {
    printf("Usage: either do:\n");
    printf("         1. move SQUARE DIR\n");
    printf("         2. move X -- but only after SOLVED.\n");
    printf(" where SQUARE is a letter square in the puzzle\n");
    printf(" where DIR is a WASD direction\n");
    exit(1);
  }

  PUZZLE_PTR puzzle = load_puzzle();

  char p = argv[1][0];
  int rc = -1;

  if (p == 'X') {
    MOVES_PTR moves = load_moves();
    if (moves == NULL) {
      printf("No More Moves\n");
      exit(1);
    }

    MOVES_PTR next = moves->next;

    puzzle->board->selected = moves->piece;
    rc = try_move(puzzle, puzzle->board, moves->direction);  // try it
    output_moves(next);
  } else {
    char dir = argv[2][0];
    puzzle->board->selected = p;
    rc = try_move(puzzle, puzzle->board, dir);
  }

  output_puzzle(puzzle, 1);

  // awkward! But this is how code was originally written in model, which is
  // backwards from UNIX.
  if (rc) {
    exit (0);
  }
  exit (1);
}
