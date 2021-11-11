package ooga.view.home;

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
import javafx.stage.Stage;
import ooga.view.UINodeBuilder.UINodeBuilder;

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
    setupScene();

    root.getStyleClass().add(getClass().getResource(DEFAULT_STYLESHEET).toExternalForm());
    //Add css styling
    return scene;
  }

  private void setupScene() {
    Node row = makeFileButtons();
    root.setCenter(row);
    root.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(0), Insets.EMPTY)));

}
  private Node makeButtons(Node ... nodes) {
    HBox row = new HBox();
    row.getChildren().addAll(nodes);
    row.getStyleClass().add("button-row");
    return row;
  }


  private Node makeFileButtons(){
    Button loadFileButton = myNodeBuilder.makeButton(myResources.getString("LoadFile"), e -> readFile());
    return makeButtons(loadFileButton);
  }

  private void readFile(){
    System.out.println("read file");
  }


}
