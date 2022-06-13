package woodpuzzle.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

/**
 * I'm still not entirely comfortable with my strategy for dealing with winning pieces, but I can make this work.
 */
public class Puzzle implements Iterable<Piece> {
	ArrayList<Piece> pieces = new ArrayList<>();
	ArrayList<Piece> originals = new ArrayList<>();
	public final int numRows;
	public final int numColumns;
	int numMoves;
	
	int exitRow;
	int exitColumn;
	MoveType exitDirection;
	
	// needed for drawing borders
	int startExit;
	int endExit;
	
	/**
	 * Rectangular puzzle has number of rows and columns.
	 * 
	 * @param width
	 * @param height
	 */
	public Puzzle (int rows, int columns) {
		this.numRows = rows;
		this.numColumns = columns;
	}
	
	/** Make a clone of the puzzle. Useful for solving. */
	public Puzzle clone() {
		Puzzle copy = new Puzzle(numRows, numColumns);
		copy.exitRow = exitRow;
		copy.exitColumn = exitColumn;
		copy.exitDirection = exitDirection;
		copy.startExit = startExit;
		copy.endExit = endExit;
		for (Piece p : pieces) {
			copy.placePiece(p.copy(), p.row, p.col);
		}
		return copy;
	}
	
	/** Return key for the puzzle state. */
	public String key() {
		StringBuffer sb = new StringBuffer();
		for (int r = 0; r < numRows; r++) {
			for (int c = 0; c < numColumns; c++) {
				Optional<Piece> op = getPiece(new Coordinate(r, c));
				if (op.isPresent()) {
					sb.append("" + op.get().size());    // insert SIZE (and not label) for better key.
				} else {
					sb.append(" ");
				}
			}
		}
		
		return sb.toString();
	}

	/** Remove the given piece. Needed when removing final piece from the board. NOT PUBLIC. */
	void remove(Piece p) {
		pieces.remove(p);
		for (Piece o : originals) {
			if (o.label == p.label) {
				originals.remove(o);
				return;
			}
		}
	}
	
	/**
	 * Place given piece at the specific location (based on anchor point of the piece).
	 */
	public boolean placePiece (Piece p, int row, int col) {
		// already covered
		if (isCovered(new Coordinate(row, col))) { 
			return false;
		}
		
		// place piece properly
		pieces.add(p);
		p.place(row, col);
		originals.add(p.copy());
		return true;
	}
	
	/** Get piece containing given coordinate (if it exists). */
	public Optional<Piece> getPiece (Coordinate c) {
		for (Piece p : pieces) {
			if (p.contains(c)) {
				return Optional.of(p);
			}
		}
		
		return Optional.empty();
	}
	
	/**
	 * Determines if any piece in the puzzle covers given coordinate.
	 * 
	 * @param coord
	 * @return
	 */
	public boolean isCovered(Coordinate coord) {
		for (Piece p : pieces) {
			if (p.contains(coord)) {
				return true;
			}
		}
		
		return false;
	}

	/** Return all pieces in the game. */
	@Override
	public Iterator<Piece> iterator() {
		return pieces.iterator();
	}

	/** Reset to original state. */
	public void reset() {
		pieces.clear();
		for (Piece p: originals) {
			pieces.add(p.copy());
		}
	}

	/** Each puzzle needs to know when it is ready to be won. */
	public void setWinCondition(int row, int col, MoveType direction) {
		this.exitRow = row;
		this.exitColumn = col;
		this.exitDirection = direction;
	}
	
	/** Determines if winning move. */
	public boolean isWinCondition(int row, int col, MoveType direction) {
		return row == exitRow && col == exitColumn && direction == exitDirection;
	}
	
	/** Determine the location for target to move to. */
	public Coordinate getWinLocation() {
		return new Coordinate(exitRow, exitColumn);
	}

	/** Return the direction from the winning location. */
	public MoveType getExitDirection() { return exitDirection; }

	/** represent as 2d form. */
	public String toString() {
		char[][] bd = new char[numRows][numColumns];
		for (Piece p : this) {  
			for (int r = 0; r < p.height; r++) {
				for (int c = 0; c < p.width; c++) {
					bd[p.row+r][p.col+c] = p.label;
				}
			}
		}
		
		StringBuffer sb = new StringBuffer();
		for (int r = 0; r < numRows; r++) {
			for (int c = 0; c < numColumns; c++) {
				sb.append(bd[r][c]);
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}

	public void setExit(int start, int end) {
		startExit = start;
		endExit = end;
	}

	public MoveType getFinalMove() {
		return exitDirection;
	}

	public int getExitEnd() {
		return endExit;
	}
	public int getExitStart() {
		return startExit;
	}
}
