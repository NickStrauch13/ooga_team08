package ooga.models.game;

import ooga.controller.Controller;
import ooga.controller.JSONContainer;
import ooga.controller.JSONReader;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class BoardTest {
    private Board newBoard;
    @BeforeEach
    public void initializeBoard() throws IOException, ParseException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        JSONReader reader = new JSONReader("English","data/test/vanillaTest.json");
        JSONContainer container = reader.readJSONConfig();

        int numOfRows = container.getMyNumOfRows();
        int numOfCols = container.getMyNumOfCols();
        newBoard = new Board(numOfRows, numOfCols);

        List<List<String>> stringBoard = container.getMyStringBoard();

        for (int row = 0; row < numOfRows; row++) {
            for (int col = 0; col < numOfCols; col ++) {
                String objectName = stringBoard.get(row).get(col);
                newBoard.createGameObject(row, col, objectName);
            }
        }

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


}
