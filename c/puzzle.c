#include <ncurses.h>
#include <stdlib.h>
#include <ctype.h>
#include "board.h"
#include "solve.h"
#include "boundary.h"
#include "puzzle.h"

// https://tldp.org/HOWTO/NCURSES-Programming-HOWTO/

static char init_board[5][4] = {
      { 'A', 'B', 'B', 'C'},
      { 'A', 'B', 'B', 'C'},
      { 'D', 'E', 'F', 'G'},
      { 'D', 'H', 'I', 'G'},
      { ' ', 'J', 'J', ' '}
};

// computed solution
static MOVES_PTR moves = NULL;

void setup_board(char from[5][4], BOARD_PTR bd) {
  for (int r = 0; r < bd->rows; r++) {
    for (int c = 0; c < bd->cols; c++) {
      char ch = from[r][c];
      bd->contents[r*bd->cols + c] = ch;
    }
  }
}

char select_piece(BOARD_PTR bd) {
  for (;;) {
    move(20, 2);
    addstr("piece: [A-");
    addch(max_piece(bd));
    if (moves != NULL) {
      addstr(", Q to quit, R to reset, X to automove]    ");    
    } else {
      addstr(", Q to quit, R to reset, S to solve]       ");
    }
    move(21, 2);
    char ch = toupper(getch());
    flushinp();

    if (ch == 'X' && moves != NULL) { return 'X'; }   // auto-execute
    if (ch == 'Q' || ch == 'q') { return '\0'; }      // quit
    if (ch == 'R') { return ch; }                     // reset
    if (ch == 'S') { return ch; }                     // solve
    if (ch < 'A' || ch > max_piece(bd)) { continue; } // invalid
    return ch;
  }
}

char choose_direction() {
  for (;;) {
    move(20, 2);
    addstr("move: [WASD or Q]                            ");
    move(21, 2);
    char ch = toupper(getch());
    flushinp();

    if ((int) ch == 18) {
      return UP;
    } else if ((int) ch == 13) {
      return LEFT;
    } else if ((int) ch == 15) {
      return RIGHT;
    } else if ((int) ch == 94) {
      return DOWN;
    }

    if (ch == UP || ch == DOWN || ch == LEFT || ch == RIGHT) {
      return ch;
    }
    if (ch == 'Q') {
      return '\0';
    }
  }
}

//--------------------------------------------------------
// FUNCTION main
//--------------------------------------------------------
int main(void)
{
  initscr();
  noecho();              // don't show the keyboard output
  keypad(stdscr,TRUE);   // allows keypad arrows to work

  // the board to represent the puzzle
  BOARD_PTR board = make_board(5, 4);
  setup_board(init_board, board);

  for (;;) {
    draw_board(board, moves);
    refresh();

    char ch = select_piece(board);
    if (ch == 'R') {
      setup_board(init_board, board);
      moves = NULL;
      continue;
    } else if (ch == 'S') {
      // solve
      moves = solve_puzzle(board);
      continue;
    } else if (ch == 'X' && moves != NULL) {
      MOVES_PTR next = moves->next;
      char p = moves->piece;
      char dir = moves->direction;
      board->selected = p;
      try_move(board, dir);  // should always succeed
      free(moves);
      moves = next;      
      continue;
    }

    if (ch == '\0') { break; }
    board->selected = ch;

    draw_board(board, moves);
    refresh();
    ch = choose_direction();

    if (try_move(board, ch)) {
      // advance solution, if one exists...
      if (moves != NULL) {
	MOVES_PTR next = moves->next;
	free(moves);
	moves = next;
      }
    }

    board->selected = '\0';
  }
    
  endwin();
  return EXIT_SUCCESS;
}
