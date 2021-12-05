package ooga.controller;

import ooga.models.game.Game;
import ooga.view.gameDisplay.center.BoardView;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CheatControllerInterface {
    public void initializeGame(String path);
    public int getCellCoordinate(double pixels);
    public int getLives();
    public Game getGame();
    public int getScore();
    public String getGameType();
    public boolean getIsPoweredUp();
    public BoardView getBoardView();
    public int getCellSize();
    public void step(String direction);
    public int[] getUserPosition();
    public int[] getGhostPosition(String nodeID);
    public void changeToGameScreen(String filePath);
    public boolean handleCollision(String nodeID);
    public void loadNextLevel(BoardView boardView);
    public void restartGame();
    public void addScoreToCSV(String[] nameAndScore);
    public List<String[]> getScoreData();
    public int getLevel();
    public boolean isGameOver();
    public String getLanguage();
    public void setUsername(String username);
    public String getUsername();
}
