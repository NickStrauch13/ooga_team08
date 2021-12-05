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
    buttonBox.getChildren().add(myNodeBuilder.makeButton(myResources.getString("GoHomeButton"), null, "GoHomeButton","HomeButtonID" ,e -> goHome()));
    playPauseButton = myNodeBuilder.makeButton("",PLAY_ICON, "PausePlayButton", "PlayButtonID",e -> playPause());
    buttonBox.getChildren().add(playPauseButton);
    buttonBox.getChildren().add(myNodeBuilder.makeButton(myResources.getString("Reset"), null, "ResetButton","ResetButtonID", e -> restartGame()));
    buttonBox.getStyleClass().add("BottomGameButtons");
    return buttonBox;
  }


  public void goHome(){
    mySimManager.playPause();
    mySimManager.stopAnimation();
    HomeScreen homeScreen = new HomeScreen(myStage, myWidth, myHeight, myController);
    homeScreen.setMainDisplay("Home");
  }

  public void playPause(){
    if(mySimManager.playPause()){
      playPauseButton.setGraphic(PAUSE_ICON);
      playPauseButton.getStyleClass().clear();
      playPauseButton.getStyleClass().add("PauseButtonID");
    }
    else{
      playPauseButton.setGraphic(PLAY_ICON);
      playPauseButton.getStyleClass().clear();
      playPauseButton.getStyleClass().add("PlayButtonID");
    }
  }

  public void restartGame(){
    goHome();
    myController.restartGame();
    GameDisplay gameDisplay = new GameDisplay(myStage, myWidth, myHeight, "Default", myLanguage,  "Pacman", myController, myController.getBoardView());
    gameDisplay.setMainDisplay("Pacman");
    myController.restartGame();
  }

}
