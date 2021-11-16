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

public class GameScreenTests extends DukeApplicationTest {
  public Controller myController;

  @Override
  public void start(Stage stage)
      throws IOException, ParseException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    Controller myController = new Controller(stage);
    myController.changeToGameScreen("data/game/basics.json");
  }

  @BeforeEach
  public void setUp() {
  }

  @Test
  public void clickOnGoHomeButton(){
    Button goHomeButton = lookup("#HomeButtonID").query();
    clickOn(goHomeButton);
  }

  @Test
  public void clickOnPlayButtonAndLetPacmanMoveInGameBoard(){
    Button playButton = lookup("#PlayButtonID").query();
    clickOn(playButton);
    sleep(2000);
    clickOn(playButton);
  }

  @Test
  public void clickOnPlayButtonTwiceToPauseAnimation(){
    Button playButton = lookup("#PlayButtonID").query();
    clickOn(playButton);
    clickOn(playButton);
  }

  @Test
  public void clickOnResetButton(){
    Button resetButton = lookup("#ResetButtonID").query();
    clickOn(resetButton);
  }




}

