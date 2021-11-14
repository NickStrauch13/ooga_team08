package ooga.controller;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JSONReaderTest {

    final String FILE_PATH = "data/test/vanillaTest.json";
    final String WRONG_PATH = "data/test/vanillaTet.json";

    @Test
    void checkNumOfRows() throws IOException, ParseException {
        setup setup = new setup(FILE_PATH);
        JSONReader reader = setup.readJSONConfig();
        assertTrue(reader.checkNumOfRows());
    }

    @Test
    void checkNumOfCols() throws IOException, ParseException {
        setup setup = new setup(FILE_PATH);
        JSONReader reader = setup.readJSONConfig();
        assertTrue(reader.checkNumOfCols());
    }
}