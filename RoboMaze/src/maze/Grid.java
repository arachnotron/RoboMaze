package maze;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
		 * 	XVAL,YVAL,[Rock  Spinner.D  Hole.X.Y]\n
		 */
		File obs = new File(obstacles);
		BufferedReader obsReader = new BufferedReader(new FileReader(obs));
		
		while (obsReader.ready()) {
			String[] line = obsReader.readLine().trim().split(",");
			String[] obsline = line[2].split("\\.");
					
			int xcoord = Integer.parseInt(line[0]);
			int ycoord = Integer.parseInt(line[1]);
			System.out.printf("[%d,%d]\n", xcoord, ycoord);
			
			board[xcoord][ycoord].addObstacle(obsline);
		}
		
		obsReader.close();
	}
	
	/**
	 * Arguments for program:
	 * 
	 * RoboMaze W H File X Y
	 * 
	 * W = Width of Grid
	 * H = Height of Grid
	 * File = Obstacle File (Sample provided)
	 * X = Robot initial X // Defaults to 0
	 * Y = Robot initial Y // Defaults to 0
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Usage: RoboMaze W H File [X] [Y]");
			System.exit(1);
		}
		
		int width = Integer.parseInt(args[0]);
		int height = Integer.parseInt(args[1]);
		String obstaclefile = args[2];
		
		int xcoord = 0; int ycoord = 0;
		
		if (args.length == 5) {
			xcoord = Integer.parseInt(args[3]);
			ycoord = Integer.parseInt(args[4]);
			
			if (xcoord >= width || ycoord >= height || xcoord < 0 || ycoord < 0) {
				System.out.println("Invalid starting position for robot: Outside of grid.");
				System.exit(1);
			}
		}
		
		try {
			generateGrid(width, height, obstaclefile);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Certain obstacles attempted to place in out-of-bounds " 
					+ "rooms. They will be ignored.");
			e.printStackTrace();
		}
		
		int[] coords = new int[2];
		coords[0] = xcoord; coords[1] = ycoord;
		Robot robby = new Robot(getRoom(coords), 90);
		
		System.out.println("Insert command sequence one command at a time." +
				" Possible inputs: L - Move Left / R - Move Right / F - Move Forward");
		
		BufferedReader isr = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			while (true) {
				String ln = isr.readLine();
				
				if (ln.equals("f"))
					robby.moveForward();
				else if (ln.equals("l"))
					robby.moveLeft();
				else if (ln.equals("r"))
					robby.moveRight();
				else
					break;
			}
			
			isr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int[] finalpos = robby.getLocation().getCoordinates();
		System.out.printf("Final Robot position: [%d, %d]\n", finalpos[0], finalpos[1]);
	}
}
