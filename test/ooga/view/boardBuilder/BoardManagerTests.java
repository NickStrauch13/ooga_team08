package ooga.view.boardBuilder;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javafx.scene.control.Button;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.models.creatures.cpuControl.CPUCreature;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import util.DukeApplicationTest;

public class BoardManagerTests extends DukeApplicationTest {
    private Controller myController;
    private Button playButton;
    private Button resetButton;
    private FxRobot robot;

    @Override
    public void start(Stage stage)
            throws IOException, ParseException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        myController = new Controller(stage);
        myController.changeToGameScreen("data/game/basics.json");
        robot = new FxRobot();
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
        clickOn(resetButton);
    }

    @Test
    public void clickOnPlayButtonTwiceToPauseAnimation(){
        clickOn(playButton);
        sleep(300);
        clickOn(playButton);
        clickOn(resetButton);
    }

    @Test
    public void clickOnResetButtonAfterGameWasStarted(){
        clickOn(playButton);
        sleep(500);
        int prevScore = myController.getScore();
        clickOn(resetButton);
        sleep(400);
        int resetScore = myController.getScore();
        assertNotEquals(prevScore, resetScore);
    }

    @Test
    public void clickOnPlayThenMovePacmanAndCheckIfPositionChanged(){
        int[] startPos = myController.getUserPosition();
        clickOn(playButton);
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
        clickOn(resetButton);
    }

    @Test
    public void clickOnResetButtonAfterGameHasPlayedForABriefPeriod(){
        int[] startPos = myController.getUserPosition();
        clickOn(playButton);
        sleep(800);
        robot.press(KeyCode.UP).release(KeyCode.UP);
        sleep(2300);
        robot.press(KeyCode.LEFT).release(KeyCode.LEFT);
        sleep(620);
        clickOn(resetButton);
        assertEquals(startPos[0], myController.getUserPosition()[0]);
        sleep(100);
    }

    @Test
    public void startGameAndLetPacmanDieOnceAndThenCheckLives(){
        clickOn(playButton);
        int startLives = myController.getLives();
        sleep(700);
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
        sleep(5000);
        int finalLives = myController.getLives();
        assertNotEquals(startLives, finalLives);
        clickOn(resetButton);
    }

    @Test
    public void startGameAndEatGhostToGetScoreBoost(){
        clickOn(playButton);
        int startScore = myController.getScore();
        robot.press(KeyCode.DOWN).release(KeyCode.DOWN);
        sleep(1500);
        robot.press(KeyCode.RIGHT).release(KeyCode.RIGHT);
        sleep(400);
        robot.press(KeyCode.LEFT).release(KeyCode.LEFT);
        sleep(2000);
        int finalScore = myController.getScore();
        assertNotEquals(startScore, finalScore);
        clickOn(resetButton);
    }

    @Test
    public void addLifeUsingCheatKeyA(){
        clickOn(playButton);
        int startLives = myController.getLives();
        robot.press(KeyCode.A).release(KeyCode.A);
        sleep(100);
        int finalLives = myController.getLives();
        assertEquals(startLives+1, finalLives);
        clickOn(resetButton);
    }

    @Test
    public void endGameUsingCheatKeyE(){
        clickOn(playButton);
        robot.press(KeyCode.E).release(KeyCode.E);
        sleep(100);
        assert(myController.getGame().isGameOver());
        clickOn(resetButton);
    }

    @Test
    public void addMillionScoreUsingCheatKeyP(){
        clickOn(playButton);
        int startScore = myController.getScore()+100;
        robot.press(KeyCode.P).release(KeyCode.P);
        int finalScore = myController.getScore();
        sleep(100);
        assertEquals(startScore+1000000, finalScore);
        clickOn(resetButton);
    }


    @Test
    public void AddFiveHundredScoreUsingCheatKeyI(){
        clickOn(playButton);
        int score = myController.getScore();
        robot.press(KeyCode.I).release(KeyCode.I);
        sleep(100);
        assert(myController.getScore()==score+600);
        clickOn(resetButton);
    }

    @Test
    public void addOneHundredScoreUsingCheatKeyO(){
        clickOn(playButton);
        int startScore = myController.getScore() +100;
        robot.press(KeyCode.O).release(KeyCode.O);
        int finalScore = myController.getScore();
        sleep(100);
        assertEquals(startScore+100, finalScore);
        clickOn(resetButton);
    }

    @Test
    public void FreezeGhostsUsingCheatKeyQ(){
        clickOn(playButton);

        robot.press(KeyCode.Q).release(KeyCode.Q);
        sleep(100);
        assert(myController.getGame().getCPUs().size()==0);
        clickOn(resetButton);
    }
    @Test
    public void ResetUserPositionUsingKeyR(){
        clickOn(playButton);



        clickOn(playButton);
        robot.press(KeyCode.R).release(KeyCode.R);
        System.out.println(myController.getGame().getUser().getXpos());
        System.out.println(myController.getGame().getUser().getHomeX());
        System.out.println(myController.getGame().getUser().getYpos());
        System.out.println(myController.getGame().getUser().getHomeY());
        assert(myController.getGame().getUser().getXpos()==myController.getGame().getUser().getHomeX());
        assert(myController.getGame().getUser().getYpos()==myController.getGame().getUser().getHomeY());
        clickOn(resetButton);
    }

    @Test
    public void removeOneMillionScoreUsingCheatKeyS(){
        clickOn(playButton);
        int startScore = myController.getScore();
        robot.press(KeyCode.S).release(KeyCode.S);
        int finalScore = myController.getScore();
        assertEquals(startScore-999900, finalScore);
        clickOn(resetButton);
    }

    @Test
    public void goToNextLevelWithCheatKeyT(){
        clickOn(playButton);
        int startLevel = myController.getLevel();
        robot.press(KeyCode.T).release(KeyCode.T);
        int finalLevel = myController.getLevel();
        sleep(100);
        assertEquals(startLevel+1, finalLevel);
        clickOn(resetButton);
    }

    @Test
    public void GhostsDieUsingKeyW(){
        clickOn(playButton);
        clickOn(playButton);
        robot.press(KeyCode.W).release(KeyCode.W);
        for(CPUCreature creature: myController.getGame().getCPUs()) {
            assert (creature.getXpos() == creature.getHomeX());
            assert (creature.getYpos() == creature.getHomeY());
        }
        clickOn(resetButton);
    }

    @Test
    public void powerUpUsingCheatKeyU(){
        clickOn(playButton);
        robot.press(KeyCode.U).release(KeyCode.U);
        sleep(100);

        assert(myController.getIsPoweredUp());
        clickOn(resetButton);
    }

    @Test
    public void RemoveLifeUsingCheatKeyY(){
        clickOn(playButton);
        int startLives = myController.getLives();
        robot.press(KeyCode.Y).release(KeyCode.Y);
        sleep(100);
        int finalLives = myController.getLives();
        assertEquals(startLives-1, finalLives);
        clickOn(resetButton);
    }



}

