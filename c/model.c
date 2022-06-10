#include <malloc.h>
#include "model.h"
#include "puzzle.h"   // for UP/DOWN/LEFT/RIGHT

BOARD_PTR make_board(int nr, int nc) {
  BOARD_PTR bd = calloc(1, sizeof(BOARD));
  bd->rows = nr;
  bd->cols = nc;
  bd->contents = calloc(nr*nc, sizeof(char));
  bd->selected = '\0';

  return bd;
}

char min_piece(BOARD_PTR bd) {
  return 'A';
}

char max_piece(BOARD_PTR bd) {
  return 'J';
}

char loc(BOARD_PTR bd, int r, int c) {
  return bd->contents[r*bd->cols + c];
}

char setloc(BOARD_PTR bd, int r, int c, char ch) {
  bd->contents[r*bd->cols + c] = ch;
}

int has_won(BOARD_PTR bd) {
  // TODO
  return ((loc(bd, 3, 1) == 'B') &&
	  (loc(bd, 3, 2) == 'B') &&
	  (loc(bd, 4, 1) == 'B') &&
	  (loc(bd, 4, 2) == 'B'));
}

int try_move(BOARD_PTR bd, char ch) {
  if (ch == 'Q') {
    return 0;
  }
  if (bd->selected == '\0') {
    return 0;
  }

  // find selected piece and try to move in direction
  int dr = 0;
  int dc = 0;
  if (ch == UP) { 
    dr = -1; 
  } else if (ch == DOWN) {
    dr = +1;
  } else if (ch == LEFT) {
    dc = -1;
  } else if (ch == RIGHT) {
    dc = +1;
  } else {
    return 0; // unknown?
  }

  // VICTORY? B in final spot and move DOWN
  if (has_won(bd) && ch == 'S') {
    setloc(bd, 3, 1, ' ');
    setloc(bd, 3, 2, ' ');
    setloc(bd, 4, 1, ' ');
    setloc(bd, 4, 2, ' ');    
    return 1;
  }

  // validate
  int valid = 1;              // assume valid
  int num_to_move = 0;
  int what_to_clear[bd->cols * bd->rows][2];  // locations to clear
  int where_to_move[bd->cols * bd->rows][2];  // locations to adjust
  for (int r = 0; r < bd->rows; r++) {
    for (int c = 0; c < bd->cols; c++) {
      char ch = loc(bd, r, c);

      if (bd->selected != ch) {
	continue;
      }

      int nr = r + dr;
      int nc = c + dc;
      if (nr < 0 || nr >= bd->rows) { valid = 0; continue; }
      if (nc < 0 || nc >= bd->cols) { valid = 0; continue; }

      // allowed only if SPACE and same
      what_to_clear[num_to_move][0] = r;
      what_to_clear[num_to_move][1] = c;      
      where_to_move[num_to_move][0] = nr;
      where_to_move[num_to_move][1] = nc;      
      num_to_move++;

      if ((loc(bd, nr, nc) != ' ') && (loc(bd, nr, nc) != bd->selected)) {
	valid = 0;
      }
    }
  }

  // make move
  if (valid) {
    for (int idx = 0; idx < num_to_move; idx++) {
      setloc(bd, what_to_clear[idx][0], what_to_clear[idx][1], ' ');
    }
    for (int idx = 0; idx < num_to_move; idx++) {
      setloc(bd, where_to_move[idx][0], where_to_move[idx][1], bd->selected);
    }
    return 1;
  } else {
    return 0;
  }
}
  
