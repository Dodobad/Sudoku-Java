import java.lang.Math;
import java.util.HashSet;
import java.util.Arrays;

public class Sudoku {
  public static void main(String[] args) {

    int totalSlots = 81;
    int generatedClues = (int)((Math.random() * 8) + 17);
    System.out.println("Generated clues : " + generatedClues);
    int freeSlots = totalSlots - (int)generatedClues;
    System.out.println("Free slots : " + freeSlots);
    int[][] sudokuBoard = new int[9][9];
    generate_first(sudokuBoard, generatedClues);
    System.out.println(Arrays.deepToString(sudokuBoard));
    System.out.println();

    for(int i =0; i<3; i++) {
      for (int j =0 ; j<3; j++) {
        System.out.print(sudokuBoard[j][i]);
      }
      System.out.print(" ");
      for (int j =3 ; j<6; j++) {
        System.out.print(sudokuBoard[j][i]);
      }
      System.out.print(" ");
      for (int j =6 ; j<9; j++) {
        System.out.print(sudokuBoard[j][i]);
      }
      System.out.println(" ");
    }
    System.out.println();
    for(int i =3; i<6; i++) {
      for (int j =0 ; j<3; j++) {
        System.out.print(sudokuBoard[j][i]);
      }
      System.out.print(" ");
      for (int j =3 ; j<6; j++) {
        System.out.print(sudokuBoard[j][i]);
      }
      System.out.print(" ");
      for (int j =6 ; j<9; j++) {
        System.out.print(sudokuBoard[j][i]);
      }
      System.out.println(" ");
    }
    System.out.println("");
    for(int i =6; i<9; i++) {
      for (int j =0 ; j<3; j++) {
        System.out.print(sudokuBoard[j][i]);
      }
      System.out.print(" ");
      for (int j =3 ; j<6; j++) {
        System.out.print(sudokuBoard[j][i]);
      }
      System.out.print(" ");
      for (int j =6 ; j<9; j++) {
        System.out.print(sudokuBoard[j][i]);
      }
      System.out.println(" ");
    }

  }

  public static void generate_first(int[][] sudokuBoard,int clues) {
    HashSet<String> values = new HashSet();
    for (int i=0; i<clues; i++) {
      int current_value = (int)(Math.random()*9)+1;
      int x = (int)(Math.random() * 9);
      int y = (int)(Math.random() *9);
      while(values.contains(current_value + " in row " + x) || values.contains(current_value+ " in column " + y) || values.contains(current_value + " in subsection " + x/3 + " " + y/3)){
        x = (int)(Math.random() * 9);
        y = (int)(Math.random() *9);
      }
      sudokuBoard[x][y] = current_value;
      values.add(current_value + " in row " + x);
      values.add(current_value+ " in column " + y);
      values.add(current_value + " in subsection " + x/3 + " " + y/3);

    }
  }

}