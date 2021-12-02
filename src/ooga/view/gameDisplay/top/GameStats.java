package ooga.view.gameDisplay.top;

import javafx.scene.layout.HBox;
import ooga.controller.Controller;
import ooga.view.UINodeBuilder.UINodeFactory;
import javafx.scene.control.Label;
import javafx.scene.Node;

import java.util.ResourceBundle;

public class GameStats {
    private UINodeFactory nodeBuilder;
    private Controller myController;
    private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
    private static final String DEFAULT_STYLESHEET =
            "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/") + "Default.css";
    private ResourceBundle myResources;
    private static final String language = "English"; //TODO
    private Label numScoreText;
    private Label numLivesText;

    public GameStats(Controller controller) {
        myController = controller;
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
        nodeBuilder = new UINodeFactory();
    }


    public HBox makeStatLabels(){
        numLivesText = nodeBuilder.makeLabel("   " + myController.getLives());
        Label livesText = nodeBuilder.makeLabel(myResources.getString("LivesText"));
        Node livesVBox = nodeBuilder.makeCol("statsFormat", livesText, numLivesText);
        numScoreText = nodeBuilder.makeLabel("    " + myController.getScore());
        Label scoreText = nodeBuilder.makeLabel(myResources.getString("ScoreText"));
        Node scoreVBox = nodeBuilder.makeCol("statsFormat", scoreText, numScoreText);
        Label gameType = nodeBuilder.makeLabel("" + myController.getGameType());
        Label gameText = nodeBuilder.makeLabel(myResources.getString("GameText"));
        Node gameTypeVBox = nodeBuilder.makeCol("statsFormat", gameText, gameType);
        Node myHbox = nodeBuilder.makeRow("statsHolder", livesVBox,scoreVBox,gameTypeVBox);
        return (HBox) myHbox;
    }

    public void setScoreText(int score) {
        numScoreText.setText(String.format("%d",score));
    }
    public void setLivesText(int lives) {
        numLivesText.setText(String.format("%d",lives));
    }
}
