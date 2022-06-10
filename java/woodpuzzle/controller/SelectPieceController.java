package woodpuzzle.controller;

import java.awt.Point;
import java.awt.Rectangle;

import woodpuzzle.boundary.PuzzleView;
import woodpuzzle.boundary.SlidingPuzzleApp;
import woodpuzzle.model.Model;
import woodpuzzle.model.Piece;
import woodpuzzle.model.Puzzle;

public class SelectPieceController {

	Model model;
	SlidingPuzzleApp app;
	
	public SelectPieceController(SlidingPuzzleApp app, Model m) {
		this.app = app;
		this.model = m;
	}
	

	public boolean select(Point point) {
		PuzzleView pv = app.getPuzzleView();
		Puzzle puzzle = model.getPuzzle();
		
		for (Piece p : puzzle) {
			Rectangle r = pv.computeRectangle(p);
			if (r.contains(point)) {
				// select this piece!
				model.selectPiece(p.label);
				
				UpdateButtons.enableButtons(app, model.availableMoves());
				pv.repaint();
				return true;
			}
		}
		
		return false;
	}
}
