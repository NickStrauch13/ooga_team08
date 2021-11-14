package ooga.view.gameDisplay.center;

import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ooga.view.gameDisplay.gamePieces.DotPiece;
import ooga.view.gameDisplay.gamePieces.GamePiece;
import ooga.view.gameDisplay.gamePieces.PacmanPiece;
import ooga.view.gameDisplay.gamePieces.WallPiece;

public class BoardView {
  public static final int CELL_SIZE = 25;
  private GridPane myGrid;
  private GamePiece myPieces;
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
          myPieces = new WallPiece();  //example of using the GamePiece abstraction. Obviously still need to remove conditionals (replace with refection)
          myGrid.add(myPieces.getPiece(), c, r);
        }
        if(currentState == 1){ //empty with dot pickup
          myPieces = new DotPiece();
          Node dot = myPieces.getPiece();
          myGrid.add(dot, c, r);
          myGrid.setHalignment(dot, HPos.CENTER);
        }
        if(currentState == 2){ //Pacman
          myPieces = new PacmanPiece();
          Node pacman = myPieces.getPiece();
          myGrid.add(pacman, c,r);
          myGrid.setHalignment(pacman, HPos.CENTER);
        }
      }
    }


    return myGrid;
  }
}
