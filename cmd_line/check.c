#include <stdio.h>
#include <stdlib.h>
#include "load.h"
#include "share.h"
#include "model.h"

//--------------------------------------------------------
// FUNCTION main
//--------------------------------------------------------
int main(int argc, char **argv)
{
  PUZZLE_PTR puzzle = load_puzzle();

  char p = puzzle->target;

  char *s = puzzle->board->contents;
  while (*s) {
    if (*s == p) {
      exit (0);
    }
    s++;
  }

  exit (1);
}


