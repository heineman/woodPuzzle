#include <ncurses.h>
#include <stdlib.h>
#include <ctype.h>
#include "solve.h"
#include "boundary.h"
#include "puzzle.h"

// https://tldp.org/HOWTO/NCURSES-Programming-HOWTO/

char select_piece(BOARD_PTR bd, MOVES_PTR moves) {
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

