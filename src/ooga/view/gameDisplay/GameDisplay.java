package ooga.view.gameDisplay;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import ooga.controller.ViewerControllerInterface;
import ooga.view.UINodeFactory.UINodeFactory;
import ooga.view.gameDisplay.bottom.*;
import ooga.view.gameDisplay.center.*;
import javafx.scene.Scene;
import ooga.view.gameDisplay.top.GameStats;
import ooga.view.popups.PopupFactory;
import java.util.ResourceBundle;

public class GameDisplay {

    public static final String GAME_OVER_POPUP = "GameOverPopup";
    public static final String GO_HOME_BUTTON = "GoHomeButton";
    public static final String GAME_OVER_HOME = "GameOverHome";
    public static final String GAME_OVER_HOME_ID = "GameOverHomeID";
    public static final String GAME_OVER_RESTART_ID = "GameOverRestartID";
    public static final String RESET = "Reset";
    public static final String HOME_ROW_FORMAT = "homeRowFormat";
    public static final String EXIT_INSTRUCTIONS = "ExitInstructions";
    public static final String SCORE_EXIT_ID = "ScoreExitID";
    private Stage myStage;
    private Scene myScene;
    private BorderPane root;
    private GameButtons myGameButtons;
    private BoardView myBoardView;
    private GameStats myGameStats;
    private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
    private static String DEFAULT_STYLESHEET;
    private ViewerControllerInterface myController;
    private SimulationManager mySimManager;
    private ResourceBundle myResources;

    public GameDisplay(Stage stage, int width, int height, String language, ViewerControllerInterface controller, BoardView boardView) {
        myController = controller;
        myBoardView = boardView;
        myGameStats = new GameStats(myController);
        mySimManager = new SimulationManager(myController,myGameStats, boardView, this);
        myGameButtons = new GameButtons(stage, width, height, myController, mySimManager, language);
        mySimManager.linkGameButtons(myGameButtons);
        myStage = stage;
        root = new BorderPane();
        myScene = new Scene(root, width, height);
        myScene.setOnKeyPressed(e -> mySimManager.handleKeyInput(e.getCode()));
        DEFAULT_STYLESHEET = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/") + myController.getViewMode();
        myScene.getStylesheets().add(getClass().getResource(DEFAULT_STYLESHEET).toExternalForm());
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    }

    /**
     * Sets the new scene which will show the actual pacman games
     */
    public void setMainDisplay() {
        setupScene();
        myStage.setScene(myScene);
    }

    public void showGameOverPopup() {
        UINodeFactory UINodeFactory = new UINodeFactory(myController);
        PopupFactory myPopupFactory = new PopupFactory(myController);
        Popup gameOverPopup = myPopupFactory.makePopup(GAME_OVER_POPUP);
        Node popupHome = UINodeFactory.makeButton(myResources.getString(GO_HOME_BUTTON), null,
            GAME_OVER_HOME, GAME_OVER_HOME_ID, e -> myGameButtons.goHome());
        Node popupRestart = UINodeFactory.makeButton(myResources.getString(RESET), null,
            GAME_OVER_HOME, GAME_OVER_RESTART_ID, e -> myGameButtons.restartGame());
        HBox buttonRow =(HBox) UINodeFactory.makeRow(HOME_ROW_FORMAT, popupHome, popupRestart);
        myPopupFactory.getMyVBox().getChildren().addAll(buttonRow);
        myPopupFactory.addExitInfo(EXIT_INSTRUCTIONS, SCORE_EXIT_ID);
        myPopupFactory.showPopup(myStage, gameOverPopup);
    }

    private void setupScene(){
        root.setCenter(myBoardView.getInitialBoard());
        root.setTop(myGameStats.makeStatLabels(myController.getTimer(), myController.getLives()));
        root.setBottom(myGameButtons.makeButtonBox());
    }

    /**
     * Returns the BoardView object
     * @return BoardView object.
     */
    public BoardView getBoardView() {
        return myBoardView;
    }

}
