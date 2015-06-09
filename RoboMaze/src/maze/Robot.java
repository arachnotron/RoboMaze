package maze;

public class Robot {

	private Room location;
	private int facing;
	
	/**
	 * Gets current direction of robot. Used in unit tests, nowhere else.
	 * @return
	 */
	public int getFacing() {
		return facing;
	}
	
	/**
	 * Default constructor for robot. Gives it a starting room and a direction.
	 * @param start
	 * @param direction
	 */
	public Robot(Room start, int direction) {
		location = start;
		
		if (direction == 360)
			direction = 0;
		
		facing = direction;
	}
	
	/**
	 * Turns robot by 90 degrees.
	 */
	public void turnLeft() {
		if (facing == 270)
			facing = 0;
		else
			facing += 90;
	}
	
	/**
	 * Turns robot by -90 degrees.
	 */
	public void turnRight() {
		if (facing == 0)
			facing = 270;
		else
			facing -= 90;
	}
	
	/**
	 * Moves robot to the next room in the direction it is facing.
	 */
	public void moveForward() {
		int[] coords = location.getCoordinates();
		System.out.printf("[%d,%d] -> ", coords[0], coords[1]);
		
		if (facing == 0)
			coords[0] += 1;
		else if (facing == 90)
			coords[1] += 1;
		else if (facing == 180)
			coords[0] -= 1;
		else if (facing == 270)
			coords[1] -= 1;
		
		//System.out.printf("Robot moving into new position, [%d, %d]\n", coords[0], coords[1]);
			
		try {
			Room temp = Grid.getRoom(coords);
			Room newLocation = temp.moveto(this);

			location = newLocation;
			
		} catch (IndexOutOfBoundsException e) {
			// Don't move.
			//System.out.println("Robot failed to move due to new position being outside of grid.");
		}
		
		coords = location.getCoordinates();
		System.out.printf("[%d,%d]\n", coords[0], coords[1]);
	}
	
	/**
	 * Moves robot to the left of its current room.
	 */
	public void moveLeft() {
		turnLeft();
		moveForward();
		turnRight();
	}
	
	/**
	 * Moves robot to the right of its current room.
	 */
	public void moveRight() {
		turnRight();
		moveForward();
		turnLeft();
	}
	
	/**
	 * Retrieves the current location of the robot.
	 * @return a room
	 */
	public Room getLocation() {
		return location;
	}
}
