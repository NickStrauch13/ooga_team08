package ooga.controller;

import java.util.Map;

public interface GameSettingsI {
    Map<String, Map<String, String>> getAllSettings();

    Map<String, String> getGeneralSettings();

    Map<String, String> getUserSettings();

    Map<String, String> getWallSettings();

    Map<String, String> getScoreBoosterSettings();

    Map<String, String> getStateChangerSettings();

    Map<String, String> getScoreMultiplierSettings();

    Map<String, String> getPortalSettings();

    Map<String, String> ghostSlowerSettings();

    Map<String, String> getExtraLifeSettings();

    Map<String, String> getInvincibilitySettings();

    Map<String, String> getSpeedCutterSettings();

    Map<String, String> getWinLevelSettings();
}
