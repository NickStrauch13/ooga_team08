package ooga.controller.settings;

import java.util.HashMap;
import java.util.Map;

public class Settings {
    private String myLanguage;
    private String myGameType;
    private String myWinCondition;

    private String myTimer;
    private String myCellSize;

    // TODO: ENUM -
    final String[] settingStrings = {"LANGUAGE", "GAME_TYPE", "TIMER" , "WIN_CONDITION", "CELL_SIZE"};
    String[] myStringSettings = {myLanguage, myGameType, myTimer, myWinCondition, myCellSize};


    public Settings(Map<String, String> settingMap) {
        System.out.println(settingMap);
        myLanguage = settingMap.get("LANGUAGE");
        myGameType = settingMap.get("GAME_TYPE");
        myTimer = settingMap.get("TIMER");
        myWinCondition = settingMap.get("WIN_CONDITION");
        myCellSize = settingMap.get("CELL_SIZE");
    }


    public void initializeSettings(Map<String, String> settings) {
        for (int i = 0; i < settingStrings.length; i++) {
            if (settings.containsKey(settingStrings[i])) {
                myStringSettings[i] = settings.get(settingStrings[i]);
                System.out.println(myLanguage);
            }
        }
    }


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
        return Integer.parseInt(myCellSize.trim());
    }

    public boolean isShowTimer() {
        return Integer.parseInt(myTimer.trim()) == 1;
    }




}

