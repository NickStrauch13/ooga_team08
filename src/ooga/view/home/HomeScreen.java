package ooga.view.home;

import java.io.File;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
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
    //Add css styling?
    return scene;
  }

  private void setupScene() {
    Node row = homeButtons();
    root.setCenter(row);
}
  private Node makeButtons(String rowFormatting, Node ... nodes) {
    HBox row = new HBox();
    row.getChildren().addAll(nodes);
    row.setSpacing(5.0);
    row.getStyleClass().add(rowFormatting);
    return row;
  }

  private Node homeButtons(){
    Button loadFileButton = myNodeBuilder.makeButton(myResources.getString("LoadFile"), null, "homeScreenButton" ,e -> readFile());
    Button newGameButton = myNodeBuilder.makeButton(myResources.getString("NewGame"), null, "homeScreenButton",e -> startNewGame());
    return makeButtons("homeRowFormat", loadFileButton, newGameButton);
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
    GameDisplay gameDisplay = new GameDisplay(myWidth, myHeight, "Default", language,  "Pacman");
    gameDisplay.setMainDisplay(myStage, "Pacman");
  }


}
