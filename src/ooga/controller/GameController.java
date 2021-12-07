package ooga.controller;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import ooga.models.creatures.cpuControl.CPUCreature;
import ooga.models.game.CollisionManager;
import ooga.models.game.Game;
import ooga.view.popups.ErrorView;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    private final int MILLION = 1000000;
    private final int ONE_HUNDRED = 100;
    private final int FIVE_HUNDRED = 500;
    private final int HIGH_SCORE_VALS = 10;
    private final String SCORE_PATH = "./data/highscores/HighScores.csv";
    private final String IOE_EXCEPTION_CSV = "IOE exceptions for CSV file path. Please check your CSV file";

    private final String[] BLANK_ENTRY = new String[]{"", "-1"};

    private Game myGame;
    private CollisionManager collisionManager;
    private CSVWriter myCSVWriter;
    private ErrorView myErrorView;

    @Deprecated
    public GameController (Game game, String language) {
        myGame = game;
        myErrorView = new ErrorView(language);
    }

    @Deprecated
    public GameController (Game game, ErrorView errorView) {
        myGame = game;
        myErrorView = errorView;
    }

    public GameController (Game game) {
        myGame = game;
        collisionManager = new CollisionManager();
    }

    /**
     * Sends information about the collision to the backend
     *
     * @param nodeID
     * @return
     */
    public boolean handleCollision(String nodeID) {
        collisionManager.setCollision(nodeID);
        return myGame.dealWithCollision(collisionManager);
    }

    /**
     * Update and sync each frame of the game with the last direction used
     *
     * @param direction the string value for the direction
     */
    public void step(String direction) {
        myGame.setLastDirection(direction);
        myGame.step();
    }

    /**
     * Get a game object
     * @return the Game object
     */
    public Game getGame() {
        return myGame;
    }

    /**
     * Get the number of lives remained
     *
     * @return the number of lives remained
     */
    public int getLives() {
        return myGame.getLives(); //TODO change this to the model's get lives
    }

    /**
     * Get the current game scores
     *
     * @return the current game scores
     */
    public int getScore() {
        return myGame.getScore();
    }

    public boolean getIsPoweredUp() {
        return myGame.getUser().isPoweredUp();
    }

    public boolean getIsInvincible() {
        return myGame.getUser().isInvincible();
    }

    public int getLevel() {
        return myGame.getLevel();
    }

    public boolean isGameOver() {
        return myGame.isGameOver();
    }

    /**
     * Returns the current time of the game.
     *
     * @return Integer values representing time.
     */
    public int getGameTime() {
        return myGame.getTime();
    }

    public void addOneMillionPoints() {
        getGame().addScore(MILLION);
    }

    public void addOneHundredPoints() {
        getGame().addScore(ONE_HUNDRED);
    }

    public void addFiveHundredPoints() {
        getGame().addScore(FIVE_HUNDRED);
    }

    public void resetGhosts() {
        List<CPUCreature> ghosts = getGame().getCPUs();
        for (CPUCreature ghost : ghosts) {
            ghost.die();
        }
    }

    public void addLife() {
        getGame().addLives(1);
    }

    public void goToNextLevel() {
        getGame().nextLevel();
    }

    public void powerUp() {
        getGame().getUser().setPoweredUp(true);
    }

    public void FreezeGhosts() {
        getGame().getCPUs().removeAll(getGame().getCPUs());
    }//TODO (billion, billion)

    public void RemoveOneMillionPoints() {
        getGame().addScore(-MILLION);
    }

    public void resetUserPosition() {
        getGame().getUser().die();
    }

    public void loseLife() {
        getGame().addLives(-1);
    }

    public void gameOver() {
        getGame().endGame();
    }
}
