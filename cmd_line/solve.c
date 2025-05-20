#include <stdio.h>
#include <stdlib.h>

#include "load.h"
#include "share.h"
#include "model.h"

void do_nothing(int num) {

}

//--------------------------------------------------------
// FUNCTION main
//--------------------------------------------------------
int main(int argc, char **argv)
{

  PUZZLE_PTR puzzle = load_puzzle();
  if (puzzle == NULL) {
    exit (1);
  }

  MOVES_PTR moves = solve_puzzle(puzzle, &do_nothing);

  output_moves(moves);
  exit (0);
}
