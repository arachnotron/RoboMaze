package test;

import static org.junit.Assert.*;

import maze.Robot;
import maze.Room;

import org.junit.Test;

public class RoomTest {

	@Test
	public void CanMoveToEmpty() {
		int[] coords = new int[2];
		coords[0] = 0; coords[1] = 0;
		
		Room r1 = new Room(coords[0], coords[1]);
		Robot robby = new Robot(r1, 90);
		
		Room r2 = new Room(0, 1);
		assertEquals(r2, r2.moveto(robby));
	}
	
	@Test
	public void CannotMoveToRock() {
		int[] coords = new int[2];
		coords[0] = 0; coords[1] = 0;
		String[] obstacle = new String[1];
		obstacle[0] = "Rock";
		
		Room r1 = new Room(coords[0], coords[1]);
		Robot robby = new Robot(r1, 90);
		
		Room r2 = new Room(0, 1);
		r2.addObstacle(obstacle);
		assertEquals(r1, r2.moveto(robby));
	} // Hole, Spinner functionality tested in RobotTest so we know they work

}
