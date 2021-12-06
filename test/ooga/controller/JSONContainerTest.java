package ooga.controller;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JSONContainerTest {

    final String FILE_PATH = "data/test/vanillaTest.json";
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
}