package ooga.models.game;

import ooga.models.gameObjects.GameObject;
import ooga.models.creatures.Creature;
import ooga.models.creatures.cpuControl.CPUCreature;
import ooga.models.creatures.userControl.UserCreature;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


public class Game implements PickupGame {

    private String gameType = "";
    private int bfsThreshold = 1;
    private int standardBFSThreshold = 1;
    private boolean gameOver=false;
    private String lastDirection;
    private int boardXSize;
    private int timer;
    private int boardYSize;
    private static final int WALL_STATE = 1;
    private static final int EAT_CREATURE_SCORE = 400;
    private static final String[] POSSIBLE_DIRECTIONS= new String[]{
            "LEFT","DOWN","UP","RIGHT"
    };
    private ResourceBundle myCreatureResources;
    private ResourceBundle myGameTypeThresholds;
    private static final String CREATURE_RESOURCE_PACKAGE = "ooga.models.creatures.resources.";
    private static final String GAME_RESOURCE_PACKAGE = "ooga.models.resources.";

    public int getStepCounter() {
        return stepCounter;
    }

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
    private Set<String> visitedNodes = new HashSet<>();
    private Queue<String> queue = new ArrayDeque<String>();
    public Game(Board board){
        myBoard=board;
    }
    private int startingPickUps;

    public Game(Board board, int numPickUps, UserCreature userPlayer, List<CPUCreature> CPUCreatures,int cellSize){
        myBoard=board;
        pickUpsLeft = numPickUps;
        startingPickUps = numPickUps;
        myUserControlled = userPlayer;
        activeCPUCreatures = CPUCreatures;
        myCreatureResources = ResourceBundle.getBundle(CREATURE_RESOURCE_PACKAGE + "directions");
        myGameTypeThresholds = ResourceBundle.getBundle(GAME_RESOURCE_PACKAGE+"gameTypes");
        level=1;
        lives=3;
        score=0;
        timer = 5000;
        myCellSize = cellSize;
        initializeGhosts();
        boardXSize=cellSize*board.getCols();
        boardYSize=cellSize*board.getRows();
    }
    public UserCreature getUser(){
        return myUserControlled;
    }

    public GameObject getGameObject(int row, int col){
        return myBoard.getGameObject(row,col);
    }
    public List<CPUCreature> getCPUs(){
        return activeCPUCreatures;
    }

    public void setUserSpeed(int i){
        myUserControlled.setSpeed(i);
    }

    public void step() {
        timer--;


        if(gameType.equals("ANTIPACMAN")){
            if(timer==0){
                endGame();
            }
            if(checkLives()){
                nextLevel();
            }
        }
        if (checkPickUps()){
            nextLevel();
            return;
        }
        if (checkLives()){
            endGame();
            return;
        }
        adjustGhostCollisions(gameType);
        if (stepCounter%myUserControlled.getSpeed()==0){
            moveUser();
        }
        moveCPUCreaturesPacman(Integer.parseInt(myGameTypeThresholds.getString(gameType)));
        stepCounter++;

        if (stepCounter == powerupEndtime){
            myUserControlled.setPoweredUp(false);
            myUserControlled.setSpeed(myUserControlled.getStandardSpeed());
            myUserControlled.setInvincible(false);
            setBfsThreshold(standardBFSThreshold);
        }
    }
    public int getTime(){
        return timer/100;
    }

    private void adjustGhostCollisions(String gameType){
        if (Integer.parseInt(myGameTypeThresholds.getString(gameType))<4){
            myUserControlled.setPoweredUp(true);
        }
    }


    public void moveCreatureToCell(int[]cellIndex){
        myUserControlled.moveTo(cellIndex[1]*myCellSize+1,cellIndex[0]*myCellSize+1);
    }

    private void moveUser(){
        moveToNewPossiblePosition(myUserControlled,generateDirectionArray(lastDirection));
    }

    private void moveCPUCreaturesPacman(int bfsThreshold) {
        for (CPUCreature currentCreature : activeCPUCreatures){
            if (stepCounter%myCellSize==0){
                setBfsThreshold(bfsThreshold);
                currentCreature.setCurrentDirection(generateDirectionArray(adjustedMovement(Integer.parseInt(myGameTypeThresholds.getString(gameType)),currentCreature)));
                //System.out.println(adjustedMovement(Integer.parseInt(myGameTypeThresholds.getString(gameType)),currentCreature));
            }
            moveToNewPossiblePosition(currentCreature,currentCreature.getCurrentDirection());
        }
    }

    private boolean moveToNewPossiblePosition(Creature currentCreature, int[] direction){
        int xDirection = direction[0];
        int yDirection = direction[1];
        int xCorner = (xDirection+1)%2;
        int yCorner = (yDirection+1)%2;

        int corner1X = (currentCreature.getCenterX()+xDirection*currentCreature.getSize()/2+xDirection)%boardXSize+xCorner*currentCreature.getSize()/2;
        int corner1Y = (currentCreature.getCenterY()+yDirection*currentCreature.getSize()/2+yDirection)%boardYSize+yCorner*currentCreature.getSize()/2;

        int corner2X = (currentCreature.getCenterX()+xDirection*currentCreature.getSize()/2+xDirection)%boardXSize-xCorner*currentCreature.getSize()/2;
        int corner2Y = (currentCreature.getCenterY()+yDirection*currentCreature.getSize()/2+yDirection)%boardYSize-yCorner*currentCreature.getSize()/2;

        int actualNewPositionX = (currentCreature.getXpos()+xDirection + boardXSize) %boardXSize;
        int actualNewPositionY = (currentCreature.getYpos()+yDirection + boardYSize) %boardYSize;


        if (!getIsWallAtPosition(corner1X,corner1Y)&&!getIsWallAtPosition(corner2X,corner2Y)){
            currentCreature.moveTo(actualNewPositionX,actualNewPositionY);
            return true;
        }
        return false;
    }

    private String adjustedMovement(int threshold, CPUCreature cpu) {
        Random r = new Random();
        boolean usePath = r.nextInt(4)<threshold;
        String movementDirection;
        if (usePath){
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
        int firstStep = potentialPath.getLast()-potentialPath.get(potentialPath.size()-2);
        if (Math.abs(firstStep)>1){
            if (firstStep<0){
                cpuDirection = "DOWN";
            }
            else {
                cpuDirection = "UP";
            }
        }
        else{
            if (firstStep>0){
                cpuDirection = "LEFT";
            }
            else {
                cpuDirection = "RIGHT";
            }
        }
        return cpuDirection;
    }

    private LinkedList<Integer> getPathtoUser(
            Map<Integer,List<Integer>> adj,
            int s, int dest, int v)
    {
        int pred[] = new int[v];

        if (!BFS(adj, s, dest, v,pred)) {
            //System.out.println("Given source and destination are not connected");
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
        for (int i = 0; i < v; i++) {
            visited[i] = false;
            pred[i] = -1;
        }

        visited[src] = true;
        queue.add(src);

        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < adj.get(u).size(); i++) {
                //System.out.println(adj.get(u));
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

    private int getBFSgridCoordinate(Creature currentCreature){
        return getCellCoordinate(currentCreature.getYpos())*myBoard.getCols()+getCellCoordinate(currentCreature.getXpos());
    }

    private void initializeGhosts() {
        for (CPUCreature creature : activeCPUCreatures) {
            Random r = new Random();
            String randomDirection = POSSIBLE_DIRECTIONS[r.nextInt(POSSIBLE_DIRECTIONS.length)];
            creature.setCurrentDirection(generateDirectionArray(randomDirection));
        }
    }


    private boolean getIsWallAtPosition(double xPos, double yPos){
        int row = getCellCoordinate(yPos);
        int col = getCellCoordinate(xPos);
        return myBoard.getisWallAtCell(row, col);
    }

    public int getCellCoordinate(double pixels){
        return ((int)pixels)/myCellSize;
    }

    private boolean checkPickUps(){
        return pickUpsLeft ==0;
    }

    private boolean checkLives(){return lives == 0;}
    /**
     * Subtracts a life from the number of Pacman lives which is defined in this class. (maybe controller)
     * @return new number of lives remaining
     */
    private void loseLife(){
        lives-=1;
    };

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
    public void updatePickupsLeft(){
        pickUpsLeft--;
    }
    public void addLives(int numLives){
        lives+=numLives;
    }
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
        if(gameType.equals("ANTIPACMAN")){
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
     */
    public void addScore(int scoreToBeAdded){
        score+=scoreToBeAdded;
    }

    public void multiplyScore(int multiplier){
        score*=multiplier;
    }

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

    public boolean isGameOver() {
        return gameOver;
    }

    public void setPowerupEndtime(int powerupEndtime) {
        this.powerupEndtime = powerupEndtime;
    }

    public boolean setLastDirection(String lastDirection) {
        this.lastDirection = lastDirection;
        return true;
    }

    public void setBfsThreshold(int bfsThreshold) {
        this.bfsThreshold = bfsThreshold;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public ArrayList<int[]> getPortalLocations(){
        return myBoard.getPortalLocations();
    }

    public ArrayList<int[]> getWallLocations(){
        return myBoard.getWallLocations();
    }

    public void setPortalsGone(){myBoard.setPortalsGone();}

    public void removePortal(int[] portalLocations){
        myBoard.removePortal(portalLocations);
    }

    public void addLife(){
        lives++;
    }

    public void wallStateChange(boolean toSet){
        for (int[] wall:getWallLocations()){
            myBoard.setWallatCell(wall,toSet);
        }
    }
}
