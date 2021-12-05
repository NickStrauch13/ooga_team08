package ooga.controller;

import ooga.models.creatures.cpuControl.CPUCreature;
import ooga.models.game.Game;
import ooga.view.gameDisplay.center.BoardView;

import java.util.List;

public interface ViewerControllerInterface {
    public void initializeGame(String path);
    public BoardView getBoardView();
    public List<String[]> getScoreData();
    public String getLanguage();
    public void setUsername(String username);
    public int getLives();
    public void restartGame();
    public int getScore();
    public void step(String direction);
    public int getLevel();
    public boolean isGameOver();
    public void addScoreToCSV(String[] nameAndScore);
    public String getUsername();
    public boolean handleCollision(String nodeID);
    public void loadNextLevel(BoardView boardView);
    public boolean getIsPoweredUp();
    public int[] getUserPosition();
    public int[] getGhostPosition(String nodeID);
    public int getCellSize();
    public String getGameType();
    public boolean getIsInvincible();
    public String getViewMode();
    public int getGameTime();
    public void addOneMillionPoints();
    public void addOneHundredPoints();
    public void addFiveHundredPoints();
    public void resetGhosts();
    public void addLife();
    public void goToNextLevel();
    public void powerUp();
    public void FreezeGhosts();
    public void RemoveOneMillionPoints();
    public void resetUserPosition();
    public void loseLife();
    public void gameOver();
    public int getTimer();


}
