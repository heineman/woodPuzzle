#ifndef _PUZZLE_H_
#define _PUZZLE_H_

#include "solve.h"

// standard WASD controls
#define UP     'W'
#define DOWN   'S'
#define LEFT   'A'
#define RIGHT  'D'

// prototypes
char select_piece(BOARD_PTR bd, MOVES_PTR moves);
char choose_direction();

#endif /* _PUZZLE_H_ */
