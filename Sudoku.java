import java.lang.Math;
import java.util.HashSet;
import java.util.Arrays;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Sudoku extends Application{
  public static void main(String[] args) {
    Pane grid;
    int totalSlots = 81;
    int generatedClues = (int)((Math.random() * 8) + 17);
    System.out.println("Generated clues : " + generatedClues);
    int freeSlots = totalSlots - (int)generatedClues;
    System.out.println("Free slots : " + freeSlots);
    int[][] sudokuBoard = new int[9][9];
    generate_first(sudokuBoard, generatedClues);
    System.out.println(Arrays.deepToString(sudokuBoard));
    grid = makeGUI(9);
    launch(args);
    
  }

  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Sudoku Generator");
    primaryStage.show();
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

  public static Pane makeGUI(int n) {
    double width = 150;
    Pane grid = new Pane();

    Rectangle [][] rec = new Rectangle [n][n];
    
    for(int i = 0; i<n; i++) {
      for(int j = 0; j<n; j++) {
        rec[i][j] = new Rectangle();
        rec[i][j].setX(i  * width);
        rec[i][j].setY(j * width);
        rec[i][j].setWidth(width);
        rec[i][j].setHeight(width);
        rec[i][j].setFill(null);
        rec[i][j].setStroke(Color.BLACK);
        grid.getChildren().add(rec[i][j]);
      }
    }
    return grid;
  }

}