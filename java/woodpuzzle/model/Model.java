package woodpuzzle.model;

import java.util.ArrayList;
import java.util.Optional;

public class Model {

	Puzzle puzzle;
	int numMoves = 0;
	char selectedPiece;     // the selected piece.
	boolean gameOver;
	
	public Model () {
		
	}
	
	public Model clone() {
		Model m = new Model();
		m.setPuzzle(puzzle.clone());
		m.gameOver = gameOver;
		m.numMoves = numMoves;
		m.selectedPiece = selectedPiece;
		
		return m;
	}
	
	/** Reset to initial state. */
	public void resetPuzzle() {
		puzzle.reset();
		selectedPiece = '\0';
		numMoves = 0;
		gameOver = false;
	}
	
	/** Sets the puzzle to use. */
	public void setPuzzle(Puzzle p) { 
		numMoves = 0;
		this.puzzle = p; 
	}
	
	/** Retrieves puzzle. */
	public Puzzle getPuzzle() {
		return puzzle;
	}

	/** Set the selected piece. */
	public void selectPiece(char p) {
		selectedPiece = p; 
	}
	
	/** Get piece. */
	public Piece findPiece(char ch) {
		for (Piece p : puzzle) {
			if (p.label == ch) { return p; }
		}
		
		return null;
	}
	
	/** Return Optional of a selected piece. */
	public Optional<Piece> getSelectedPiece() { 
		if (selectedPiece == '\0') { return Optional.empty(); }
		Piece p = findPiece(selectedPiece);
		if (p == null) { return Optional.empty(); }
		
		return Optional.of(p);
	}
	
	/** Return available moves for selected piece. */
	public MoveType[] availableMoves() {
		// nothing selected? No moves.
		if (selectedPiece == '\0') { return new MoveType[0]; }
		return availableMoves(selectedPiece);
	}

	/** 
	 * Return available moves for given piece. 
	 * Only the winning piece can exit. BE SURE TO INCLUDE THIS!
	 */
	public MoveType[] availableMoves(char ch) {
		Piece p = findPiece(ch);
		// check available (width and height).
		Coordinate coord = p.getLocation();
		
		ArrayList<MoveType> moves = new ArrayList<>();
		
		// CHECK WIN CONDITION!
		if (p.isWinner) {
			if (p.getLocation().equals(puzzle.getWinLocation())) {
				moves.add(puzzle.getExitDirection());
			}
		}
		
		boolean available;
		if (coord.column > 0) {
			available = true;
			for (int r = 0; r < p.height; r++) {
				if (puzzle.isCovered(new Coordinate(coord.row + r, coord.column-1))) {
					available = false;
					break;
				}
			}
			if (available) { moves.add(MoveType.Left); };
		}
		
		if (coord.column + p.width < 4) {
			available = true;
			for (int r = 0; r < p.height; r++) {
				if (puzzle.isCovered(new Coordinate(coord.row + r, coord.column+p.width))) {
					available = false;
					break;
				}
			}
			if (available) { moves.add(MoveType.Right); };
		}
		
		if (coord.row > 0) {
			available = true;
			for (int c = 0; c < p.width; c++) {
				if (puzzle.isCovered(new Coordinate(coord.row-1, coord.column + c))) {
					available = false;
					break;
				}
			}
			if (available) { moves.add(MoveType.Up); };
		}
		
		if (coord.row + p.height < 5) {
			available = true;
			for (int c = 0; c < p.width; c++) {
				if (puzzle.isCovered(new Coordinate(coord.row+p.height, coord.column + c))) {
					available = false;
					break;
				}
			}
			if (available) { moves.add(MoveType.Down); };
		}
		// return as array
		return moves.toArray(new MoveType[0]);
	}
	
	/**
	 * Try to move in this direction.
	 * 
	 * note: unexpected feature interaction when UNDO is present
	 */
	public boolean tryMove(MoveType direction) {
		if (selectedPiece == '\0') { return false; }
		Piece p = findPiece(selectedPiece);
		
		// winning? Remove the piece
		if (isWinCondition(direction)) { 
			this.getPuzzle().remove(p);
			gameOver = true;
			return true;
		}
		
		MoveType[] available = availableMoves();
		for (MoveType m : available) {
			if (m == direction) {
				p.move(direction);
				numMoves++;	 
				return true; 
			}
		}
		
		return false;
	}

	/** Return the number of moves. */
	public int getMoveCount() {
		return numMoves;
	}

	/** Is the game over? */
	public void setGameOver(boolean b) {
		gameOver = b;
	}

	/** Determine if game has been won. */
	public boolean isGameOver() {
		return gameOver;
	}
	
	/** Determine if winning condition has been established. */
	public boolean isWinCondition(MoveType dir) { 
		if (selectedPiece == '\0') { return false; }
		Piece p = findPiece(selectedPiece);
		if (!p.isWinner) { return false; }
		Coordinate target = p.getLocation();
		
		return puzzle.isWinCondition(target.row, target.column, dir);
	}
	
	public String toString() {
		if (puzzle == null) { return ""; }
		return puzzle.toString();
	}
}
