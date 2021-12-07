package ooga.view.boardBuilder;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ooga.controller.JSONBuilder;
import ooga.controller.ViewerControllerInterface;
import ooga.view.UINodeFactory.UINodeFactory;
import ooga.view.gameDisplay.GameDisplay;
import ooga.view.gameDisplay.bottom.*;
import ooga.view.gameDisplay.center.*;
import javafx.scene.Scene;
import ooga.view.gameDisplay.gamePieces.GamePiece;
import ooga.view.gameDisplay.top.GameStats;
import java.util.ResourceBundle;

/**
 * This class sets up the board builder display
 * Uses a boardview that can be edited to produce a game board
 * BuilderButtons adds the different GamePieces
 * Author: Neil Mosca
 */
public class BuilderDisplay {
    private static final String WALL = "WALL";
    private static final String D_D_S = "%d,%d,%s";
    private static final String BOARD_BUILDER_HOLDER = "boardBuilderHolder";
    private static final String BOARD_BUILDER_TEXT = "BoardBuilderText";
    private static final String BUILDER_TEXT_ID = "BuilderTextID";
    private static final String MY_BOARD = "MyBoard";
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
                GamePiece wall = myBoardView.addBoardPiece(r, c, WALL, null);
                wall.getPiece().setId(String.format(D_D_S, r, c,wall.getClass().getSimpleName()));
                wall.getPiece().setOnMouseClicked(e -> myBoardManager.updateGrid(wall.getPiece()));

            }
        }
    }

    private void setupScene(){
        Label boardBuilderText = myNodeBuilder.makeLabel(myResources.getString(BOARD_BUILDER_TEXT), BUILDER_TEXT_ID);
        Node myHbox = myNodeBuilder.makeRow(BOARD_BUILDER_HOLDER, boardBuilderText);
        root.setTop(myHbox);
        setupBoard();
        root.setCenter(myBoardView.getInitialBoard());
        root.setBottom(myBuilderButtons.makeBottomHBox());
    }

    /**
     * Starts a new game with the board that was edited by the user
     */
    public void newGameWithBoard() {
        myJSONBuilder.compileBoard(myBoardManager.getUserAdded());
        myController.initializeGame(String.format(myJSONBuilder.getBoardPath(), MY_BOARD));
        GameDisplay gameDisplay = new GameDisplay(myStage, (int)myScene.getWidth(),  (int)myScene.getHeight(), myController.getLanguage(), myController, myController.getBoardView());
        gameDisplay.setMainDisplay();
    }

}
