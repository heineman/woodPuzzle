package woodpuzzle;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileReader;
import java.io.File;

import woodpuzzle.boundary.SlidingPuzzleApp;
import woodpuzzle.controller.ExitController;
import woodpuzzle.controller.UpdateButtons;
import woodpuzzle.model.Model;
import woodpuzzle.model.Parser;
import woodpuzzle.model.Puzzle;

import com.eclipsesource.json.*;  // needed for JSon

public class Main {
	public static void main(String args[]) {
	    if (args.length > 0) {
		try {
		    JsonValue puzzleJson = Json.parse(new FileReader(args[0]));
		} catch (Exception e) {
		    System.err.println("Unable to process:" + args[0]);
		    System.exit(0);
		}
	    }

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
