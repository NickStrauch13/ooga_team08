package ooga.view.gameDisplay.top;

import javafx.scene.layout.HBox;
import ooga.controller.ViewerControllerInterface;
import ooga.view.UINodeFactory.UINodeFactory;
import javafx.scene.control.Label;
import javafx.scene.Node;

import java.util.ResourceBundle;

public class GameStats {

    public static final String STATS_HOLDER = "statsHolder";
    public static final String LEVEL_TEXT = "LevelText";
    public static final String LEVEL_TEXT_ID = "levelTextID";
    public static final String NUM_LEVEL_ID = "numLevelID";
    public static final String STATS_FORMAT = "statsFormat";
    public static final String GAME_TYPE_ID = "gameTypeID";
    public static final String GAME_TEXT = "GameText";
    public static final String GAME_TYPE_TEXT_ID = "gameTypeTextID";
    public static final String STATS_FORMAT1 = "statsFormat";
    public static final String NUM_SCORE_ID = "numScoreID";
    public static final String SCORE_TEXT_ID = "scoreTextID";
    public static final String SCORE_TEXT = "ScoreText";
    public static final String STATS_FORMAT2 = "statsFormat";
    public static final String TIME_ID = "timeID";
    public static final String TIMER_TEXT = "TimerText";
    public static final String TIMER_TEXT_ID = "timerTextID";
    public static final String TIMER_FORMAT = "timerFormat";
    public static final String NUM_LIVES_ID = "numLivesID";
    public static final String LIVES_TEXT = "LivesText";
    public static final String LIVES_TEXT_ID = "livesTextID";
    public static final String STATS_FORMAT3 = "statsFormat";
    public static final String TIME_FORMAT = "%d";
    public static final String EMPTY = "";
    private static final String PERIOD = ".";
    private static final String SLASH = "/";
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
    private PlayerProfile myPlayerProfile;

    public GameStats(ViewerControllerInterface controller) {
        myController = controller;
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myController.getLanguage());
        nodeBuilder = new UINodeFactory(myController);
        DEFAULT_STYLESHEET = SLASH + DEFAULT_RESOURCE_PACKAGE.replace(PERIOD, SLASH) + myController.getViewMode();
        myPlayerProfile = new PlayerProfile(controller);
    }

    /**
     * Creates the stats labels in the game screen.
     * @param timer Timer start value.
     * @param lives Lives start value.
     * @return HBox node with the labels.
     */
    public HBox makeStatLabels(int timer, int lives){
        Node livesVBox = makeLivesBox();
        Node timerVBox = makeTimerBox();
        Node scoreVBox = makeScoreBox();
        Node gameTypeVBox = makeGameTitleBox();
        Node levelVBox = makeLevelBox();
        Node playerBox = myPlayerProfile.generateProfile();
        HBox myHBox = nodeBuilder.makeRow(STATS_HOLDER, scoreVBox,gameTypeVBox, levelVBox);
        if (timer >= 0) {
            myHBox.getChildren().add(timerVBox);
        }
        if (lives >=0) {
            myHBox.getChildren().add(livesVBox);
        }
        myHBox.getChildren().add(playerBox);
        return myHBox;
    }

    private Node makeLevelBox() {
        Label levelText = nodeBuilder.makeLabel(myResources.getString(LEVEL_TEXT), LEVEL_TEXT_ID);
        numLevelText = nodeBuilder.makeLabel(SPACE + myController.getGameController().getLevel(), NUM_LEVEL_ID);
        Node levelVBox = nodeBuilder.makeCol(STATS_FORMAT, levelText, numLevelText);
        return levelVBox;
    }

    private Node makeGameTitleBox() {
        Label gameType = nodeBuilder.makeLabel(EMPTY + myController.getGameType().toLowerCase(), GAME_TYPE_ID);
        Label gameText = nodeBuilder.makeLabel(myResources.getString(GAME_TEXT), GAME_TYPE_TEXT_ID);
        Node gameTypeVBox = nodeBuilder.makeCol(STATS_FORMAT1, gameText, gameType);
        return gameTypeVBox;
    }

    private Node makeScoreBox() {
        numScoreText = nodeBuilder.makeLabel(SPACE + myController.getGameController().getScore(), NUM_SCORE_ID);
        Label scoreText = nodeBuilder.makeLabel(myResources.getString(SCORE_TEXT), SCORE_TEXT_ID);
        Node scoreVBox = nodeBuilder.makeCol(STATS_FORMAT2, scoreText, numScoreText);
        return scoreVBox;
    }

    private Node makeTimerBox() {
        timeText = nodeBuilder.makeLabel(SPACE+myController.getGameController().getGameTime(), TIME_ID);
        Label timerText = nodeBuilder.makeLabel(myResources.getString(TIMER_TEXT), TIMER_TEXT_ID);
        Node timerVBox = nodeBuilder.makeCol(TIMER_FORMAT, timerText, timeText);
        return timerVBox;
    }

    private Node makeLivesBox() {
        numLivesText = nodeBuilder.makeLabel(SPACE + myController.getGameController().getLives(), NUM_LIVES_ID);
        Label livesText = nodeBuilder.makeLabel(myResources.getString(LIVES_TEXT), LIVES_TEXT_ID);
        Node livesVBox = nodeBuilder.makeCol(STATS_FORMAT3, livesText, numLivesText);
        return livesVBox;
    }

    /**
     * Sets the score label from the game.
     * @param score integer score value
     */
    public void setScoreText(int score) {
        numScoreText.setText(String.format(TIME_FORMAT,score));
    }

    /**
     * Sets the number of lives label from the game
     * @param lives int number of lives
     */
    public void setLivesText(int lives) {
        numLivesText.setText(String.format(TIME_FORMAT,lives));
    }

    /**
     * Sets the current time label from the game.
     * @param time integer time value
     */
    public void setTimeText(int time){timeText.setText(String.format(TIME_FORMAT, time));}

    /**
     * Sets the number of levels text.
     * @param level integer number of lives.
     */
    public void setLevelText(int level) {
        numLevelText.setText(String.format(TIME_FORMAT, level));
    }
}
