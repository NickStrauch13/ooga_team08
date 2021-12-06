package ooga.view.home.center;

import java.io.File;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Popup;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.controller.ViewerControllerInterface;
import ooga.view.UINodeFactory.UINodeFactory;
import ooga.view.boardBuilder.BuilderDisplay;
import ooga.view.gameDisplay.GameDisplay;
import ooga.view.home.HomeScreen;
import ooga.view.popups.PopupFactory;

public class HomeButtons {

  public static final String GUEST = "Guest";
  public static final String HIGH_SCORES = "HighScores";
  public static final String NEW_GAME = "NewGame";
  public static final String BUILD_BOARD = "BuildBoard";
  public static final String USER_NAME_TEXT = "userNameText";
  public static final String INPUT_TEXT_ID = "inputTextID";
  public static final String HOME_SCREEN_BUTTON = "homeScreenButton";
  public static final String HIGH_SCORES_BUTTON = "highScoresButton";
  public static final String NEW_GAME_BUTTON = "newGameButton";
  public static final String BUILD_BOARD_BUTTON = "buildBoardButton";
  public static final String USER_NAME_FIELD_ID = "userNameFieldID";
  public static final String HOME_COL_FORMAT = "homeColFormat";
  public static final String PLAYER_LABEL = "PlayerLabel";
  public static final String PLAYER_LABEL_ID = "PlayerLabelID";
  public static final String PLAYER_TS_LABL = "PlayerTSLabl";
  public static final String HOME_ROW_FORMAT = "homeRowFormat";
  public static final String HIGH_SCORE_TITLE = "HighScoreTitle";
  public static final String EXIT_INSTRUCTIONS = "ExitInstructions";
  public static final String SCORE_EXIT_ID = "ScoreExitID";
  public static final String SCORE_ENTRY_ID = "ScoreEntryID";
  public static final String LOAD_FILE = "LoadFile";
  public static final String JSON = "JSON";
  public static final String JSON_PATH = "*.json";
  public static final String BOARD_BUILDER = "Board Builder";
  private ViewerControllerInterface myController;
  private UINodeFactory myNodeBuilder;
  private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
  private ResourceBundle myResources;
  private Stage myStage;
  private static final String SCORE_DIVIDER = "%s-----------%s";
  private int myHeight;
  private int myWidth;
  private String myUsername;
  private Label myPlayerNameLabel;
  private Label myPlayerTopScore;

  public HomeButtons(ViewerControllerInterface controller, Stage stage, int width, int height){
    myController = controller;
    myNodeBuilder = new UINodeFactory(myController);
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myController.getLanguage());
    myStage = stage;
    myWidth = width;
    myHeight = height;
    myUsername = myResources.getString(GUEST);
  }

  /**
   * Creates the buttons for the home screen display.
   * @return VBox containing all the button nodes.
   */
  public Node makeCenterButtons(){
    Button highScoresButton = myNodeBuilder.makeButton(myResources.getString(HIGH_SCORES),null,
        HOME_SCREEN_BUTTON, HIGH_SCORES_BUTTON,e -> displayHighScores());
    Button newGameButton = myNodeBuilder.makeButton(myResources.getString(NEW_GAME), null,
        HOME_SCREEN_BUTTON, NEW_GAME_BUTTON,e -> startNewGame());
    Button buildBoardButton = myNodeBuilder.makeButton(myResources.getString(BUILD_BOARD), null,
        HOME_SCREEN_BUTTON, BUILD_BOARD_BUTTON,e -> startBoardBuilder());
    Label inputText = myNodeBuilder.makeLabel(myResources.getString(USER_NAME_TEXT), INPUT_TEXT_ID);
    TextField userName = myNodeBuilder.makeInputField(USER_NAME_FIELD_ID, e -> setUserName(e), "");
    Node row1 = myNodeBuilder.makeRow(HOME_COL_FORMAT, highScoresButton, newGameButton, buildBoardButton);
    Node row2 = myNodeBuilder.makeRow(HOME_COL_FORMAT, inputText, userName);
    myPlayerNameLabel = myNodeBuilder.makeLabel(String.format(myResources.getString(PLAYER_LABEL), myUsername),
        PLAYER_LABEL_ID);
    myPlayerTopScore = myNodeBuilder.makeLabel(String.format(myResources.getString(PLAYER_TS_LABL), myUsername, myController.getTopScoreForUser()), "PlayerTopScoreID");
    return myNodeBuilder.makeCol(HOME_ROW_FORMAT, row1, row2, myPlayerNameLabel, myPlayerTopScore);
  }


  private void setUserName(String userName) {
    myUsername = userName;
    myController.setUsername(userName);
    myPlayerNameLabel.setText(String.format(myResources.getString(PLAYER_LABEL), myUsername));
    myPlayerTopScore.setText(String.format(myResources.getString(PLAYER_TS_LABL), myUsername, myController.getTopScoreForUser()));
  }

  private void displayHighScores(){
    PopupFactory highScoreView = new PopupFactory(myController);
    makeHighScoreView(highScoreView, myController.getScoreData());
  }

  private void makeHighScoreView(PopupFactory highScoreView, List<String[]> testList) {
    Popup scorePopup = highScoreView.makePopup(HIGH_SCORE_TITLE);
    addScores(testList, highScoreView.getMyVBox());
    highScoreView.addExitInfo(EXIT_INSTRUCTIONS, SCORE_EXIT_ID);
    highScoreView.showPopup(myStage, scorePopup);
  }

  private void addScores(List<String[]> scores, VBox box) {
    for(String[] score: scores){
      String scoreText = String.format(SCORE_DIVIDER, score[0], score[1]);
      Label entry = myNodeBuilder.makeLabel(scoreText, SCORE_ENTRY_ID);
      box.getChildren().add(entry);
    }
  }

  private void startNewGame() {
    readFile();
    GameDisplay gameDisplay = new GameDisplay(myStage, myWidth, myHeight, myController.getLanguage(), myController, myController.getBoardView());
    gameDisplay.setMainDisplay();
  }

  private boolean readFile(){
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle(LOAD_FILE);
    fileChooser.getExtensionFilters().addAll(new ExtensionFilter(JSON, JSON_PATH));
    File selectedFile = fileChooser.showOpenDialog(myStage);
    if (selectedFile == null) {
      return false;
    }
    else {
      myController.initializeGame(selectedFile.getPath());
      if (myController.getBoardView() == null) {
        return false;
      }
      return true;

    }
  }

  private void startBoardBuilder() {
    BuilderDisplay builderDisplay = new BuilderDisplay(myStage, myWidth, myHeight, myController);
    builderDisplay.setMainDisplay(BOARD_BUILDER);
  }
}
