package ooga.controller;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JSONReaderTest {

    final String FILE_PATH = "data/test/vanillaTest.json";
    final String WRONG_PATH = "data/test/vanillaTet.json";

    @Test
    void testReadRows() throws IOException, ParseException {
        JSONReader reader = new JSONReader("English", FILE_PATH);
        JSONContainer container = reader.readJSONConfig();

        int numOfRows = 11;
        assertEquals(numOfRows, container.getMyNumOfRows());
    }

    @Test
    void testReadCols() throws IOException, ParseException {
        JSONReader reader = new JSONReader("English", FILE_PATH);
        JSONContainer container = reader.readJSONConfig();

        int numOfCols = 10;
        assertEquals(numOfCols, container.getMyNumOfCols());
    }

    @Test
    void testReadInfo() throws IOException, ParseException {
        JSONReader reader = new JSONReader("English",FILE_PATH);
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
    void testMapConversion() throws IOException, ParseException {
        JSONReader reader = new JSONReader("English", FILE_PATH);
        JSONContainer container = reader.readJSONConfig();
        Map<Integer, String> conversionMap = container.getMyConversionMap();
        Map<Integer, String> creatureMap = container.getMyCreatureMap();

        String expectedObject = "POWERUP1";
        String expectedCreature = "CPUGHOST";


        assertEquals(expectedObject, conversionMap.get(1));
        assertEquals(expectedCreature, creatureMap.get(5));
    }

//    @Test
//    public void IOExceptionThrown() {
//        JSONReader reader = new JSONReader("English", WRONG_PATH);
//
////        String message = "data\\test\\vanillaTet.json (???????????)";
//        Exception expectedException = assertThrows(ExceptionInInitializerError.class, () -> {
//            reader.readJSONConfig();
//        });
//
//        assertEquals("message", expectedException.getMessage());
//    }

}