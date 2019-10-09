# SudokuSolver
This console program solves easy to medium Sudoku puzzles. I created this during grade 12 as a bonus challenge project suggested by my AP Computer Science A teacher.

To use the program, download and run the .java file, and follow the prompts to enter in your sudoku puzzle. The program will compute the puzzle, showing each step, and print the finished puzzle to the terminal.

If I were to improve on this project, I would add an algorithm that would solve harder sudoku puzzles. As of now, the program will only make a move if it determines that there is only one possible number that can go in that cell. For harder puzzles, sometimes you arrive at a point where all of the cells have more than one possible option. The program would need to guess a possible number for a cell with the fewest options, then make subsequent moves until it gets stuck. Then the program would backtrack to the point where it made the guess, and guess another number, and so on until it solves the puzzle.
