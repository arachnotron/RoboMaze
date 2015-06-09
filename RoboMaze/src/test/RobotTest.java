package test;

import static org.junit.Assert.*;

import java.io.IOException;

import maze.Grid;
import maze.Robot;

import org.junit.Test;

public class RobotTest {

	private Robot robby;

	public void init() {
		try {
			Grid.generateGrid(3, 3, "C://Users/C/Desktop/git_proj/RoboMaze/RoboMaze/src/test/obstacles-ex.txt");
		} catch (IOException e) {
			fail("Unable to load file.");
		} catch (IndexOutOfBoundsException e) {
			// loader catches this, plus we don't care about it
		}
		
		int[] coords = new int[2];
		coords[0] = 0; coords[1] = 0;
		
		robby = new Robot(Grid.getRoom(coords), 90);
	}
	
	public void initNoObs() {
		try {
			Grid.generateGrid(3, 3, "C://Users/C/Desktop/git_proj/RoboMaze/RoboMaze/src/test/obstacles-ex-none.txt");
		} catch (IOException e) {
			fail("Unable to load file.");
		} catch (IndexOutOfBoundsException e) {
			// loader catches this, plus we don't care about it
		}
		
		int[] coords = new int[2];
		coords[0] = 1; coords[1] = 1;
		
		robby = new Robot(Grid.getRoom(coords), 90);
	}
	
	@Test
	public void CanTurnLeft() {
		init();
		
		robby.turnLeft();
		assertEquals(robby.getFacing(), 180);
		robby.turnLeft();
		assertEquals(robby.getFacing(), 270);
		robby.turnLeft();
		assertEquals(robby.getFacing(), 0);
		robby.turnLeft();
		assertEquals(robby.getFacing(), 90);
	}
	
	@Test
	public void CanTurnRight() {
		init();
		
		robby.turnRight();
		assertEquals(robby.getFacing(), 0);
		robby.turnRight();
		assertEquals(robby.getFacing(), 270);
		robby.turnRight();
		assertEquals(robby.getFacing(), 180);
		robby.turnRight();
		assertEquals(robby.getFacing(), 90);
	}
	
	@Test
	public void CanMoveForward() {
		initNoObs();
		
		int[] coords = new int[2];
		coords[0] = 1; coords[1] = 2;
		
		// Facing north
		robby.moveForward();
		assertEquals(robby.getLocation(), Grid.getRoom(coords));
		
		coords[0] = 1; coords[1] = 1;
		robby.turnRight(); robby.turnRight();
		robby.moveForward();
		assertEquals(robby.getLocation(), Grid.getRoom(coords));
	}
	
	// Also tests ability to move forward when facing east
	@Test
	public void CanMoveRight() {
		initNoObs();
		
		int[] coords = new int[2];
		coords[0] = 2; coords[1] = 1;
		
		robby.moveRight();
		assertEquals(robby.getLocation(), Grid.getRoom(coords));
	}
	
	// Also tests ability to move forward when facing west
	@Test
	public void CanMoveLeft() {
		initNoObs();
		
		int[] coords = new int[2];
		coords[0] = 0; coords[1] = 1;
		
		robby.moveLeft();
		assertEquals(robby.getLocation(), Grid.getRoom(coords));
	}

	@Test
	public void CorrectRockBehavior() {
		init();
		
		robby.moveForward();
		int[] coords = new int[2];
		coords[0] = 0; coords[1] = 1; // robby's current location
		
		robby.moveRight();
		assertEquals(robby.getLocation(), Grid.getRoom(coords));
	}
	
	@Test
	public void CorrectSpinnerBehavior() {
		init();
		
		robby.moveForward();
		robby.moveForward();
		robby.moveRight();
		
		assertEquals(robby.getFacing(), 180);
	}
	
	@Test
	public void CorrectHoleBehavior() {
		init();
		
		robby.moveForward();
		robby.moveForward();
		robby.moveForward();
		robby.moveRight();
		
		int[] coords = new int[2];
		coords[0] = 0; coords[1] = 0;
		
		assertEquals(robby.getLocation(), Grid.getRoom(coords));
	}
}
