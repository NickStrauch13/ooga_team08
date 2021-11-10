package ooga.view.home;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HomeScreen {
  private BorderPane root;
  private int myWidth;
  private int myHeight;
  private Stage myStage;

  public HomeScreen(Stage stage, int width, int height){
    root = new BorderPane();
    myWidth = width;
    myHeight = height;
    myStage = stage;
  }


  /**
   * Creates the home screen scene.
   * @return the created scene object
   */
  public Scene setScene(){
    Scene scene = new Scene(root, myWidth, myHeight);
    //Add css styling
    return scene;
  }




}
