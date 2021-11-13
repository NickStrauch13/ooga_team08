package ooga.models;

import ooga.models.creatures.Creature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Game {

    private String lastDirection;
    private int boardXSize=400;
    private int boardYSize=400;
    private int CELL_SIZE = 30;
    private static final int WALL_STATE = 1;
    private ResourceBundle myCreatureResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE+"directions");
    private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.models.creatures.resources";
    private int lives;
    private int score;
    private int level;
    private int pickUpsLeft;
    private Board myBoard;
    private List<Creature> activeCreatures = new ArrayList<>();

    public Game(Board board){
        myBoard=board;
    }

    public Game(Board board,int numPickUps){
        myBoard=board;
        pickUpsLeft = numPickUps;
    }

    public void step(){
        if (checkPickUps()){nextLevel();}
        if (checkLives()){ endGame();}

        moveCreatures();
    }

    private void moveCreatures(){
        for (Creature currentCreature :activeCreatures){
            int possibleNewPositionX = ((currentCreature.getHomeX()+currentCreature.getSpeed()*generateDirectionArray(lastDirection)[0])%boardXSize);
            int possibleNewPositionY = ((currentCreature.getHomeY()+currentCreature.getSpeed()*generateDirectionArray(lastDirection)[1])%boardYSize);

            int row = getCellCoordinate(possibleNewPositionX);
            int col = getCellCoordinate(possibleNewPositionY);

            if (myBoard.getCellState(row,col)!=WALL_STATE){
                currentCreature.moveTo(possibleNewPositionX,possibleNewPositionY);
            }
            else{
                currentCreature.moveTo(possibleNewPositionX,possibleNewPositionY);
            }
        }
    }

    private int getCellCoordinate(int pixels){
        return pixels/CELL_SIZE;
    }

    private boolean checkPickUps(){return pickUpsLeft ==0;}

    private boolean checkLives(){return lives == 0;}
    /**
     * Subtracts a life from the number of Pacman lives which is defined in this class. (maybe controller)
     * @return new number of lives remaining
     */
    private void loseLife(){
        lives-=1;
    };

    private void dealWithCollision(CollisionManager cm){

    }

    /**
     * Adds points to the score which is housed in this class.
     */
    private void addScore(int scoreToBeAdded){
        score+=scoreToBeAdded;
    };

    /**
     * Resets the lives and score if the user restarts the game etc.
     */
    private void resetCreatureStates(){
        for (Creature currentCreature : activeCreatures){
            currentCreature.die();
        }
        lives=0;
        score=0;
    };

    /**
     * Increments the level.
     */
    private void nextLevel(){
        level+=1;
    };

    /**
     * Gets the score and level and returns it
     */
    private void endGame(){

    };

    private int[] generateDirectionArray(String lastDirection){
        int[] directionArray = Arrays.stream(myCreatureResources.getString(lastDirection).split(",")).mapToInt(Integer::parseInt).toArray();
        return directionArray;
    }

    private String getLastDirection() {
        return lastDirection;
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public Board getMyBoard() {
        return myBoard;
    }

    public List<Creature> getActiveCreatures() {
        return activeCreatures;
    }
}
