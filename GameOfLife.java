package bbc;
/*
 * Author: Victoria Cody
 * Title: Game of Life
 * 
*/

public class GameOfLife {

	// 2D Grid Dimensions (Grid is a square)
	private static final int GRID_LENGTH = 10;
	// The symbols printed to represent the grid
	private static final char DEAD_CELL = 'o';
	private static final char ALIVE_CELL = '●';
	// The pause between iterations
	private static final int WAIT = 500;

	// Set up the Grid for the previous iteration using Boolean
	private static Boolean[][] previousIteration = new Boolean[GRID_LENGTH][GRID_LENGTH];
	// Set up the Grid for the current iteration using Boolean
	private static Boolean[][] grid = new Boolean[GRID_LENGTH][GRID_LENGTH];

	// Method for printing the grid out
	public static void printGrid(Boolean[][] grid) {
		for (int i = 0; i < GRID_LENGTH; i++) {
			for (int j = 0; j < GRID_LENGTH; j++) {
				// If true set cell to DEAD if false set cell to ALIVE
				System.out.print(grid[i][j] ? DEAD_CELL : ALIVE_CELL);
				System.out.print(' ');
			}
			System.out.println();
		}
		System.out.println();
	}

	// Method for changing the cells according to the rules
	public static Boolean updateCell(int x, int y, Boolean[][] cell) {
		int neighbours = 0;
		// Calculate how many alive cell neighbours the cell has
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (x + i >= 0 && y + j >= 0 && x + i < GRID_LENGTH && y + j < GRID_LENGTH) {
					if (cell[x + i][y + j] == false && !(i == 0 && j == 0)) {
						neighbours++;
					}
				}
			}
		}
		// Scenario 1: Underpopulation
		if (cell[x][y] == false && neighbours < 2)
			return true;
		// Scenario 2: Overcrowding
		else if (cell[x][y] == false && neighbours > 3)
			return true;
		// Scenario 3: Survival
		else if (cell[x][y] == false && (neighbours == 2 || neighbours == 3))
			return false;
		// Scenario 4: Creation of Life
		else if (neighbours == 3 && cell[x][y] == true)
			return false;
		else
			return true;
	}

	public static void main(String[] args) throws InterruptedException {

		// Set all the cells to dead
		for (int i = 0; i < GRID_LENGTH; i++) {
			for (int j = 0; j < GRID_LENGTH; j++) {
				grid[i][j] = true;
			}
		}
		// Make some alive to begin with
		grid[4][6] = false;
		grid[5][6] = false;
		grid[5][7] = false;
		grid[6][4] = false;
		grid[6][3] = false;
		grid[3][4] = false;
		grid[5][5] = false;

		// Print the initial grid
		printGrid(grid);

		// Picked a point for it to stop as didn't want to have an infinite loop
		int maxIteration = 15;
		for (int z = 0; z < maxIteration; z++) {
			// Swap last to current
			for (int i = 0; i < GRID_LENGTH; i++) {
				for (int j = 0; j < GRID_LENGTH; j++) {
					previousIteration[i][j] = grid[i][j];
				}
			}
			// Change the cells based on the Scenario's
			for (int i = 0; i < GRID_LENGTH; i++) {
				for (int j = 0; j < GRID_LENGTH; j++) {
					grid[i][j] = updateCell(i, j, previousIteration);
				}
			}
			// Print out the new grid
			printGrid(grid);
			// Delay in between iterations
			Thread.sleep(WAIT);
		}
	}
}
