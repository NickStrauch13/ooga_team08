package ooga.view.boardBuilder;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import ooga.controller.ViewerControllerInterface;
import ooga.view.UINodeFactory.UINodeFactory;
import ooga.view.boardBuilder.bottom.BuilderButtons;
import ooga.view.gameDisplay.bottom.*;
import ooga.view.gameDisplay.center.*;
import javafx.scene.Scene;
import ooga.view.gameDisplay.gamePieces.GamePiece;
import ooga.view.gameDisplay.gamePieces.MovingPiece;
import ooga.view.gameDisplay.top.GameStats;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BuilderDisplay {
    private Stage myStage;
    private Scene myScene;
    private BorderPane root;
    private GameButtons myGameButtons;
    private BoardView myBoardView;
    private GameStats myGameStats;
    private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
    private static String DEFAULT_STYLESHEET;
    private static final int DEFAULT_BOARD_SIZE = 10;
    private static final int DEFAULT_CELL_SIZE = 25;
    private static final int SPACING = 5;
    private static final String PIECE_PATH = "ooga.view.gameDisplay.gamePieces.%sPiece";
    private ViewerControllerInterface myController;
    private UINodeFactory myNodeBuilder;
    private ResourceBundle myResources;
    private BuilderButtons myBuilderButtons;
    private ArrayList<String> userAdded;

    public BuilderDisplay(Stage stage, int width, int height, ViewerControllerInterface controller) {
        myController = controller;
        myGameStats = new GameStats(myController);
        myBoardView = new BoardView(myController);
        myStage = stage;
        root = new BorderPane();
        myScene = new Scene(root, width, height);
        DEFAULT_STYLESHEET = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/") + myController.getViewMode();
        myScene.getStylesheets().add(getClass().getResource(DEFAULT_STYLESHEET).toExternalForm());
        myNodeBuilder = new UINodeFactory(myController);
        myBuilderButtons = new BuilderButtons(myStage,width, height, myController, DEFAULT_CELL_SIZE, myBoardView, this);
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myController.getLanguage());
        userAdded = new ArrayList<>();

    }

    /**
     * Sets the new scene which will show the actual pacman games
     * @param title The title for the stage
     */
    public void setMainDisplay(String title) {
        setupScene();
        myStage.setTitle(title);
        myStage.setScene(myScene);
    }

    private void setupBoard() {
        myController.setCellSize(DEFAULT_CELL_SIZE);
        myBoardView.getMyGrid().setHgap(SPACING);
        myBoardView.getMyGrid().setVgap(SPACING);
        for (int r = 0; r < DEFAULT_BOARD_SIZE; r++) {
            for (int c = 0; c < DEFAULT_BOARD_SIZE; c++) {
                GamePiece newPiece =  myBoardView.addBoardPiece(r, c, "WALL",null);
                newPiece.getPiece().setOnMouseClicked(e -> updateGrid(newPiece.getPiece()));
                Rectangle temp = (Rectangle)newPiece.getPiece();
                temp.setFill(Color.LIGHTGRAY);
            }
        }
    }

    private void updateGrid(Node oldPiece) {
        myBoardView.removeNode(oldPiece.getId());
        String[] position = oldPiece.getId().split(",");
        String objectClass = myBuilderButtons.getSelected().getClass().toString();
        String objectName = objectClass.substring(objectClass.lastIndexOf(".")+1, objectClass.length()-5);
        GamePiece newNode = myBoardView.addBoardPiece(Integer.parseInt(position[0]), Integer.parseInt(position[1]), objectName, null);
        if (!userAdded.contains(newNode.getPiece().getId())) {
            userAdded.add(newNode.getPiece().getId());
        }
        if (!(myBuilderButtons.getSelected() instanceof MovingPiece)) { //TODO get rid of instanceof
            Shape temp = (Shape)newNode.getPiece();
            Shape temp2 = (Shape)myBuilderButtons.getSelected().getPiece();
            temp.setFill(temp2.getFill());
        }
        newNode.getPiece().setOnMouseClicked(e -> updateGrid(newNode.getPiece()));
    }

    private void setupScene(){
        Label boardBuilderText = myNodeBuilder.makeLabel(myResources.getString("BoardBuilderText"), "BuilderTextID");
        Node myHbox = myNodeBuilder.makeRow("boardBuilderHolder", boardBuilderText);
        root.setTop(myHbox);
        setupBoard();
        root.setCenter(myBoardView.getInitialBoard());
        root.setBottom(myBuilderButtons.makeBottomHBox());
    }

    public void buildBoardFile() {
        for (String id : userAdded) {
            System.out.println(id);
        }
    }
}
