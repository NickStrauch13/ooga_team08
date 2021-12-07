package ooga.controller;

import ooga.view.gameDisplay.center.BoardView;
import java.util.List;
import java.util.Map;

public interface ViewerControllerInterface {
    void initializeGame(String path);
    BoardView getBoardView();
    List<String[]> getScoreData();
    String getLanguage();
    void setUsername(String username);
    void restartGame();
    void step(String direction);

    void addScoreToCSV(String[] nameAndScore);
    String getUsername();
    void loadNextLevel(BoardView boardView);
    int[] getUserPosition();
    int[] getGhostPosition(String nodeID);
    int getCellSize();
    String getGameType();
    String getViewMode();
    GameController getGameController();
    int getTimer();
    void setCellSize(int newSize);
    Map<Integer,String> createGameObjectMap();
    Map<Integer,String> createCreatureMap();
    void setUILanguage(String lang);
    void setViewMode(String cssName);
    String getTopScoreForUser();

    @Deprecated
    int getGameTime();
    @Deprecated
    void addOneMillionPoints();
    @Deprecated
    void addOneHundredPoints();
    @Deprecated
    void addFiveHundredPoints();
    @Deprecated
    void resetGhosts();
    @Deprecated
    void addLife();
    @Deprecated
    void goToNextLevel();
    @Deprecated
    void powerUp();
    @Deprecated
    void FreezeGhosts();
    @Deprecated
    void RemoveOneMillionPoints();
    @Deprecated
    void resetUserPosition();
    @Deprecated
    void loseLife();
    @Deprecated
    void gameOver();
    @Deprecated
    boolean getIsInvincible();
    @Deprecated
    boolean getIsPoweredUp();
    @Deprecated
    boolean handleCollision(String nodeID);
    @Deprecated
    int getLevel();
    @Deprecated
    boolean isGameOver();
    @Deprecated
    int getScore();
    @Deprecated
    int getLives();
}
