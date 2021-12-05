package ooga.view;

import java.awt.Dimension;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.view.home.HomeScreen;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import util.DukeApplicationTest;

public class HomeScreenTests extends DukeApplicationTest {
  public static final Dimension DEFAULT_SIZE = new Dimension(1400, 800);
  public static final String TITLE = "Start Screen";
  public Controller myController;

  @Override
  public void start(Stage stage) throws IOException, ParseException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    myController = new Controller(stage);
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
  public void testClickingOnHighScoresButtonAndThenCloseHighScoreWindow(){
    FxRobot robot = new FxRobot();
    Button highScoresButton = lookup("#highScoresButton").query();
    clickOn(highScoresButton);
    sleep(600);
    robot.press(KeyCode.ESCAPE).release(KeyCode.ESCAPE);
    sleep(300);
  }

  @Test
  public void testEnteringUsername(){
    String username = "Player1";
    TextField userNameBox = lookup("#userNameFieldID").query();
    writeInputTo(userNameBox, username);
    sleep(400);
    assertEquals(username, myController.getUsername());
  }
}
