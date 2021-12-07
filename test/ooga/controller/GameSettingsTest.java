package ooga.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GameSettingsTest {

    JSONContainer container;
    JSONReader reader;
    GameSettings gameSettings;

    final String FILE_PATH = "data/test/controller/basicBoardInfo.json";

        private final Map<String, Set<String>> SETTING_PARAMETERS =
             Map.ofEntries(
                     Map.entry("SETTINGS", Set.of("LANGUAGE", "GAME_TITLE", "TIMER", "LIVES", "CELL_SIZE",
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

    @BeforeEach
    void initialize() {
        reader = new JSONReader("English", FILE_PATH);
        container = reader.readJSONConfig();
        gameSettings = container.getMyGameSettings();
    }

    @Test
    void getAllSettings() {
        Map<String, Map<String, String>> allSettings = gameSettings.getAllSettings();
        boolean isEqual = true;

        for (String key: allSettings.keySet()) {
            if (!SETTING_PARAMETERS.get(key).equals(allSettings.get(key).keySet())) {
                isEqual = false;
            }
        }
        assertTrue(isEqual);
    }

    @Test
    void getGeneralSettings() {
        Map<String, String> settings = gameSettings.getGeneralSettings();
        assertEquals(settings.keySet(), SETTING_PARAMETERS.get("SETTINGS"));
    }

    @Test
    void getUserSettings() {
        Map<String, String> settings = gameSettings.getUserSettings();
        assertEquals(settings.keySet(), SETTING_PARAMETERS.get("PACMAN"));
    }

    @Test
    void getWallSettings() {
        Map<String, String> settings = gameSettings.getWallSettings();
        assertEquals(settings.keySet(), SETTING_PARAMETERS.get("WALL"));
    }

    @Test
    void getScoreBoosterSettings() {
        Map<String, String> settings = gameSettings.getScoreBoosterSettings();
        assertEquals(settings.keySet(), SETTING_PARAMETERS.get("SCOREBOOSTER"));
    }

    @Test
    void getStateChangerSettings() {
        Map<String, String> settings = gameSettings.getStateChangerSettings();
        assertEquals(settings.keySet(), SETTING_PARAMETERS.get("STATECHANGER"));
    }

    @Test
    void getScoreMultiplierSettings() {
        Map<String, String> settings = gameSettings.getScoreMultiplierSettings();
        assertEquals(settings.keySet(), SETTING_PARAMETERS.get("SCOREMULTIPLIER"));
    }

    @Test
    void getPortalSettings() {
        Map<String, String> settings = gameSettings.getPortalSettings();
        assertEquals(settings.keySet(), SETTING_PARAMETERS.get("PORTAL"));
    }

    @Test
    void ghostSlowerSettings() {
        Map<String, String> settings = gameSettings.ghostSlowerSettings();
        assertEquals(settings.keySet(), SETTING_PARAMETERS.get("GHOSTSLOWER"));
    }

    @Test
    void getExtraLifeSettings() {
        Map<String, String> settings = gameSettings.getExtraLifeSettings();
        assertEquals(settings.keySet(), SETTING_PARAMETERS.get("EXTRALIFE"));
    }

    @Test
    void getInvincibilitySettings() {
        Map<String, String> settings = gameSettings.getInvincibilitySettings();
        assertEquals(settings.keySet(), SETTING_PARAMETERS.get("INVINCIBILITY"));
    }


    @Test
    void getSpeedCutterSettings() {
        Map<String, String> settings = gameSettings.getSpeedCutterSettings();
        assertEquals(settings.keySet(), SETTING_PARAMETERS.get("SPEEDCUTTER"));
    }

    @Test
    void getWinLevelSettings() {
        Map<String, String> settings = gameSettings.getWinLevelSettings();
        assertEquals(settings.keySet(), SETTING_PARAMETERS.get("WINLEVEL"));
    }
}