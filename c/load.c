#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <malloc.h>
#include "cJSON.h"
#include "model.h"
#include "puzzle.h"
#include "load.h"

char *load_file(char *filename, long *length) {
  char *buffer = NULL;

  FILE *f = fopen (filename, "rb");
  if (f == NULL) {
    return NULL;
  }

  fseek (f, 0, SEEK_END);
  *length = ftell (f);
  fseek (f, 0, SEEK_SET);
  buffer = malloc (*length);
  if (buffer) {
    fread (buffer, 1, *length, f);
  }
  fclose (f);
  return buffer;
}

int to_int(const cJSON *json) {
  if (cJSON_IsString(json) && (json->valuestring != NULL)) {
    return atoi(json->valuestring);
  }

  return 0;  // reasonable default...
}

char *to_string(const cJSON *json) {
  if (cJSON_IsString(json) && (json->valuestring != NULL)) {
    return json->valuestring;
  }

  return "";  // reasonable default...
}

char *to_upper_string(const cJSON *json) {
  if (cJSON_IsString(json) && (json->valuestring != NULL)) {
    // convert to upper case
    char *s = json->valuestring;
    while (*s) {
      *s = toupper((unsigned char) *s);
      s++;
    }

    return json->valuestring;
  }

  return "";  // reasonable default...
}

BOARD_PTR form_board(int rows, int columns) {
  return NULL;
}


// makes code easier to read?
#define get_cJSON cJSON_GetObjectItemCaseSensitive

PUZZLE_PTR json_parse(char *file) {

  // load up file into character
  long length;
  char *contents = load_file(file, &length);
  if (contents == NULL) {
    return NULL;
  }

  cJSON *contents_json = cJSON_Parse(contents);

  if (contents_json == NULL) {
    goto end;
  }

  const cJSON *name_json = get_cJSON(contents_json, "name");
  char *name = strdup(to_string(name_json));
  const cJSON *board_json = get_cJSON(contents_json, "board"); 

  const cJSON *rows_json = get_cJSON(board_json, "rows");
  int rows = to_int(rows_json);

  const cJSON *cols_json = get_cJSON(board_json, "columns");
  int columns = to_int(cols_json);

  const cJSON *target_json = get_cJSON(board_json, "target");
  char target = to_string(target_json)[0];

  const cJSON *destination_json = get_cJSON(board_json, "destination");
  const cJSON *drow_json = get_cJSON(destination_json, "row");
  int drow = to_int(drow_json);
  const cJSON *dcol_json = get_cJSON(destination_json, "column");
  int dcol = to_int(dcol_json);

  const cJSON *exit_json = get_cJSON(board_json, "exit");
  const cJSON *start_json = get_cJSON(exit_json, "start");
  int start_value = to_int(start_json);
  const cJSON *end_json = get_cJSON(exit_json, "end");
  int end_value = to_int(end_json);

  const cJSON *final_json = get_cJSON(board_json, "finalMove");
  char *final_move_str = to_upper_string(final_json);
  char final_move = '\0';
  if (!strcmp("UP", final_move_str)) {
    final_move = UP;
  } else if (!strcmp("DOWN", final_move_str)) {
    final_move = DOWN;
  } else if (!strcmp("LEFT", final_move_str)) {
    final_move = LEFT;
  } else if (!strcmp("RIGHT", final_move_str)) {
    final_move = RIGHT;
  }

  // construct list of pieces.
  PIECE_PTR pieces = NULL;
  const cJSON *piece_json;
  const cJSON *pieces_json = cJSON_GetObjectItemCaseSensitive(contents_json, "pieces");
  cJSON_ArrayForEach(piece_json, pieces_json) {
    cJSON *label = cJSON_GetObjectItemCaseSensitive(piece_json, "label");
    cJSON *isWinner = cJSON_GetObjectItemCaseSensitive(piece_json, "isWinner");
    cJSON *width = cJSON_GetObjectItemCaseSensitive(piece_json, "width");
    cJSON *height = cJSON_GetObjectItemCaseSensitive(piece_json, "height");

    PIECE_PTR piece = calloc(1, sizeof(PIECE));
    piece->label = to_string(label)[0];   // first character.
    if (!strcmp("TRUE", to_upper_string(isWinner))) {
      piece->isWinner = 1;
    } else {
      piece->isWinner = 0;
    }
    piece->width = to_int(width);
    piece->height = to_int(height);

    if (pieces == NULL) {
      pieces = piece;
    } else {
      piece->next = pieces;
      pieces = piece;
    }
  }

  // locations
  // construct list of pieces.
  LOCATION_PTR locations = NULL;
  const cJSON *location_json;
  const cJSON *locations_json = cJSON_GetObjectItemCaseSensitive(contents_json, "locations");
  cJSON_ArrayForEach(location_json, locations_json) {
    cJSON *piece = cJSON_GetObjectItemCaseSensitive(location_json, "piece");
    cJSON *inner_json = cJSON_GetObjectItemCaseSensitive(location_json, "location");

    cJSON *row = cJSON_GetObjectItemCaseSensitive(inner_json, "row");
    cJSON *column = cJSON_GetObjectItemCaseSensitive(inner_json, "column");

    LOCATION_PTR loc = calloc(1, sizeof(LOCATION));
    loc->piece = to_string(piece)[0];   // first character.
    loc->row = to_int(row);
    loc->column = to_int(column);

    if (locations == NULL) {
      locations = loc;
    } else {
      loc->next = locations;
      locations = loc;
    }

  }

  // now get pieces, which is an array
  BOARD_PTR board = make_board(rows, columns);
  // clear board
  for (int r = 0; r < rows; r++) {    
    for (int c = 0; c < columns; c++) {
      setloc(board, r, c, ' ');  // clear
    }
  }

  PIECE_PTR np = pieces;
  while (np != NULL) {

    LOCATION_PTR lp = locations;
    while (lp != NULL) {
      if (lp->piece == np->label) {
	break;
      }
      lp = lp->next;
    }

    // place each piece
    for (int r = 0; r < np->height; r++) {
      for (int c = 0; c < np->width; c++) {
	setloc(board, r+lp->row, c+lp->column, np->label);
      }
    }	

    np = np->next;
  }
  

 end:
  cJSON_Delete(contents_json);
    
  PUZZLE_PTR ptr = calloc(1, sizeof(PUZZLE));
  ptr->name = name;
  ptr->board = board;
  ptr->target = target;
  ptr->destination_row = drow;
  ptr->destination_column = dcol;
  ptr->exit_start= start_value;
  ptr->exit_end= end_value;
  ptr->final_move = final_move;
  return ptr;
}
