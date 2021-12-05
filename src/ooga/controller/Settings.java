package ooga.controller;

import java.util.HashMap;
import java.util.Map;

public class Settings {
    String myLanguage;
    String myGameType;
    String myTimer;
    String myWinCondition;
    String myCellSize;
    String[] settingStrings = {"myLanguage", "myGameType", "myTimer" , "myWinCondition", "myCellSize"};
    String[] myStringSettings = {myLanguage, myGameType,myTimer,myWinCondition, myCellSize};


    public Settings(Map<String, String> settings) {
        initializeSettings(settingStrings, settings);
    }

    public void initializeSettings(String[] stringSettings, Map<String, String> settings) {
        for (int i = 0; i < settingStrings.length; i++) {
            if (settings.containsKey(settingStrings[i])) {
                myStringSettings[i] = settings.get(settingStrings[i]);
            }
        }
    }


    public String getLanguage() {
        return myLanguage;
    }

    public String getGameType() {
        return myGameType;
    }

    public String getMyTimer() {
        return myTimer;
    }

    public String getWinCondition() {
        return myWinCondition;
    }

    public Integer getCellSize() {
        return Integer.parseInt(myCellSize);
    }

}

