package ooga.view.boardBuilder;

import javafx.scene.Node;
import ooga.controller.Controller;
import ooga.controller.ViewerControllerInterface;
import ooga.view.gameDisplay.center.BoardView;
import ooga.view.gameDisplay.gamePieces.GamePiece;
import java.util.ArrayList;
import java.util.Collection;

public class BoardManager {
    private ArrayList<String> userAdded;
    private BoardView myBoardView;
    private ViewerControllerInterface myController;
    private BuilderButtons myBuilderButtons;
    private static final int ROW = 0;
    private static final int COL = 1;

    public BoardManager(ViewerControllerInterface controller, BoardView boardView, BuilderButtons builderButtons) {
        myController = controller;
        myBoardView = boardView;
        myBuilderButtons = builderButtons;
        userAdded = new ArrayList<>();
    }

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
        newNode.getPiece().setId(String.format("%d,%d,%s", row, col, myBuilderButtons.getClassName(newNode)));
        if (!userAdded.contains(newNode.getPiece().getId())) {
            userAdded.add(newNode.getPiece().getId());
        }
    }

    private void updateColor(GamePiece newNode) {
        Node temp = newNode.getPiece();
        Node temp2 = myBuilderButtons.getSelected().getPiece();
        temp.getStyleClass().add(temp2.getStyle());
    }

    public int getPosition(String myID, int i ) {
        String[] position = myID.split(",");
        return Integer.parseInt(position[i]);
    }

    public ArrayList<String> getUserAdded() {
        return userAdded;
    }

}
