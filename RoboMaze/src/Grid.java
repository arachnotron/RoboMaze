import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Grid {

	/**
	 * Array representation of navigable grid.
	 */
	private static Room[][] board;
	
	/**
	 * Returns the room at the given coordinates.
	 * 
	 * @param	coords
	 * @return
	 */
	public static Room getRoom(int[] coords) {
		return board[coords[0]][coords[1]];
	}
	
	/**
	 * Generates the grid for the robot to navigate. Initializes its size and fills it with empty rooms,
	 * then adds obstacles to the rooms.
	 * 
	 * @param	x			Width of grid
	 * @param	y			Height of grid
	 * @param 	obstacles	Path to text file to read in obstacles.
	 * @throws IOException 
	 */
	public static void generateGrid(int x, int y, String obstacles) throws IOException {
		board = new Room[x][y];
		
		for (int X = 0; X < x; X++) {
			for (int Y = 0; Y < y; Y++) {
				board[X][Y] = new Room(X, Y);
			}
		}
		
		/*
		 * Format of obstacle file:
		 * 	XVAL,YVAL,[Rock.  Spinner.D  Hole.X.Y]\n
		 */
		File obs = new File(obstacles);
		BufferedReader obsReader = new BufferedReader(new FileReader(obs));
		
		while (obsReader.ready()) {
			String[] line = obsReader.readLine().trim().split(",");
			board[Integer.parseInt(line[0])][Integer.parseInt(line[1])].addObstacle(line[2].split("."));
		}
		
		obsReader.close();
	}
	
}
