package ooga.view.boardBuilder;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ooga.controller.JSONBuilder;
import ooga.controller.ViewerControllerInterface;
import ooga.view.UINodeFactory.UINodeFactory;
import ooga.view.gameDisplay.GameDisplay;
import ooga.view.gameDisplay.bottom.*;
import ooga.view.gameDisplay.center.*;
import javafx.scene.Scene;
import ooga.view.gameDisplay.top.GameStats;
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
    private BoardManager myBoardManager;
    private JSONBuilder myJSONBuilder;

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
        myBuilderButtons = new BuilderButtons(myStage,width, height, myController, myBoardView, this);
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myController.getLanguage());
        myBoardManager = new BoardManager(myController, myBoardView, myBuilderButtons);
        myJSONBuilder = new JSONBuilder(myController);
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
                Node gridSpace = new Rectangle(DEFAULT_CELL_SIZE,DEFAULT_CELL_SIZE, Color.LIGHTGRAY);
                myBoardView.getMyGrid().add(gridSpace, c, r);
                gridSpace.setId(String.format("%d,%d,%s", r, c,gridSpace.getClass().getSimpleName()));
                gridSpace.setOnMouseClicked(e -> myBoardManager.updateGrid(gridSpace));

            }
        }
    }

    private void setupScene(){
        Label boardBuilderText = myNodeBuilder.makeLabel(myResources.getString("BoardBuilderText"), "BuilderTextID");
        Node myHbox = myNodeBuilder.makeRow("boardBuilderHolder", boardBuilderText);
        root.setTop(myHbox);
        setupBoard();
        root.setCenter(myBoardView.getInitialBoard());
        root.setBottom(myBuilderButtons.makeBottomHBox());
    }

    public void newGameWithBoard() {
        myController.initializeGame(String.format(myJSONBuilder.getBoardPath(), "MyBoard"));
        GameDisplay gameDisplay = new GameDisplay(myStage, (int)myScene.getWidth(),  (int)myScene.getHeight(), myController.getLanguage(), myController, myController.getBoardView());
        gameDisplay.setMainDisplay();
    }

}
