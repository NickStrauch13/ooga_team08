package ooga.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JSONReaderTest {

    final String FILE_PATH = "data/test/controller/basicBoardInfo.json";
    final String EMPTY_PATH = "data/test/controller/absoluteEmptyTest.json";


    final Set<String> GAME_SETTINGS = Set.of(
            "SETTINGS", "PACMAN",
            "CPUGHOST", "WALL",
            "SCOREBOOSTER", "STATECHANGER",
            "SCOREMULTIPLIER", "GHOSTSLOWER",
            "EXTRALIFE", "INVINCIBILITY",
            "PORTAL", "SPEEDCUTTER",
            "WINLEVEL");

    final Map<String, Set<String>> SETTING_PARAMETERS =
            Map.ofEntries(
                    Map.entry("SETTINGS" ,Set.of("LANGUAGE", "GAME_TITLE", "TIMER", "LIVES", "CELL_SIZE",
                            "CSS_FILE_NAME", "USER_IS_PREDATOR", "HARD", "IS_PICKUPS_A_VALID_WIN_CONDITION")),
                    Map.entry("PACMAN" , Set.of("USER_IMAGE")),
                    Map.entry( "CPUGHOST" ,  Set.of("CPU_IMAGE")),
                    Map.entry( "WALL" ,  Set.of("WALL_COLOR")),
                    Map.entry("SCOREBOOSTER" ,  Set.of("POWERUP_COLOR", "POWERUP_SIZE")),
                    Map.entry( "STATECHANGER" ,  Set.of("POWERUP_COLOR", "POWERUP_SIZE")),
                    Map.entry( "SCOREMULTIPLIER",  Set.of("POWERUP_COLOR", "POWERUP_SIZE")),
                    Map.entry("GHOSTSLOWER" ,  Set.of("POWERUP_COLOR", "POWERUP_SIZE")),
                    Map.entry("EXTRALIFE" ,  Set.of("POWERUP_COLOR", "POWERUP_SIZE")),
                    Map.entry("INVINCIBILITY" ,  Set.of("POWERUP_COLOR", "POWERUP_SIZE")),
                    Map.entry("PORTAL" ,  Set.of("POWERUP_COLOR", "POWERUP_SIZE")),
                    Map.entry("SPEEDCUTTER" ,  Set.of("POWERUP_COLOR", "POWERUP_SIZE")),
                    Map.entry("WINLEVEL" ,  Set.of("POWERUP_COLOR", "POWERUP_SIZE"))
            );

    JSONContainer container;
    JSONReader reader;
    GameSettings gameSettings;

    @BeforeEach
    void initialize() {
        reader = new JSONReader("English", FILE_PATH);
        container = reader.readJSONConfig();
        gameSettings = container.getMyGameSettings();
    }

    @Test
    void testReadRows() {

        int numOfRows = 11;
        assertEquals(numOfRows, container.getMyNumOfRows());
    }

    @Test
    void testReadCols() {

        int numOfCols = 10;
        assertEquals(numOfCols, container.getMyNumOfCols());
    }

    @Test
    void testReadInfo() {

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
    void testMapConversion() {
        Map<Integer, String> conversionMap = container.getMyConversionMap();
        Map<Integer, String> creatureMap = container.getMyCreatureMap();

        String expectedObject = "SCOREBOOSTER";
        String expectedCreature = "CPUGHOST";


        assertEquals(expectedObject, conversionMap.get(1));
        assertEquals(expectedCreature, creatureMap.get(5));
    }

    @Test
    void getMostRecentPath() {
        assertEquals(FILE_PATH, reader.getMostRecentPath());
    }

    @Test
    void testGameSettings() {

        assertEquals(gameSettings.getAllSettings().keySet(), GAME_SETTINGS);
    }

    @Test
    void testSettingContent() {
        boolean isSame = true;
        for (String key: gameSettings.getAllSettings().keySet()) {
            if (!SETTING_PARAMETERS.get(key).equals(gameSettings.getAllSettings().get(key).keySet())) {
                isSame = false;
            }
        }
        assertTrue(isSame);
    }

    @Test
    void testParseException() {
        reader = new JSONReader("English", EMPTY_PATH);
        ParseException expectedException = assertThrows(ParseException.class, () -> {
            JSONObject jsonData = reader.extractJSONObject();
        });
    }

//    @Test
//    public void IOExceptionThrown() {
//        JSONReader reader = new JSONReader("English", EMPTY_PATH);
//
//        String message = "data\\test\\vanillaTet.json (???????????)";
//        Exception expectedException = assertThrows(IOException.class, () -> {
//            reader.readJSONConfig();
//        });
//
//        assertEquals("message", expectedException.getMessage());
//    }


}