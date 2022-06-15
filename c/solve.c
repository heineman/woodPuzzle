#include <stdio.h>
#include <malloc.h>
#include <string.h>
#include <ncurses.h>
#include "queue.h"

#include "solve.h"


NODE_PTR make_node(BOARD_PTR bd, NODE_PTR prev, int direction) {
  NODE_PTR np = calloc(1, sizeof(NODE));

  np->board = copy_board(bd);
  np->prev = prev;
  np->direction = direction;
  return np;
}

/* Convert board into size-based representation. */
void convert(char *rep) {
  int len = strlen(rep);
  for (int i = 0; i < len; i++) {
    if (rep[i] == ' ') { continue; }

    /** Count # of each and insert that wherever appears. */
    int num = 0;
    for (int j = i; j < len; j++) {     // count
      if (rep[i] == rep[j]) { num++; }
    }
    for (int j = i; j < len; j++) {     // replace
      if (rep[i] == rep[j]) { rep[i] = (char)('0' + num); }
    }
  } 
}

/* Check if already in seen. */
int already_seen(SEEN_PTR seen, BOARD_PTR bp) {
  char *s = strdup(bp->contents);
  convert(s);
  while (seen != NULL) {
    if (!strcmp(seen->contents, s)) {
      return 1;
    }
    seen = seen->next;
  }

  return 0;
}

MOVES_PTR solution(NODE_PTR n) {
  MOVES_PTR mp = NULL;

  while (n != NULL) {
    MOVES_PTR next = calloc(1, sizeof(MOVES));

    next->piece = n->board->selected;
    next->direction = n->direction;
    if (mp == NULL) {
      mp = next;
    } else {
      next->next = mp;
      mp = next;
    }

    n = n->prev;
  }

  // skip FIRST one, since that is starting point!
  return mp->next;
}  

/* Free all puzzle storage. */
void free_storage(SEEN_PTR sp, QUEUE_PTR qp) {
  while (sp != NULL) {
    SEEN_PTR tmp = sp->next;
    free(sp->contents);
    sp = tmp;
  }

  QNODE_PTR ptr = qp->head;
  while (ptr != NULL) {
    QNODE_PTR next = ptr->next;

    free(ptr->node->board->contents);
    free(ptr->node->board);
    free(ptr->node);
    free(ptr);

    ptr = next;
  }
}

/* Use BFS to solve puzzle. */
MOVES_PTR solve_puzzle(PUZZLE_PTR puzzle) {
  BOARD_PTR bd = puzzle->board;
  SEEN_PTR seen = NULL;
  QUEUE_PTR queue = calloc(1, sizeof (QUEUE));

  NODE_PTR n = make_node(bd, NULL, 0);
  seen = calloc(1, sizeof(SEEN));
  seen->contents = strdup(bd->contents);
  convert(seen->contents);
  
  enqueue(queue, n);
  char *directions = "WASD";

  int num = 0;
  while (!is_empty(queue)) {
    n = dequeue(queue);
    num++;
    if (num % 100 == 0) {
      solve_update(num);
    }

    char maxp = max_piece(bd);
    for (char ch = min_piece(bd); ch <= maxp; ch++) {
      for (int d = 0; d < 4; d++) {
	
	BOARD_PTR copy = copy_board(n->board);
	copy->selected = ch;
	if (try_move(puzzle, copy, directions[d])) {
	  NODE_PTR np = make_node(copy, n, directions[d]);
	  if (has_won(puzzle, copy)) {
	    MOVES_PTR solved = solution(np);
	    free_storage(seen, queue);
	    seen = NULL;
	    queue = NULL;
	    return solved;
	  }

	  if (!already_seen(seen, copy)) {
	    SEEN_PTR sp = calloc(1, sizeof(SEEN));
	    sp->contents = strdup(copy->contents);
	    convert(sp->contents);
	    sp->next = seen;
	    seen = sp;
	    enqueue(queue, np);
	  }
	}
      }
    }
  }

  free_storage(seen, queue);
  seen = NULL;
  queue = NULL;

  return NULL;
}
