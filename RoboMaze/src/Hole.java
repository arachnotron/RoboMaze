
public class Hole implements Obstacle {

	private Room connected;
	
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
		return connected.moveto(robot);
	}

}
