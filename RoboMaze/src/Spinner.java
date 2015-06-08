
public class Spinner implements Obstacle {

	private int spin;
	
	public Spinner(String string) {
		spin = Integer.parseInt(string);
	}

	public Room moveto(Robot robot) {
		for (int i = 0; i < spin/90; i++) {
			robot.turnLeft();
		}
		
		return null;
	}

}
