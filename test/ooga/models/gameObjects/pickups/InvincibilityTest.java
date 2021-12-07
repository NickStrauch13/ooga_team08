package ooga.models.gameObjects.pickups;

import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.controller.JSONContainer;
import ooga.controller.JSONReader;
import ooga.models.creatures.cpuControl.CPUCreature;
import ooga.models.game.Board;
import ooga.models.game.Game;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InvincibilityTest extends DukeApplicationTest {
    private Map<String,String> map;
    private List<CPUCreature> creatureList;
    private Board newBoard;
    private Game g;
    private int numPickups;
    private Controller myController;

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


        myController.initializeGame("data/test/powerUpVanillaTest.json");
        List<List<String>> stringBoard = container.getMyStringBoard();
        newBoard=myController.getGame().getMyBoard();
        myController.getGame().setLastDirection("RIGHT");
        // myController.initializeBoard(numOfRows, numOfCols, gameObjectMap, stringBoard);
        numPickups = 10;
        CPUCreature c1 = new CPUCreature(100,100);
        c1.setId("CREATURE123");
        c1.setCurrentDirection(new int[]{0,1});
        creatureList=new ArrayList<CPUCreature>();
        creatureList.add(c1);
        g=myController.getGameController().getGame();
    }

    @Test
    public void invincibilityInteractTest(){
        newBoard.getGameObject(1,3).interact(myController.getGame());
        assert (myController.getGameController().getGame().getUser().isInvincible());
    }
}
