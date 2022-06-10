#include <ncurses.h>
#include <stdlib.h>
#include <ctype.h>

#include "boundary.h"
#include "puzzle.h"

static char *spinning = "\\|/-\\|/-|";
static int sidx = 0;

// -----------------------
// Call-back hooks for solver.
// ------------------------
void solve_update(int num) {
  move(21, 2);
  addch(spinning[sidx]);
  move(21, 2);
  sidx = (sidx + 1) % 8;
  refresh();
}

void draw_board(BOARD_PTR bd, MOVES_PTR moves) {
  int off = 2;
  clear();

  // outer board
  for (int r = 0; r < bd->rows; r++) {
    mvaddch(1+r*W, 1,   '|');
    mvaddch(1+r*W+1, 1, '|');
    mvaddch(1+r*W+2, 1, '|');
    mvaddch(1+r*W, bd->cols*W+1,   '|');
    mvaddch(1+r*W+1, bd->cols*W+1, '|');
    mvaddch(1+r*W+2, bd->cols*W+1, '|');
  }
  
  for (int c = 0; c < bd->cols; c++) {
    mvaddch(1, 2+c*W,  '_');
    mvaddch(1, 3+c*W,  '_');
    mvaddch(1, 4+c*W,  '_');

    // not exit!
    if (c < 1 || c > 2) {
      mvaddch(1+bd->rows*W, 2+c*W,  '-');
      mvaddch(1+bd->rows*W, 3+c*W,  '-');
      if (c > 2) { mvaddch(1+bd->rows*W, 4+c*W,  '-'); }
    }
  }
  mvaddch(1,1, '.');
  mvaddch(1,bd->cols*W+1, '.');
  mvaddch(1+bd->rows*W,1, '.');
  mvaddch(1+bd->rows*W,bd->cols*W+1, '.');

  for (int r = 0; r < bd->rows; r++) {
    for (int c = 0; c < bd->cols; c++) {
      char ch = loc(bd, r, c);
      if (ch == bd->selected) { attron(A_REVERSE); }
      mvaddch(off+r*W, off+c*W, ch);
      mvaddch(off+r*W, off+c*W+1, ch);
      mvaddch(off+r*W+1, off+c*W+1, ch);
      mvaddch(off+r*W+1, off+c*W, ch);

      // stretch into next COL
      int both = 0;
      if (c < bd->cols && ch == loc(bd, r, c+1)) {
	mvaddch(off+r*W, off+c*W+2, ch);
	mvaddch(off+r*W+1, off+c*W+2, ch);
	both += 1;
      }
      // stretch into next ROW
      if (r < bd->rows && ch == loc(bd, r+1, c)) {
	mvaddch(off+r*W+2, off+c*W, ch);
	mvaddch(off+r*W+2, off+c*W+1, ch);
	both += 2;
      }

      // IF BOTH, then fill in missing spot
      if (both == 3) {
	mvaddch(off+r*W+2, off+c*W+2, ch);
      }

      if (ch == bd->selected) { attroff(A_REVERSE); }
    }
  }

  // show first ten moves
  if (moves != NULL) {
    move(1, 30);
    
    attron(A_UNDERLINE); 
    addstr("SOLUTION:");
    attroff(A_UNDERLINE); 
    MOVES_PTR n = moves;
    int idx = 0;
    while (n != NULL && idx < 10) {
      move(2+idx, 30);
      if (idx == 0) {
	attron(A_REVERSE); 
      }
      addch(n->piece);

      switch (n->direction) {
      case UP:
	addstr(" up");
	break;
      case LEFT:
	addstr(" left");
	break;
      case DOWN:
	addstr(" down");
	break;
      case RIGHT:
	addstr(" right");
	break;
      }

      if (idx == 0) {
	attroff(A_REVERSE); 
      }

      idx++;
      n = n->next;
    }
  }
}
