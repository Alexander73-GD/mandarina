public class Sudoku {
	public static void main(String[] args) {	
		int[][] grid = Methods.gridDeclaration();
		//bucle until there are no empty spaces left
		//if it ends with 0s on the grid, the given sudoku is incorrect
		boolean end = false;
		while (!end) {
			//tries to solve for each number once
			for (int i = 1; i <= 4; i++) {
				grid = Methods.solver(i, grid);
				System.out.println("Grid "+i);
				Methods.printGrid(grid);
			}
			//checks if there's 0s left and it needs to repeat the cycle
			//hypothesis: it only needs 1 or 2 cycles
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (grid[i][j] == 0) {
						end = false;
						break;
					} else {
						end = true;
					}
				}
			}
		}
		//solved sudoku
		Methods.printGrid(grid);
	}
}
