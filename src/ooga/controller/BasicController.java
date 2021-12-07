package ooga.controller;

import ooga.models.game.Game;
import ooga.view.gameDisplay.center.BoardView;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface BasicController {
    void initializeGame(String path);
    int getCellCoordinate(double pixels);

    String getGameType();
    BoardView getBoardView();
    int getCellSize();
    void step(String direction);
    int[] getUserPosition();
    int[] getGhostPosition(String nodeID);
    void changeToGameScreen(String filePath);
    void loadNextLevel(BoardView boardView);
    void restartGame();
    void addScoreToCSV(String[] nameAndScore);
    List<String[]> getScoreData();

    String getLanguage();
    void setUsername(String username);
    String getUsername();

    @Deprecated
    int getLevel();
    @Deprecated
    boolean isGameOver();
    @Deprecated
    boolean handleCollision(String nodeID);
    @Deprecated
    boolean getIsPoweredUp();
    @Deprecated
    int getLives();
    @Deprecated
    int getScore();
}
