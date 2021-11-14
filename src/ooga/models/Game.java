package ooga.models;

import ooga.models.creatures.Creature;
import ooga.models.creatures.cpuControl.CPUCreature;
import ooga.models.creatures.userControl.UserCreature;
import ooga.models.pickups.pickup;

import java.util.*;

public class Game {

    public void setLastDirection(String lastDirection) {
        this.lastDirection = lastDirection;
    }
    private boolean gameOver=false;
    private String lastDirection;
    private int boardXSize=400;
    private int boardYSize=400;
    private int CELL_SIZE = 30;
    private static final int WALL_STATE = 1;
    private static final int EAT_CREATURE_SCORE = 400;
    private static final String[] POSSIBLE_DIRECTIONS= new String[]{
            "left","down","up","right"
    };
    private ResourceBundle myCreatureResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE+"directions");
    private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.models.creatures.resources";
    private int lives;
    private int score;
    private int level;
    private int pickUpsLeft;
    private Board myBoard;
    private List<CPUCreature> activeCPUCreatures = new ArrayList<>();

    public UserCreature getMyUserControlled() {
        return myUserControlled;
    }

    private UserCreature myUserControlled;

    public Game(Board board){
        myBoard=board;
    }

    public Game(Board board, int numPickUps, UserCreature userPlayer){
        myBoard=board;
        pickUpsLeft = numPickUps;
        myUserControlled = userPlayer;
    }

    public void step(){
        if (checkPickUps()){nextLevel();}
        if (checkLives()){ endGame();}

        moveCreatures();
    }

    private void moveCreatures(){
        for (CPUCreature currentCreature : activeCPUCreatures){
            moveCPUCreature(currentCreature);
        }
        moveToNewPossiblePosition(myUserControlled,generateDirectionArray(lastDirection));
    }
    private boolean moveToNewPossiblePosition(Creature currentCreature, int[] direction){
        int xDirection = direction[0];
        int yDirection = direction[1];
        int possibleNewPositionX = ((currentCreature.getHomeX()+xDirection)%boardXSize);
        int possibleNewPositionY = ((currentCreature.getHomeY()+yDirection)%boardYSize);
        int row = getCellCoordinate(possibleNewPositionX+xDirection*currentCreature.getSize()/2);
        int col = getCellCoordinate(possibleNewPositionY+yDirection*currentCreature.getSize()/2);

        if (myBoard.getCellState(row,col)!=WALL_STATE){
            currentCreature.moveTo(possibleNewPositionX,possibleNewPositionY);
            return true;
        }
        return false;
    }

    private void moveCPUCreature(CPUCreature currentCreature) {
        int[] direction = currentCreature.getCurrentDirection();
        if(moveToNewPossiblePosition(currentCreature,direction));
        else {
            Random r = new Random();
            String randomDirection = POSSIBLE_DIRECTIONS[r.nextInt(POSSIBLE_DIRECTIONS.length)];
            currentCreature.setCurrentDirection(generateDirectionArray(randomDirection));
            moveCPUCreature(currentCreature);
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

    public void dealWithCollision(CollisionManager cm){
        if(cm.checkIfCollision()){
            if(cm.isCreature()){
                creatureVsCreatureCollision(cm);
            }
            else{
                creatureVSPickupCollision(cm);
            }
        }
    }

    private void creatureVSPickupCollision(CollisionManager cm) {
        int[] collisionIndex = Arrays.stream(cm.getCurrentCollision().split(",")).mapToInt(Integer::parseInt).toArray();
        pickup collidingPickup=myBoard.getPickup(getCellCoordinate(collisionIndex[0]),getCellCoordinate(collisionIndex[1]));
        addScore(collidingPickup.pickUp(myUserControlled));
    }

    private void creatureVsCreatureCollision(CollisionManager cm){
        if(myUserControlled.isPoweredUp()){
            addScore(EAT_CREATURE_SCORE);
            for(Creature c:activeCPUCreatures){
                if (c.getId().equals(cm.getCurrentCollision())){
                    c.die();
                    break;
                }
            }
        }
        else{
            myUserControlled.die();
            loseLife();
        }
    }

    /**
     * Adds points to the score which is housed in this class.
     */
    public void addScore(int scoreToBeAdded){
        score+=scoreToBeAdded;
    };

    /**
     * Resets the lives and score if the user restarts the game etc.
     */
    private void resetCreatureStates(){
        for (Creature currentCreature : activeCPUCreatures){
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
        gameOver=true;
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

    public List<CPUCreature> getActiveCreatures() {
        return activeCPUCreatures;
    }
}
