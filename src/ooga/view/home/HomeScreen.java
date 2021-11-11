package ooga.view.home;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
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
    setupScene();
    //Add css styling
    return scene;
  }

  private void setupScene() {
    Node row1 = homeButtons();
    root.setCenter(row1);
}

  private Node homeButtons(){
    Button loadFileButton = myNodeBuilder.makeButton(myResources.getString("HighScores"), "homeScreenButton","highScoresButton",e -> readFile());
    Button newGameButton = myNodeBuilder.makeButton(myResources.getString("NewGame"), "homeScreenButton","newGameButton",e -> startNewGame());
    Label inputText = myNodeBuilder.makeLabel("userNameText");
    TextField userName = myNodeBuilder.makeInputField("userName", e -> setUserName(e), "");
    Node row1 = myNodeBuilder.makeRow("homeColFormat", loadFileButton, newGameButton);
    Node row2 = myNodeBuilder.makeRow("homeColFormat", inputText, userName);
    return myNodeBuilder.makeCol("homeRowFormat", row1, row2);
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  private void readFile(){
    System.out.println("read file");
  }

  private void startNewGame() {
    GameDisplay gameDisplay = new GameDisplay(myWidth, myHeight, "Default", language,  "Pacman");
    gameDisplay.setMainDisplay(myStage, "Pacman");
  }



  /*
   * public Scene setupDisplay(int width, int height, String viewMode) {
    STYLESHEET = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/") + viewMode + ".css";
    root = new BorderPane();
    createCellGrid();
    buttonPane = new GridPane();
    buttonPane.getStyleClass().add("button-box");
    FileChooser csvFileChooser = new FileChooser();
    FileChooser simFileChooser = new FileChooser();
    csvFileChooser.getExtensionFilters().add(new ExtensionFilter("CSV File", "*.csv"));
    createButtonRow1(csvFileChooser, simFileChooser);
    createButtonRow2();
    createButtonRow3();
    createButtonRow4();
    root.setCenter(cellPane);
    root.setBottom(buttonPane);
    myScene = new Scene(root, width, height);
    myScene.getStylesheets().add(getClass().getResource(STYLESHEET).toExternalForm());
    return myScene;
  }
   */
}
