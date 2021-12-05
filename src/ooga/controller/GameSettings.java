package ooga.controller;

import ooga.controller.settings.Settings;

import java.util.Map;

public class GameSettings {

    private Settings mySettings;
    private Map<String, String> myUserSettings;
    private Map<String, String> myCPUSettings;
    private Map<String, String> myWallSettings;
    private Map<String, String> myFoodSettings;
    private Map<String, String> myPowerUp1Settings;
    private Map<String, String> myPowerUp2Settings;
    private Map<String, String> myPowerUp3Settings;
//
// , Map<String, String> CPUSettings, Map<String, String> wallSettings, Map<String, String> foodSettings
    public GameSettings(Settings settings, Map<String, String> userSettings, Map<String, String> CPUSettings, Map<String, String> wallSettings, Map<String, String> foodSettings, Map<String, String> powerUP1Settings, Map<String, String> powerUP2Settings, Map<String, String> powerUP3Settings) {
        mySettings = settings;
        myUserSettings = userSettings;
        myCPUSettings = CPUSettings;
        myWallSettings = wallSettings;
        myFoodSettings = foodSettings;
        myPowerUp1Settings = powerUP1Settings;
        myPowerUp2Settings = powerUP2Settings;
        myPowerUp3Settings = powerUP3Settings;
    }

    public Settings getMySettings() {
        return mySettings;
    }
}
