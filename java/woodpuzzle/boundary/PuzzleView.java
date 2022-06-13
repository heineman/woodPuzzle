package woodpuzzle.boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPanel;

import woodpuzzle.model.Coordinate;
import woodpuzzle.model.Model;
import woodpuzzle.model.MoveType;
import woodpuzzle.model.Piece;
import woodpuzzle.model.Puzzle;

/** 
 * Knows how to visually present the puzzle.
 *  
 */
public class PuzzleView extends JPanel {

	private static final long serialVersionUID = -5780076752366384800L;
	
	public final static int boxSize = 80;
	public final static int offset = 8;
	
	Model model;
	
	public PuzzleView(Model m) {
		this.model = m;
	}
	
	/** Map piece into rectangle in puzzle view. */
	public Rectangle computeRectangle (Piece p) {
		Coordinate c = p.getLocation();
		return new Rectangle(boxSize*c.column + offset, boxSize*c.row + offset, boxSize*p.width - 2*offset, boxSize*p.height - 2*offset);
	}

	/** Map a point to a given Coordinate in puzzle. */
	public Coordinate pointToCoordinate(Point pt) {
		return new Coordinate(pt.y/boxSize, pt.x/boxSize);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (model == null) { return; }
		
		Puzzle puzzle = model.getPuzzle();
		Piece selectedPiece = model.getSelectedPiece().orElse(null);
		
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
		for (Piece p : puzzle) {
			if (p.isWinner) {
				g.setColor(Color.red);
			} else {
				g.setColor(Color.lightGray);
			}
			
			if (p.equals(selectedPiece)) {
				g.setColor(Color.yellow);
			}
			
			Rectangle r = computeRectangle(p);
			g.fillRect(r.x, r.y, r.width, r.height);
			g.setColor(Color.black);
			g.drawString("" + p.label, r.x + r.width/2 - 10, r.y + r.height/2 + 10);
		}
		
		// draw board.
		g.setColor(Color.black);
		if (puzzle.getFinalMove() == MoveType.Left) {
			g.fillRect(0, 0, offset, puzzle.getExitStart()*boxSize);
			g.fillRect(0, (puzzle.getExitEnd()+1)*boxSize, offset, (puzzle.numRows - puzzle.getExitStart())*boxSize);
		} else {
			g.fillRect(0, 0, offset,  puzzle.numRows*boxSize);
		}
		
		if (puzzle.getFinalMove() == MoveType.Right) {
			g.fillRect(puzzle.numColumns*boxSize - offset, 0, offset, (puzzle.getExitStart())*boxSize);
		    g.fillRect(puzzle.numColumns*boxSize - offset, (puzzle.getExitEnd()+1)*boxSize, offset, (puzzle.numRows-puzzle.getExitEnd()-1)*boxSize);
		  } else {
		    g.fillRect(puzzle.numColumns*boxSize - offset, 0, offset, puzzle.numRows * boxSize);
		  }
		
		if (puzzle.getFinalMove() == MoveType.Down) {
		    g.fillRect(0, puzzle.numRows*boxSize, (puzzle.getExitEnd()-1)*boxSize, offset);
		    g.fillRect((puzzle.getExitEnd()+1)*boxSize, puzzle.numRows*boxSize, (puzzle.numColumns-puzzle.getExitEnd()-1)*boxSize, offset);
		  } else {
	        g.fillRect(0, puzzle.numRows*boxSize - offset, puzzle.numColumns*boxSize, offset);
		  }
		
		if (puzzle.getFinalMove() == MoveType.Up) {
		    g.fillRect(0, 0, (puzzle.getExitEnd()-1)*boxSize, offset);
		    g.fillRect((puzzle.getExitEnd()+1)*boxSize, 0, (puzzle.getExitEnd()-puzzle.getExitStart())*boxSize, offset);
		  } else {
	        g.fillRect(0, 0, puzzle.numColumns * boxSize, offset);
		  }

		if (model.isGameOver()) {
			g.setColor(Color.blue);
			g.drawString("Game Won!", 200, 200);
			g.drawString("Congratulations!", 200, 300);			 
		}
		
	}
}
