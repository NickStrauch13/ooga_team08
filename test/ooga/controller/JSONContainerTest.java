package ooga.controller;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JSONContainerTest {

    final String FILE_PATH = "data/test/vanillaTest.json";

    @Test
    void checkNumOfRows() throws IOException, ParseException {
        JSONReader reader = new JSONReader("English", FILE_PATH);
        JSONContainer container = reader.readJSONConfig();
        assertTrue(container.checkNumOfRows());
    }

    @Test
    void checkNumOfCols() throws IOException, ParseException {
        JSONReader reader = new JSONReader("English",FILE_PATH);
        JSONContainer container = reader.readJSONConfig();
        assertTrue(container.checkNumOfCols());
    }
}