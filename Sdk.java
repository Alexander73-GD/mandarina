public class Sdk {
	public static void main(String[] args) {
		int[][] dgrid = Mth.gridDeclaration();
		int[][] pgrid = Mth.toPrime(dgrid);
		boolean solved = true;
		int iterations = 0;

	
		for (int n = 1; n < 50; n++) {
			pgrid = Mth.SimpleElimination(pgrid);
			pgrid = Mth.NakedPairs(pgrid);
			//pgrid = Mth.PointingPairs(pgrid); //this breaks something
			pgrid = Mth.TwoStringKite(pgrid);	
		}
	
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!Mth.isPrime(pgrid[i][j])) {
					solved = false;
				}				
			}
		}
		if (solved) {
			//sometimes it will output a solved sudoku even if the initial grid was invalid
			System.out.println("Iterations needed: "+iterations); //this is a lie
			dgrid = Mth.toDigit(pgrid);
			Mth.printDgrid(dgrid);
		} else {
			System.out.println("This sudoku is either invalid or too hard for this program.");
			Mth.printPgrid(pgrid);
			int left = 0;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (!Mth.isPrime(pgrid[i][j])) {
						left++;
					}
				}
			}
			System.out.print("There's "+left+" cells left");
		}
	}
}