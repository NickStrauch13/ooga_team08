package ooga.view;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javafx.scene.control.Button;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import ooga.controller.Controller;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import util.DukeApplicationTest;

public class GameScreenTests extends DukeApplicationTest {
  private Controller myController;
  private Button playButton;

  @Override
  public void start(Stage stage)
      throws IOException, ParseException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    myController = new Controller(stage);
    myController.changeToGameScreen("data/game/basics.json");
  }

  @BeforeEach
  public void setUp() {
    playButton = lookup("#PlayButtonID").query();
  }

  @Test
  public void clickOnGoHomeButton(){
    Button goHomeButton = lookup("#HomeButtonID").query();
    clickOn(goHomeButton);
    sleep(400);
  }

  @Test
  public void clickOnPlayButtonAndLetPacmanMoveInGameBoard(){
    clickOn(playButton);
    sleep(2000);
    clickOn(playButton);
  }

  @Test
  public void clickOnPlayButtonTwiceToPauseAnimation(){
    clickOn(playButton);
    sleep(300);
    clickOn(playButton);
  }

  @Test
  public void clickOnResetButton(){
    Button resetButton = lookup("#ResetButtonID").query();
    clickOn(resetButton);
    //TODO improve test when reset button functionality is added.
  }

  @Test
  public void clickOnPlayThenMovePacmanAndCheckIfPositionChanged(){
    int[] startPos = myController.getUserPosition();
    clickOn(playButton);
    FxRobot robot = new FxRobot();
    sleep(700);
    robot.press(KeyCode.UP).release(KeyCode.UP);
    sleep(2300);
    robot.press(KeyCode.LEFT).release(KeyCode.LEFT);
    sleep(620);
    robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
    sleep(340);
    robot.press(KeyCode.RIGHT).release(KeyCode.RIGHT);
    sleep(500);

    assertNotEquals(myController.getUserPosition()[0], startPos[0]);
    assertNotEquals(myController.getUserPosition()[1], startPos[1]);
  }

}

