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

  public static final String BOTTOM_ROW_FORMAT_ID = "bottomRowFormatID";
  public static final String LANG_BOX_ID = "langBoxID";
  public static final String ENGLISH = "English";
  public static final String FRENCH = "French";
  public static final String ESPERANTO = "Esperanto";
  public static final String LANG_LABEL_ID = "LangLabelID";
  public static final String LANG_LABEL = "LangLabel";
  public static final String LANG_COL_FORMAT_ID = "langColFormatID";
  public static final String CSS_MODE_BOX_ID = "cssModeBoxID";
  public static final String DEFAULT = "Default";
  public static final String DARK = "Dark";
  public static final String DUKE = "Duke";
  public static final String UNC = "UNC";
  public static final String CSS_MODE_LABEL = "cssModeLabel";
  public static final String CSS_MODE_LABEL_ID = "cssModeLabelID";
  public static final String CSS_MODE_COL_FORMAT_ID = "cssModeColFormatID";
  public static final String ITALIAN = "Italian";
  public static final String GAME_SELECT_BOX_ID = "gameSelectBoxID";
  public static final String PACMAN = "Pacman";
  public static final String MRS_PACMAN = "mrsPacman";
  public static final String PACMAN_EXTREME = "PacmanExtreme";
  public static final String MAZE_GAME = "MazeGame";
  public static final String EASY_MAZE = "EasyMaze";
  public static final String SPANISH = "Spanish";
  public static final String GAME_SELECT_TEXT = "gameSelectText";
  public static final String GAME_SELECT_ID = "GameSelectID";
  public static final String GAME_SELECTOR_BOX_ID = "gameSelectorBoxID";
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
    Node bottomHBox = myNodeBuilder.makeRow(BOTTOM_ROW_FORMAT_ID, langVBox, cssModeVBox, gameSelectVBox);
    return bottomHBox;
  }

  private Node makeLangVBox() {
    ChoiceBox languageBox = myNodeBuilder.makeChoiceBox(LANG_BOX_ID, myResources.getString(ENGLISH),
        myResources.getString(SPANISH), myResources.getString(ITALIAN),myResources.getString(FRENCH), myResources.getString(
            ESPERANTO));
    languageBox.setOnAction(e->changeLanguage((String)languageBox.getSelectionModel().getSelectedItem()));
    Label langLabel = myNodeBuilder.makeLabel(myResources.getString(LANG_LABEL), LANG_LABEL_ID);
    Node langVBox = myNodeBuilder.makeCol(LANG_COL_FORMAT_ID, langLabel, languageBox);
    return langVBox;
  }

  private Node makeCSSVBox(){
    ChoiceBox cssBox = myNodeBuilder.makeChoiceBox(CSS_MODE_BOX_ID, DEFAULT, DARK, DUKE, UNC);
    cssBox.setOnAction(e->changeCSS((String)cssBox.getSelectionModel().getSelectedItem()));
    Label cssModeLabel = myNodeBuilder.makeLabel(myResources.getString(CSS_MODE_LABEL),
        CSS_MODE_LABEL_ID);
    Node cssVBox = myNodeBuilder.makeCol(CSS_MODE_COL_FORMAT_ID,cssModeLabel, cssBox);
    return cssVBox;
  }

  private Node makeGameSelectBox(){
    ChoiceBox gameBox = myNodeBuilder.makeChoiceBox(GAME_SELECT_BOX_ID, PACMAN, MRS_PACMAN,
        PACMAN_EXTREME, MAZE_GAME, EASY_MAZE);
    gameBox.setOnAction((e->loadGame((String)gameBox.getSelectionModel().getSelectedItem())));
    Label gameSelectLabel = myNodeBuilder.makeLabel(myResources.getString(GAME_SELECT_TEXT),
        GAME_SELECT_ID);
    Node gameSelectVBox = myNodeBuilder.makeCol(GAME_SELECTOR_BOX_ID,gameSelectLabel, gameBox);
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
