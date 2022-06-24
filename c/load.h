#ifndef _LOAD_H_
#define _LOAD_H_

#include "model.h"

typedef struct piece {
  char    label;
  int     isWinner;
  int     width;
  int     height;

  struct piece *next;
} PIECE, *PIECE_PTR;

typedef struct location {
  char            piece;
  int             row;
  int             column;

  struct location *next;
} LOCATION, *LOCATION_PTR;

// prototype
PUZZLE_PTR json_parse(char *file);

/** 
 * JSON parsing library: https://github.com/DaveGamble/cJSON
 */

#endif /* _LOAD_H_ */
