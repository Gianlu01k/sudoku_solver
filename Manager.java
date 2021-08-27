package SudokuSolve;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Manager {

  private HashMap<Integer, ArrayList<Integer>> temp_values;

  public Manager() {
    this.temp_values = new HashMap();
    for (int j = 1; j <= 81; j++) {
      this.temp_values.put(j, new ArrayList<>());
    }


  }

  private int retrive_key(int x, int y) {
    return x * 9 + y + 1;
  }

  public int[][] read_matrix() throws FileNotFoundException {
    int[][] m = new int[9][9];
    int r = 0, c = 0;
    try {
      File file = new File("matrix_file.txt");
      if (file.createNewFile()) {
        System.out.println("File created: " + file.getName());
      } else {
        System.out.println("File already exists.");

        FileReader reader = new FileReader(file);
        char[] chars = new char[(int) file.length()];
        reader.read(chars);
        for (char x : chars
        ) {
          int n = ((int) x) - 48;
          if (n >= 0 && n <= 9) {
            m[r][c] = n;
            c++;
          } else {
            if (n == -38) {
              c = 0;
              r++;
            }
          }
          if (c == 9 && r == 8) {
            break;
          }
        }
        reader.close();
      }
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return m;
  }

  public int[][] create_matrix() throws IOException {
    BufferedReader t = new BufferedReader(new InputStreamReader(System.in));
    int choise = -1;
    int[][] matrix = new int[9][9];
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        matrix[i][j] = 0;
      }
    }
    do {
      System.out.println("Inserisci 0 per terminare inserimento\n"
          + "Inserisci 1 per aggiungere numero");
      choise = Integer.parseInt(t.readLine());
      switch (choise) {
        case 0:
          break;
        default:
          break;
        case 1:
          int x, y, val;
          do {
            System.out.println("Inserisci valore da inserire: ");
            val = Integer.parseInt(t.readLine());
            if (val < 1 || val > 9) {
              System.out.println("Valore inserito non corretto");
            }
          } while (val < 1 || val > 9);
          do {
            System.out.println("Inserisci numero riga:");
            x = Integer.parseInt(t.readLine());
            if (x < 1 || x > 9) {
              System.out.println("Valore inserito non corretto");
            }
          } while (x < 1 || x > 9);
          x--;
          do {
            System.out.println("Inserisci colonna: ");
            y = Integer.parseInt(t.readLine());
            if (y < 1 || y > 9) {
              System.out.println("Valore inserito non corretto");
            }
          } while (y < 1 || y > 9);
          y--;
          if (matrix[x][y] == 0) {
            matrix[x][y] = val;
          } else {
            System.out.println("Casella già riempita");
          }

          break;
      }
    } while (choise != 0);
    return matrix;
  }

  public int[][] solve(int[][] m) {
    int x = 0, y = 0;
    int cells_empty = 0;
    boolean end=false;
    do {
      do {
        if (m[x][y] == 0) {
          int[] row, column = new int[9]; //crete the row, the column and the matrix to search the right number
          row = m[x];
          for (int i = 0; i < 9; i++) {
            column[i] = m[i][y];
          }
          int[][] subm = threexthree(m, x, y);
          //searching value module
          for (int count = 1; count <= 9; count++) {
            final int prob_val = count;
            boolean found = false;
            for (int inrow : row
            ) {

              if (prob_val == inrow) {
                found = true;
              }}
              if (!found) {
                for (int incol : column
                ) {
                  if (prob_val == incol) {
                    found = true;
                  }
                }

              }
              if (!found) {
                for (int i = 0; i < 3; i++) {
                  for (int j = 0; j < 3; j++) {
                    if (prob_val == subm[i][j]) {
                      found = true;
                    }
                  }
                }

              }
              if (!found) {

                this.temp_values.get(retrive_key(x, y)).add(prob_val);
              }
          }
          if (this.temp_values.get(retrive_key(x, y)).size() == 1) {
            m[x][y] = this.temp_values.get(retrive_key(x, y)).remove(0);
          }

        }
        if (y == 8) {
          y = 0;
          x++;
        } else {
          y++;
        }

        //print_matrix(m);
      } while (x < 9 && y < 9);
      //end inner loop
      cells_empty=0;
      for (int r = 0; r < 9; r++) {
        for (int c = 0; c < 9; c++) {
          if (m[r][c]==0)
            cells_empty++;
        }
      }
      if (cells_empty != 0) {
        x = 0;
        y = 0;

        for (int j = 1; j <= 81; j++) {
          this.temp_values.remove(j);
          this.temp_values.put(j, new ArrayList<>());
        }
      }
    } while (cells_empty != 0);

    return m;
  }

  private int[][] threexthree(int[][] m, int x, int y) {
    int[][] three = new int[3][3];
    if (x / 3 < 1 && y / 3 < 1) {
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          three[i][j] = m[i][j];
        }
      }
    } else if (x / 3 >= 1 && x / 3 < 2 && y / 3 < 1) {
      int c = 0, r = 0;
      for (int i = 3; i < 6; i++) {
        for (int j = 0; j < 3; j++) {
          three[r][c] = m[i][j];
          c++;
        }
        c = 0;
        r++;
      }
    } else if (x / 3 >= 2 && x / 3 < 3 && y / 3 < 1) {
      int c = 0, r = 0;
      for (int i = 6; i < 9; i++) {
        for (int j = 0; j < 3; j++) {
          three[r][c] = m[i][j];
          c++;
        }
        c = 0;
        r++;
      }


    } else if (x / 3 < 1 && y / 3 >= 1 && y / 3 < 2) {
      int c = 0, r = 0;
      for (int i = 0; i < 3; i++) {
        for (int j = 3; j < 6; j++) {
          three[r][c] = m[i][j];
          c++;
        }
        c = 0;
        r++;
      }
    } else if (x / 3 >= 1 && x / 3 < 2 && y / 3 >= 1 && y / 3 < 2) {
      int c = 0, r = 0;
      for (int i = 3; i < 6; i++) {
        for (int j = 3; j < 6; j++) {
          three[r][c] = m[i][j];
          c++;
        }
        c = 0;
        r++;
      }
    } else if (x / 3 >= 2 && x / 3 < 3 && y / 3 >= 1 && y / 3 < 2) {
      int c = 0, r = 0;
      for (int i = 6; i < 9; i++) {
        for (int j = 3; j < 6; j++) {
          three[r][c] = m[i][j];
          c++;
        }
        c = 0;
        r++;
      }
    } else if (x / 3 < 1 && y / 3 >= 2 && y / 3 < 3) {
      int c = 0, r = 0;
      for (int i = 0; i < 3; i++) {
        for (int j = 6; j < 9; j++) {
          three[r][c] = m[i][j];
          c++;
        }
        c = 0;
        r++;
      }
    } else if (x / 3 >= 1 && x / 3 < 2 && y / 3 >= 2 && y / 3 < 3) {
      int c = 0, r = 0;
      for (int i = 3; i < 6; i++) {
        for (int j = 6; j < 9; j++) {
          three[r][c] = m[i][j];
          c++;
        }
        c = 0;
        r++;
      }

    } else if (x / 3 >= 2 && x / 3 < 3 && y / 3 >= 2 && y / 3 < 3) {
      int c = 0, r = 0;
      for (int i = 6; i < 9; i++) {
        for (int j = 6; j < 9; j++) {
          three[r][c] = m[i][j];
          c++;
        }
        c = 0;
        r++;
      }
    }
    return three;

  }


  public void print_matrix(int[][] m) {
    System.out.println("Matrice:");
    for (int i=0; i<m.length; i++){
      for (int j=0; j<m.length; j++){
        if (m[i][j]!=0)
        System.out.print(m[i][j]+"\t");
        else
          System.out.print("0"+"\t");
      }
      System.out.print("\n");
    }
  }
}