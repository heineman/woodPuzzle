package woodpuzzle.controller;

import woodpuzzle.boundary.SlidingPuzzleApp;
import woodpuzzle.model.Model;
import woodpuzzle.undo.UndoManager;

public class ResetPuzzleController {

	Model model;
	SlidingPuzzleApp app;
	
	public ResetPuzzleController(SlidingPuzzleApp app, Model m) {
		this.app = app;
		this.model = m;
	}

	public void reset() {
		model.resetPuzzle();
		app.getMovesLabel().setText("" + model.getMoveCount());
		
		// must be sure to 'drain' all past moves
		UndoManager um = UndoManager.instance();
		um.clearState();
		
		UpdateButtons.enableButtons(app);
		app.repaint();
	}
}
