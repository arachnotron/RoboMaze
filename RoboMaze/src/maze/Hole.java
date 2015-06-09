package maze;

public class Hole implements Obstacle {

	private Room connected;
	
	/**
	 * Default constructor for hole. Connects hole to a room somewhere else on the grid.
	 * @param string
	 * @param string2
	 */
	public Hole(String string, String string2) {
		int X = Integer.parseInt(string);
		int Y = Integer.parseInt(string2);
		
		int[] coords = new int[2];
		coords[0] = X; coords[1] = Y;
		
		connected = Grid.getRoom(coords);
	}

	/**
	 * When a robot enters a hole, it moves to the connected room.
	 */
	public Room moveto(Robot robot) {
		int[] np = connected.getCoordinates();
		
		System.out.printf("Robot has entered a hole. Moving into new position, [%d, %d]\n",
				np[0], np[1]);
		
		return connected.moveto(robot);
	}

}
