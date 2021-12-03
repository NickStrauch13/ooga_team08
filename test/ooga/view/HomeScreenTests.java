package ooga.view;

import java.awt.Dimension;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.view.home.HomeScreen;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class HomeScreenTests extends DukeApplicationTest {
  public static final Dimension DEFAULT_SIZE = new Dimension(1400, 800);
  public static final String TITLE = "Start Screen";
  public Controller myController;

  @Override
  public void start(Stage stage) throws IOException, ParseException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    Controller myController = new Controller(stage);
  }

  @BeforeEach
  public void setUp(){

  }

  @Test
  public void testClickingOnNewGameButton(){
    Button newGameButton = lookup("#newGameButton").query();
    clickOn(newGameButton);
  }

  @Test
  public void testClickingOnHighScoresButton(){
    Button highScoresButton = lookup("#highScoresButton").query();
    clickOn(highScoresButton);
    sleep(200);
  }

  @Test
  public void testEnteringUsername(){
    String username = "Player1";
    TextField userNameBox = lookup("#userName").query();
    writeInputTo(userNameBox, username);
  }
}
