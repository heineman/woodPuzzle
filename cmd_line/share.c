#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "model.h"
#include "solve.h"

void output_moves(MOVES_PTR moves) {
  FILE *fp = fopen("MOVES", "w");
  while (moves != NULL) {
    fprintf(fp, "%c %d\n", moves->piece, moves->direction);

    moves = moves->next;
  }
  fclose(fp);
}

MOVES_PTR load_moves() {
  FILE   *fp = fopen("MOVES", "r");
  char   ch;
  int    dir;

  MOVES_PTR head = NULL;
  MOVES_PTR tail = NULL;
  
  while (2 == fscanf(fp, "%c %d\n", &ch, &dir)) {
    if (head == NULL) {
      head = tail = calloc(1, sizeof (MOVES));
      head->piece = ch;
      head->direction = dir;
    } else {
      tail->next = calloc(1, sizeof (MOVES));
      tail->next->piece = ch;
      tail->next->direction = dir;      
      tail = tail->next;
    }
  }
  fclose(fp);
  return head;
}

PUZZLE_PTR load_puzzle() {
  // find number of columns and then number of rows.
  FILE *fp = fopen("PUZZLE", "r");

  char ch;
      
  int num_rows = 0;
  int num_cols = 0;
  int real_num_cols = 0;
  while ((ch = fgetc(fp))) {
    if (ch == '\n') {
      if (real_num_cols == 0) {
	real_num_cols = num_cols;
      }
      num_cols = 0;
      num_rows += 1;
    } else {
      num_cols += 1;
    }
  }
  fclose(fp);

  PUZZLE_PTR puzzle = calloc(1, sizeof(PUZZLE));
  puzzle->board = calloc(1, sizeof(BOARD));
  puzzle->board->rows = num_rows;
  puzzle->board->cols = real_num_cols;
  puzzle->board->selected = 0;

  puzzle->board->contents = calloc(num_rows*real_num_cols+1, 1);

  fp = fopen("PUZZLE", "r");

  // DO AGAIN but this time keep track of contents
  int idx = 0;
  while ((ch = fgetc(fp))) {
    if (ch == '\n') {
      // nothing to do
    } else {
      puzzle->board->contents[idx++] = ch;
    }
  }

  // get meta-data. First line is name and might have spaces in it!
  // NEED TO MANUALLY remove the \n
  char buf[1024];
  fgets(buf, 1024, fp);
  buf[strlen(buf)-1] = 0;
  puzzle->name = strdup((char*) buf);

  if (1 != fscanf(fp, "%c\n", &puzzle->target)) {
    printf(" ** couldn't read target\n");
  }

  fscanf(fp, "%d\n", &puzzle->destination_row);
  fscanf(fp, "%d\n", &puzzle->destination_column);
  fscanf(fp, "%d\n", &puzzle->exit_start);
  fscanf(fp, "%d\n", &puzzle->exit_end);
  fscanf(fp, "%c\n", &puzzle->final_move);

  fclose(fp);

  return puzzle;
}

void output_puzzle(PUZZLE_PTR puzzle, int to_screen) {
  FILE *fp = fopen("PUZZLE", "w");

  for (int r = 0; r < puzzle->board->rows; r++) {
    for (int c = 0; c < puzzle->board->cols; c++) {
      char ch = loc(puzzle->board, r, c);

      fprintf(fp, "%c", ch);
      if (to_screen) {
	printf("%c", ch);
      }
    }
    fprintf(fp, "%s", "\n");
    if (to_screen) {
      printf("\n");
    }
  }

  // separator is a single 0 char.
  fprintf(fp, "%c", 0);

  // NOW output meta-data
  fprintf(fp, "%s\n", puzzle->name);
  fprintf(fp, "%c\n", puzzle->target);
  fprintf(fp, "%d\n", puzzle->destination_row);
  fprintf(fp, "%d\n", puzzle->destination_column);
  fprintf(fp, "%d\n", puzzle->exit_start);
  fprintf(fp, "%d\n", puzzle->exit_end);
  fprintf(fp, "%c\n", puzzle->final_move);

  fclose(fp);
}
