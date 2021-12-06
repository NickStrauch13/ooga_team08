package ooga.view.home.bottom;

import java.io.File;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ooga.controller.ViewerControllerInterface;
import ooga.view.UINodeFactory.UINodeFactory;
import ooga.view.gameDisplay.GameDisplay;
import ooga.view.home.HomeScreen;

public class HomeDropDowns {
  private ViewerControllerInterface myController;
  private UINodeFactory myNodeBuilder;
  private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
  private ResourceBundle myResources;
  private Stage myStage;
  private int myHeight;
  private int myWidth;
  private static final String GAME_FILE_PATH = "./data/game/%s.json";

  public HomeDropDowns(ViewerControllerInterface controller, Stage stage, int width, int height){
    myController = controller;
    myNodeBuilder = new UINodeFactory(myController);
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myController.getLanguage());
    myStage = stage;
    myWidth = width;
    myHeight = height;
  }

  /**
   * Make the drop-down menus for the home screen.
   * @return HBox node containing all the drop-down boxes.
   */
  public Node makeDropDowns(){
    Node langVBox = makeLangVBox();
    Node cssModeVBox = makeCSSVBox();
    Node gameSelectVBox = makeGameSelectBox();
    Node bottomHBox = myNodeBuilder.makeRow("bottomRowFormatID", langVBox, cssModeVBox, gameSelectVBox);
    return bottomHBox;
  }

  private Node makeLangVBox() {
    ChoiceBox languageBox = myNodeBuilder.makeChoiceBox("langBoxID", myResources.getString("English"),
        myResources.getString("Spanish"), myResources.getString("Italian"),myResources.getString("French"), myResources.getString("Esperanto"));
    languageBox.setOnAction(e->changeLanguage((String)languageBox.getSelectionModel().getSelectedItem()));
    Label langLabel = myNodeBuilder.makeLabel(myResources.getString("LangLabel"), "LangLabelID");
    Node langVBox = myNodeBuilder.makeCol("langColFormatID", langLabel, languageBox);
    return langVBox;
  }

  private Node makeCSSVBox(){
    ChoiceBox cssBox = myNodeBuilder.makeChoiceBox("cssModeBoxID", "Default", "Dark", "Duke", "UNC"); //TODO get rid of hard strings
    cssBox.setOnAction(e->changeCSS((String)cssBox.getSelectionModel().getSelectedItem()));
    Label cssModeLabel = myNodeBuilder.makeLabel(myResources.getString("cssModeLabel"), "cssModeLabelID");
    Node cssVBox = myNodeBuilder.makeCol("cssModeColFormatID",cssModeLabel, cssBox);
    return cssVBox;
  }

  private Node makeGameSelectBox(){
    ChoiceBox gameBox = myNodeBuilder.makeChoiceBox("gameSelectBoxID", "Pacman", "mrsPacman", "PacmanExtreme", "MazeGame", "EasyMaze"); //TODO
    gameBox.setOnAction((e->loadGame((String)gameBox.getSelectionModel().getSelectedItem())));
    Label gameSelectLabel = myNodeBuilder.makeLabel(myResources.getString("gameSelectText"), "GameSelectID");
    Node gameSelectVBox = myNodeBuilder.makeCol("gameSelectorBoxID",gameSelectLabel, gameBox);
    return gameSelectVBox;
  }

  private void changeLanguage(String newLang){
    myController.setUILanguage(newLang);
    HomeScreen newHome  = new HomeScreen(myStage, myWidth, myHeight, myController);
    newHome.setMainDisplay();
  }

  private void changeCSS(String newCSS){
    myController.setViewMode(newCSS);
    HomeScreen newCSSHome = new HomeScreen(myStage, myWidth, myHeight, myController);
    newCSSHome.setMainDisplay();
  }

  private void loadGame(String gameFileName){
    myController.initializeGame(String.format(GAME_FILE_PATH, gameFileName));
    GameDisplay gameDisplay = new GameDisplay(myStage, myWidth, myHeight, myController.getLanguage(), myController, myController.getBoardView());
    gameDisplay.setMainDisplay();
  }


}
