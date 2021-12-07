package ooga.models.game;

import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.controller.JSONContainer;
import ooga.controller.JSONReader;
import ooga.models.creatures.cpuControl.CPUCreature;
import ooga.models.creatures.userControl.UserPacman;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardTest extends DukeApplicationTest {
    private Map<String,String> map;
    private List<CPUCreature> creatureList;
    private Board newBoard;
    private Game g;
    private UserPacman userPacman;
    private int numPickups;
    private Controller myController;
    private CPUCreature c1;
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


        myController.initializeGame("data/test/vanillaTest.json");
        List<List<String>> stringBoard = container.getMyStringBoard();
        newBoard=myController.getGame().getMyBoard();
        myController.getGame().setLastDirection("RIGHT");
        // myController.initializeBoard(numOfRows, numOfCols, gameObjectMap, stringBoard);
        numPickups = 10;
        userPacman=(UserPacman) myController.getGame().getUser();

        g=myController.getGame();
        c1=g.getCPUs().get(0);
    }
    @Test
    public void createGameObject() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        newBoard.createGameObject(3,3,"SCOREMULTIPLIER");
        assert (newBoard.getGameObject(3,3).getClass().getName().equals("ooga.models.gameObjects.pickups.ScoreMultiplier"));
    }
    @Test
    public void TestGetGameObject(){
        assert(newBoard.getGameObject(0,0).getClass().getName().equals("ooga.models.gameObjects.walls.BasicWall"));

    }
    @Test
    public void testGetIsWallAtCellTrue(){
        assert(newBoard.getisWallAtCell(0,0));
    }
    @Test
    public void testGetIsWallAtCellFalse(){
        assert(!newBoard.getisWallAtCell(0,3));
    }
    @Test
    public void testGetCols(){
        assert(newBoard.getCols()==10);
    }
    @Test
    public void testGetRows(){
        assert(newBoard.getRows()==11);
    }

    @Test
    public void testGetMyCPU(){
        assert (newBoard.getMyCPU("CPUGHOST0").equals(c1));
    }
    @Test
    public void testGetMyCPUNull(){
        assert (newBoard.getMyCPU("l") == null);
    }
    @Test
    public void testNumPickupsAtStart(){
        assert (newBoard.getMyCPU("l") == null);
    }
    @Test
    public void testGetGameObjects(){

        assert(newBoard.getGameObjects().length==11);
    }


}
