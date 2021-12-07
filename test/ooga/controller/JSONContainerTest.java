package ooga.controller;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JSONContainerTest {

    final String FILE_PATH = "data/test/controller/basicBoardInfo.json";
    final String EMPTY_PATH = "data/test/controller/emptyInputTest.json";

    JSONContainer container;
    JSONReader reader;

    @BeforeEach
    void initialize() {
        reader = new JSONReader("English", FILE_PATH);
        container = reader.readJSONConfig();
    }

    @Test
    void checkNumOfRows() {
        assertTrue(container.checkNumOfRows());
    }

    @Test
    void checkNumOfCols() {
        assertTrue(container.checkNumOfCols());
    }


    @Test
    void getMyStringBoard() {
        List<List<String>> stringBoard =  container.getMyStringBoard();
        assertEquals(stringBoard.get(0).get(0), "WALL");
        assertEquals(stringBoard.get(0).get(3), "SCOREBOOSTER");
        assertEquals(stringBoard.get(1).get(6), "STATECHANGER");
        assertEquals(stringBoard.get(9).get(6), "SCOREMULTIPLIER");
    }

    @Test
    void isMissingContent() {
        assertFalse(container.isMissingContent());
        reader = new JSONReader("English", EMPTY_PATH);

        container = new JSONContainer(0, 0, null, null, null, null, null);
        assertTrue(container.isMissingContent());
    }
}