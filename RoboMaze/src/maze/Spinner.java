package maze;

public class Spinner implements Obstacle {

	/**
	 * Degree value of the rotation. Always multiples of 90.
	 */
	private int spin;
	
	/**
	 * Default constructor.
	 * @param string
	 */
	public Spinner(String string) {
		spin = Integer.parseInt(string);
	}

	/**
	 * When a robot moves onto a spinner, it will spin by some specified amount.
	 * This is either a positive degree value, in which case the robot will turn
	 * left, or a negative degree value, and the robot will turn right.
	 */
	public Room moveto(Robot robot) {
		if (spin > 0) {
			for (int i = 0; i < spin/90; i++) {
				robot.turnLeft();
			}
		} else {
			for (int i = 0; i > spin/90; i--) {
				robot.turnRight();
			}
		}
		
		return null;
	}

}
