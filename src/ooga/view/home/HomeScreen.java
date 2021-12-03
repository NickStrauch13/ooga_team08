package ooga.view.home;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.view.UINodeFactory.UINodeFactory;
import ooga.view.boardBuilder.BuilderDisplay;
import ooga.view.gameDisplay.GameDisplay;
import ooga.view.popups.HighScoreView;

public class HomeScreen {
  private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
  private static final String DEFAULT_STYLESHEET =
      "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/") + "Default.css";
  private BorderPane root;
  private int myWidth;
  private int myHeight;
  private Stage myStage;
  private UINodeFactory myNodeBuilder;
  private ResourceBundle myResources;
  private Scene myScene;
  private static final String language = "English"; //TODO add to prop file
  private String userName;
  private Controller myController;

  public HomeScreen(Stage stage, int width, int height, Controller controller) {
    root = new BorderPane();
    myController = controller;
    myWidth = width;
    myHeight = height;
    myStage = stage;
    myScene = new Scene(root, myWidth, myHeight);
    myScene.getStylesheets().add(getClass().getResource(DEFAULT_STYLESHEET).toExternalForm());
    myNodeBuilder = new UINodeFactory();
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
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
    Button buildBoardButton = myNodeBuilder.makeButton(myResources.getString("BuildBoard"), null,"homeScreenButton","buildBoardButton",e -> startBoardBuilder());
    Label inputText = myNodeBuilder.makeLabel(myResources.getString("userNameText"), "inputTextID");
    TextField userName = myNodeBuilder.makeInputField("userName", e -> setUserName(e), "");
    Node row1 = myNodeBuilder.makeRow("homeColFormat", highScoresButton, newGameButton, buildBoardButton);
    Node row2 = myNodeBuilder.makeRow("homeColFormat", inputText, userName);
    return myNodeBuilder.makeCol("homeRowFormat", row1, row2);
  }

  public void setUserName(String userName) {this.userName = userName; }

  private void readFile(){
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle(myResources.getString("LoadFile"));
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
    GameDisplay gameDisplay = new GameDisplay(myStage, myWidth, myHeight, "Default", language,  "Pacman", myController, myController.getBoardView());
    gameDisplay.setMainDisplay("Pacman");
  }

  private void startBoardBuilder() {
    BuilderDisplay builderDisplay = new BuilderDisplay(myStage, myWidth, myHeight, myController);
    builderDisplay.setMainDisplay("Board Builder");
  }

  private void displayHighScores(){
    HighScoreView highScoreView = new HighScoreView();
    highScoreView.showHighScores(myController.getScoreData(), myStage);
  }

  /**
   *METHOD ONLY FOR TESTFX TESTS. Needed some way to load in a file in the testfx tests...
   */
  public void startNewGameForViewTests(String filePath){
    myController.initializeGame(filePath);
    GameDisplay gd = new GameDisplay(myStage, myWidth, myHeight, "Default", language,  "Pacman", myController, myController.getBoardView());
    gd.setMainDisplay("Test");
  }

}
