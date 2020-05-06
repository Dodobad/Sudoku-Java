import java.lang.Math;
import java.util.HashSet;
import java.util.Arrays;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;


public class Sudoku extends Application{
  public static void main(String[] args) {
    launch(args);
    
  }

  public void start(Stage primaryStage) throws Exception {
    sudokuLogic(primaryStage);
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

  public static Pane makeGUI(int n, int[][] initBoard) {
    double width = 50;
    Pane grid = new Pane();
    double offsetXAdded =0;
    double offsetYAdded = 0;
    Rectangle [][] rec = new Rectangle [n][n];
    
    
    for(int i = 0; i<n; i++) {
      for(int j = 0; j<n; j++) {
        //if(((i+1)%3==1&& i !=8 && i!=0)) offsetXAdded=5;
        //if(((j+1)%3 ==1 && j!=8 && j!=0)) offsetYAdded+=5;
        if(j==0) offsetYAdded= 0;
        if(i==0) offsetXAdded =0;
        if(i==3) offsetXAdded = 5;
        if(j==3) offsetYAdded = 5;
        if(i==6) offsetXAdded = 10;
        if(j==6) offsetYAdded = 10;
        StackPane block = new StackPane();
        block.setLayoutX(i*width + offsetXAdded);
        block.setLayoutY(j*width + offsetYAdded);
        rec[i][j] = new Rectangle();
        rec[i][j].setX(i * width +offsetXAdded);
        rec[i][j].setY(j * width +offsetYAdded);
        rec[i][j].setWidth(width);
        rec[i][j].setHeight(width);
        rec[i][j].setFill(null);
        rec[i][j].setStroke(Color.BLUE);
        
        if (initBoard[i][j] != 0){ 
          Text number = new Text(Integer.toString(initBoard[i][j]));
          block.getChildren().addAll(rec[i][j],number);
        } else{
          MenuButton numberSelector = new MenuButton();
          numberSelector.setText("?");
          MenuItem[] itemChoices = new MenuItem[9];
          for (int num =1; num < 10; num++){
            itemChoices[num-1] =new MenuItem(Integer.toString(num));
            numberSelector.getItems().add(itemChoices[num-1]);
          }

          
          block.getChildren().addAll(rec[i][j],numberSelector);
        }

        grid.getChildren().add(block);

      }
    }

    return grid;
  }

  private void sudokuLogic(Stage stage){
    Pane grid = new Pane();
    int totalSlots = 81;
    int generatedClues = (int)((Math.random() * 8) + 17);
    int[][] sudokuBoard = new int[9][9];
    int freeSlots = totalSlots - (int)generatedClues;

    System.out.println("Generated clues : " + generatedClues);
    System.out.println("Free slots : " + freeSlots);
    
    generate_first(sudokuBoard, generatedClues);
    System.out.println(Arrays.deepToString(sudokuBoard));

    grid = makeGUI(9, sudokuBoard);
    
    Scene scene = new Scene(grid,465,465,Color.WHITESMOKE);
    stage.setTitle("Sudoku Generator");
    stage.setScene(scene);
    stage.show();
  }

}