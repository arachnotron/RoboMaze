package maze;

public class Room {

	private int xcoord;
	private int ycoord;
	private Obstacle obstacle = null;
	
	/**
	 * Default constructor for Room.
	 * @param	x	X-axis position.
	 * @param	y	Y-axis position.
	 */
	public Room(int x, int y) {
		xcoord = x;
		ycoord = y;
	}
	
	/**
	 * Returns the coordinates for the room.
	 * 
	 * @return	{x,y} coordinate array.
	 */
	public int[] getCoordinates() {
		int[] coords = new int[2];
		coords[0] = xcoord; coords[1] = ycoord;
		
		return coords;
	}

	/**
	 * Moves the robot into the room, rather than have the robot move itself.
	 * This allows us to trigger an obstacle without needing to expose it to
	 * the robot.
	 * 
	 * It really just returns itself to the robot, or the result of the obstacle.
	 * 
	 * @param robot
	 */
	public Room moveto(Robot robot) {
		Room newRoom = this;
		
		if (obstacle == null)
			return newRoom;
		
		newRoom = obstacle.moveto(robot);
		
		if (obstacle.getClass().equals(Spinner.class))
			newRoom = this; // I forgot about spinners.
		
		return newRoom;
	}

	/**
	 * Adds an obstacle to the room. With holes, we're given an X and Y
	 * connection point. With spinners, we're given a degree rotation.
	 * 
	 * Any unknown obstacle definition is by default a rock.
	 * 
	 * @param toAdd
	 */
	public void addObstacle(String[] toAdd) {
		if (toAdd[0].equalsIgnoreCase("spinner"))
			obstacle = new Spinner(toAdd[1]);
		else if (toAdd[0].equalsIgnoreCase("hole"))
			obstacle = new Hole(toAdd[1], toAdd[2]);
		else
			obstacle = new Rock();
	}

}
