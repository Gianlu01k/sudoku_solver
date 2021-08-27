package SudokuSolve;

import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {
    Manager create=new Manager();
    int [][] sudo=create.read_matrix();
    /*int[][] sudo = create.create_matrix();*/
    create.print_matrix(sudo);


    int [][] solved_sudo=create.solve(sudo);
    System.out.println("Solved sudoku:");
    create.print_matrix(solved_sudo);
  }
}
