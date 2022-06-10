#ifndef _SOLVE_H_
#define _SOLVE_H_

#include "model.h"

typedef struct seen_node {
  char *contents;
  struct seen_node *next;

} SEEN, *SEEN_PTR;

typedef struct node {
  BOARD_PTR     board;
  struct node   *prev;
  int           direction;
} NODE, *NODE_PTR;

typedef struct qnode {
  NODE_PTR       node;
  struct qnode   *next;
} QNODE, *QNODE_PTR;

typedef struct queue {
  QNODE_PTR head;
  QNODE_PTR tail;
} QUEUE, *QUEUE_PTR;

typedef struct moves {
  char            piece;
  int             direction;
  struct moves    *next;
} MOVES, *MOVES_PTR;

MOVES_PTR solve_puzzle(BOARD_PTR bd);
BOARD_PTR copy_board(BOARD_PTR bd);

/** Call-back update. */
void solve_update(int num);

#endif /* _SOLVE_H_ */
