package ooga.view.gameDisplay.center;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import ooga.controller.ViewerControllerInterface;
import ooga.view.gameDisplay.gamePieces.*;


public class BoardView {

  public static final String GROUP_STYLING = "groupStyling";
  public static final String GAME_GRID_PANE = "gameGridPane";
  public static final String ID_HASH = "#";
  private GridPane myGrid;
  private Group myGroup;
  private int[][] controllerBoard;
  private int myCellSize;
  private ViewerControllerInterface myController;
  private MovingPiece myUserPiece;
  private MovingPiece myCPUPiece;
  private List<Node> myNodeList;

  public List<Node> getMyWallList() {
    return myWallList;
  }

  private List<Node> myWallList;
  private List<MovingPiece> myCreatureList;
  private static final String ID_FORMAT = "%s,%s";
  private int cpuCount = 0;
  private static final String PIECE_PATH = "ooga.view.gameDisplay.gamePieces.%sPiece";

  public BoardView(ViewerControllerInterface controller){
    myController = controller;
    myGroup = new Group();
    myCellSize = myController.getCellSize();
    myGroup.getStyleClass().add(GROUP_STYLING);
    resetBoardView();

  }

  /**
   * Adds a static board piece to the game
   * @param row
   * @param col
   * @param objectName
   */
  public GamePiece addBoardPiece(int row, int col, String objectName, Map<String, String> myObjectValues) {
      String formattedString = String.format(PIECE_PATH,objectName.substring(0, 1) + objectName.toLowerCase().substring(1));
      GamePiece gamePiece = pieceReflection(formattedString, myObjectValues);
      Node pieceNode = gamePiece.getPiece();
      gamePiece.getPiece().setId(String.format(ID_FORMAT, row, col));
      myGrid.add(pieceNode, col, row);
      myGrid.setHalignment(pieceNode, HPos.CENTER);
      myNodeList.add(pieceNode);
      return gamePiece;
  }

  /**
   * Adds any type of user controlled creature to the game.
   * @param row user creature starting row.
   * @param col user creature starting column.
   * @param creatureName name of the user creature
   */
  public void addUserCreature(int row, int col, String creatureName,Map<String,String> myCreatureValues) {
    String formattedString = String.format(PIECE_PATH, creatureName.substring(0, 1) + creatureName.toLowerCase().substring(1));
    myUserPiece = creatureReflection(formattedString,myCreatureValues);
    Node pieceNode = myUserPiece.getPiece();
    pieceNode.setId(creatureName);
    myGroup.getChildren().add(pieceNode);
    myUserPiece.updatePosition(col*myController.getCellSize(), row*myController.getCellSize());
    myCreatureList.add(myUserPiece);
  }

  /**
   * Adds any type of CPU creature to the game.
   * @param row CPU starting row
   * @param col CPU starting col
   * @param creatureName name of the creature
   */
  public void addCPUCreature(int row, int col, String creatureName,Map<String,String> myCreatureValues){
    String formattedString = String.format(PIECE_PATH, creatureName.substring(0, 1) + creatureName.toLowerCase().substring(1));
    myCPUPiece = creatureReflection(formattedString,myCreatureValues);
    Node cpuNode = myCPUPiece.getPiece();
    cpuNode.setId(creatureName + cpuCount);
    myGroup.getChildren().add(cpuNode);
    myCPUPiece.updatePosition(col*myController.getCellSize(), row*myController.getCellSize());
    myCreatureList.add(myCPUPiece);
    cpuCount++;
  }

  public GamePiece pieceReflection(String objectName, Map<String, String> myObjectValues) {
    GamePiece gamePiece = null;
    try {
      Class<?> clazz = Class.forName(objectName);
        gamePiece = (GamePiece) clazz.getDeclaredConstructor(Integer.class, Map.class)
                .newInstance(myController.getCellSize(), myObjectValues);
    }catch(NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return gamePiece;
  }

  public MovingPiece creatureReflection(String creatureName, Map<String,String> myCreatureValues){
    MovingPiece creaturePiece = null;
    try {
      Class<?> clazz = Class.forName(creatureName);
      creaturePiece = (MovingPiece) clazz.getDeclaredConstructor(Integer.class,Map.class)
              .newInstance(myController.getCellSize(),myCreatureValues);
    }catch(NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException | ClassNotFoundException e) {
      e.printStackTrace(); //TODO improve? or is this already handled in controller
    }
    return creaturePiece;
  }

  /**
   * Getter method for the javaFX group holding the board.
   * @return javaFX Group
   */
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
   * Returns the ID of the node that the user is colliding with.
   * @return String representing the collision node.
   */
  public String getUserCollision(){
    String creatureID = myUserPiece.getCreatureCollision(myCreatureList);
    if (creatureID == null) {
      creatureID = myUserPiece.getCollision(myNodeList);
    }
    return creatureID;
  }

  /**
   * Removes the node specified by the backend from the boardView.
   */
  public void removeNode(String nodeID){
    String removedID = ID_HASH + nodeID;
    Node nodeInGrid = myGrid.lookup(removedID);
    Node nodeInGroup = myGroup.lookup(removedID);
    if(myGrid.getChildren().remove(nodeInGrid)){
      myNodeList.remove(nodeInGrid);
    }
    if(myGroup.getChildren().remove(nodeInGroup)){
      myNodeList.remove(nodeInGroup);
    }
  }

  public void resetBoardView() {
    myGrid = new GridPane();
    myGrid.setMaxSize(myCellSize, myCellSize);
    myNodeList = new ArrayList<>();
    myCreatureList = new ArrayList<>();
    myGroup.getChildren().add(myGrid);
    myGrid.getStyleClass().add(GAME_GRID_PANE);
    cpuCount = 0;
  }

  /**
   * Gets the list of the current view creatures.
   */
  public List<MovingPiece> getCreatureList() {
    return myCreatureList;
  }

  public List<Node> getNodeList() {
    return myNodeList;
  }

  public GridPane getMyGrid() {
    return myGrid;
  }

  public Node getNodeInGrid(String removedID) {
    return myGrid.lookup(removedID);
  }


}
