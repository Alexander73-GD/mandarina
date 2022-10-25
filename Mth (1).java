public class Mth {
	//test sudoku solved for reference
//	123456789
//	456789123
//	789123456
//	912345678
//	345678912
//	678912345
//	891234567
//	234567891
//	567891234
	
	//the single digit to prime conversion/bitmap used goes like this: d(1,2,3,4,5,6,7,8,9) -> p(2,3,5,7,11,13,17,19,23)
	//when I say known cells, it means cells which number it's there
	//when I say unknown cells, it means cells that have a combination of possible digit numbers written as a multiplication of prime numbers
	public static int[][] gridDeclaration() {
		//test sudoku made by myself
//		int[][] grid = {{1, 0, 0, 4, 0, 0, 7, 0, 0}, 
//						{0, 5, 0, 0, 8, 0, 0, 2, 0}, 
//						{0, 0, 9, 0, 0, 3, 0, 0, 6}, 
//						{0, 1, 0, 0, 4, 0, 0, 7, 0}, 
//						{0, 0, 5, 0, 0, 8, 0, 0, 2}, 
//						{6, 0, 0, 9, 0, 0, 3, 0, 0}, 
//						{0, 0, 1, 0, 0, 4, 0, 0, 7}, 
//						{2, 0, 0, 5, 0, 0, 8, 0, 0}, 
//						{0, 6, 0, 0, 9, 0, 0, 3, 0}};
		//easy sudoku
		int[][] grid = {{0, 0, 3, 0, 0, 0, 2, 0, 0}, 
						{0, 6, 0, 9, 8, 0, 0, 4, 3}, 
						{4, 9, 0, 0, 3, 1, 0, 0, 6}, 
						{9, 0, 7, 0, 0, 0, 8, 6, 0}, 
						{0, 4, 0, 0, 9, 8, 0, 0, 0}, 
						{0, 0, 5, 4, 0, 7, 1, 0, 9}, 
						{6, 0, 0, 0, 0, 3, 9, 0, 5}, 
						{5, 0, 8, 1, 0, 0, 0, 7, 2}, 
						{2, 0, 9, 0, 5, 6, 0, 3, 8}};
		return grid;
	}
	public static void printDgrid(int[][] grid) { //prints normal sudoku with 1-9 digits
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
					System.out.print(grid[i][j]);
				if (j == 2 || j == 5) {
					System.out.print("|");
				}
			}
			if (i == 2 || i == 5) {
				System.out.print("\n———————————");
			}
			System.out.print("\n");
		}
	}
	public static void printPgrid(int[][] grid) { //prints sudoku with possible answers using a prime number multiplications
		String str = "—";
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (grid[i][j] < 100000) { //to improve readability a little bit
					System.out.print(" "+grid[i][j]+" ");
				} else {
					System.out.print(grid[i][j]);
				}
				if (j == 2 || j == 5) {
					System.out.print(" | ");
				} else if (j != 8) {
					System.out.print(" , ");
				}
			}
			if (i == 2 || i == 5) {
				System.out.print("\n"+str.repeat(73));
			}
			System.out.print("\n");
		}
	}
	
	public static int[][] toPrime(int[][] dgrid){ //passes digit sudoku to prime one with all 0s as 223092870 (product of the first 9 primes)
		int[][] pgrid = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				switch(dgrid[i][j]) { //Monday morning code
					case 0:
						pgrid[i][j] = 223092870;
						break;
					case 1:
					    pgrid[i][j] = 2;
					    break;
					case 2:
					    pgrid[i][j] = 3;
					    break;
					case 3:
					    pgrid[i][j] = 5;
					    break;
					case 4:
					    pgrid[i][j] = 7;
					    break;
					case 5:
					    pgrid[i][j] = 11;
					    break;
					case 6:
					    pgrid[i][j] = 13;
					    break;
					case 7:
					    pgrid[i][j] = 17;
					    break;
					case 8:
					    pgrid[i][j] = 19;
					    break;
					case 9:
					    pgrid[i][j] = 23;
					    break;
					default:
						dgrid[i][j] = 0;
						break;
				}
			}
		}
		return pgrid;
	}
	public static int[][] toDigit(int[][] pgrid){ //passes primes sudoku to normal digit one
		int[][] dgrid = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				switch(pgrid[i][j]) { //Monday morning code 2
					case 2:
					    dgrid[i][j] = 1;
					    break;
					case 3:
					    dgrid[i][j] = 2;
					    break;
					case 5:
					    dgrid[i][j] = 3;
					    break;
					case 7:
					    dgrid[i][j] = 4;
					    break;
					case 11:
					    dgrid[i][j] = 5;
					    break;
					case 13:
					    dgrid[i][j] = 6;
					    break;
					case 17:
					    dgrid[i][j] = 7;
					    break;
					case 19:
					    dgrid[i][j] = 8;
					    break;
					case 23:
					    dgrid[i][j] = 9;
					    break;	
					default:
						dgrid[i][j] = 0;
						break;
				}
			}
		}
		return dgrid;
	}
	
	public static int[][] line(int[][] pgrid) { //removes numbers that already are in the same line from unknown cells
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (Mth.isPrime(pgrid[i][j])) {
					for (int k = 0; k < 9; k++) {							
						if (!Mth.isPrime(pgrid[i][k]) && pgrid[i][k]%pgrid[i][j] == 0) {
							pgrid[i][k] /= pgrid[i][j];
						}
					}
				}
			}
		}
		return pgrid;
	}
	public static int[][] column(int[][] pgrid) { //removes numbers that already are in the same column from unknown cells
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (Mth.isPrime(pgrid[i][j])) {
					for (int k = 0; k < 9; k++) {							
						if (!Mth.isPrime(pgrid[k][j]) && pgrid[k][j]%pgrid[i][j] == 0) {
							pgrid[k][j] /= pgrid[i][j];
						}
					}
				}
			}
		}
		return pgrid;
	}
	public static int[][] box(int[][] pgrid){ //removes numbers that already are in the same box from unknown cells
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (Mth.isPrime(pgrid[i][j])) { //Tuesday morning code
					int x = inBox(i, j);
					if (!Mth.isPrime(pgrid[(x-1)/3*3][(x-1)%3*3]) && pgrid[(x-1)/3*3][(x-1)%3*3] % pgrid[i][j] == 0) {
						pgrid[(x-1)/3*3][(x-1)%3*3] /= pgrid[i][j];
					}
					if (!Mth.isPrime(pgrid[(x-1)/3*3][(x-1)%3*3+1]) && pgrid[(x-1)/3*3][(x-1)%3*3+1] % pgrid[i][j] == 0) {
						pgrid[(x-1)/3*3][(x-1)%3*3+1] /= pgrid[i][j];
					}
					if (!Mth.isPrime(pgrid[(x-1)/3*3][(x-1)%3*3+2]) && pgrid[(x-1)/3*3][(x-1)%3*3+2] % pgrid[i][j] == 0) {
						pgrid[(x-1)/3*3][(x-1)%3*3+2] /= pgrid[i][j];
					}
					if (!Mth.isPrime(pgrid[(x-1)/3*3+1][(x-1)%3*3]) && pgrid[(x-1)/3*3+1][(x-1)%3*3] % pgrid[i][j] == 0) {
						pgrid[(x-1)/3*3+1][(x-1)%3*3] /= pgrid[i][j];
					}
					if (!Mth.isPrime(pgrid[(x-1)/3*3+1][(x-1)%3*3+1]) && pgrid[(x-1)/3*3+1][(x-1)%3*3+1] % pgrid[i][j] == 0) {
						pgrid[(x-1)/3*3+1][(x-1)%3*3+1] /= pgrid[i][j];
					}
					if (!Mth.isPrime(pgrid[(x-1)/3*3+1][(x-1)%3*3+2]) && pgrid[(x-1)/3*3+1][(x-1)%3*3+2] % pgrid[i][j] == 0) {
						pgrid[(x-1)/3*3+1][(x-1)%3*3+2] /= pgrid[i][j];
					}		
					if (!Mth.isPrime(pgrid[(x-1)/3*3+2][(x-1)%3*3]) && pgrid[(x-1)/3*3+2][(x-1)%3*3] % pgrid[i][j] == 0) {
						pgrid[(x-1)/3*3+2][(x-1)%3*3] /= pgrid[i][j];
					}
					if (!Mth.isPrime(pgrid[(x-1)/3*3+2][(x-1)%3*3+1]) && pgrid[(x-1)/3*3+2][(x-1)%3*3+1] % pgrid[i][j] == 0) {
						pgrid[(x-1)/3*3+2][(x-1)%3*3+1] /= pgrid[i][j];
					}
					if (!Mth.isPrime(pgrid[(x-1)/3*3+2][(x-1)%3*3+2]) && pgrid[(x-1)/3*3+2][(x-1)%3*3+2] % pgrid[i][j] == 0) {
						pgrid[(x-1)/3*3+2][(x-1)%3*3+2] /= pgrid[i][j];
					}		
				}
			}
		}
		return pgrid;
	}
	
	public static int inBox(int j, int i) { //checks in which box is a certain cell, labelled arbitrarily from 1 to 9, left to right, up to down
		int x = 0;
		if (i <= 2 && j <= 2) {
			x = 1;
		} else if (i <= 5 && j <= 2) {
			x = 2;
		} else if (i <= 8 && j <= 2) {
			x = 3;
		} else if (i <= 2 && j <= 5) {
			x = 4;
		} else if (i <= 5 && j <= 5) {
			x = 5;
		} else if (i <= 8 && j <= 5) {
			x = 6;
		} else if (i <= 2 && j <= 8) {
			x = 7;
		} else if (i <= 5 && j <= 8) {
			x = 8;
		} else if (i <= 8 && j <= 8) {
			x = 9;
		}
		return x;
	}

	public static boolean isPrime(int p) { //stolen from the internet because I didn't want to think, it's pretty self-explanatory
		for (int i = 2; i <= p/2; i++) {
		      if (p % i == 0) {
		    	  return false;
		      }
		}
		return true;
	}
}