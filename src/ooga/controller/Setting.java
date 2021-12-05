package ooga.controller;

import java.util.HashMap;
import java.util.Map;

public class Setting {
    String myLanguage;
    String myGameType;
    String myWinCondition;

    String myTimer;
    String myCellSize;

    // TODO: ENUM -
//    final String[] settingStrings = {"myLanguage", "myGameType", "myTimer" , "myWinCondition", "myCellSize"};
//    String[] myStringSettings = {myLanguage, myGameType,myTimer,myWinCondition, myCellSize};


    public Setting(String language, String gameType, String winCondition, String timer, String cellSize) {
//        initializeSettings(settingStrings, settings);
        myLanguage = language;
        myGameType = gameType;
        myTimer = timer;
        myWinCondition = winCondition;
        myCellSize = cellSize;
    }


//    public void initializeSettings(String[] stringSettings, Map<String, String> settings) {
//        for (int i = 0; i < settingStrings.length; i++) {
//            if (settings.containsKey(settingStrings[i])) {
//                myStringSettings[i] = settings.get(settingStrings[i]);
//            }
//        }
//    }


    public String getLanguage() {
        return myLanguage;
    }

    public String getGameType() {
        return myGameType;
    }

    public String getWinCondition() {
        return myWinCondition;
    }

    public int getCellSize() {
        return Integer.parseInt(myCellSize);
    }

    public boolean isShowTimer() {
        return Integer.parseInt(myTimer.trim()) == 1;
    }




}

