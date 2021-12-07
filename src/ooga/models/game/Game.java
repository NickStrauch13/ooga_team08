package ooga.models.game;

import ooga.models.gameObjects.GameObject;
import ooga.models.creatures.Creature;
import ooga.models.creatures.cpuControl.CPUCreature;
import ooga.models.creatures.userControl.UserCreature;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


public class Game implements PickupGame {

    private boolean gameOver=false;
    private String lastDirection;
    private int boardXSize;
    private int timer;
    private int boardYSize;
    private static final int EAT_CREATURE_SCORE = 400;
    private static final String[] POSSIBLE_DIRECTIONS= new String[]{"DOWN","RIGHT","LEFT","UP"};
    private ResourceBundle myCreatureResources;
    private static final String CREATURE_RESOURCE_PACKAGE = "ooga.models.creatures.resources.";
    private static final String GAME_RESOURCE_PACKAGE = "ooga.models.resources.";
    private ArrayList<Integer> POSSIBLE_FIRST_STEPS = new ArrayList<Integer>(){};
    private int stepCounter;
    private int lives;
    private int score;
    private int level;
    private int pickUpsLeft;
    private Board myBoard;
    private List<CPUCreature> activeCPUCreatures;
    private int myCellSize;
    private int powerupEndtime=-1;
    private UserCreature myUserControlled;
    private int startingPickUps;
    private Map<String, String> gameSettings;
    private boolean isPredator;
    private boolean isHard;
    private boolean isPickups;
    private int startTime;
    private ArrayList<int[]> levelPortalLocations;

    /**
     * Simple constructor for Game class
     * @param board board on which game is played
     */
    public Game(Board board){
        myBoard=board;
    }

    /**
     * Complex constructor for game class
     * @param board board on which game is played
     * @param numPickUps number of pickups at beginning of game
     * @param userPlayer user controlled creature
     * @param CPUCreatures list of all CPU controlled creatures
     * @param cellSize size of cell on the board
     * @param generalSettings Map of game settings obtained from file
     */
    public Game(Board board, int numPickUps, UserCreature userPlayer, List<CPUCreature> CPUCreatures,int cellSize, Map<String, String> generalSettings){
        myBoard=board;
        pickUpsLeft = numPickUps;
        startingPickUps = numPickUps;
        myUserControlled = userPlayer;
        activeCPUCreatures = CPUCreatures;
        myCreatureResources = ResourceBundle.getBundle(CREATURE_RESOURCE_PACKAGE + "directions");
        level=1;
        myCellSize = cellSize;
        boardXSize=cellSize*board.getCols();
        boardYSize=cellSize*board.getRows();
        gameSettings = generalSettings;
        setGameSettings();
        startTime=timer;
        adjustGhostCollisions();
        createPossibleSteps();
        levelPortalLocations = (ArrayList<int[]>)myBoard.getPortalLocations().clone();
    }

    private void setGameSettings(){
        timer=Integer.parseInt(gameSettings.get("TIMER"));
        lives = Integer.parseInt(gameSettings.get("LIVES"));
        isPredator = gameSettings.get("USER_IS_PREDATOR").equals("1");
        isHard = gameSettings.get("HARD").equals("1");
        lives=Integer.parseInt(gameSettings.get("LIVES"));
        isPredator= Integer.parseInt(gameSettings.get("USER_IS_PREDATOR"))<0;
        isPickups = gameSettings.get("IS_PICKUPS_A_VALID_WIN_CONDITION").equals("1");
    }

    private void createPossibleSteps(){
        POSSIBLE_FIRST_STEPS.add(-myBoard.getCols());
        POSSIBLE_FIRST_STEPS.add(-1);
        POSSIBLE_FIRST_STEPS.add(1);
        POSSIBLE_FIRST_STEPS.add(myBoard.getCols());
    }

    private String randomDirection(){
        Random r = new Random();
        return POSSIBLE_DIRECTIONS[r.nextInt(POSSIBLE_DIRECTIONS.length)];
    }

    /**
     * gets user controlled object in the game
     * @return user controlled object in the game
     */
    public UserCreature getUser(){return myUserControlled;}

    /**
     * Gets game object out at certain position
     * @param row row index of desired game object
     * @param col col index of desired game object
     * @return game object at row,col position
     */
    public GameObject getGameObject(int row, int col){return myBoard.getGameObject(row,col);}

    /**
     * Gets list of all CPU creatures in the game
     * @return list of all CPU creatures objects
     */
    public List<CPUCreature> getCPUs(){return activeCPUCreatures;}

    /**
     * Sets new speed for the user controlled creature
     * @param newSpeed desired new speed of user controlled creature
     */
    public void setUserSpeed(double newSpeed){myUserControlled.setSpeed(newSpeed);}

    /**
     * alters CPU speed by inputted multiplier
     * @param multiplier value to multiply current CPU speed by
     */
    public void setCPUSpeed(double multiplier){
        for(CPUCreature creature: activeCPUCreatures) {
            creature.setSpeed(creature.getSpeed()*multiplier);
        }
    }

    /**
     * step function that gets called at each step of program
     * Checks win/loss conditions, updates positions of moving objects, and handles resetting powerups if necessary
     */
    public void step() {
        timer--;
        if(isPredator){
            predatorWinLoss();
        }
        else {
            preyWinLoss();
        }
        if (stepCounter%myUserControlled.getSpeed()==0){
            moveUser();
        }
        moveCPUCreaturesPacman();
        stepCounter++;
        resetPowerups(stepCounter);
    }

    private void predatorWinLoss(){
        if(timer==0){
            endGame();
            return;
        }
        if(checkLives()){
            nextLevel();
            return;
        }
    }

    private void preyWinLoss(){
        if (checkPickUps() && isPickups) {
            nextLevel();
            return;
        }
        if (checkLives()||timer==0) {
            endGame();
            return;
        }
    }

    private void resetPowerups(int stepCounter){
        if (stepCounter == powerupEndtime){
            myUserControlled.setPoweredUp(false);
            myUserControlled.setSpeed(myUserControlled.getStandardSpeed());
            myUserControlled.setInvincible(false);
        }
    }

    /**
     * gets the current time of the animation
     * @return the current time of the animation
     */
    public int getTime(){return timer/100;}

    private void adjustGhostCollisions(){
        myUserControlled.setPoweredUp(isPredator);
    }

    /**
     * moves creature to a certain cell on board
     * @param cellIndex index of cell (equal to row index*numCols+ col index)
     */
    public void moveCreatureToCell(int[]cellIndex){
        myUserControlled.moveTo(cellIndex[1]*myCellSize+1,cellIndex[0]*myCellSize+1);
    }

    private void moveUser(){
        moveToNewPossiblePosition(myUserControlled,generateDirectionArray(lastDirection));
    }

    private void moveCPUCreaturesPacman() {
        for (CPUCreature currentCreature : activeCPUCreatures){
            if (ghostFullyInCell(currentCreature)){
                currentCreature.setCurrentDirection(generateDirectionArray(adjustedMovement(currentCreature)));
            }
            if (stepCounter%currentCreature.getSpeed()==0) {
                moveToNewPossiblePosition(currentCreature,currentCreature.getCurrentDirection());
            }
        }
    }

    private boolean moveToNewPossiblePosition(Creature currentCreature, int[] direction){
        int actualNewPositionX = (currentCreature.getXpos()+direction[0] + boardXSize) %boardXSize;
        int actualNewPositionY = (currentCreature.getYpos()+direction[1] + boardYSize) %boardYSize;

        if (checkCorners(currentCreature,direction)){
            currentCreature.moveTo(actualNewPositionX,actualNewPositionY);
        }
        return checkCorners(currentCreature,direction);
    }

    private boolean checkCorners(Creature currentCreature,int[] direction){
        int xDirection = direction[0];
        int yDirection = direction[1];
        int xCorner = (xDirection+1)%2;
        int yCorner = (yDirection+1)%2;

        int corner1X = (currentCreature.getCenterX()+xDirection*currentCreature.getSize()/2+xDirection)%boardXSize+xCorner*currentCreature.getSize()/2;
        int corner1Y = (currentCreature.getCenterY()+yDirection*currentCreature.getSize()/2+yDirection)%boardYSize+yCorner*currentCreature.getSize()/2;

        int corner2X = (currentCreature.getCenterX()+xDirection*currentCreature.getSize()/2+xDirection)%boardXSize-xCorner*currentCreature.getSize()/2;
        int corner2Y = (currentCreature.getCenterY()+yDirection*currentCreature.getSize()/2+yDirection)%boardYSize-yCorner*currentCreature.getSize()/2;

        return !getIsWallAtPosition(corner1X,corner1Y)&&!getIsWallAtPosition(corner2X,corner2Y);
    }

    private String adjustedMovement(CPUCreature cpu) {
        Random r = new Random();
        String movementDirection;
        if (isHard){
            movementDirection = bfsChase(cpu);
        }
        else{
            movementDirection = POSSIBLE_DIRECTIONS[r.nextInt(4)];
        }
        return movementDirection;
    }

    private String bfsChase(CPUCreature cpu){
        String cpuDirection;
        int dest = getBFSgridCoordinate(myUserControlled);
        int src = getBFSgridCoordinate(cpu);
        LinkedList<Integer> potentialPath = getPathtoUser(myBoard.generateAdjacencies(),src,dest,myBoard.getCols()*myBoard.getRows());
        if (potentialPath==null){
          return randomDirection();
        }
        int firstStep = potentialPath.getLast()-potentialPath.get(potentialPath.size()-2);
        cpuDirection = POSSIBLE_DIRECTIONS[POSSIBLE_FIRST_STEPS.indexOf(firstStep)];
        return cpuDirection;
    }

    private LinkedList<Integer> getPathtoUser(Map<Integer,List<Integer>> adj, int s, int dest, int v) {
        int pred[] = new int[v];
        if (!BFS(adj, s, dest, v,pred)) {
            return null;
        }
        LinkedList<Integer> path = new LinkedList<Integer>();
        int crawl = dest;
        path.add(crawl);
        while (pred[crawl] != -1) {
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }
//        System.out.println("Path is ::");
//        for (int i = path.size() - 1; i >= 0; i--) {
//            System.out.print(path.get(i) + " ");
//        }
        return path;
    }

    private boolean BFS(Map<Integer,List<Integer>> adj, int src,int dest, int v, int[] pred) {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        boolean visited[] = new boolean[v];
        setBFSInitials(visited,pred,v);
        visited[src] = true;
        queue.add(src);
        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < adj.get(u).size(); i++) {
                if (!visited[adj.get(u).get(i)]) {
                    visited[adj.get(u).get(i)] = true;
                    pred[adj.get(u).get(i)] = u;
                    queue.add(adj.get(u).get(i));
                    if (adj.get(u).get(i) == dest)
                        return true;
                }
            }
        }
        return false;
    }

    private void setBFSInitials(boolean[] visited,int[] pred,int v){
        for (int i = 0; i < v; i++) {
            visited[i] = false;
            pred[i] = -1;
        }
    }

    private int getBFSgridCoordinate(Creature currentCreature){
        return getCellCoordinate(currentCreature.getYpos())*myBoard.getCols()+getCellCoordinate(currentCreature.getXpos());
    }

    private boolean ghostFullyInCell(Creature cpu){
        return getCellCoordinate(cpu.getXpos()) == getCellCoordinate(cpu.getXpos()+cpu.getSize()) && getCellCoordinate(cpu.getYpos()) == getCellCoordinate(cpu.getYpos()+cpu.getSize());
    }

    private boolean getIsWallAtPosition(double xPos, double yPos){
        int row = getCellCoordinate(yPos);
        int col = getCellCoordinate(xPos);
        return myBoard.getisWallAtCell(row, col);
    }

    /**
     * gets cell coordinate based on pixel value
     * @param pixels pixel value
     * @return cell coordinate at a certain pixel value
     */
    public int getCellCoordinate(double pixels){return ((int)pixels)/myCellSize;}

    private boolean checkPickUps(){return pickUpsLeft ==0;}

    private boolean checkLives(){return lives == 0;}

    /**
     * Subtracts a life from the number of Pacman lives which is defined in this class. (maybe controller)
     * @return new number of lives remaining
     */
    private void loseLife(){
        lives-=1;
    };

    /**
     * Handles collisions of user with other objects
     * @param cm Collision manager instance with the object of collision within it
     * @return true if collision dealt executed, false if not or collided with wall
     */
    public boolean dealWithCollision(CollisionManager cm){
        if(cm.checkIfCollision()){
            if(cm.isCreature()){
                return creatureVsCreatureCollision(cm);
            }
            else{
                return creatureVSPickupCollision(cm);
            }
        }
        cm.setCollision(null);
        return false;
    }

    /**
     * decreases the number of pickups left by 1
     */
    public void updatePickupsLeft(){
        pickUpsLeft--;
    }

    /**
     * adds certain number of lives
     * @param numLives number of lives to be added
     */
    public void addLives(int numLives){
        lives+=numLives;
    }

    /**
     * Handles collision with pickups
     * @param cm CollisionManager instance
     * @return true if colliding with anything but wall, false if wall
     */
    public boolean creatureVSPickupCollision(CollisionManager cm) {
        String[] collisionIndex = cm.getCurrentCollision().split(",");
        GameObject collidingPickup = myBoard.getGameObject(Integer.parseInt(collisionIndex[0]) , Integer.parseInt(collisionIndex[1]));
        if (!collidingPickup.isWall()) {
            collidingPickup.interact(this);
            return true;
        }
        return false;
    }

    private boolean creatureVsCreatureCollision(CollisionManager cm){
        if(isPredator){
            lives--;
        }
        if(myUserControlled.isPoweredUp()){
            addScore(EAT_CREATURE_SCORE);
            for(Creature c:activeCPUCreatures){
                if (c.getId().equals(cm.getCurrentCollision())){
                    c.die();
                    break;
                }
            }
        }
        else if (myUserControlled.isInvincible()){
            return true;
        }
        else{
            myUserControlled.die();
            loseLife();
            for (Creature currentCreature : activeCPUCreatures){
                currentCreature.die();
            }
        }
        return true;
    }

    /**
     * Adds points to the score which is housed in this class.
     * @param scoreToBeAdded score to be added
     */
    public void addScore(int scoreToBeAdded){
        score+=scoreToBeAdded;
    }

    /**
     * Multiply score by certain variable
     * @param multiplier value to multiply score by
     */
    public void multiplyScore(int multiplier){
        score*=multiplier;
    }

    /**
     * reset the game to its original state
     */
    public void resetGame(){
        resetCreatureStates();
    }

    /**
     * Resets the lives and score if the user restarts the game etc.
     */
    private void resetCreatureStates(){
        for (Creature currentCreature : activeCPUCreatures){
            currentCreature.die();
        }
        myUserControlled.die();
        lives=3;
        pickUpsLeft = startingPickUps;
        gameOver=false;
    }

    /**
     * Increments the level.
     */
    public void nextLevel(){
        level+=1;
        resetPortals();
        timer= (int) (5000/Math.pow(1.1,level));
    }

    /**
     * Gets the score and level and returns it
     */
    public void endGame(){
        gameOver=true;
    }

    private int[] generateDirectionArray(String lastDirection){
        int[] directionArray = Arrays.stream(myCreatureResources.getString(lastDirection).split(",")).mapToInt(Integer::parseInt).toArray();
        return directionArray;
    }

    /**
     * gets the number of lives left
     * @return lives left
     */
    public int getLives() {return lives;}

    /**
     * gets the score
     * @return score
     */
    public int getScore() {return score;}

    /**
     * gets the current level
     * @return current level
     */
    public int getLevel() {return level;}

    /**
     * gets the board
     * @return board
     */
    public Board getMyBoard() {return myBoard;}

    /**
     * checks whether game should end
     * @return true if game should end, false if not
     */
    public boolean isGameOver() {return gameOver;}

    /**
     * sets the end time for a powerup
     * @param powerupEndtime
     */
    public void setPowerupEndtime(int powerupEndtime) {this.powerupEndtime = powerupEndtime;}

    /**
     * sets the last direction of user creature
     * @param lastDirection direction to be set to last
     * @return true when complete
     */
    public boolean setLastDirection(String lastDirection) {
        this.lastDirection = lastDirection;
        return true;
    }

    /**
     * gets remaining portal locations on current level
     * @return remaining portal locations on current level
     */
    public ArrayList<int[]> getPortalLocations(){return levelPortalLocations;}

    /**
     * gets the number of times step has been run
     * @return number of times step has been run
     */
    public int getStepCounter() {return stepCounter;}

    /**
     * removes all portals from board
     */
    public void setPortalsGone(){levelPortalLocations=null;}

    /**
     * removes one portal from board
     * @param portalLocation location of portal to be removed
     */
    public void removePortal(int[] portalLocation){
        int index=-1;
        for (int[] currentPortal : levelPortalLocations){
            if (Arrays.equals(portalLocation,currentPortal)){
                index = levelPortalLocations.indexOf(currentPortal);
            }
        }
        levelPortalLocations.remove(index);
    }

    private void resetPortals(){
        levelPortalLocations = (ArrayList<int[]>)myBoard.getPortalLocations().clone();
    }

    /**
     * adds one life
     */
    public void addLife(){
        lives++;
    }
}
