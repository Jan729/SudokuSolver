public class SudokuSolver {
	public static void main(String[] args) {
		int[][] p = { //enter your puzzle here
			{ 1, 2, 8, 6, 0, 0, 5, 9, 0 },
	        { 0, 0, 0, 1, 0, 0, 2, 0, 8 },
	        { 0, 9, 6, 2, 0, 0, 0, 0, 3 },
	        { 6, 0, 0, 0, 0, 2, 7, 5, 0 },
	        { 2, 4, 0, 0, 1, 8, 6, 0, 0 },
	        { 0, 1, 9, 0, 6, 0, 0, 0, 0 },
	        { 0, 7, 0, 4, 0, 0, 0, 8, 5 },
	        { 0, 0, 1, 0, 5, 7, 0, 2, 0 },
	        { 9, 0, 0, 0, 0, 1, 3, 7, 0 }
		};
		SudokuSolver s = new SudokuSolver();
		System.out.println("Puzzle:");
		s.showPuzzle(p);

		int R = 0;
		int C = 0;
		int options = 0;
		int ans = 0;
		boolean checkedOnce = false;
		String[][] optionArr = new String[9][9];
		
		while(s.Unfinished(p)) {
			if(p[R][C] == 0) {
				for (int num = 1; num <= 9; num ++) {
					if(s.isValid(p, R, C, num)) { // if valid num, add 1 to counter, store num in var
						options++;
						ans = num;
						if(checkedOnce) s.newBoxCheck(R, C, num, p, optionArr, s);
						if (optionArr[R][C] == null)  optionArr[R][C] = String.valueOf(num); 
						if (optionArr[R][C] != null && !optionArr[R][C].contains(String.valueOf(num)))  optionArr[R][C] += String.valueOf(num);  
					} else {
						if(optionArr[R][C] != null && optionArr[R][C].contains(String.valueOf(num))) {
							if(optionArr[R][C].indexOf(String.valueOf(num)) == (optionArr[R][C].length()-1))
								optionArr[R][C] = optionArr[R][C].substring(0, optionArr[R][C].indexOf(String.valueOf(num)));
							else	
								optionArr[R][C] = optionArr[R][C].substring(0, optionArr[R][C].indexOf(String.valueOf(num))) + optionArr[R][C].substring(optionArr[R][C].indexOf(String.valueOf(num)) +1);
						}
					}
				}
			}
				if (options == 1) {
					p[R][C] = ans;
					optionArr[R][C] = null;
				} 
				options = 0;
				ans = 0;
			System.out.println("Options for " + R + ", " + C +": " + optionArr[R][C]);
			
			if (C == 8 && R == 8) { 
				checkedOnce = true;	
				s.showPuzzle(p);
			}
				C++; //traverse thru rows and cols 
				if (C == 9) {
					C = 0;
					R++;
				}
				if (R == 9) {
					R = 0;
				}
		}
		System.out.println("Completed Puzzle:");
		s.showPuzzle(p);
	}

	public void showPuzzle (int[][] arr) {
		System.out.println();
		for(int i = 0; i < 20; i++) {
			System.out.print("–");
		}
		System.out.println();
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				System.out.print(arr[r][c] + " ");
				if(c == 2 || c == 5) System.out.print(" ");
			}
			System.out.println();
			if(r == 2 || r == 5) System.out.println();
		}
		for(int i = 0; i < 20; i++) {
			System.out.print("–");
		}
		System.out.println();
	}

	public boolean isValid (int[][] p, int R, int C, int num) {
		//check col
		boolean valid = true;
		for(int r = 0; r < 9; r++) {
			if (p[r][C] == num) valid = false;
		}
		//check row
		for (int c = 0; c < 9; c++) {
			if (p[R][c] == num) valid = false;
		}
		//check 3x3 box
		int rLimit = 0;
		int cLimit = 0;
		
		if(R >= 6) rLimit = 9;
		else if(R >= 3) rLimit = 6;
		else rLimit = 3;
		if(C >= 6) cLimit = 9;
		else if(C>= 3) cLimit = 6;
		else cLimit = 3;
		
		for(int r = rLimit - 3; r < rLimit; r++) {
			for(int c = cLimit - 3; c < cLimit; c++) {
				if (p[r][c] == num) valid = false;
			}
		}
		return valid;
	}
	
	public void newBoxCheck(int R, int C, int num, int[][] p, String[][]opArr, SudokuSolver s) { 
		//check for unique number option for that cell
			int numCount = 0;
			int rTemp = 0;
			int cTemp = 0;
			int rLimit = 0;
			int cLimit = 0;
			
			if(R >= 6) rLimit = 9;
			else if(R >= 3) rLimit = 6;
			else rLimit = 3;
			if(C >= 6) cLimit = 9;
			else if(C>= 3) cLimit = 6;
			else cLimit = 3;
			
			for(int r = rLimit - 3; r < rLimit; r++) {
				for(int c = cLimit - 3; c < cLimit; c++) {
					if (p[r][c] == 0 && opArr[r][c].contains(String.valueOf(num))) {
						numCount++;
						rTemp = r;
						cTemp = c;
						}
					}
			  	}
			if (numCount == 1) {
				p[rTemp][cTemp] = num;
				opArr[rTemp][cTemp] = null;
			}
			numCount = 0;
	}
	public boolean Unfinished(int[][] p) {
		//if no boxes contain 0, return true
		for(int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (p[r][c] == 0) return true;
			}
		}
		return false;
	}
	public SudokuSolver() {
	}
}
