package woodpuzzle;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import woodpuzzle.boundary.SlidingPuzzleApp;
import woodpuzzle.controller.ExitController;
import woodpuzzle.controller.UpdateButtons;
import woodpuzzle.model.Model;
import woodpuzzle.model.Parser;
import woodpuzzle.model.Puzzle;

// 012    in 82  + 1 to move out	 Open states:120	Closed states:23703		Num Total moves:77027
// 34     in 131 + 1 to move out	 Open states:369	Closed states:41700		Num Total moves:133635
// 56     in 179 + 1 to move out	 Open states:603	Closed states:38908		Num Total moves:124817
// 789    in 116 + 1 to move out     Open states:294	Closed states:42055		Num Total moves:136989
public class Main {
	public static void main(String args[]) {
		Puzzle p = Parser.parse(new File ("puzzles/start.puzzle")).get();
		Model m = new Model();
		m.setPuzzle(p);
		
		SlidingPuzzleApp app = new SlidingPuzzleApp(m);
		UpdateButtons.enableButtons(app);
		
		app.addWindowListener (new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				new ExitController(app).process();
			}

		});
		app.setVisible(true);		
	}
}
