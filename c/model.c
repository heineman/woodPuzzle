#include <malloc.h>
#include <string.h>
#include "model.h"
#include "puzzle.h"   // for UP/DOWN/LEFT/RIGHT

// compute anew with max_piece() once set to \0
static int _max_piece = '\0';


// TODO: FIX WINNING CONDITIONS AND START/END ROWS and direction

BOARD_PTR make_board(int nr, int nc) {
  BOARD_PTR bd = calloc(1, sizeof(BOARD));
  bd->rows = nr;
  bd->cols = nc;
  bd->contents = calloc(nr*nc, sizeof(char));
  bd->selected = '\0';

  _max_piece = '\0';  // unknown
  return bd;
}

BOARD_PTR copy_board(BOARD_PTR bd) {
  BOARD_PTR copy = calloc(1, sizeof(BOARD));
  copy->rows = bd->rows;
  copy->cols = bd->cols;
  copy->selected = bd->selected;
  copy->contents = strdup((const char *)bd->contents);
  return copy;
}

PUZZLE_PTR make_puzzle(char *name, int nr, int nc,
		       char target, int drow, int dcol,
		       int exit_start, int exit_end, char final_move) {
  PUZZLE_PTR ptr = calloc(1, sizeof(PUZZLE));

  ptr->name = strdup(name);
  ptr->board = make_board(nr, nc);
  ptr->target = target;
  ptr->destination_row = drow;
  ptr->destination_column = dcol;
  ptr->exit_start = exit_start;
  ptr->exit_end = exit_end;
  ptr->final_move = final_move;
  return ptr;
}

char min_piece(BOARD_PTR bd) {
  return 'A';
}

char max_piece(BOARD_PTR bd) {
  if (_max_piece == '\0') {
    for (int r = 0; r < bd->rows; r++) {
      for (int c = 0; c < bd->cols; c++) {
	char ch = loc(bd, r, c);
	if (ch > _max_piece) {
	  _max_piece = ch;
	}
      }
    }
  }

  return _max_piece;

}

char loc(BOARD_PTR bd, int r, int c) {
  return bd->contents[r*bd->cols + c];
}

char setloc(BOARD_PTR bd, int r, int c, char ch) {
  bd->contents[r*bd->cols + c] = ch;
}

/**
 * Determines if upper-left corner of target piece to be removed
 * is in the destination_row, destination_column.
 *
 * Only a little bit awkward. Must make sure NO other fragment of this
 * target piece appears in board "before" it.
 */
int has_won(PUZZLE_PTR puzzle, BOARD_PTR bd) {
  for (int r = 0; r < bd->rows; r++) {
    for (int c = 0; c < bd->cols; c++) {
      if ((r == puzzle->destination_row) &&
	  (c == puzzle->destination_column)) {
	return loc(bd, r, c) == puzzle->target;
      }

      /* If you find any part of the target before, not in position */
      if (loc(bd, r, c) == puzzle->target) {
	return 0;
      }
    }
  }

  return 0;  // not even present?
}

void clear_piece(BOARD_PTR bd, char p) {
  for (int r = 0; r < bd->rows; r++) {
    for (int c = 0; c < bd->cols; c++) {
      if (loc(bd, r, c) == p) {
	setloc(bd, r, c, ' ');
      }
    }
  }
}


int try_move(PUZZLE_PTR puzzle, BOARD_PTR bd, char ch) {
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
  if (has_won(puzzle, bd) && ch == puzzle->final_move) {
    clear_piece(bd, puzzle->target);
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
  
