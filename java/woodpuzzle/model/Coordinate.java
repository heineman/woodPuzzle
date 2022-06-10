package woodpuzzle.model;

/**
 * A coordinate in a rectangular sliding puzzle.
 */
public class Coordinate {
	public final int row;
	public final int column;
	
	public Coordinate (int row, int col) {
		this.row = row;
		this.column = col;
	}
	
	/** Default equals method. */
	public boolean equals (Object o) {
		if (o == null) { return false; }
		if (o instanceof Coordinate) {
			Coordinate other = (Coordinate) o;
			return other.row == row && other.column == column;
		}
		
		return false;
	}
	
	public String toString () { return "(R=" + row + ", C=" + column + ")"; }
}
