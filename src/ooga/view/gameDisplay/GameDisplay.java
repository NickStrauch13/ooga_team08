package ooga.view.gameDisplay;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
//import ooga.view.gameDisplay.bottom.GameButtons;
import javafx.scene.Scene;
import ooga.view.UINodeBuilder.UINodeBuilder;

public class GameDisplay {
    private Scene myScene;
    private BorderPane root;
    private UINodeBuilder myNodeBuilder;
    //private GameButtons myGameButtons;
    private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
    private static final String DEFAULT_STYLESHEET =
            "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/") + "Default.css";

    public GameDisplay(int width, int height, String viewMode, String language,  String gameType) {
        root = new BorderPane();
        myScene = new Scene(root, width, height);
        myScene.getStylesheets().add(getClass().getResource(DEFAULT_STYLESHEET).toExternalForm());
        myNodeBuilder = new UINodeBuilder();
    }

    /**
     * Sets the new scene which will show the actual pacman games
     * @param stage
     * @param title
     */
    public void setMainDisplay(Stage stage, String title) {
        stage.setTitle(title);
        stage.setScene(myScene);
    }


    private void setupScene(){
        //root.setTop(myGameStats.makeLabels());
        //root.setCenter(myBoardView.makeBoard());
        //root.setBottom(myGameButtons.makeButtons());
    }

}
