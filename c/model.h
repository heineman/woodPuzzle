#ifndef _MODEL_H_
#define _MODEL_H_

typedef struct bd {
  int rows;
  int cols;
  int selected;

  // allocated as a string, to be chunked into 'cols' segments
  char *contents;
} BOARD, *BOARD_PTR;


BOARD_PTR make_board(int nr, int nc);
char min_piece(BOARD_PTR bd);  // returns lowest letter piece on board
char max_piece(BOARD_PTR bd);  // returns highest letter piece on board

int has_won(BOARD_PTR bd);
int try_move(BOARD_PTR, char ch);
char loc(BOARD_PTR bd, int r, int c);
char setloc(BOARD_PTR bd, int r, int c, char ch);

#endif /* _MODEL_H_ */
