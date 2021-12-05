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

    public GameDisplay(Stage stage, int width, int height, String viewMode, String language, String gameType, ViewerControllerInterface controller, BoardView boardView) {
        myController = controller;
        myBoardView = boardView;
        myGameStats = new GameStats(myController);
        mySimManager = new SimulationManager(myController,myGameStats, boardView, this);
        myStage = stage;
        root = new BorderPane();
        myScene = new Scene(root, width, height);
        myScene.setOnKeyPressed(e -> mySimManager.handleKeyInput(e.getCode()));
        DEFAULT_STYLESHEET = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/") + myController.getViewMode();
        myScene.getStylesheets().add(getClass().getResource(DEFAULT_STYLESHEET).toExternalForm());
        myGameButtons = new GameButtons(stage, width, height, myController, mySimManager, language);
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
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

    public void showGameOverPopup() {
        UINodeFactory UINodeFactory = new UINodeFactory(myController);
        PopupFactory myPopupFactory = new PopupFactory(myController);
        Popup gameOverPopup = myPopupFactory.makePopup("GameOverPopup");
        Node popupHome = UINodeFactory.makeButton(myResources.getString("GoHomeButton"), null, "GameOverHome","GameOverHomeID", e -> myGameButtons.goHome());
        Node popupRestart = UINodeFactory.makeButton(myResources.getString("Reset"), null, "GameOverHome","GameOverRestartID", e -> myGameButtons.restartGame());
        HBox buttonRow =(HBox) UINodeFactory.makeRow("homeRowFormat", popupHome, popupRestart);
        myPopupFactory.getMyVBox().getChildren().addAll(buttonRow);
        myPopupFactory.addExitInfo("ExitInstructions", "ScoreExitID");
        myPopupFactory.showPopup(myStage, gameOverPopup);
    }

    private void setupScene(){
        root.setTop(myGameStats.makeStatLabels());
        root.setCenter(myBoardView.getInitialBoard());
        root.setBottom(myGameButtons.makeButtonBox());
    }

    public BoardView getBoardView() {
        return myBoardView;
    }

}
