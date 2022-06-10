package woodpuzzle.model;

import woodpuzzle.undo.ICommand;

public class Move implements ICommand {

	final Model model;
	final MoveType direction;
	char piece;

	public Move (Model m, MoveType dir) {
		this.model = m;
		this.direction = dir;
	}

	public static MoveType opposite (MoveType dir) {
		switch (dir) {
		case Left: return MoveType.Right;
		case Right: return MoveType.Left;
		case Up: return MoveType.Down;
		
		// last one is down
		default: return MoveType.Up;
		}
	}

	@Override
	public boolean execute() {
		if (!model.getSelectedPiece().isPresent()) {
			return false;
		}

		piece = model.getSelectedPiece().get().label;
		return model.tryMove(direction);
	}
 
	@Override
	public boolean undo() {
		model.selectPiece(piece);

		if (!model.tryMove(opposite(direction))) {
			return false;
		}
		
		// TODO: HACK CAREFUL! Decrements number of moves. Must do TWICE since tryMove actually increments moves.
		model.numMoves -= 2;
		return true;
	}

}
