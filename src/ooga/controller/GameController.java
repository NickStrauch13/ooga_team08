package ooga.controller;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import ooga.models.creatures.cpuControl.CPUCreature;
import ooga.models.game.CollisionManager;
import ooga.models.game.Game;
import ooga.view.popups.ErrorView;

import java.util.List;

public class GameController {
    private static final int ONE_LIFE = 1;
    private final int MILLION = 1000000;
    private final int ONE_HUNDRED = 100;
    private final int FIVE_HUNDRED = 500;

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

    /**
     * Constructor for GameController objects
     * @param game
     */
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

    /**
     * Pass isPoweredUp from myGame
     * @return state of PoweredUp
     */
    public boolean getIsPoweredUp() {
        return myGame.getUser().isPoweredUp();
    }

    /**
     * Pass IsInvincible from myGame
     * @return state of isInvincible
     */
    public boolean getIsInvincible() {
        return myGame.getUser().isInvincible();
    }

    /**
     * Pass the number of level from myGame
     * @return the level of my game
     */
    public int getLevel() {
        return myGame.getLevel();
    }

    /**
     * Return whether the game is over
     * @return whether the game is over
     */
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

    /**
     * Add 1e6 points
     */
    public void addOneMillionPoints() {
        getGame().addScore(MILLION);
    }

    /**
     * Add 100 points
     */
    public void addOneHundredPoints() {
        getGame().addScore(ONE_HUNDRED);
    }

    /**
     * Add 500 points
     */
    public void addFiveHundredPoints() {
        getGame().addScore(FIVE_HUNDRED);
    }

    /**
     * Reset the ghost status
     */
    public void resetGhosts() {
        List<CPUCreature> ghosts = getGame().getCPUs();
        for (CPUCreature ghost : ghosts) {
            ghost.die();
        }
    }

    /**
     * Add one more life
     */
    public void addLife() {
        getGame().addLives(ONE_LIFE);
    }

    /**
     * Head to next level in game
     */
    public void goToNextLevel() {
        getGame().nextLevel();
    }

    /**
     * set powerUp for the game
     */
    public void powerUp() {
        getGame().getUser().setPoweredUp(true);
    }

    /**
     * Free all the CPU ghosts
     */
    public void FreezeGhosts() {
        getGame().getCPUs().removeAll(getGame().getCPUs());
    }

    /**
     * Remove 1e6 points
     */
    public void RemoveOneMillionPoints() {
        getGame().addScore(-MILLION);
    }

    /**
     * Reset the player's status
     */
    public void resetUserPosition() {
        getGame().getUser().die();
    }

    /**
     * Lose 1 extra life
     */
    public void loseLife() {
        getGame().addLives(-ONE_LIFE);
    }

    /**
     * Execute end game program
     */
    public void gameOver() {
        getGame().endGame();
    }
}
