package woodpuzzle.controller;

import woodpuzzle.boundary.SlidingPuzzleApp;
import woodpuzzle.model.MoveType;

public class UpdateButtons {
	
	/**
	 * Given any number of directions (variable arguments) enable only those in this list.
	 * 
	 */
	public static void enableButtons(SlidingPuzzleApp app, MoveType... dirs) {
		app.getUpButton().setEnabled(false);
		app.getDownButton().setEnabled(false);
		app.getLeftButton().setEnabled(false);
		app.getRightButton().setEnabled(false);
		
		for (MoveType d : dirs) {
			switch (d) {
			case Left:
				app.getLeftButton().setEnabled(true);
				break;
			case Right:
				app.getRightButton().setEnabled(true);
				break;
			case Up:
				app.getUpButton().setEnabled(true);
				break;
			case Down:
				app.getDownButton().setEnabled(true);
				break;
			case None:
				break;
			}
		}
	}
}
