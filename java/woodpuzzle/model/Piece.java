package woodpuzzle.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Each piece in a sliding puzzle is assumed to be rectangular.
 * 
 * I have equals method to ensure I can properly reset board.
 */
public class Piece implements Iterable<Coordinate> {
	public final int height;         // height in terms of 'squares'
	public final int width;          // width in terms of 'squares'
	public final boolean isWinner;   // determines if winner. Unexpected that it was easier to put here.
	
	int row;  // where top-left corner of piece is in the board.
	int col;
	
	public final char label;                // label ('A' through ... whatever)
	
	public Piece (int w, int h, boolean isWinner, char label) {
		this.width = w;
		this.height = h;
		this.isWinner = isWinner;
		this.label = label;
	}
	
	/**
	 * Place piece in the board.
	 * @param row
	 * @param col
	 */
	public void place (int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	/** 
	 * Move piece in the given direction. 
	 * 
	 * Made public for solving engine 
	 */
	public void move(MoveType direction) {
		if (direction == null) {
			System.out.println("HELP");
		}
		this.row += direction.deltaR;
		this.col += direction.deltaC;
	}
	
	/** Two pieces are same if all attributes are the same. */
	public boolean equals (Object o) {
		if (o == null) { return false; }
		if (o instanceof Piece) {
			Piece other = (Piece) o;
			return other.height == height && other.width == width && other.row == row && other.col == col;
		}
		
		return false;
	}

	/** Get location of upper-left corner of piece. */
	public Coordinate getLocation() {
		return new Coordinate(row, col);
	}
	
	/**
	 * Determine whether piece contains the given coordinate.
	 * 
	 * @param coord
	 * @return
	 */
	public boolean contains(Coordinate coord) {
		for (Coordinate c : this) {
			if (coord.equals(c)) { return true; }
		}
		
		return false;
	}
	
	/** Returns the size of this rectangular block. Useful for key. */
	public int size() {
		return height*width;
	}

	/**
	 * Report on the coordinates contained by this piece.
	 */
	@Override
	public Iterator<Coordinate> iterator() {
		ArrayList<Coordinate> coords = new ArrayList<Coordinate>();
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c++) {
				coords.add(new Coordinate(row + r, col + c));
			}
		}
		
		return coords.iterator();
	}

	/** Returns a copy of this piece. */
	public Piece copy() {
		Piece p = new Piece(width, height, isWinner, label);
		p.place(row, col);
		return p;
	}

}
