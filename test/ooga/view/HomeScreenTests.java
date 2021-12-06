package ooga.view;

import java.awt.Dimension;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
  private static final Dimension DEFAULT_SIZE = new Dimension(1400, 800);
  private static final String TITLE = "Start Screen";
  private Controller myController;
  private ChoiceBox myLangBox;
  private ChoiceBox myViewModeBox;
  private ChoiceBox myGameBox;

  @Override
  public void start(Stage stage) throws IOException, ParseException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    myController = new Controller(stage);
  }

  @BeforeEach
  public void setUp(){
    myLangBox = lookup("#langBoxID").query();
    myViewModeBox = lookup("#cssModeBoxID").query();
    myGameBox = lookup("#gameSelectBoxID").query();
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

  @Test
  public void testChangingLanguageToSpanish(){
    select(myLangBox, "Spanish");
    sleep(100);
    assertEquals("Spanish", myController.getLanguage());
  }
  @Test
  public void testChangingLanguageToItalian(){
    select(myLangBox, "Italian");
    sleep(100);
    assertEquals("Italian", myController.getLanguage());
  }
  @Test
  public void testChangingLanguageToEsperanto(){
    select(myLangBox, "Esperanto");
    sleep(100);
    assertEquals("Esperanto", myController.getLanguage());
  }
  @Test
  public void testChangingLanguageToFrench(){
    select(myLangBox, "French");
    sleep(100);
    assertEquals("French", myController.getLanguage());
  }
  @Test
  public void testChangingLanguageToEnglish(){
    select(myLangBox, "English");
    sleep(100);
    assertEquals("English", myController.getLanguage());
  }
  @Test
  public void testChangedViewToDarkMode(){
    select(myViewModeBox, "Dark");
    sleep(200);
    assertEquals("Dark.css", myController.getViewMode());
  }
  @Test
  public void testChangedViewToDukeMode(){
    select(myViewModeBox, "Duke");
    sleep(200);
    assertEquals("Duke.css", myController.getViewMode());
  }
  @Test
  public void testChangedViewToUNCMode(){
    select(myViewModeBox, "UNC");
    sleep(200);
    assertEquals("UNC.css", myController.getViewMode());
  }
  @Test
  public void testChangedViewToDefaultMode(){
    select(myViewModeBox, "Default");
    sleep(200);
    assertEquals("Default.css", myController.getViewMode());
  }

  @Test
  public void testStartingPacmanGameWithQuickGameBox(){
    select(myGameBox, "Pacman");
    sleep(200);
    assertEquals("PACMAN", myController.getGameType());
  }
  @Test
  public void testStartingExtremePacmanGameWithQuickGameBox(){
    select(myGameBox, "PacmanExtreme");
    sleep(200);
    assertEquals("PACMAN EXTREME", myController.getGameType());
  }
  @Test
  public void testStartingMrsPacmanGameWithQuickGameBox(){
    select(myGameBox, "mrsPacman");
    sleep(200);
    assertEquals("MRS.PACMAN", myController.getGameType());
  }
  @Test
  public void testStartingMazeGameWithQuickGameBox(){
    select(myGameBox, "MazeGame");
    sleep(200);
    assertEquals("MAZE", myController.getGameType());
  }
  @Test
  public void testStartingGameWithQuickGameBox(){
    select(myGameBox, "EasyMaze");
    sleep(200);
    assertEquals("MAZE", myController.getGameType());
  }

}
