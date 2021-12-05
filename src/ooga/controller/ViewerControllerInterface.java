package ooga.controller;

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
}
