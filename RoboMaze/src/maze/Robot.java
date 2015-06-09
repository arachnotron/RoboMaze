package maze;

public class Robot {

	private Room location;
	private int facing;
	
	public int getFacing() {
		return facing;
	}
	
	public Robot(Room start, int direction) {
		location = start;
		facing = direction == 360 ? 0 : direction;
	}
	
	public void turnLeft() {
		if (facing == 270)
			facing = 0;
		else
			facing += 90;
	}
	
	public void turnRight() {
		if (facing == 0)
			facing = 270;
		else
			facing -= 90;
	}
	
	public void moveForward() {
		int[] coords = location.getCoordinates();
		System.out.printf("Robot moving into new position, [%d, %d]\n", coords[0], coords[1]);
		
		switch(facing/90) {
		case 0:
			coords[0] = coords[0]+1;
		case 1:
			coords[1] = coords[1]+1;
		case 2:
			coords[0] = coords[0]-1;
		case 3:
			coords[1] = coords[1]-1;
		}
			
		try {
			Room temp = Grid.getRoom(coords);
			Room newLocation = temp.moveto(this);

			location = newLocation;
			
		} catch (IndexOutOfBoundsException e) {
			// Don't move.
			System.out.println("Robot failed to move due to new position being outside of grid.");
		}
	}
	
	public void moveLeft() {
		turnLeft();
		moveForward();
		turnRight();
	}
	
	public void moveRight() {
		turnRight();
		moveForward();
		turnLeft();
	}
	
	public Room getLocation() {
		return location;
	}
}
