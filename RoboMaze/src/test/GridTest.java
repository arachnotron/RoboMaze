package test;

import static org.junit.Assert.*;

import java.io.IOException;

import maze.Grid;

import org.junit.Test;

public class GridTest {

	@Test
	public void GenerateSucceeds() {
		try {
			Grid.generateGrid(3, 3, "C://Users/C/Desktop/git_proj/RoboMaze/RoboMaze/src/test/obstacles-ex.txt");
		} catch (IOException e) {
			fail("Unable to load file.");
		} catch (IndexOutOfBoundsException e) {
			fail("Obstacles out of bounds");
		}
		
		int[] coords = new int[2];
		coords[0] = 0; coords[1] = 0;
		assertNotNull(Grid.getRoom(coords));
		coords[0] = 1; coords[1] = 1;
		assertNotNull(Grid.getRoom(coords));
		coords[0] = 0; coords[1] = 1;
		assertNotNull(Grid.getRoom(coords));
		coords[0] = 1; coords[1] = 0;
		assertNotNull(Grid.getRoom(coords));
		coords[0] = 2; coords[1] = 0;
		assertNotNull(Grid.getRoom(coords));
		coords[0] = 0; coords[1] = 2;
		assertNotNull(Grid.getRoom(coords));
		coords[0] = 2; coords[1] = 1;
		assertNotNull(Grid.getRoom(coords));
		coords[0] = 1; coords[1] = 2;
		assertNotNull(Grid.getRoom(coords));
		coords[0] = 2; coords[1] = 2;
		assertNotNull(Grid.getRoom(coords));
	}

}
