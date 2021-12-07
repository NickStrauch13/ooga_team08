package ooga.view.gameDisplay.gamePieces;
import javafx.scene.Node;
import javafx.scene.paint.Color;

/**
 * Abstract class that is the parent of all game pieces. This includes walls, dots, pacman, etc.
 */
public abstract class GamePiece{

  public static final String CELL_INDEX_FORMAT = "%d,$d";
  private static final int MOD_256 = 256;
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

  /**
   * Sets the current gamePiece.
   * @param myPiece The piece node to be set.
   */
  public void setMyPiece(Node myPiece) {
    this.myPiece = myPiece;
  }

  /**
   * Generates a node for the piece.
   * @return Node piece.
   */
  protected abstract Node makeNode();

  /**
   * Returns the cell index of the piece. Comes in the format "4,3" for exmaple
   * @return String representing cell index.
   */
  protected String getCellIndexID(){
    return String.format(CELL_INDEX_FORMAT, myRow, myCol);
  }

  /**
   * Sets the Node IDs of the piece
   * @param node Node to set ID
   * @param cssID CSS ID of Node
   * @param generalID General Node ID to set.
   */
  protected void setIDs(Node node, String cssID, String generalID){
    node.getStyleClass().add(cssID);
    node.setId(generalID);
  }

  /**
   * Returns the cell size integer.
   * @return The size of the game cell.
   */
  protected int getCellSize(){
    return myCellSize;
  }

  /**
   * Sets the size of the cell.
   * @param newSize The integer value of the new cell size.
   */
  public void setCellSize(int newSize) {
    myCellSize = newSize;
  }

  /**
   * Scans the json file for the node color.
   * @param rgbColor RGB color value.
   * @return The color value scanned form the json file.
   */
  protected Color parseRGBs(String rgbColor){
    String[] colorValues = rgbColor.split(COMMA);
    return Color.rgb(Integer.parseInt(colorValues[0])% MOD_256,Integer.parseInt(colorValues[1])% MOD_256,Integer.parseInt(colorValues[2])% MOD_256);
  }



}

