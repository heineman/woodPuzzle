#ifndef _MODEL_H_
#define _MODEL_H_

typedef struct bd {
  int rows;
  int cols;
  int selected;

  // allocated as a string, to be chunked into 'cols' segments
  char *contents;
} BOARD, *BOARD_PTR;

typedef struct puzzle {
  BOARD_PTR     board;
  char          *name;
  char          target;
  int           destination_row;
  int           destination_column;
  int           exit_start;
  int           exit_end;
  char          final_move;
} PUZZLE, *PUZZLE_PTR;

// Prototypes
//
PUZZLE_PTR make_puzzle(char *name, int nr, int nc,
		       char target, int drow, int dcol,
		       int exit_start, int exit_end, char final_move);
BOARD_PTR make_board(int nr, int nc);
char min_piece(BOARD_PTR bd);  // returns lowest letter piece on board
char max_piece(BOARD_PTR bd);  // returns highest letter piece on board

int has_won(PUZZLE_PTR puzzle, BOARD_PTR bd);
int try_move(PUZZLE_PTR puzzle, BOARD_PTR, char ch);
char loc(BOARD_PTR bd, int r, int c);
char setloc(BOARD_PTR bd, int r, int c, char ch);

#endif /* _MODEL_H_ */
