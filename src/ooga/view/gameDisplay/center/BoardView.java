package ooga.view.gameDisplay.center;

import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ooga.controller.Controller;
import ooga.view.gameDisplay.gamePieces.DotPiece;
import ooga.view.gameDisplay.gamePieces.GamePiece;
import ooga.view.gameDisplay.gamePieces.MovingPiece;
import ooga.view.gameDisplay.gamePieces.PacmanPiece;
import ooga.view.gameDisplay.gamePieces.WallPiece;

public class BoardView {
  private GridPane myGrid;
  private Group myGroup;
  private GamePiece myPieces;
  private int[][] controllerBoard;
  private int myCellSize;
  private Controller myController;

  public BoardView(Controller controller){
    myController = controller;
    myGroup = new Group();
    myGrid = new GridPane();
    myGroup.getChildren().add(myGrid);
    myCellSize = myController.getCellSize();
    myGrid.setMaxSize(myCellSize, myCellSize);
    //myGrid.setGridLinesVisible(true);
    myGrid.getStyleClass().add("gameGridPane");

  }

  //TODO change to reflection instead of conditionals
  public void addBoardPiece(int row, int col, String objectName) {

      if(objectName.equals("WALL")){ //Wall
        System.out.println(objectName);
        myPieces = new WallPiece(myController.getCellSize());  //example of using the GamePiece abstraction. Obviously still need to remove conditionals (replace with refection)
        myGrid.add(myPieces.getPiece(), col, row);
      }
      if(objectName.equals("POWERUP1")){ //empty with dot pickup
        System.out.println(objectName);
        myPieces = new DotPiece(myController.getCellSize());
        Node dot = myPieces.getPiece();
        myGrid.add(dot, col, row);
        myGrid.setHalignment(dot, HPos.CENTER);
      }

  }

  public void addCreature(int row, int col, String objectName) {
    if(objectName.equals("PACMAN")){ //Pacman
      myPieces = new PacmanPiece(myController.getCellSize());
      ImageView pacman = (ImageView) myPieces.getPiece();
      myGrid.add(pacman, col,row);
      myGrid.setHalignment(pacman, HPos.CENTER);
      /*myGroup.getChildren().add(pacman);
      pacman.setX(col*myCellSize);
      pacman.setY(row*myCellSize);*/
    }
  }

  public void makeBoard(int rows, int cols){
    controllerBoard = new int[rows][cols];
  }

  public Group getInitialBoard() {
    return myGroup;
  }


}
