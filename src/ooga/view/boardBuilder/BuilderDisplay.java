package ooga.view.boardBuilder;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.view.UINodeFactory.UINodeFactory;
import ooga.view.boardBuilder.bottom.BuilderButtons;
import ooga.view.gameDisplay.bottom.*;
import ooga.view.gameDisplay.center.*;
import javafx.scene.Scene;
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
    private static final String DEFAULT_STYLESHEET =
            "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/") + "Default.css";
    private static final int DEFAULT_BOARD_SIZE = 10;
    private static final int SPACING = 5;
    private static final String PIECE_PATH = "ooga.view.gameDisplay.gamePieces.%sPiece";
    private int cellSize = 25;
    private Controller myController;
    private UINodeFactory myNodeBuilder;
    private ResourceBundle myResources;
    private BuilderButtons myBuilderButtons;
    private ArrayList<String> userAdded;

    public BuilderDisplay(Stage stage, int width, int height, Controller controller) {
        myController = controller;
        myGameStats = new GameStats(myController);
        myBoardView = new BoardView(myController);
        myStage = stage;
        root = new BorderPane();
        myScene = new Scene(root, width, height);
        myScene.getStylesheets().add(getClass().getResource(DEFAULT_STYLESHEET).toExternalForm());
        myNodeBuilder = new UINodeFactory();
        myBuilderButtons = new BuilderButtons(myStage,width, height, myController, cellSize, myBoardView, this);
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English");
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
        myBoardView.makeBoard(DEFAULT_BOARD_SIZE, DEFAULT_BOARD_SIZE);
        myBoardView.getMyGrid().setHgap(SPACING);
        myBoardView.getMyGrid().setVgap(SPACING);
        for (int r = 0; r < DEFAULT_BOARD_SIZE; r++) {
            for (int c = 0; c < DEFAULT_BOARD_SIZE; c++) {
                Node newPiece =  myBoardView.addBoardPiece(r, c, "WALL");
                newPiece.setOnMouseClicked(e -> updateGrid(newPiece));
                Rectangle temp = (Rectangle)newPiece;
                temp.setFill(Color.LIGHTGRAY);
            }
        }
    }

    private void updateGrid(Node oldPiece) {
        myBoardView.removeNode(oldPiece.getId());
        String[] position = oldPiece.getId().split(",");
        String objectClass = myBuilderButtons.getSelected().getClass().toString();
        String objectName = objectClass.substring(objectClass.lastIndexOf(".")+1, objectClass.length()-5);
        Node newNode = myBoardView.addBoardPiece(Integer.parseInt(position[0]), Integer.parseInt(position[1]), objectName);
        if (!userAdded.contains(newNode.getId())) {
            userAdded.add(newNode.getId());
        }
        if (!(myBuilderButtons.getSelected() instanceof MovingPiece)) { //TODO get rid of instanceof
            Shape temp = (Shape)newNode;
            Shape temp2 = (Shape)myBuilderButtons.getSelected().getPiece();
            temp.setFill(temp2.getFill());
        }
        newNode.setOnMouseClicked(e -> updateGrid(newNode));
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
