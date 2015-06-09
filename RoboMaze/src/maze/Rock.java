package maze;

public class Rock implements Obstacle {

	/**
	 * If a robot attempts to move onto a rock, it instead stays in its current room.
	 */
	public Room moveto(Robot robot) {
		System.out.println("A rock was present, and the robot could not move.");
		return robot.getLocation();
	}

}
