package ooga.view.home;

import java.io.File;
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
import ooga.view.UINodeBuilder.UINodeBuilder;
import ooga.view.gameDisplay.GameDisplay;

public class HomeScreen {
  private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
  private static final String DEFAULT_STYLESHEET =
      "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/") + "Default.css";
  private BorderPane root;
  private int myWidth;
  private int myHeight;
  private Stage myStage;
  private UINodeBuilder myNodeBuilder;
  private ResourceBundle myResources;
  private static final String language = "English"; //TODO add to prop file
  private String userName;

  public HomeScreen(Stage stage, int width, int height){
    root = new BorderPane();
    myWidth = width;
    myHeight = height;
    myStage = stage;
    myNodeBuilder = new UINodeBuilder();
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
  }

  /**
   * Creates the home screen scene.
   * @return the created scene object
   */
  public Scene setScene(){
    Scene scene = new Scene(root, myWidth, myHeight);
    scene.getStylesheets().add(getClass().getResource(DEFAULT_STYLESHEET).toExternalForm());
    root.setCenter(homeButtons());
    return scene;
  }

  private Node homeButtons(){
    Button loadFileButton = myNodeBuilder.makeButton(myResources.getString("HighScores"),null, "homeScreenButton","highScoresButton",e -> viewHighScores());
    Button newGameButton = myNodeBuilder.makeButton(myResources.getString("NewGame"), null,"homeScreenButton","newGameButton",e -> startNewGame());
    Label inputText = myNodeBuilder.makeLabel("userNameText");
    TextField userName = myNodeBuilder.makeInputField("userName", e -> setUserName(e), "");
    Node row1 = myNodeBuilder.makeRow("homeColFormat", loadFileButton, newGameButton);
    Node row2 = myNodeBuilder.makeRow("homeColFormat", inputText, userName);
    return myNodeBuilder.makeCol("homeRowFormat", row1, row2);
  }

  private void readFile(){
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle(myResources.getString("LoadFile"));
    fileChooser.getExtensionFilters().addAll(new ExtensionFilter("JSON", "*.json"));
    File selectedFile = fileChooser.showOpenDialog(myStage);
    if (selectedFile == null) {
      return;
    }
    //TODO have controller deal with file myController.openJSONFile(selectedFile);
  }

  private void startNewGame() {
    readFile();
    GameDisplay gameDisplay = new GameDisplay(myWidth, myHeight, "Default", language,  "Pacman");
    gameDisplay.setMainDisplay(myStage, "Pacman");
  }

  private void viewHighScores() {
    System.out.println("High Scores");
  }

  private void setUserName(String userName) {
    this.userName = userName;
  }


}
