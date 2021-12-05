package ooga.controller;

import ooga.controller.settings.Settings;

import java.util.Map;

public class GameSettings {

    private Map<String,Map<String,String>> myGameSettings;
//
// , Map<String, String> CPUSettings, Map<String, String> wallSettings, Map<String, String> foodSettings
    public GameSettings(Map<String,Map<String,String>> gameSettings) {
        myGameSettings = gameSettings;
    }

    public Map<String, String> getGeneralSettings() {return myGameSettings.get("general");}
    public Map<String, String> getUserSettings() {return myGameSettings.get("user");}
    public Map<String, String> getWallSettings() {return myGameSettings.get("wall");}
    public Map<String, String> getScoreBoosterSettings() { return myGameSettings.get("scoreBooster");}
    public Map<String, String> getStateChangerSettings() {return myGameSettings.get("stateChanger");}
    public Map<String, String> getScoreMultplierSettings() {return myGameSettings.get("scoreMultiplier");}
    public Map<String, String> getPortalSettings() {return myGameSettings.get("portal");}
    public Map<String, String> getWallBreakerSettings() {return myGameSettings.get("wallBreaker");}
    public Map<String, String> getExtraLifeSettings() { return myGameSettings.get("extraLife");}
    public Map<String, String> getInvicibilitySettings() {return myGameSettings.get("invincibility");}
    public Map<String, String> getSpeedCutterSettings() {return myGameSettings.get("speedCutter");}
    public Map<String, String> getWinLevelSettings() {return myGameSettings.get("winLevel");}


}
