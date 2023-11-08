package ooga;

import java.awt.Dimension;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javafx.application.Application;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.view.home.HomeScreen;
import org.json.simple.parser.ParseException;

/**
 * This is our Pacman Project
 * Authors: Abhinav Ratnagiri, David Wu, Neil Mosca, Nick Strauch, and Nick Conterno
 */
public class Main extends Application {

    /**
     * Initializes the display, launches window with initial properties
     */
    @Override
    public void start(Stage stage) throws IOException, ParseException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Controller myController = new Controller(stage);

    }
}

