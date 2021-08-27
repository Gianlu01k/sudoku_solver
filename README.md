# sudoku_solver

## Brief introduction to sudoku game
Sudoku is a logic-based, combinatoria number-placement puzzle. In classic sudoku, the objective is to fill a 9×9 grid with digits so that each column, each row, and each of the nine 3×3 subgrids that compose the grid (also called "boxes", "blocks", or "regions") contains all of the digits from 1 to 9. The puzzle setter provides a partially completed grid, which for a well-posed puzzle has a single solution.

## v1.0

### What sudoku_solver can do
sudoku_solver v1.00 can solve easy and medium difficulty sudoku, read a 9x9 matrix from keyboars whith a simple value-x-y input or read the matrix from the matrix_file.txt. 
For now the app uses 0 instead of the empty cell -this semplifies comparisons and searches of numbers-. Will be updated in next versions.

### Manager class
###### Functions:
- Manager constructor
- int retrive_key(int x, int y)
- int[][] read_matrix
- int[][] create_matrix
- int[][] solve(int[][] m)
- int[][] threexthree(int[][] m, int x, int y)
- void print_matrix(int[][] m)
