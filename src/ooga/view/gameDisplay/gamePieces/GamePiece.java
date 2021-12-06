package ooga.view.gameDisplay.gamePieces;
import javafx.scene.Node;
import javafx.scene.paint.Color;

/**
 * Abstract class that is the parent of all game pieces. This includes walls, dots, pacman, etc.
 */
public abstract class GamePiece{
  private int myRow;
  private int myCol;
  private Node myPiece;
  private int myCellSize;
  private static final String COMMA = ",";


  public GamePiece(int cellSize){
    myCellSize = cellSize;
    myRow = 0;
    myCol = 0;
  }

  /**
   * Returns the node representing the specific view game piece.
   * @return Node that can be added to the grid pane in BoardView.
   */
  public Node getPiece(){
    return myPiece;
  }

  public void setMyPiece(Node myPiece) {
    this.myPiece = myPiece;
  }

  protected abstract Node makeNode();

  protected String getCellIndexID(){
    return String.format("%d,$d", myRow, myCol);
  }

  protected void setIDs(Node node, String cssID, String generalID){
    node.getStyleClass().add(cssID);
    node.setId(generalID);
  }

  public abstract String getMyClass();

  protected int getCellSize(){
    return myCellSize;
  }

  public void setCellSize(int newSize) {
    myCellSize = newSize;
  }

  protected Color parseRGBs(String rgbColor){
    String[] colorValues = rgbColor.split(COMMA); //TODO PARSE OUT NEGATIVE DATA
    return Color.rgb(Integer.parseInt(colorValues[0])%256,Integer.parseInt(colorValues[1])%256,Integer.parseInt(colorValues[2])%256);
  }



}

