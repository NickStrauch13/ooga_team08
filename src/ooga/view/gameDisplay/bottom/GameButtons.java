package ooga.view.gameDisplay.bottom;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.view.UINodeBuilder.UINodeBuilder;
import ooga.view.home.HomeScreen;

public class GameButtons {
  private static final int SPACING = 5;
  private UINodeBuilder myNodeBuilder;
  private Stage myStage;
  private int myWidth;
  private int myHeight;
  private ResourceBundle myResources;
  private String language = "English";
  private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
  private final String ICONS = String.format("/%sicons/", DEFAULT_RESOURCE_PACKAGE.replace(".", "/"));
  private final ImageView PLAY_ICON = new ImageView(String.format("%splay.png", ICONS));
  private final ImageView PAUSE_ICON = new ImageView(String.format("%spause.png", ICONS));
  private Controller myController;

  public GameButtons(Stage stage, int width, int height, Controller controller){
    myController = controller;
    myStage = stage;
    myWidth = width;
    myHeight = height;
    myNodeBuilder = new UINodeBuilder();
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
  }

  public Node makeButtonBox(){
    HBox buttonBox = new HBox();
    buttonBox.setSpacing(SPACING);
    buttonBox.getChildren().add(myNodeBuilder.makeButton(myResources.getString("GoHomeButton"), null, "GoHomeButton","HomeButtonID" ,e -> goHome()));
    buttonBox.getChildren().add(myNodeBuilder.makeButton("",PLAY_ICON, "PausePlayButton", "PlayButtonID",e -> playPause()));
    buttonBox.getChildren().add(myNodeBuilder.makeButton(myResources.getString("Reset"), null, "ResetButton","ResetButtonID", e -> reset()));
    buttonBox.getStyleClass().add("BottomGameButtons");
    return buttonBox;
  }


  private void goHome(){
    HomeScreen homeScreen = new HomeScreen(myStage, myWidth, myHeight, myController);
    homeScreen.setMainDisplay("Home");
  }

  private void playPause(){

  }

  private void reset(){

  }



}
