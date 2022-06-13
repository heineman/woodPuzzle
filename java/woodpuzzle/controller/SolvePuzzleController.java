package woodpuzzle.controller;

import java.util.*;

import woodpuzzle.boundary.SlidingPuzzleApp;
import woodpuzzle.model.Model;
import woodpuzzle.model.Move;
import woodpuzzle.model.MoveType;
import woodpuzzle.model.Piece;
import woodpuzzle.model.Puzzle;

public class SolvePuzzleController {

	Model model;
	SlidingPuzzleApp app;
	
	public SolvePuzzleController(SlidingPuzzleApp app, Model m) {
		this.app = app;
		this.model = m;
	}
	
	class MyMove {
		final char piece;
		final MoveType direction;
		
		public MyMove (char ch, MoveType dir) {
			this.piece = ch;
			this.direction = dir;
		}
	}
	
	static Solver solvingThread  = null;
	static boolean stopSolver;
	
	class Solver extends Thread {
		
		class State {
			final Model model;
			final MyMove move;
			State previous;
			
			public State(Model mod, State previous, MyMove move) {
				this.model = mod;
				this.previous = previous;
				this.move = move;
			}
			
			public String toString() {
				return model.toString();
			}
		}
		
		@Override
		public void run() {
			Set<String> seen = new HashSet<>();
			Puzzle puz = model.getPuzzle().clone();
			Model mod = new Model();
			mod.setPuzzle(puz);
			seen.add(puz.key());
			
			Queue<State> queue = new LinkedList<>();
			queue.add(new State(mod, null, null));
			
			while (!queue.isEmpty()) {
				State s = queue.remove();
				
				// Choose all available moves
				for (Piece p : s.model.getPuzzle()) {
					MoveType[] dirs = s.model.availableMoves(p.label);
					for (MoveType dir : dirs) {
						Model copy = s.model.clone();
						
						copy.selectPiece(p.label);
						Move m = new Move(copy, dir);
						if (m.execute()) {
							// should always work
							State next = new State(copy, s, new MyMove(p.label, dir));
							
							if (copy.isGameOver()) {
								StringBuffer sb = new StringBuffer();
								
								while (next != null && next.move != null) {
									sb.insert(0, next.move.piece + " " + next.move.direction.toString() + "\n");
									next = next.previous;
								}
								
								app.getSolutionArea().setText(sb.toString());
								app.requestFocusInWindow();  // stay on frame.
								app.getSolveButton().setText("Solve");
								return;
							}
							
							String k1 = copy.getPuzzle().key();
							if (!seen.contains(k1)) {
								seen.add(k1);
								queue.add(next);
							}
						}
					}
				}
			}
		}
	}

	public void solve() {
		if (solvingThread == null) {
			app.getSolveButton().setText("Stop");
			stopSolver = false;
			solvingThread = new Solver();
			solvingThread.start();
		} else {
			stopSolver = true;
			solvingThread = null;
		}
	}
}
