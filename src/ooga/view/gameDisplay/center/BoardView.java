package ooga.view.gameDisplay.center;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import ooga.view.gameDisplay.gamePieces.GamePiece;
import ooga.view.gameDisplay.gamePieces.WallPiece;

public class BoardView {
  public static final int CELL_SIZE = 25;
  private GridPane myGrid;
  private GamePiece myPiece;
  private static final int[][] fakeControllerBoard = {{0,0,0,0,0,0}, {0,1,1,1,1,0}, {0,0,0,1,0,0}, {0,1,1,2,1,0}, {0,1,0,1,1,0}, {0,0,0,0,0,0}};

  public BoardView(){
    myGrid = new GridPane();
    myGrid.setMaxSize(CELL_SIZE, CELL_SIZE);
    //myGrid.setGridLinesVisible(true);
    myGrid.getStyleClass().add("gameGridPane");
  }

  public GridPane makeBoard(){
    //TODO get board data from the controller
    //TODO Refactor conditionals out. Maybe make an abstraction of 'game pieces' then use reflection?
    int currentState; //may actually want this as a string
    for (int r = 0; r < 6; r++) { //Replace 6 with num rows and num cols
      for (int c = 0; c < 6; c++) {
        currentState = fakeControllerBoard[r][c]; //TODO replace with a myController.getState(r,c) method
        //TODO Refactor conditionals out. Maybe make an abstraction of 'game pieces' then use reflection?
        if(currentState == 0){ //Wall
          myPiece = new WallPiece();  //example of using the GamePiece abstraction. Obviously still need to remove conditionals (replace with refection)
          myGrid.add(myPiece.getPiece(), c, r);
        }
        if(currentState == 1){ //empty with dot pickup
          Circle dot = new Circle(5);
          dot.getStyleClass().add("dotPickup");
          myGrid.add(dot, c, r);
          myGrid.setHalignment(dot, HPos.CENTER);
        }
        if(currentState == 2){ //Pacman
          Circle testPacman = new Circle(12.5, Color.YELLOW);
          myGrid.add(testPacman, c,r);
        }
      }
    }


    return myGrid;
  }
}
