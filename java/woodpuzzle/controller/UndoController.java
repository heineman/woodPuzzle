package woodpuzzle.controller;

import woodpuzzle.boundary.SlidingPuzzleApp;
import woodpuzzle.model.Model;
import woodpuzzle.undo.ICommand;
import woodpuzzle.undo.UndoManager;

public class UndoController {

	Model model;
	SlidingPuzzleApp app;
	
	public UndoController(SlidingPuzzleApp app, Model m) {
		this.app = app;
		this.model = m;
	}

	public boolean undo() {
		UndoManager um = UndoManager.instance();
		ICommand com = um.removeLastMove();
		if (com == null) {
			return false;
		}

		if (!com.undo()) {
			return false;
		}
		
		app.getMovesLabel().setText("" + model.getMoveCount());
		UpdateButtons.enableButtons(app, model.availableMoves());
		app.repaint();
		return true;
	}
}
