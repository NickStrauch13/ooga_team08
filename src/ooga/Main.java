package ooga;

import java.awt.Dimension;
import javafx.application.Application;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.view.home.HomeScreen;

public class Main extends Application {

    /**
     * Initializes the display, launches window with initial properties
     */
    @Override
    public void start(Stage stage) {
        Controller myController = new Controller(stage);


    }
}

