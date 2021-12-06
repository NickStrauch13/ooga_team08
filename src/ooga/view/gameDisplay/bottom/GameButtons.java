package ooga.view.gameDisplay.bottom;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ooga.controller.ViewerControllerInterface;
import ooga.view.UINodeFactory.UINodeFactory;
import ooga.view.gameDisplay.GameDisplay;
import ooga.view.gameDisplay.SimulationManager;
import ooga.view.home.HomeScreen;

public class GameButtons {

  private static final int SPACING = 5;
  public static final String GO_HOME_BUTTON = "GoHomeButton";
  public static final String HOME_BUTTON_ID = "HomeButtonID";
  public static final String PAUSE_PLAY_BUTTON = "PausePlayButton";
  public static final String PLAY_BUTTON_ID = "PlayButtonID";
  public static final String RESET = "Reset";
  public static final String RESET_BUTTON = "ResetButton";
  public static final String RESET_BUTTON_ID = "ResetButtonID";
  public static final String BOTTOM_GAME_BUTTONS = "BottomGameButtons";
  public static final String PAUSE_BUTTON_ID = "PauseButtonID";
  private UINodeFactory myNodeBuilder;
  private Stage myStage;
  private int myWidth;
  private int myHeight;
  private String myLanguage;
  private ResourceBundle myResources;
  private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
  private final String ICONS = String.format("/%sviewIcons/", DEFAULT_RESOURCE_PACKAGE.replace(".", "/"));
  private final ImageView PLAY_ICON = new ImageView(String.format("%splay.png", ICONS));
  private final ImageView PAUSE_ICON = new ImageView(String.format("%spause.png", ICONS));
  private ViewerControllerInterface myController;
  private SimulationManager mySimManager;
  private Button playPauseButton;

  public GameButtons(){};

  public GameButtons(Stage stage, int width, int height, ViewerControllerInterface controller, SimulationManager simManager, String language){
    myController = controller;
    mySimManager = simManager;
    myStage = stage;
    myWidth = width;
    myHeight = height;
    myNodeBuilder = new UINodeFactory(myController);
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    myLanguage = language;
  }


  public Node makeButtonBox(){
    HBox buttonBox = new HBox();
    buttonBox.setSpacing(SPACING);
    buttonBox.getChildren().add(myNodeBuilder.makeButton(myResources.getString(GO_HOME_BUTTON), null,
        GO_HOME_BUTTON, HOME_BUTTON_ID,e -> goHome()));
    playPauseButton = myNodeBuilder.makeButton("",PLAY_ICON, PAUSE_PLAY_BUTTON, PLAY_BUTTON_ID,e -> playPause());
    buttonBox.getChildren().add(playPauseButton);
    buttonBox.getChildren().add(myNodeBuilder.makeButton(myResources.getString(RESET), null,
        RESET_BUTTON, RESET_BUTTON_ID, e -> restartGame()));
    buttonBox.getStyleClass().add(BOTTOM_GAME_BUTTONS);
    return buttonBox;
  }


  public void goHome(){
    mySimManager.playPause();
    mySimManager.stopAnimation();
    HomeScreen homeScreen = new HomeScreen(myStage, myWidth, myHeight, myController);
    homeScreen.setMainDisplay();
  }

  public void playPause(){
    if(mySimManager.playPause()){
      playPauseButton.setGraphic(PAUSE_ICON);
      playPauseButton.getStyleClass().clear();
      playPauseButton.getStyleClass().add(PAUSE_BUTTON_ID);
    }
    else{
      resetPlayButtonIcon();
    }
  }

  public void restartGame(){
    goHome();
    myController.restartGame();
    GameDisplay gameDisplay = new GameDisplay(myStage, myWidth, myHeight,  myLanguage, myController, myController.getBoardView());
    gameDisplay.setMainDisplay();
    myController.restartGame();
  }

  private void resetPlayButtonIcon(){
    playPauseButton.setGraphic(PLAY_ICON);
    playPauseButton.getStyleClass().clear();
    playPauseButton.getStyleClass().add(PLAY_BUTTON_ID);
  }

}
