public class Sudoku {
		public static void main(String[] args) {	
		int[][] grid = Methods.gridDeclaration();
		int c0 = 16;
		while (c0 > 0) {
			for (int i = 1; i <=4; i++) {
				grid = Methods.fill(i, grid);
			}
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (grid[i][j] != 0) {
						c0--;
					}
				}
			}
		}	
		Methods.printGrid(grid);
	}
}

//{0, 4, 0, 1};     3421
//{0, 0, 0, 0};     1243
//{2, 0, 0, 0};     2314
//{0, 1, 0, 0};     4132

//for (int i = 0; i < 4; i++) {
//	for (int j = 0; j < 4; j++) {
//		
//	}
//}




//List<Integer> fileof1 = new ArrayList<>();
//List<Integer> columnof1 = new ArrayList<>();
//for (int i = 0; i < 4; i++) {
//	if (grid[i][0] == 1 || grid[i][1] == 1 || grid[i][2] == 1 || grid[i][3] == 1) {
//		System.out.println("file "+(i+1)+" contains 1");
//		fileof1.add(i);
//	}
//}
//for (int j = 0; j < 4; j++) {
//	if (grid[0][j] == 1 || grid[1][j] == 1 || grid[2][j] == 1 || grid[3][j] == 1) {
//		System.out.println("column "+(j+1)+" contains 1");
//		columnof1.add(j);
//	}
//}
//int f1size = fileof1.size(), c1size = columnof1.size();
////System.out.println(file1+" "+column1); //1 already is in these ones:
//for (int i = 0; i < 4; i++) {
//	if (fileof1.contains(i)) {
//		options[i][0] = 9;
//		options[i][1] = 9;
//		options[i][2] = 9;
//		options[i][3] = 9;
//	}
//}
//for (int j = 0; j < 4; j++) {
//	if (columnof1.contains(j)) {
//		options[0][j] = 9;
//		options[1][j] = 9;
//		options[2][j] = 9;
//		options[3][j] = 9;
//	}
//}
//
//System.out.println("sq are "+Arrays.toString(sq1)+Arrays.toString(sq2)+Arrays.toString(sq3)+Arrays.toString(sq4));
//if (Methods.inSquare(sq1, 1)) {
//	options[0][0] = 9;
//	options[0][1] = 9;
//	options[1][0] = 9;
//	options[1][1] = 9;
//}
//if (Methods.inSquare(sq2, 1)) {
//	options[0][2] = 9;
//	options[0][3] = 9;
//	options[1][2] = 9;
//	options[1][3] = 9;
//}
//if (Methods.inSquare(sq3, 1)) {
//	options[2][0] = 9;
//	options[2][1] = 9;
//	options[3][0] = 9;
//	options[3][1] = 9;
//}
//if (Methods.inSquare(sq4, 1)) {
//	options[2][2] = 9;
//	options[2][3] = 9;
//	options[3][3] = 9;
//	options[3][3] = 9;
//}
//for (int i = 0; i < 4; i++) {
//	for (int j = 0; j < 4; j++) {
//		if (grid[i][j] != 0 && grid[i][j] != 1) {
//			options[i][j] = 9;
//		}
//	}
//}
//
//
//
////all 0s are 1
//Methods.printGrid(options);
//for (int i = 0; i < 4; i++) {
//	for (int j = 0; j < 4; j++) {
//		if (options[i][j] == 0) {
//			grid[i][j] = 1;
//		}
//	}
//}}