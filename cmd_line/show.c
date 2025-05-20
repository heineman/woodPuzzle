#include <stdio.h>
#include <stdlib.h>


//--------------------------------------------------------
// FUNCTION main
//--------------------------------------------------------
int main(int argc, char **argv)
{
  FILE *fp = fopen("PUZZLE", "r");
  if (fp == NULL) {
    printf("No PUZZLE exists.\n");
    exit (1);
  }

  char ch;
      
  while (1 == fscanf(fp, "%c", &ch)) {
    if (ch == '\0') {
      break;                        // end of the puzzle
    } else if (ch == '\n') {
      printf("\n");
    } else {
      printf("%c", ch);
    }
  }

  fclose(fp);
  exit (0);
}
