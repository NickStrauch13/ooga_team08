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
    int getLives();
    void restartGame();
    int getScore();
    void step(String direction);
    int getLevel();
    boolean isGameOver();
    void addScoreToCSV(String[] nameAndScore);
    String getUsername();
    boolean handleCollision(String nodeID);
    void loadNextLevel(BoardView boardView);
    boolean getIsPoweredUp();
    int[] getUserPosition();
    int[] getGhostPosition(String nodeID);
    int getCellSize();
    String getGameType();
    boolean getIsInvincible();
    String getViewMode();
    int getGameTime();
    void addOneMillionPoints();
    void addOneHundredPoints();
    void addFiveHundredPoints();
    void resetGhosts();
    void addLife();
    void goToNextLevel();
    void powerUp();
    void FreezeGhosts();
    void RemoveOneMillionPoints();
    void resetUserPosition();
    void loseLife();
    void gameOver();
    int getTimer();
    void setCellSize(int newSize);
    Map<Integer,String> createGameObjectMap();
    Map<Integer,String> createCreatureMap();
    void setUILanguage(String lang);
    void setViewMode(String cssName);
    public String getTopScoreForUser();

}
