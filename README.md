# woodPuzzle
Multiple implementations of Sliding Wood Block Puzzle

![Actual Puzzle](images/actual_puzzle.png?raw=true "Actual Puzzle")

This repository contains standalone applications that enable a user to slide wooden tiles in a 5x4 rectangular puzzle to ultimately release one of the tiles from the board through an opening at the bottom of the board. These applications are developed using different languages and technologies.

When you start your application, the puzzle will be in its initial state. 

![Abstract Representation](images/rendition.png?raw=true "Abstract Representation")

From this initial state, the user can quit the application or reset the puzzle to its original configuration. Moving a piece is a two-step operation. First, the user selects a piece. Next the user requests to move the piece either up, down, left, or right one square. Naturally only valid moves should be allowed.

When the large 2x2 piece (highlighted in red) has left the board through the slot on the board of the board, the user has won the game, and should be congratulated! During game play, the total number of moves should be shown to the user.

## JSON encoding of new puzzles


You can load up different puzzles, using the JSON format. A sample `extra.json` puzzle is provided for you. Pieces are labeled with single characters, starting at 'A' and continuing up to 'Z'.
 
````{verbatim, language="json"}
{
    "name": "<NAME>",
    "board" : {
	"rows"         : "<NUM-ROWS>",
	"columns"      : "<NUM-COLUMNS",
	"target"       : "<CHARACTER>",
	"destination" : {
	    "row"      : "<DESTINATION-INT>",
	    "column"   : "<DESTINATION-INT>"
	},
	"exit" : {
	    "start"    : "<EXIT-START-INT>",
	    "end"      : "<EXIT-END-INT>"
	},
	"finalMove" : "<LEFT|RIGHT|DOWN|UP>"
    },
    "pieces" : [
	{ "label"    : "<CHARACTER>",
	  "isWinner" : "<TRUE|FALSE>",
	  "width"    : "<INTEGER>",
	  "height"   : "<INTEGER>"
	},
        ...
    ],
    "locations" : [
	{ "piece" : "<CHARACTER>",
	  "location" : {
	      "row"    : "<LOCATION-INT>",
	      "column" : "<LOCATION-INT>"
	  }
	},
        ...
    ]
}
````

## Enabling CI/CD on `devel` branch

Automatic testing via CI/CD when checking in devel branch.
