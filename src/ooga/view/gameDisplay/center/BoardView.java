package ooga.view.gameDisplay.center;

import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ooga.controller.Controller;
import ooga.view.gameDisplay.gamePieces.DotPiece;
import ooga.view.gameDisplay.gamePieces.GamePiece;
import ooga.view.gameDisplay.gamePieces.PacmanPiece;
import ooga.view.gameDisplay.gamePieces.WallPiece;

public class BoardView {
  private GridPane myGrid;
  private GamePiece myPieces;
  private int[][] controllerBoard;
  private Controller myController;

  public BoardView(Controller controller){
    myController = controller;
    myGrid = new GridPane();
    myGrid.setMaxSize(myController.getCellSize(), myController.getCellSize());
    //myGrid.setGridLinesVisible(true);
    myGrid.getStyleClass().add("gameGridPane");

  }

  public void addBoardPiece(int row, int col, String objectName) {

      if(objectName.equals("WALL")){ //Wall
        myPieces = new WallPiece();  //example of using the GamePiece abstraction. Obviously still need to remove conditionals (replace with refection)
        myGrid.add(myPieces.getPiece(), col, row);
      }
      if(objectName.equals("POWERUP1")){ //empty with dot pickup
        myPieces = new DotPiece();
        Node dot = myPieces.getPiece();
        myGrid.add(dot, col, row);
        myGrid.setHalignment(dot, HPos.CENTER);
      }

  }

  public void addCreature(int row, int col, String objectName) {
    if(objectName.equals("PACMAN")){ //Pacman
      myPieces = new PacmanPiece();
      Node pacman = myPieces.getPiece();
      myGrid.add(pacman, col,row);
      myGrid.setHalignment(pacman, HPos.CENTER);
    }
  }

  public void makeBoard(int rows, int cols){
    controllerBoard = new int[rows][cols];
  }

  public GridPane getInitialBoard() {
    return myGrid;
  }


}
