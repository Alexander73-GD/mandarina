public class Sdk {
	public static void main(String[] args) {	
		int[][] dgrid = Mth.gridDeclaration();
		int[][] pgrid = Mth.toPrime(dgrid);
		boolean solved = false;
		int iterations = 0;
		while (!solved) {
			solved = true;
			pgrid = Mth.line(pgrid);
			pgrid = Mth.column(pgrid);
			pgrid = Mth.box(pgrid);
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (!Mth.isPrime(pgrid[i][j])) {
						solved = false;
					}
				}
			}
			iterations++;
			if (iterations > 20) { //not solved yet, probably in an infinite loop
				System.out.print("This sudoku is either invalid or too hard for this program.");
				break;
			}
		}
		if (iterations <= 20) {
			dgrid = Mth.toDigit(pgrid);
			Mth.printDgrid(dgrid);
			System.out.print("Iterations needed: "+iterations);
		}
	}
}