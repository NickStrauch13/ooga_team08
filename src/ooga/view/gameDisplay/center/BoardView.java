package ooga.view.gameDisplay.center;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Node;
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
  private MovingPiece myUserPiece;
  private List<Node> myNodeList;
  private static final String ID_FORMAT = "%s,%s";

  public BoardView(Controller controller){
    myController = controller;
    myGroup = new Group();
    myGrid = new GridPane();
    myGroup.getChildren().add(myGrid);
    myCellSize = myController.getCellSize();
    myGrid.setMaxSize(myCellSize, myCellSize);
    //myGrid.setGridLinesVisible(true);
    myGrid.getStyleClass().add("gameGridPane");
    myNodeList = new ArrayList<>();
  }

  //TODO change to reflection instead of conditionals
  public void addBoardPiece(int row, int col, String objectName) {

      if(objectName.equals("WALL")){ //Wall
        myPiece = new WallPiece(myController.getCellSize());  //example of using the GamePiece abstraction. Obviously still need to remove conditionals (replace with refection)
        myPiece.getPiece().setId(String.format(ID_FORMAT, row, col));
        myGrid.add(myPiece.getPiece(), col, row);
        myNodeList.add(myPiece.getPiece());
      }
      if(objectName.equals("POWERUP1")){ //empty with dot pickup
        myPiece = new DotPiece(myController.getCellSize());
        Node dot = myPiece.getPiece();
        dot.setId(String.format(ID_FORMAT, row, col));
        myGrid.add(dot, col, row);
        myGrid.setHalignment(dot, HPos.CENTER);
        myNodeList.add(dot);
      }

  }

  public void addCreature(int row, int col, String objectName) {
    if(objectName.equals("PACMAN")){ //Pacman   //TODO Bad... Refactor with reflection
      myUserPiece = new PacmanPiece(myController.getCellSize());
      Node pacmanNode = myUserPiece.getPiece();
      pacmanNode.setId(objectName);
      myGroup.getChildren().add(pacmanNode);
      myUserPiece.updatePosition(col*myController.getCellSize(), row*myController.getCellSize());
      myNodeList.add(pacmanNode);
    }
  }

  public void makeBoard(int rows, int cols){
    controllerBoard = new int[rows][cols];
  }

  public Group getInitialBoard() {
    return myGroup;
  }


  /**
   * Getter method that will return the user controlled movingPiece instance.
   * @return movingPiece instance
   */
  //TODO refactor to allow multiple controlled pieces at once?
  public MovingPiece getUserPiece(){
    return myUserPiece;
  }

  /**
   * Checks if the current userPiece is colliding with a node in the game group.
   * @return The ID of the collided node if there is a collision, null otherwise.
   */
  public String getUserCollision(){
    return myUserPiece.getCollision(myNodeList);
  }
}
