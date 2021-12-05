package ooga.view.home;

import java.io.File;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Popup;
import javafx.stage.Stage;
import ooga.controller.ViewerControllerInterface;
import ooga.view.UINodeFactory.UINodeFactory;
//import ooga.view.boardBuilder.BuilderDisplay;
import ooga.view.gameDisplay.GameDisplay;
import ooga.view.popups.PopupFactory;

public class HomeScreen {
  private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
  private static String DEFAULT_STYLESHEET;
  private BorderPane root;
  private int myWidth;
  private int myHeight;
  private Stage myStage;
  private UINodeFactory myNodeBuilder;
  private ResourceBundle myResources;
  private Scene myScene;
  private static final String SCORE_DIVIDER = "%s-----------%s";
  private ViewerControllerInterface myController;

  public HomeScreen(Stage stage, int width, int height, ViewerControllerInterface controller) {
    root = new BorderPane();
    myController = controller;
    myWidth = width;
    myHeight = height;
    myStage = stage;
    myScene = new Scene(root, myWidth, myHeight);
    DEFAULT_STYLESHEET = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/") + myController.getViewMode();
    myScene.getStylesheets().add(getClass().getResource(DEFAULT_STYLESHEET).toExternalForm());
    myNodeBuilder = new UINodeFactory(myController);
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myController.getLanguage());
  }

  /**
   * Creates the home screen scene.
   * @return the created scene object
   */

  public Scene createScene(){
    setupScene();
    //Add css styling?
    return myScene;
  }

  /**
   * Sets the new scene which will show the home screen.
   * @param title title for the stage.
   */
  public void setMainDisplay(String title) {
    setupScene();
    myStage.setTitle(title);
    myStage.setScene(myScene);
  }

  private void setupScene() {
    //Add code here to add more features to the home screen...
    Node row = homeButtons();
    root.setCenter(row);
}

  private Node homeButtons(){
    Button highScoresButton = myNodeBuilder.makeButton(myResources.getString("HighScores"),null, "homeScreenButton","highScoresButton",e -> displayHighScores());
    Button newGameButton = myNodeBuilder.makeButton(myResources.getString("NewGame"), null,"homeScreenButton","newGameButton",e -> startNewGame());
    //Button buildBoardButton = myNodeBuilder.makeButton(myResources.getString("BuildBoard"), null,"homeScreenButton","buildBoardButton",e -> startBoardBuilder());
    Label inputText = myNodeBuilder.makeLabel(myResources.getString("userNameText"), "inputTextID");
    TextField userName = myNodeBuilder.makeInputField("userNameFieldID", e -> setUserName(e), "");
    Node row1 = myNodeBuilder.makeRow("homeColFormat", highScoresButton, newGameButton);//TODO buildBoardButton
    Node row2 = myNodeBuilder.makeRow("homeColFormat", inputText, userName);
    return myNodeBuilder.makeCol("homeRowFormat", row1, row2);
  }

  public void setUserName(String userName) { myController.setUsername(userName); }

  private void readFile(){
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("LoadFile");
    fileChooser.getExtensionFilters().addAll(new ExtensionFilter("JSON", "*.json"));
    File selectedFile = fileChooser.showOpenDialog(myStage);
    if (selectedFile == null) {
      return;
    }
    else {
      try {
        myController.initializeGame(selectedFile.getPath());
      }
      catch (Exception e)  {
        e.printStackTrace(); //TODO update this error handling
      }
    }
  }

  private void startNewGame() {
    readFile();
    GameDisplay gameDisplay = new GameDisplay(myStage, myWidth, myHeight, "Default", myController.getLanguage(),  "Pacman", myController, myController.getBoardView());
    gameDisplay.setMainDisplay("Pacman");
  }

//  private void startBoardBuilder() {
//    BuilderDisplay builderDisplay = new BuilderDisplay(myStage, myWidth, myHeight, myController);
//    builderDisplay.setMainDisplay("Board Builder");
//  }

  private void displayHighScores(){
    PopupFactory highScoreView = new PopupFactory(myController);
    makeHighScoreView(highScoreView, myController.getScoreData());
  }

  private void makeHighScoreView(PopupFactory highScoreView, List<String[]> testList) {
    Popup scorePopup = highScoreView.makePopup("HighScoreTitle");
    addScores(testList, highScoreView.getMyVBox());
    highScoreView.addExitInfo("ExitInstructions", "ScoreExitID");
    highScoreView.showPopup(myStage, scorePopup);
  }

  public void addScores(List<String[]> scores, VBox box) {
    for(String[] score: scores){
      String scoreText = String.format(SCORE_DIVIDER, score[0], score[1]);
      Label entry = myNodeBuilder.makeLabel(scoreText, "ScoreEntryID");
      box.getChildren().add(entry);
    }

  }

  /**
   *METHOD ONLY FOR TESTFX TESTS. Needed some way to load in a file in the testfx tests...
   */
  public void startNewGameForViewTests(String filePath){
    myController.initializeGame(filePath);
    GameDisplay gd = new GameDisplay(myStage, myWidth, myHeight, "Default",
        myController.getLanguage(),  "Pacman", myController, myController.getBoardView());
    gd.setMainDisplay("Test");
  }

}
