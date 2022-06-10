package woodpuzzle.model;

public enum MoveType {
	Up(-1,0) {
		public String toString() { return "up"; }
	}, 
	Down(1,0) {
		public String toString() { return "down"; }
	},
	Left(0,-1) {
		public String toString() { return "left"; }
	},
	Right(0,1) {
		public String toString() { return "right"; }
	}, 

	None(0,0);   // no move is possible.

	final int deltaR;
	final int deltaC;

	MoveType(int dr, int dc) {
		this.deltaR = dr;
		this.deltaC = dc;
	}
}
