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

### Documentation
#### retrive_key
- modifier: private
- throws: none
- input: int x, int y
- output: int 
- scope: map the sudoku 9x9 matrix with numbers from 1 to 81 with the matrix cell indexes (x for the rows and y for the columns)

#### read_matrix
- modifier: public
- throws: FileNotFoundException
- input: none
- output: int[][]
- scope: read from matrix_file.txt a 9x9 matrix with empty cells (indicated with 0 value)

#### create_matrix
- modifier: public
- throws: IOException
- input: none
- output: int[][]
- scope: read from keyboard values of the unsolved sudoku. Function needs the x/y index and a value beetween 1 and 9

#### solve
- modifier: public
- throws: none
- input: int[][] m
- output: int[][]
- scope: solve sudoku througth comparison of numbers from 1 to 9 with numbers in row/columns/3x3 matrix related. Two do-while loops - - allow to analyze every empty cells in the 9x9 matrix until sudoku is solved and every cell-list containing probable value solutions - are empty

#### threexthree
- modifier: private
- throws: none
- input: int[][]m, int x, int y
- output: int[][]
- scope: retrieve one of the 9 main 3x3 matrix understanding the matrix related to a specific couple of index

#### print_matrix
- modifier: public
- throws: none
- input: int[][]m
- output: none
- scope: print in the terminal a general matrix with any row/column lengths
