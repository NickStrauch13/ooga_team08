package ooga;

import java.awt.Dimension;
import javafx.application.Application;
import javafx.stage.Stage;
import ooga.view.home.HomeScreen;

public class Main extends Application {

    public static final Dimension DEFAULT_SIZE = new Dimension(1400, 800);
    public static final String TITLE = "Start Screen";

    /**
     * Initializes the display, launches window with initial properties
     */
    @Override
    public void start(Stage stage) {
        HomeScreen startScreen = new HomeScreen(stage, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
        stage.setTitle(TITLE);
        stage.setScene(startScreen.createScene());
        stage.show();
    }
}

