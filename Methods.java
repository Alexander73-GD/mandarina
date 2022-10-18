public class Methods {	
	//prints a grid in a 4x4 format, useful for debugging
	public static void printGrid(int[][] grid) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(grid[i][j]);
			}
				System.out.print("\n");
		}
	}
	//sudoku puzzle to solve in a readable format
	public static int[][] gridDeclaration() {
		int[][] grid = {{0, 0, 0, 0},
						{1, 0, 3, 0},
						{0, 4, 0, 2},
						{0, 0, 0, 0}};
		return grid;
	}
	//checks if the number n is in a box
	public static boolean inBox(int[] box, int n) {
		for (int i : box) {
			if(i == n){
				return true;
			}
		}
		return false;
	}
	//checks in how many cells in a line the number can go
	//returns true if it can go only in 1 of them (and therefore it will go there)
	public static boolean inLine(int[][] options, int n) {
		int inLine = 0;
		for (int j = 0; j < 4; j++) {
			if (options[n][j] == 0) {
				inLine++;
			}
		}
		if (inLine == 1) {
			return true;
		} else {
			return false;
		}
	}
	//checks in how many cells in a column the number can go
	//returns true if it can go only in 1 of them (and therefore it will go there)
	public static boolean inColumn(int[][] options, int n) {
		int inColumn = 0;
		for (int i = 0; i < 4; i++) {
			if (options[i][n] == 0) {
				inColumn++;
			}
		}
		if (inColumn == 1) {
			return true;
		} else {
			return false;
		}
	}
	//makes a grid of possible cells where the number can go
	public static int[][] solver(int n, int[][] grid) {
		int[][] options = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
		int[] sq1 = {grid[0][0], grid[0][1], grid[1][0], grid[1][1]};
		int[] sq2 = {grid[0][2], grid[0][3], grid[1][2], grid[1][3]};
		int[] sq3 = {grid[2][0], grid[2][1], grid[3][0], grid[3][1]};
		int[] sq4 = {grid[2][2], grid[2][3], grid[3][2], grid[3][3]};
		
		//checks cells already occupied by other numbers
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (grid[i][j] != 0 && grid[i][j] != n) {
					options[i][j] = 9;
				}
			}
		}
		//if the number is in a box, it can't go anywhere else in the same box
		if (Methods.inBox(sq1, n)) {
			options[0][0] = options[0][1] = options[1][0] = options[1][1] = 9;
		}
		if (Methods.inBox(sq2, n)) {
			options[0][2] = options[0][3] = options[1][2] = options[1][3] = 9;
		}
		if (Methods.inBox(sq3, n)) {
			options[2][0] = options[2][1] = options[3][0] = options[3][1] = 9;
		}
		if (Methods.inBox(sq4, n)) {
			options[2][2] = options[2][3] = options[3][2] = options[3][3] = 9;
		}
		System.out.println("Options of "+n);
		Methods.printGrid(options); //to check the 0/9 table
		//if a number can go in a cell AND it's either the only possibility in a line OR column, it goes there
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if ((options[i][j] == 0 && Methods.inLine(options, i)) || (options[i][j] == 0 && Methods.inColumn(options, j))) {
					grid[i][j] = n;
				}
			}
		}
		return grid;
	}
}