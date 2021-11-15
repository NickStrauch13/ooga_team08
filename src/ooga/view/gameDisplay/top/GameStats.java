package ooga.view.gameDisplay.top;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ooga.controller.Controller;
import ooga.view.UINodeBuilder.UINodeBuilder;
import javafx.scene.control.Label;
import javafx.scene.Node;

import java.util.ResourceBundle;

public class GameStats {
    private UINodeBuilder nodeBuilder;
    private Controller myController;
    private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
    private static final String DEFAULT_STYLESHEET =
            "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/") + "Default.css";
    private ResourceBundle myResources;
    private static final String language = "English"; //TODO

    public GameStats(Controller controller) {
        myController = controller;
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
        nodeBuilder = new UINodeBuilder();
    }


    public HBox makeStatLabels(){
        Label numLivesText = nodeBuilder.makeLabel("   " + myController.getLives());
        Label livesText = nodeBuilder.makeLabel(myResources.getString("LivesText"));
        Node livesVBox = nodeBuilder.makeCol("statsFormat", livesText, numLivesText);
        Label numScoreText = nodeBuilder.makeLabel("    " + myController.getScore());
        Label scoreText = nodeBuilder.makeLabel(myResources.getString("ScoreText"));
        Node scoreVBox = nodeBuilder.makeCol("statsFormat", scoreText, numScoreText);
        Label gameType = nodeBuilder.makeLabel("" + myController.getGameType());
        Label gameText = nodeBuilder.makeLabel(myResources.getString("GameText"));
        Node gameTypeVBox = nodeBuilder.makeCol("statsFormat", gameType, gameText);
        Node myHbox = nodeBuilder.makeRow("statsHolder", livesVBox,scoreVBox,gameTypeVBox);
        return (HBox) myHbox;
    }
}
