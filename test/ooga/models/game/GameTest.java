package ooga.models.game;

import ooga.controller.JSONContainer;
import ooga.controller.JSONReader;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class GameTest {
    private Game g;
    @BeforeEach
    public void initializeGame() throws IOException, ParseException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Board newBoard;
        JSONReader reader = new JSONReader("data/test/vanillaTest.json");
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
        g=new Game(newBoard);

    }


}
