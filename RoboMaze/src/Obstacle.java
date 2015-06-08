
public interface Obstacle {

	/**
	 * When a robot enters a room with an obstacle, this function is
	 * called to achieve the desired effect for the implementing class.
	 * 
	 * @param robot
	 * @return
	 */
	public Room moveto(Robot robot);
	
}
