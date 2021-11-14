package ooga.controller;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JSONReaderTest {

    final String FILE_PATH = "data/test/vanillaTest.json";
    final String WRONG_PATH = "data/test/vanillaTet.json";

    @Test
    void testReadRows() throws IOException, ParseException {
        JSONReader reader = new JSONReader(FILE_PATH);
        JSONContainer container = reader.readJSONConfig();

        int numOfRows = 11;
        assertEquals(numOfRows, container.getMyNumOfRows());
    }

    @Test
    void testReadCols() throws IOException, ParseException {
        JSONReader reader = new JSONReader(FILE_PATH);
        JSONContainer container = reader.readJSONConfig();

        int numOfCols = 10;
        assertEquals(numOfCols, container.getMyNumOfCols());
    }

    @Test
    void testReadInfo() throws IOException, ParseException {
        JSONReader reader = new JSONReader(FILE_PATH);
        JSONContainer container = reader.readJSONConfig();

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
        assertEquals(expectedBoardInfo, container.getMyInfo());
    }

    @Test
    public void IOExceptionThrown() {
        JSONReader reader = new JSONReader(WRONG_PATH);

        String message = "data\\test\\vanillaTet.json (???????????)";
        Exception expectedException = assertThrows(IOException.class, () -> {
            reader.readJSONConfig();
        });

        assertEquals(message, expectedException.getMessage());
    }

}