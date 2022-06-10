package woodpuzzle.undo;

public interface ICommand {
	
	/** Execute given command. */
	boolean execute();
	
	/** Request undo. */
	boolean undo();
}
