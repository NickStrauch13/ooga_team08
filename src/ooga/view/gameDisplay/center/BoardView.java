package ooga.view.gameDisplay.center;

import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import ooga.controller.Controller;
import ooga.view.gameDisplay.gamePieces.DotPiece;
import ooga.view.gameDisplay.gamePieces.GamePiece;
import ooga.view.gameDisplay.gamePieces.MovingPiece;
import ooga.view.gameDisplay.gamePieces.PacmanPiece;
import ooga.view.gameDisplay.gamePieces.WallPiece;

public class BoardView {
  private GridPane myGrid;
  private Group myGroup;
  private GamePiece myPiece;
  private int[][] controllerBoard;
  private int myCellSize;
  private Controller myController;
  private MovingPiece myPacman;

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
        myPiece = new WallPiece(myController.getCellSize());  //example of using the GamePiece abstraction. Obviously still need to remove conditionals (replace with refection)
        myGrid.add(myPiece.getPiece(), col, row);
      }
      if(objectName.equals("POWERUP1")){ //empty with dot pickup
        System.out.println(objectName);
        myPiece = new DotPiece(myController.getCellSize());
        Node dot = myPiece.getPiece();
        myGrid.add(dot, col, row);
        myGrid.setHalignment(dot, HPos.CENTER);
      }

  }

  public void addCreature(int row, int col, String objectName) {
    if(objectName.equals("PACMAN")){ //Pacman
      myPacman = new PacmanPiece(myController.getCellSize());
      Node pacmanNode = myPacman.getPiece();
      myGroup.getChildren().add(pacmanNode);
      myPacman.updatePosition(col*myController.getCellSize(), row*myController.getCellSize());
    }
  }

  public void makeBoard(int rows, int cols){
    controllerBoard = new int[rows][cols];
  }

  public Group getInitialBoard() {
    return myGroup;
  }


  /**
   * Getter method that will return the pacman movingPiece instance
   * @return movingPiece instance
   */
  public MovingPiece getPacman(){
    return myPacman;
  }

}
