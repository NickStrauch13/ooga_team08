package ooga.view.gameDisplay;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.view.gameDisplay.bottom.*;
import ooga.view.gameDisplay.center.*;
import javafx.scene.Scene;
import ooga.view.gameDisplay.top.GameStats;

import java.util.Arrays;

public class GameDisplay {
    private Stage myStage;
    private Scene myScene;
    private BorderPane root;
    private GameButtons myGameButtons;
    private BoardView myBoardView;
    private GameStats myGameStats;
    private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
    private static final String DEFAULT_STYLESHEET =
            "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/") + "Default.css";
    private Controller myController;
    private SimulationManager mySimManager;

    public GameDisplay(Stage stage, int width, int height, String viewMode, String language,  String gameType, Controller controller, BoardView boardView) {
        myController = controller;
        myBoardView = boardView;
        myGameStats = new GameStats(myController);
        mySimManager = new SimulationManager(myController,myGameStats, boardView);
        myStage = stage;
        root = new BorderPane();
        myScene = new Scene(root, width, height);
        myScene.setOnKeyPressed(e -> mySimManager.handleKeyInput(e.getCode()));
        myScene.getStylesheets().add(getClass().getResource(DEFAULT_STYLESHEET).toExternalForm());
        myGameButtons = new GameButtons(stage, width, height, myController, mySimManager);
        myController.resetGame();
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

    private void setupScene(){
        root.setTop(myGameStats.makeStatLabels());
        System.out.println(Arrays.toString(myBoardView.getInitialBoard().getChildren().toArray()));
        root.setCenter(myBoardView.getInitialBoard());
        root.setBottom(myGameButtons.makeButtonBox());
    }



}
