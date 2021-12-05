package ooga.view.gameDisplay.top;

import javafx.scene.layout.HBox;
import ooga.controller.ViewerControllerInterface;
import ooga.view.UINodeFactory.UINodeFactory;
import javafx.scene.control.Label;
import javafx.scene.Node;

import java.util.ResourceBundle;

public class GameStats {
    private UINodeFactory nodeBuilder;
    private ViewerControllerInterface myController;
    private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
    private static String DEFAULT_STYLESHEET;
    private static final String SPACE = "   ";
    private ResourceBundle myResources;
    private Label numScoreText;
    private Label numLivesText;
    private Label numLevelText;
    private Label timeText;

    public GameStats(ViewerControllerInterface controller) {
        myController = controller;
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myController.getLanguage());
        nodeBuilder = new UINodeFactory(myController);
        DEFAULT_STYLESHEET = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/") + myController.getViewMode();
    }

    public HBox makeStatLabels(){
        numLivesText = nodeBuilder.makeLabel(SPACE + myController.getLives(), "numLivesID");
        Label livesText = nodeBuilder.makeLabel(myResources.getString("LivesText"), "livesTextID");
        Node livesVBox = nodeBuilder.makeCol("statsFormat", livesText, numLivesText);
        timeText = nodeBuilder.makeLabel(SPACE+myController.getGameTime(), "timeID");
        Label timerText = nodeBuilder.makeLabel(myResources.getString("TimerText"), "timerTextID");
        Node timerVBox = nodeBuilder.makeCol("timerFormat", timerText, timeText);
        numScoreText = nodeBuilder.makeLabel(SPACE + myController.getScore(), "numScoreID");
        Label scoreText = nodeBuilder.makeLabel(myResources.getString("ScoreText"), "scoreTextID");
        Node scoreVBox = nodeBuilder.makeCol("statsFormat", scoreText, numScoreText);
        Label gameType = nodeBuilder.makeLabel("" + myController.getGameType(), "gameTypeID");
        Label gameText = nodeBuilder.makeLabel(myResources.getString("GameText"), "gameTypeTextID");
        Node gameTypeVBox = nodeBuilder.makeCol("statsFormat", gameText, gameType);
        Label levelText = nodeBuilder.makeLabel(myResources.getString("LevelText"), "levelTextID");
        numLevelText = nodeBuilder.makeLabel(SPACE + myController.getLevel(), "numLevelID");
        Node levelVBox = nodeBuilder.makeCol("statsFormat", levelText, numLevelText);
        Node myHBox = nodeBuilder.makeRow("statsHolder", livesVBox,timerVBox, scoreVBox,gameTypeVBox, levelVBox);
        return (HBox) myHBox;
    }

    /**
     * Sets the score label from the game.
     * @param score integer score value
     */
    public void setScoreText(int score) {
        numScoreText.setText(String.format("%d",score));
    }

    /**
     * Sets the number of lives label from the game
     * @param lives int number of lives
     */
    public void setLivesText(int lives) {
        numLivesText.setText(String.format("%d",lives));
    }

    /**
     * Sets the current time label from the game.
     * @param time integer time value
     */
    public void setTimeText(int time){timeText.setText(String.format("%d", time));}

    /**
     * Sets the number of levels text.
     * @param level integer number of lives.
     */
    public void setLevelText(int level) {
        numLevelText.setText(String.format("%d", level));
    }
}
