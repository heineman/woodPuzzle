package woodpuzzle.model;

import java.io.FileReader;

import com.eclipsesource.json.*;

/** Load JSon puzzle and return it. */
public class Load {

	public static Puzzle load(FileReader reader) {
		JsonObject puzzleJson = null;
		try {
			puzzleJson = Json.parse(reader).asObject();
		} catch (Exception e) {
			return null;
		}
	    
	    JsonObject board = puzzleJson.get("board").asObject();
	    int rows = Integer.parseInt(board.getString("rows", "-1"));
	    int columns = Integer.parseInt(board.getString("columns", "-1"));
	    
	    //not needed: grabbed from pieces...
	    //char target= board.getString("target", "*").charAt(0);
	    
	    String finalMove = board.getString("finalMove", "Left");
	    MoveType m = MoveType.None;
	    if (finalMove.equalsIgnoreCase("UP")) { m = MoveType.Up; }
	    else if (finalMove.equalsIgnoreCase("DOWN")) { m = MoveType.Down; }
	    else if (finalMove.equalsIgnoreCase("LEFT")) { m = MoveType.Left; }
	    else if (finalMove.equalsIgnoreCase("RIGHT")) { m = MoveType.Right; }
	    
	    JsonObject destination = board.get("destination").asObject();
	    int drow = Integer.parseInt(destination.getString("row", "-1"));
	    int dcolumn = Integer.parseInt(destination.getString("column", "-1"));
	    
	    JsonObject exit = board.get("exit").asObject();
	    int start = Integer.parseInt(exit.getString("start", "-1"));
	    int end = Integer.parseInt(exit.getString("end", "-1"));
	    
	    JsonArray pieces = puzzleJson.get("pieces").asArray();
	    JsonArray locations = puzzleJson.get("locations").asArray();
	    
	    Puzzle puzzle = new Puzzle(rows, columns);
	    puzzle.setWinCondition(drow, dcolumn, m);
	    
	    for (JsonValue piece : pieces) {
	      char label = piece.asObject().getString("label", "*").charAt(0);
	      boolean isWinner = Boolean.parseBoolean(piece.asObject().getString("isWinner", "false"));
	      int width = Integer.parseInt(piece.asObject().getString("width", "-1"));
	      int height = Integer.parseInt(piece.asObject().getString("height", "-1"));
	      Piece p = new Piece(width, height, isWinner, label);
	      
	      for (JsonValue location: locations) {
	    	  JsonValue rowcol = location.asObject().get("location");
	    	  char locLabel = location.asObject().getString("piece", "*").charAt(0);
	    	  
		      if (locLabel == label) {
		    	  int row = Integer.parseInt(rowcol.asObject().getString("row", "-1"));
		    	  int column = Integer.parseInt(rowcol.asObject().getString("column", "-1"));
		    	  puzzle.placePiece(p,  row, column);
		    	  break;
		      }
	      }
	    }
	    
	    puzzle.setExit(start, end);
	    return puzzle;
	}
}
