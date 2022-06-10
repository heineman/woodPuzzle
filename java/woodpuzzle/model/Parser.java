package woodpuzzle.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Optional;

public class Parser {

	/**
	 * Return next line in the file, ignoring blank lines and comments
	 */
	static Optional<String> nextLine(BufferedReader br) throws IOException {
		while (br.ready()) {
			String s = br.readLine();
			if (s == null) { break; } 
			if (s.equals("") || s.startsWith("#")) { continue; } // ignore blank lines and comments

			return Optional.of(s);
		}
		
		return Optional.empty(); 
	}

	/**
	 * Determines size and exit criteria for Puzzle
	 */
	static Puzzle processHeader(BufferedReader br) throws IOException {
		String size = nextLine(br).get();
		String dimensions[] = size.split("\\s+");     // whitespace
		Puzzle p = new Puzzle(Integer.valueOf(dimensions[0]), Integer.valueOf(dimensions[1]));
		String exit = nextLine(br).get();
		String exits[] = exit.split("\\s+");          // whitespace

		p.setWinCondition(Integer.valueOf(exits[0]), Integer.valueOf(exits[1]), MoveType.valueOf(exits[2]));
		return p;
	}

	/**
	 * Pieces are Height Width InitialRow InitialCol lines.
	 * SpecialPiece has five values, with last one being SPECIAL
	 */
	static boolean processPieces(Puzzle puzzle, BufferedReader br) {
		try {
			while (br.ready()) {
				Optional<String> piece = nextLine(br);
				if (!piece.isPresent()) {
					return false;
				}
				String dimensions[] = piece.get().split("\\s+");     // whitespace
				boolean winner = false;
				if (dimensions.length == 6 && dimensions[5].equals("SPECIAL")) {
					winner = true;
				}
				Piece p = new Piece(Integer.valueOf(dimensions[1]), Integer.valueOf(dimensions[0]), winner, dimensions[4].charAt(0));
				if (!puzzle.placePiece(p, Integer.valueOf(dimensions[2]), Integer.valueOf(dimensions[3]))) {
					System.err.println("Invalid piece description:" + piece);
					return false;
				}
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}	
	
	public static Optional<Puzzle> parse(File f) {
		try {
			return parse(new InputStreamReader(new FileInputStream(f)));
		} catch (Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}
	
	/** Makes it easier to test this class. */
	static Optional<Puzzle> parse(Reader isr) {
		try {
			BufferedReader br = new BufferedReader(isr);
			Puzzle puzzle = processHeader(br);
			processPieces(puzzle, br);
			return Optional.of(puzzle);
		} catch (Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

}
