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

    public Map<String,Map<String,String>> getAllSettings() {
        return myGameSettings;
    }
    public Map<String, String> getGeneralSettings() {return myGameSettings.get("SETTINGS");}
    public Map<String, String> getUserSettings() {return myGameSettings.get("PACMAN");}
    public Map<String, String> getWallSettings() {return myGameSettings.get("WALL");}
    public Map<String, String> getScoreBoosterSettings() { return myGameSettings.get("SCOREBOOSTER");}
    public Map<String, String> getStateChangerSettings() {return myGameSettings.get("STATECHANGER");}
    public Map<String, String> getScoreMultiplierSettings() {return myGameSettings.get("SCOREMULTIPLIER");}
    public Map<String, String> getPortalSettings() {return myGameSettings.get("PORTAL");}
    public Map<String, String> ghostSlowerSettings() {return myGameSettings.get("GHOSTSLOWER");}
    public Map<String, String> getExtraLifeSettings() { return myGameSettings.get("EXTRALIFE");}
    public Map<String, String> getInvincibilitySettings() {return myGameSettings.get("INVINCIBILITY");}
    public Map<String, String> getSpeedCutterSettings() {return myGameSettings.get("SPEEDCUTTER");}
    public Map<String, String> getWinLevelSettings() {return myGameSettings.get("WINLEVEL");}


}
