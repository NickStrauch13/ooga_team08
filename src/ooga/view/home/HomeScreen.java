package ooga.view.home;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ooga.controller.ViewerControllerInterface;
import ooga.view.UINodeFactory.UINodeFactory;
//import ooga.view.boardBuilder.BuilderDisplay;
import ooga.view.gameDisplay.GameDisplay;
import ooga.view.home.bottom.HomeDropDowns;
import ooga.view.home.center.HomeButtons;


public class HomeScreen {
  private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
  private static String defaultStylesheet;
  private BorderPane root;
  private int myWidth;
  private int myHeight;
  private Stage myStage;
  private Scene myScene;
  private ViewerControllerInterface myController;

  public HomeScreen(Stage stage, int width, int height, ViewerControllerInterface controller) {
    root = new BorderPane();
    myController = controller;
    myWidth = width;
    myHeight = height;
    myStage = stage;
    myScene = new Scene(root, myWidth, myHeight);
    defaultStylesheet = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/") + myController.getViewMode();
    myScene.getStylesheets().add(getClass().getResource(defaultStylesheet).toExternalForm());
  }

  /**
   * Creates the home screen scene.
   * @return the created scene object
   */

  public Scene createScene(){
    setupScene();
    return myScene;
  }

  /**
   * Sets the new scene which will show the home screen.
   */
  public void setMainDisplay() {
    setupScene();
    myStage.setScene(myScene);
  }

  private void setupScene() {
    HomeButtons homeButtons = new HomeButtons(myController, myStage, myWidth, myHeight);
    HomeDropDowns dropDowns = new HomeDropDowns(myController, myStage, myWidth, myHeight);
    root.setCenter(homeButtons.makeCenterButtons());
    root.setBottom(dropDowns.makeDropDowns());
}


  /**
   *METHOD ONLY FOR TESTFX TESTS. Needed some way to load in a file in the testfx tests...
   */
  public void startNewGameForViewTests(String filePath){
    myController.initializeGame(filePath);
    GameDisplay gd = new GameDisplay(myStage, myWidth, myHeight,
        myController.getLanguage(), myController, myController.getBoardView());
    gd.setMainDisplay();
  }


}
