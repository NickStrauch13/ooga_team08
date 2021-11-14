package ooga.view;

import java.awt.Dimension;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ooga.view.home.HomeScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class GeneralViewTests extends DukeApplicationTest {
  public static final Dimension DEFAULT_SIZE = new Dimension(1400, 800);
  public static final String TITLE = "Start Screen";

  @Override
  public void start(Stage stage) {
    HomeScreen startScreen = new HomeScreen(stage, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
    stage.setTitle(TITLE);
    stage.setScene(startScreen.createScene());
    stage.show();
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
  }

  @Test
  public void testEnteringUsername(){
    String username = "Player1";
    TextField userNameBox = lookup("#userName").query();
    writeInputTo(userNameBox, username);
  }
/*
  @Test
  public void testClickingOnGoHomeButton(){
    Button newGameButton = lookup("#newGameButton").query();
    clickOn(newGameButton);
    Button goHome = lookup("#goHomeButton").query();
    clickOn(goHome);
  }

  @Test
  public void testClickingOnPlayButton(){
  }
   */
}
