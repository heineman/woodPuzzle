package woodpuzzle;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileReader;

import woodpuzzle.boundary.SlidingPuzzleApp;
import woodpuzzle.controller.ExitController;
import woodpuzzle.controller.UpdateButtons;
import woodpuzzle.model.Load;
import woodpuzzle.model.Model;
import woodpuzzle.model.Puzzle;

public class Main {
	public static void main(String args[]) {
		String toLoad = "puzzles" + java.io.File.separatorChar + "start.json";
		if (args.length > 0) {
			toLoad = args[0];
		}

		Puzzle p = null;
		try {
			p = Load.load(new FileReader(toLoad));
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Unable to load: " + toLoad);
			System.exit(0);;
		}
		
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
