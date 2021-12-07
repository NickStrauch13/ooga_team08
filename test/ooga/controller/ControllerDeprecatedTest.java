package ooga.controller;

import javafx.stage.Stage;
import ooga.models.creatures.cpuControl.CPUCreature;
import ooga.models.creatures.userControl.UserCreature;
import ooga.models.creatures.userControl.UserPacman;
import ooga.models.game.Board;
import ooga.models.game.Game;
import ooga.view.gameDisplay.center.BoardView;
import ooga.view.popups.ErrorView;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ControllerDeprecatedTest extends DukeApplicationTest {

    private Map<String,String> map;
    private List<CPUCreature> creatureList;
    private Board newBoard;
    private Game g;
    private UserPacman userPacman;
    private int numPickups;
    private Controller myController;
    private BoardView myBoardView;

    @Override
    public void start(Stage stage)
            throws IOException, ParseException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        myController = new Controller(stage);

    }

    @BeforeEach
    public void initializeGame() throws IOException, ParseException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        map= Map.of("LANGUAGE","ENGLISH","GAME_TITLE","PACMAN",
                "TIMER","-1","LIVES","3","CELL_SIZE","24","CSS_FILE_NAME","DEFAULT.CSS","USER_IS_PREDATOR","0","HARD","1","IS_PICKUPS_A_VALID_WIN_CONDITION","1");
        JSONReader reader = new JSONReader("English","data/test/vanillaTest.json");
        JSONContainer container = reader.readJSONConfig();

        myBoardView = new BoardView(myController);

        myController.initializeGame("data/test/vanillaTest.json");
        List<List<String>> stringBoard = container.getMyStringBoard();
        newBoard=myController.getGameController().getGame().getMyBoard();
        myController.getGameController().getGame().setLastDirection("RIGHT");
        myBoardView = myController.getBoardView();
        numPickups = 10;
        userPacman=(UserPacman) myController.getGameController().getGame().getUser();
        CPUCreature c1 = new CPUCreature(100,100);
        c1.setId("CREATURE123");
        c1.setCurrentDirection(new int[]{0,1});
        creatureList=new ArrayList<CPUCreature>();
        creatureList.add(c1);
        g=myController.getGameController().getGame();
    }

    @Test
    void getGame() {
        assertEquals(g,myController.getGame());
    }

    @Test
    void getLives() {
        assertEquals(g.getLives(),myController.getLives());
    }

    @Test
    void getScore() {
        assertEquals(g.getScore(),myController.getScore());
    }

    @Test
    void getIsPoweredUp() {
        assertEquals(myController.getGameController().getIsPoweredUp(), myController.getIsPoweredUp());

    }

    @Test
    void getIsInvincible() {
        assertEquals(myController.getGameController().getIsInvincible(), myController.getIsInvincible());

    }


    @Test
    void getLevel() {
        assertEquals(myController.getGameController().getLevel(), myController.getLevel());

    }

    @Test
    void isGameOver() {
        assertEquals(myController.getGameController().isGameOver(), myController.isGameOver());

    }

    @Test
    void getGameTime() {
        assertEquals(myController.getGameController().getGameTime(), myController.getGameTime());

    }

    @Test
    void addOneMillionPoints() {
        myController.addOneMillionPoints();
        assertEquals(g.getScore(), 1000000);
    }

    @Test
    void addOneHundredPoints() {
        myController.addOneHundredPoints();
        assertEquals(g.getScore(), 100);
    }

    @Test
    void addFiveHundredPoints() {
        myController.addFiveHundredPoints();
        assertEquals(g.getScore(), 500);

    }

    @Test
    void resetGhosts() {
        myController.resetGhosts();

        List<CPUCreature> ghosts = g.getCPUs();

        boolean isDead = true;

        for (CPUCreature ghost : ghosts) {
            if (ghost.getXpos() != ghost.getHomeX() || ghost.getYpos() != ghost.getHomeY()){
                isDead = false;
            }
        }
        assertTrue(isDead);
    }

    @Test
    void addLife() {
        myController.addLife();
        assertEquals(g.getLives(), 4);
    }

    @Test
    void goToNextLevel() {
        myController.goToNextLevel();
        assertEquals(g.getLevel(), 2);
    }

    @Test
    void powerUp() {
        myController.powerUp();
        assertTrue(g.getUser().isPoweredUp());
    }

    @Test
    void FreezeGhosts() {
        myController.FreezeGhosts();
        assertEquals(g.getCPUs().size(), 0);
    }

    @Test
    void removeOneMillionPoints() {
        myController.RemoveOneMillionPoints();
        assertEquals(g.getScore(), -1000000);
    }

    @Test
    void resetUserPosition() {
        myController.resetUserPosition();
        UserCreature user = g.getUser();

        boolean isDead = true;

        if (user.getXpos() != user.getHomeX() || user.getYpos() != user.getHomeY()){
            isDead = false;
        }

        assertTrue(isDead);
    }

    @Test
    void loseLife() {
        myController.loseLife();
        assertEquals(g.getLives(), 2);
    }

    @Test
    void gameOver() {
        assertEquals(g.isGameOver(), myController.isGameOver());
    }

    @Test
    void DeprecatedGameControllerConstructor() {
        GameController gameController = new GameController(g, "ENGLISH");
        assertEquals(gameController.getGame(), g);

        ErrorView errorView = new ErrorView("ENGLISH");
        GameController gameController2 = new GameController(g, errorView);
        assertEquals(gameController2.getGame(), g);
    }
}