#include <ncurses.h>
#include <stdlib.h>
#include <ctype.h>
#include "board.h"
#include "solve.h"
#include "boundary.h"
#include "puzzle.h"
#include "load.h"

// computed solution
static MOVES_PTR moves = NULL;

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

// refers to copy made for reset
static BOARD_PTR initial = NULL;

//--------------------------------------------------------
// FUNCTION main
//--------------------------------------------------------
int main(int argc, char **argv)
{
  // the board to represent the puzzle
  PUZZLE_PTR puzzle = NULL;

  if (argc > 1) {
    puzzle = json_parse(argv[1]);   // load up board from JSON
  }
  if (puzzle == NULL) {             // load up default...
    puzzle = make_puzzle("default", 5, 4,
			'B', 3, 1, 1, 2, DOWN);
    setup_board(init_board, puzzle->board);
  }

  
  initial = copy_board(puzzle->board);   // make copy for Reset

  initscr();
  noecho();              // don't show the keyboard output
  keypad(stdscr,TRUE);   // allows keypad arrows to work

  for (;;) {
    draw_puzzle(puzzle, moves);
    refresh();

    char ch = select_piece(puzzle->board, moves);
    if (ch == 'R') {
      puzzle->board = copy_board(initial);
      moves = NULL;
      continue;
    } else if (ch == 'S') {
      // solve
      moves = solve_puzzle(puzzle);
      continue;
    } else if (ch == 'X' && moves != NULL) {
      MOVES_PTR next = moves->next;
      char p = moves->piece;
      char dir = moves->direction;
      puzzle->board->selected = p;
      try_move(puzzle, puzzle->board, dir);  // should always succeed
      free(moves);
      moves = next;      
      continue;
    }

    if (ch == '\0') { break; }
    puzzle->board->selected = ch;

    draw_puzzle(puzzle, moves);
    refresh();
    ch = choose_direction();

    if (try_move(puzzle, puzzle->board, ch)) {
      // advance solution, if one exists...
      if (moves != NULL) {
	MOVES_PTR next = moves->next;
	free(moves);
	moves = next;
      }
    }

    puzzle->board->selected = '\0';
  }
    
  endwin();
  return EXIT_SUCCESS;
}
