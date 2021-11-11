package ooga.view.gameDisplay;

import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.scene.Scene;

public class GameDisplay {
    private Scene myScene;
    private BorderPane root;
    private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
    private static final String DEFAULT_STYLESHEET =
            "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/") + "Default.css";

    public GameDisplay(int width, int height, String viewMode, String language,  String gameType) {
        root = new BorderPane();
        myScene = new Scene(root, width, height);
        myScene.getStylesheets().add(getClass().getResource(DEFAULT_STYLESHEET).toExternalForm());
    }

    public void setMainDisplay(Stage stage, String title) {
        stage.setTitle(title);
        stage.setScene(myScene);
        root.setCenter(new Text("HI"));
    }


}
