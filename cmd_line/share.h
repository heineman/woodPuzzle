#ifndef _SHARE_H_
#define _SHARE_H_

#include "model.h"
#include "solve.h"

MOVES_PTR load_moves();
void output_moves(MOVES_PTR moves);

PUZZLE_PTR load_puzzle();
void output_puzzle(PUZZLE_PTR puzzle, int to_screen);

void output_moves(MOVES_PTR moves);

// standard WASD controls
#define UP     'W'
#define DOWN   'S'
#define LEFT   'A'
#define RIGHT  'D'

#endif
