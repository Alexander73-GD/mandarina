import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Methods {
	
	public static void printGrid(int[][] grid) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(grid[i][j]);
			}
				System.out.print("\n");
		}
	}

	public static int[][] gridDeclaration() {
		int f1[] = {0, 0, 0, 0};
		int f2[] = {1, 0, 2, 0};
		int f3[] = {0, 4, 0, 3};
		int f4[] = {0, 0, 0, 0};
		int[][] grid = {f1, f2, f3, f4};
		System.out.println("Initial grid");
		Methods.printGrid(grid);
		return grid;
	}
	
	public static int[] foundNumbers(int[][] grid, int c0, int c1, int c2, int c3, int c4) {
		System.out.println("Found numbers");
		for (int n = 1; n <= 4; n++) {		
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (n == grid[i][j]) {
						System.out.println(n+" found in ("+(j+1)+","+(i+1)+")");
						switch(n) {
						case 1:
					 		c1++;
					 		break;
						case 2:
					 		c2++;
					 		break;
						case 3:
					 		c3++;
					 		break;
						case 4:
					 		c4++;
					 		break;
					 	}
					}
				}
			}		
		}
		c0 = c0 - c1 - c2 - c3 - c4;
		int[] cArray = {c0, c1, c2, c3, c4};
		System.out.println("0="+c0+" 1="+c1+" 2="+c2+" 3="+c3+" 4="+c4);
		return cArray;
	}
	
	public static void squaresInfo(int[][] grid) {
		System.out.println("squaresInfo");
		int[] sq1 = {grid[0][0], grid[0][1], grid[1][0], grid[1][1]};
		int[] sq2 = {grid[0][2], grid[0][3], grid[1][2], grid[1][3]};
		int[] sq3 = {grid[2][0], grid[2][1], grid[3][0], grid[3][1]};
		int[] sq4 = {grid[2][2], grid[2][3], grid[3][2], grid[3][3]};
		System.out.println(Arrays.toString(sq1)+Arrays.toString(sq2)+Arrays.toString(sq3)+Arrays.toString(sq4));
	}
	
	public static boolean inSquare(int[] sq, int n) {
		for (int i : sq) {
			if(i == n){
				return true;
			}
		}
		return false;
	}
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
	
	public static int[][] fill(int a, int[][] grid) {
		int[][] options = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
		//Methods.printGrid(options);
		int[] sq1 = {grid[0][0], grid[0][1], grid[1][0], grid[1][1]};
		int[] sq2 = {grid[0][2], grid[0][3], grid[1][2], grid[1][3]};
		int[] sq3 = {grid[2][0], grid[2][1], grid[3][0], grid[3][1]};
		int[] sq4 = {grid[2][2], grid[2][3], grid[3][2], grid[3][3]};
		
		List<Integer> fileof1 = new ArrayList<>();
		List<Integer> columnof1 = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			if (grid[i][0] == a || grid[i][1] == a || grid[i][2] == a || grid[i][3] == a) {
				//System.out.println("file "+(i+1)+" contains 1");
				fileof1.add(i);
			}
		}
		for (int j = 0; j < 4; j++) {
			if (grid[0][j] == a || grid[1][j] == a || grid[2][j] == a || grid[3][j] == a) {
				//System.out.println("column "+(j+1)+" contains 1");
				columnof1.add(j);
			}
		}
		//System.out.println(file1+" "+column1); //1 already is in these ones:
		for (int i = 0; i < 4; i++) {
			if (fileof1.contains(i)) {
				options[i][0] = 9;
				options[i][1] = 9;
				options[i][2] = 9;
				options[i][3] = 9;
			}
		}
		for (int j = 0; j < 4; j++) {
			if (columnof1.contains(j)) {
				options[0][j] = 9;
				options[1][j] = 9;
				options[2][j] = 9;
				options[3][j] = 9;
			}
		}
		
		//System.out.println("sq are "+Arrays.toString(sq1)+Arrays.toString(sq2)+Arrays.toString(sq3)+Arrays.toString(sq4));
		if (Methods.inSquare(sq1, a)) {
			options[0][0] = 9;
			options[0][1] = 9;
			options[1][0] = 9;
			options[1][1] = 9;
		}
		if (Methods.inSquare(sq2, a)) {
			options[0][2] = 9;
			options[0][3] = 9;
			options[1][2] = 9;
			options[1][3] = 9;
		}
		if (Methods.inSquare(sq3, a)) {
			options[2][0] = 9;
			options[2][1] = 9;
			options[3][0] = 9;
			options[3][1] = 9;
		}
		if (Methods.inSquare(sq4, a)) {
			options[2][2] = 9;
			options[2][3] = 9;
			options[3][3] = 9;
			options[3][3] = 9;
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (grid[i][j] != 0 && grid[i][j] != a) {
					options[i][j] = 9;
				}
			}
		}
		//check if repeated 0 in line
		for (int n = 0; n < 4; n++) {
			
			if (Methods.inColumn(options, n)){
				System.out.println("only 1 in column "+n);
			}
			
		}
		
		//number is assigned to every 0 in options
		System.out.println("options "+a+" is");
		Methods.printGrid(options);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if ((options[i][j] == 0 && Methods.inLine(options, i)) || (options[i][j] == 0 && Methods.inColumn(options, j))) {
					grid[i][j] = a;
				}
			}
		}
		System.out.println("FINAL "+a);
		return grid;
	}
}

//0401      3421      0401      0421      3421      3421
//0000      1243      1000      1200      1200      1200
//2000      2314      2010      2010      2310      2314
//0100      4132      0100      0100      0100      4100