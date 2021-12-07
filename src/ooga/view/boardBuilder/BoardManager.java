package ooga.view.boardBuilder;

import javafx.scene.Node;
import ooga.controller.Controller;
import ooga.controller.ViewerControllerInterface;
import ooga.view.gameDisplay.center.BoardView;
import ooga.view.gameDisplay.gamePieces.GamePiece;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Updates the board that is shown in the Board Builder Screen
 * Author: Neil Mosca
 */
public class BoardManager {
    private static final String D_D_S = "%d,%d,%s";
    private static final String FORMAT = ",";
    private ArrayList<String> userAdded;
    private BoardView myBoardView;
    private ViewerControllerInterface myController;
    private BuilderButtons myBuilderButtons;
    private static final int ROW = 0;
    private static final int COL = 1;

    /**
     * Initializes the objects required to update the Board
     * @param controller
     * @param boardView
     * @param builderButtons
     */
    public BoardManager(ViewerControllerInterface controller, BoardView boardView, BuilderButtons builderButtons) {
        myController = controller;
        myBoardView = boardView;
        myBuilderButtons = builderButtons;
        userAdded = new ArrayList<>();
    }

    /**
     * Updates the grid when a user selects a gameObject
     * @param oldPiece
     */
    public void updateGrid(Node oldPiece) {
        myBoardView.removeNode(oldPiece.getId());
        Collection<String> stringList = myController.createCreatureMap().values();
        String className = myBuilderButtons.getClassName(myBuilderButtons.getSelected());
        String id = oldPiece.getId();
        int row = getPosition(id, ROW);
        int col = getPosition(id, COL);
        GamePiece newNode = myBoardView.addBoardPiece(row, col, className, null);
        if (!(stringList.contains(className))) {
            updateColor(newNode);
        }
        newNode.getPiece().setOnMouseClicked(e -> updateGrid(newNode.getPiece()));
        updateUserAdded(row, col, newNode);
    }

    private void updateUserAdded(int row, int col, GamePiece newNode) {
        newNode.getPiece().setId(String.format(D_D_S, row, col, myBuilderButtons.getClassName(newNode)));
        if (!userAdded.contains(newNode.getPiece().getId())) {
            userAdded.add(newNode.getPiece().getId());
        }
    }

    private void updateColor(GamePiece newNode) {
        Node temp = newNode.getPiece();
        Node temp2 = myBuilderButtons.getSelected().getPiece();
        temp.getStyleClass().add(temp2.getStyle());
    }

    /**
     * Gets the position (row or col) of the GameObject added
     * @param myID
     * @param i
     * @return
     */
    public int getPosition(String myID, int i ) {
        String[] position = myID.split(FORMAT);
        return Integer.parseInt(position[i]);
    }

    /**
     * Maintains a list of the objects that the user added to the board builder
     * @return
     */
    public ArrayList<String> getUserAdded() {
        return userAdded;
    }

}
