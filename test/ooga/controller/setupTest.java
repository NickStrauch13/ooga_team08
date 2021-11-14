package ooga.controller;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class setupTest {

    final String FILE_PATH = "data/test/vanillaTest.json";
    final String WRONG_PATH = "data/test/vanillaTet.json";

    @Test
    void testReadRows() throws IOException, ParseException {
        setup setup = new setup(FILE_PATH);
        JSONReader reader = setup.readJSONConfig();

        int numOfRows = 11;
        assertEquals(numOfRows, reader.getMyNumOfRows());
    }

    @Test
    void testReadCols() throws IOException, ParseException {
        setup setup = new setup(FILE_PATH);
        JSONReader reader = setup.readJSONConfig();

        int numOfCols = 10;
        assertEquals(numOfCols, reader.getMyNumOfCols());
    }

    @Test
    void testReadInfo() throws IOException, ParseException {
        setup setup = new setup(FILE_PATH);
        JSONReader reader = setup.readJSONConfig();

        List expectedBoardInfo = List.of(
                List.of(0,0,0,1,0,0,1,0,0,0),
                List.of(0,0,0,1,0,0,2,0,0,0),
                List.of(0,0,0,1,0,0,1,0,0,0),
                List.of(0,0,0,1,0,0,1,0,0,0),
                List.of(0,0,0,1,0,0,1,0,0,0),
                List.of(0,0,0,1,0,0,1,0,0,0),
                List.of(0,0,0,1,0,0,1,0,0,0),
                List.of(0,0,0,1,0,0,1,0,0,0),
                List.of(0,0,0,1,0,0,1,0,0,0),
                List.of(0,0,0,1,0,0,3,0,0,0),
                List.of(0,0,0,1,0,0,1,0,0,0)
        );
        assertEquals(expectedBoardInfo, reader.getMyInfo());
    }

    @Test
    public void IOExceptionThrown() {
        setup setup = new setup(WRONG_PATH);
        String message = "data\\test\\vanillaTet.json (???????????)";
        Exception expectedException = assertThrows(IOException.class, () -> {
            setup.readJSONConfig();
        });

        assertEquals(message, expectedException.getMessage());
    }

}