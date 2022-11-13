import java.util.*;  

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
	//the coordinates (i,j) are sometimes interchanged because I get confused sometimes 
	
	//regarding to sudoku solving:
	//a HOUSE is a group of 9 cells that must contain digits 1 through 9
	//the 3 types of HOUSEs are LINEs (horizontal), COLUMNs (vertical) and BOXes (squares)
	//a CANDIDATE number is one of the possible numbers that could go in a cell
	
	public static int[][] gridDeclaration() { //different test sudokus
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
		//pointing sudoku, only helps with (1,2 = 13)
//		int[][] grid = {{0, 1, 7, 9, 0, 3, 6, 0, 0}, 
//						{0, 0, 0, 0, 8, 0, 0, 0, 0},  //on 1,2,3,7,9 can go a 3, but 7,9 are the only cells in box 3 with a candidate 3, so it won't go on cells 1,2,3 (box 1)
//						{9, 0, 0, 0, 0, 0, 5, 0, 7}, 
//						{0, 7, 2, 0, 1, 0, 4, 3, 0}, 
//						{0, 0, 0, 4, 0, 2, 0, 7, 0}, 
//						{0, 6, 4, 3, 7, 0, 2, 5, 0}, 
//						{7, 0, 1, 0, 0, 0, 0, 6, 5}, //on 2,4,5 can go a 2, but 4,5 are the only cells in box 8 with a candidate 2, so it won't go on cell 2 (box 7)
//						{0, 0, 0, 0, 3, 0, 0, 0, 0}, 
//						{0, 0, 5, 6, 0, 1, 7, 2, 0}};
		//pointing sudoku both vertical & horitzontal
//		int[][] grid = {{0, 0, 9, 0, 7, 0, 0, 0, 0}, 
//						{0, 8, 0, 4, 0, 0, 0, 0, 0}, 
//						{0, 0, 3, 0, 0, 0, 0, 2, 8}, 
//						{1, 0, 0, 0, 0, 0, 6, 7, 0}, 
//						{0, 2, 0, 0, 1, 3, 0, 4, 0}, 
//						{0, 4, 0, 0, 0, 7, 8, 0, 0}, 
//						{6, 0, 0, 0, 3, 0, 0, 0, 0}, //(0,7) eliminates candidates 5/7 (d3/d4) thanks to pointing line and pointing column
//						{0, 1, 0, 0, 0, 0, 0, 0, 0}, 
//						{0, 0, 0, 0, 0, 0, 2, 8, 4}};
		//2-string kite
		int[][] grid = {{3, 6, 1, 7, 0, 0, 2, 9, 3}, 
						{8, 4, 2, 3, 9, 5, 6, 7, 1}, 
						{0, 5, 0, 2, 6, 1, 4, 8, 3}, 
						{1, 0, 8, 5, 2, 6, 0, 3, 4}, 
						{6, 2, 5, 0, 0, 0, 0, 1, 8}, 
						{0, 3, 4, 1, 0, 0, 5, 2, 6}, 
						{4, 0, 0, 6, 1, 0, 8, 5, 2}, //(6,7) should be divided by 23, therefore being only a 5 (d3), and I think it will be solved afterwards
						{5, 8, 0, 0, 0, 2, 1, 6, 7}, 
						{2, 1, 6, 8, 5, 7, 3, 4, 9}};
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
	
	public static int[][] toPrime(int[][] dgrid) { //passes digit sudoku to prime one with all 0s as 223092870 (product of the first 9 primes)
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
	
	public static int[][] line(int[][] pgrid) { //removes candidate numbers that already are in the same line from unknown cells
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
	public static int[][] column(int[][] pgrid) { //removes candidate numbers that already are in the same column from unknown cells
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
	public static int[][] box(int[][] pgrid){ //removes candidate numbers that already are in the same box from unknown cells
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (Mth.isPrime(pgrid[i][j])) { //Tuesday morning code
					int x = (i/3)*3 + (j/3) + 1; //x is the box number
					for (int pos = 1; pos <= 9; pos++) {
						int newI = Mth.ijBox(pos, x).get(0);
						int newJ = Mth.ijBox(pos, x).get(1);
						if (!Mth.isPrime(pgrid[newI][newJ]) && pgrid[newI][newJ] % pgrid[i][j] == 0) {
							pgrid[newI][newJ] /= pgrid[i][j];
						}
					}
				}
			}
		}
		return pgrid;
	}
	
	public static boolean isPrime(int p) { //stolen from the internet because I didn't want to think, it's pretty self-explanatory
		for (int i = 2; i <= p/2; i++) {
		      if (p % i == 0) {
		    	  return false;
		      }
		}
		return true;
	}
	
	public static List<Integer> factors(int n){ //checks factors of a number, used to see exactly which numbers can go in each cell with several candidates
	    List<Integer> factors = new LinkedList<>(); 
	    for (int i = 2; i <= n/2; i++) { //1 and n not necessary
	    	if (n%i == 0 && Mth.isPrime(i)) {
	    		factors.add(i);
	    	}
	    }
	    return factors; 
	} 
	
	public static int[][] NakedLine(int[][] grid){ //checks if a non-prime number with 2 factors appears 2 times in a line, then divides other non-primes with any of those factors by that factor
		int saved, saved2, nFactors;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				List<Integer> factors = factors(grid[i][j]);
				nFactors = factors.size();
				if (!Mth.isPrime(grid[i][j])) {
					saved = grid[i][j];
					if (nFactors == 2) {
						for (int line = 0; line < 9; line++) {
							if (saved == grid[i][line] && line != j) {
								saved2 = saved;
								for (int linesaved = 0; linesaved < 9; linesaved++) {
									if (grid[i][linesaved]%factors.get(0) == 0 && grid[i][linesaved] != saved) {
										grid[i][linesaved] /= factors.get(0);
									}
									if (grid[i][linesaved]%factors.get(1) == 0 && grid[i][linesaved] != saved) {
										grid[i][linesaved] /= factors.get(1);
									}
								}								
							}
						}	
					}	
				}
			}
		}
		return grid;
	}
	public static int[][] NakedColumn(int[][] grid){ //checks if a non-prime number with 2 factors appears 2 times in a column, then divides other non-primes with any of those factors by that factor
		int saved, saved2, nFactors;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				List<Integer> factors = factors(grid[i][j]);
				nFactors = factors.size();
				if (!Mth.isPrime(grid[i][j])) {
					saved = grid[i][j];
					if (nFactors == 2) {
						for (int column = 0; column < 9; column++) {
							if (saved == grid[column][j] && column != i) {
								saved2 = saved;
								for (int columnsaved = 0; columnsaved < 9; columnsaved++) {
									if (grid[columnsaved][j]%factors.get(0) == 0 && grid[columnsaved][j] != saved) {
										grid[columnsaved][j] /= factors.get(0);
									}
									if (grid[columnsaved][j]%factors.get(1) == 0 && grid[columnsaved][j] != saved) {
										grid[columnsaved][j] /= factors.get(1);
									}
								}							
							}
						}	
					}	
				}
			}
		}
		return grid;
	}
	
	public static int[][] NakedBox (int[][] grid){ //if 2 same non-primes (with 2 factors) are in a box, divide all (if divisible) numbers in box by the factor
		int nFactors, saved;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				List<Integer> factors = factors(grid[i][j]);
				nFactors = factors.size();
				if (!Mth.isPrime(grid[i][j]) && nFactors == 2) {
					int c = 0;
					int f1 = factors.get(0);
					int f2 = factors.get(1);
					saved = grid[i][j];
					int x = (i/3)*3 + (j/3) + 1;
					int[] arr = Mth.boxesNumbers(grid, x);
					for (int nArray = 0; nArray < 9; nArray++) {
						if (arr[nArray] == saved) {
							c++;
							if (c == 2) {
								for (int pos = 1; pos <= 9; pos++) {
									int newI = Mth.ijBox(pos, x).get(0);
									int newJ = Mth.ijBox(pos, x).get(1);
									if (grid[newI][newJ] != saved && grid[newI][newJ]%f1 == 0) {
										grid[newI][newJ] /= f1;
									}
									if (grid[newI][newJ] != saved && grid[newI][newJ]%f2 == 0) {
										grid[newI][newJ] /= f2;
									}
								}
								break;
							}
						}
					}
				}
			}
		}
		return grid;
	}
	
	public static int[] boxesNumbers(int[][] grid, int x) { //returns an array with each number in a certain box, left to right, up to down
		int[] arr = new int[9];
		arr[0] = grid[(x-1)/3*3][(x-1)%3*3];
		arr[1] = grid[(x-1)/3*3][(x-1)%3*3+1];
		arr[2] = grid[(x-1)/3*3][(x-1)%3*3+2];
		arr[3] = grid[(x-1)/3*3+1][(x-1)%3*3];
		arr[4] = grid[(x-1)/3*3+1][(x-1)%3*3+1];
		arr[5] = grid[(x-1)/3*3+1][(x-1)%3*3+2];
		arr[6] = grid[(x-1)/3*3+2][(x-1)%3*3];
		arr[7] = grid[(x-1)/3*3+2][(x-1)%3*3+1];
		arr[8] = grid[(x-1)/3*3+2][(x-1)%3*3+2];
		return arr;
	}
	
	public static List<Integer> ijBox(int pos, int nBox){ //pos is the number of the position of the cell in a box as an array 1=(0,0) 2=(0,1) ... 4=(1,0) ... 8=(2,1) ...
	    List<Integer> ij = new LinkedList<>(); 
	    switch(pos) { //Monday morning code 2
		case 1:
			ij.addAll(Arrays.asList((nBox-1)/3*3, (nBox-1)%3*3));
		    break;
		case 2:
			ij.addAll(Arrays.asList((nBox-1)/3*3, (nBox-1)%3*3+1));
		    break;
		case 3:
			ij.addAll(Arrays.asList((nBox-1)/3*3, (nBox-1)%3*3+2));
		    break;
		case 4:
			ij.addAll(Arrays.asList((nBox-1)/3*3+1, (nBox-1)%3*3));
		    break;
		case 5:
			ij.addAll(Arrays.asList((nBox-1)/3*3+1, (nBox-1)%3*3+1));
		    break;
		case 6:
			ij.addAll(Arrays.asList((nBox-1)/3*3+1, (nBox-1)%3*3+2));
		    break;
		case 7:
			ij.addAll(Arrays.asList((nBox-1)/3*3+2, (nBox-1)%3*3));
		    break;
		case 8:
			ij.addAll(Arrays.asList((nBox-1)/3*3+2, (nBox-1)%3*3+1));
		    break;
		case 9:
			ij.addAll(Arrays.asList((nBox-1)/3*3+2, (nBox-1)%3*3+2));
		    break;
	    }
	    return ij;
	} 
	
	public static int[][] PointingLine(int[][] grid) { //if a candidate appears (2 or 3 times) only in one line inside a box, it can't go anywhere else on that line	
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!Mth.isPrime(grid[i][j])) {
					List<Integer> factors = factors(grid[i][j]);
					//System.out.println("\nfactors "+grid[i][j]+": ");
					for (int f : factors) {
						int cLine = 0; //counter of factor appearances in line INSIDE box (max is 3)
						//System.out.println("fact: "+f);
						for (int posBox = 0; posBox < 3; posBox++) {
							//if the factor is repeated in the same line and same box && it's repeated in same line other box, divide that other box by the factor (if possible)
							if (grid[i][((j/3)*3 + posBox)] % f == 0) {
								cLine++;
							}							
						}
						int cBox = 0; //counter of factor appearances in box
						int x = (i/3)*3 + (j/3) + 1;
						for (int pos = 1; pos <= 9; pos++) {
							int newI = Mth.ijBox(pos, x).get(0);
							int newJ = Mth.ijBox(pos, x).get(1);
							if (!Mth.isPrime(grid[newI][newJ]) && grid[newI][newJ] % f == 0) {
								cBox++;
							}
						}
						//System.out.println("nCandidates of "+grid[i][j]+" is line:"+cLine+" and box:"+cBox);
						//if both counters are equal it's a hidden pair/triple
						if (cLine == cBox) {
							//divide (if divisible) the other numbers on the same line by that factor
							for (int posLine = 0; posLine < 9; posLine++) {
								int newX = (i/3)*3 + (posLine/3) + 1;
								if (x != newX && grid[i][posLine] % f == 0) {
									grid[i][posLine] /= f;
								}
							}
						}
					}				
				}
			}
		}
		return grid;
	}

	public static int[][] PointingColumn(int[][] grid) { //same as before but with columns instead of lines
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!Mth.isPrime(grid[i][j])) {
					List<Integer> factors = factors(grid[i][j]);
					int factSize = factors.size();
					//System.out.println("\nfactors "+grid[i][j]+": ");
					for (int f : factors) {
						int cColumn = 0;
						//System.out.println("fact: "+f);
						for (int posBox = 0; posBox < 3; posBox++) {
							if (grid[((i/3)*3 + posBox)][j] % f == 0) {
								cColumn++;
							}							
						}
						int cBox = 0;
						int x = (i/3)*3 + (j/3) + 1;
						for (int pos = 1; pos <= 9; pos++) {
							int newI = Mth.ijBox(pos, x).get(0);
							int newJ = Mth.ijBox(pos, x).get(1);
							if (!Mth.isPrime(grid[newI][newJ]) && grid[newI][newJ] % f == 0) {
								cBox++;
							}
						}
						//System.out.println("nCandidates of "+grid[i][j]+" is line:"+cColumn+" and box:"+cBox);
						if (cColumn == cBox) {
							for (int posColumn = 0; posColumn < 9; posColumn++) {
								int newX = (posColumn/3)*3 + (j/3) + 1;
								if (x != newX && grid[posColumn][j] % f == 0) {
									grid[posColumn][j] /= f;
								}
							}
						}
					}				
				}
			}
		}
		return grid;
	}
	
	public static int[][] TwoStringKite(int[][] grid){ //I don't even know
		//Concentrate again on one digit. Find a row and a column that have only two candidates left (the "strings"). 
		//One candidate from the row and one candidate from the column have to be in the same block. The candidate that sees the two other cells can be eliminated.
		for (int i = 0; i < 9; i++) {
			List<Integer> factChecked = new ArrayList<Integer>();
			for (int j = 0; j < 9; j++) {
				if (!Mth.isPrime(grid[i][j])) {
					List<Integer> factors = factors(grid[i][j]);
					for (int f : factors) { 
						if (!factChecked.contains(f)) {
							factChecked.add(f);
							int cLine = 0, boxLine1 = 0, boxLine2 = 0;
							//check if f is on another place in the line (only 2 in total)
							List<Integer> linePositions = new ArrayList<Integer>();
							for (int posLine = 0; posLine < 9; posLine++) {					
									if (grid[i][posLine] % f == 0) {
										cLine++;
										linePositions.add(posLine);
										boxLine1 = (i/3)*3 + (j/3) + 1;
										boxLine2 = (i/3)*3 + (posLine/3) + 1;
									}					
							}
							if (cLine == 2 && boxLine1 != boxLine2) {
//								System.out.println("····· factor "+f+" appears "+cLine+" times in line "+i+" ·····");	
//								System.out.println("boxl1 = "+boxLine1+" - boxl2 = "+boxLine2+"\n- - - - - - - - - - - - ");	
								
								//same for columns
								for (int j2 = 0; j2 < 9; j2++) {
								List<Integer> factChecked2 = new ArrayList<Integer>();
									for (int i2 = 0; i2 < 9; i2++) {
										if (!Mth.isPrime(grid[i2][j2])) {
											List<Integer> factors2 = factors(grid[i2][j2]);
											for (int f2 : factors2) { 
												if (!factChecked2.contains(f2)) {
													factChecked2.add(f2);
													int cColumn = 0, boxColumn1 = 0, boxColumn2 = 0;
													//check if f is on another place in the line (only 2 in total)
													List<Integer> colPositions = new ArrayList<Integer>();
													for (int posColumn = 0; posColumn < 9; posColumn++) {					
															if (grid[posColumn][j2] % f2 == 0) {
																cColumn++;
																colPositions.add(posColumn);
																boxColumn1 = (i2/3)*3 + (j2/3) + 1;
																boxColumn2 = (posColumn/3)*3 + (j2/3) + 1;
															}					
													}
													if (cColumn == 2 && boxColumn1 != boxColumn2) {
														if (f == f2 && i != i2 && j != j2) { //if f == f2 && nothing in colPositions is in colLines 
															if (boxLine1 == boxColumn1 || boxLine1 == boxColumn1 || boxLine2 == boxColumn1 || boxLine2 == boxColumn1) { //if some box coincides
//																System.out.println("factor "+f2+" appears "+cColumn+" times in column "+j2);	
//																System.out.println("boxc1 = "+boxColumn1+" - boxc2 = "+boxColumn2);
//																System.out.println("f1 = "+f+" - f2 = "+f2+"\n");
//																System.out.println("one "+f+" seen at cell in "+linePositions.get(0)+","+colPositions.get(0));
//																System.out.println("other "+f+" seen at cell in "+linePositions.get(1)+","+colPositions.get(1)+"\n");
																if (grid[colPositions.get(0)][linePositions.get(0)] % f == 0) {
																	grid[colPositions.get(0)][linePositions.get(0)] /= f;
																}
																if (grid[colPositions.get(1)][linePositions.get(1)] % f == 0) {
																	grid[colPositions.get(1)][linePositions.get(1)] /= f;
																}
															}
														}
													}													
												}
											}
										}
									}
								}	
							}				
						}
					}
				}
			}
		}
	
		//(5,6) should be divided by 23, therefore being only a 5 (d3), and I think it will be solved afterwards
		return grid;
	}
	
	//Sudoku solving techniques
	public static int[][] SimpleElimination(int[][] grid) { //the basic sudoku rule, if a number is in a house, it can't go anywhere else on that house
		grid = Mth.line(grid);
		grid = Mth.column(grid);
		grid = Mth.box(grid);
		return grid;
	}
	
	public static int[][] NakedPairs(int[][] grid) { //if a pair of candidate numbers appears alone twice on the same house, none of those numbers can go anywhere else on that house
		Mth.NakedLine(grid);
		Mth.NakedColumn(grid);
		Mth.NakedBox(grid);
		return grid;
	}
	
	public static int[][] PointingPairs(int[][] grid) { //WHY DOES THIS BREAK THE 2-STRING KITE TEST SUDOKU
		Mth.PointingLine(grid);
		Mth.PointingColumn(grid);
		return grid;
	}
}