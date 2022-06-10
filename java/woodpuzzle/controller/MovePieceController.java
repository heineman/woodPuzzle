package woodpuzzle.controller;

import woodpuzzle.boundary.SlidingPuzzleApp;
import woodpuzzle.model.Coordinate;
import woodpuzzle.model.Model;
import woodpuzzle.model.Move;
import woodpuzzle.model.MoveType;
import woodpuzzle.undo.UndoManager;

public class MovePieceController {

	Model model;
	SlidingPuzzleApp app;
	Coordinate win = new Coordinate(3, 1);
	
	public MovePieceController(SlidingPuzzleApp app, Model m) {
		this.app = app;
		this.model = m;
	}

	public boolean move(MoveType direction) {
		if (!model.getSelectedPiece().isPresent()) { return false; }
				
		// WINNING CONDITION. Not Easy to test!
		if (model.isWinCondition(direction)) {
			model.setGameOver(true);
			app.getSolutionArea().setText("");
			app.repaint();
			return true;
		}
		
		// model now auto increments move count
		Move m = new Move(model, direction); 
		if (m.execute()) {
			UndoManager.instance().recordCommand(m);
			app.getMovesLabel().setText("" + model.getMoveCount());
			UpdateButtons.enableButtons(app, model.availableMoves());
			
			// deal with solutions.
			String val = app.getSolutionArea().getText();
			if (val.length() > 5) {
				int idx = val.indexOf("\n"); // extract first move
			    String result = val.substring(idx+1);
			    app.getSolutionArea().setText(result);
			    app.getSolutionArea().setCaretPosition(0);   // go back to front
			}
			
			app.repaint();
		}
		
		return true;
	}
}
