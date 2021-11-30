package ooga.models.game;

import ooga.models.gameObjects.GameObject;
import ooga.models.creatures.Creature;
import ooga.models.creatures.cpuControl.CPUCreature;
import ooga.models.creatures.userControl.UserCreature;

import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class Game implements PickupGame {

    public boolean setLastDirection(String lastDirection) {
        this.lastDirection = lastDirection;
        return true;
    }

    public void setBfsThreshold(int bfsThreshold) {
        this.bfsThreshold = bfsThreshold;
    }

    private int bfsThreshold = 1;
    private int standardBFSThreshold;
    private boolean gameOver=false;
    private String lastDirection;
    private int boardXSize;
    private int boardYSize;
    private static final int WALL_STATE = 1;
    private static final int EAT_CREATURE_SCORE = 400;
    private static final String[] POSSIBLE_DIRECTIONS= new String[]{
            "LEFT","DOWN","UP","RIGHT"
    };
    private ResourceBundle myCreatureResources;
    private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.models.creatures.resources.";

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

    public Game(Board board, int numPickUps, UserCreature userPlayer, List<CPUCreature> CPUCreatures,int cellSize){
        myBoard=board;
        pickUpsLeft = numPickUps;
        myUserControlled = userPlayer;
        activeCPUCreatures = CPUCreatures;
        myCreatureResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "directions");
        level=1;
        lives=3;
        score=0;
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

    public void step(){

        if (checkPickUps()){
            nextLevel();
            return;
        }
        if (checkLives()){
            endGame();
            return;
        }
        moveToNewPossiblePosition(myUserControlled, generateDirectionArray(lastDirection));
        for (CPUCreature currentCreature : activeCPUCreatures){
            if (stepCounter%myCellSize==0){
                currentCreature.setCurrentDirection(generateDirectionArray(calculateBFSDirection(currentCreature)));
                System.out.println(calculateBFSDirection(currentCreature));
            }
            moveToNewPossiblePosition(currentCreature,currentCreature.getCurrentDirection());
        }
        stepCounter++;

        if (stepCounter == powerupEndtime){
            myUserControlled.setPoweredUp(false);
            setBfsThreshold(standardBFSThreshold);
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

        int actualNewPositionX = (currentCreature.getXpos()+xDirection)%boardXSize;
        int actualNewPositionY = (currentCreature.getYpos()+yDirection)%boardYSize;

        if (!getIsWallAtPosition(corner1X,corner1Y)&&!getIsWallAtPosition(corner2X,corner2Y)){
            currentCreature.moveTo(actualNewPositionX,actualNewPositionY);
            return true;
        }
        return false;
    }

    private String calculateBFSDirection(CPUCreature cpu){

        String cpuDirection;
        int dest = getBFSgridCoordinate(myUserControlled);
        int src = getBFSgridCoordinate(cpu);
        LinkedList<Integer> potentialPath = getPathtoUser(myBoard.generateAdjacencies(),src,dest,myBoard.getCols()*myBoard.getRows());
        Random s = new Random();
        boolean usePath = s.nextInt(4)<standardBFSThreshold;
        if (potentialPath == null || usePath){
            Random r = new Random();
            cpuDirection = POSSIBLE_DIRECTIONS[r.nextInt(POSSIBLE_DIRECTIONS.length)];
        }
        else {
            int firstStep = potentialPath.getLast()-potentialPath.get(potentialPath.size()-2);
            System.out.println(firstStep);
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
        }
        return cpuDirection;
    }

    private LinkedList<Integer> getPathtoUser(
            Map<Integer,List<Integer>> adj,
            int s, int dest, int v)
    {
        int pred[] = new int[v];

        if (!BFS(adj, s, dest, v,pred)) {
            System.out.println("Given source and destination are not connected");
            return null;
        }

        LinkedList<Integer> path = new LinkedList<Integer>();
        int crawl = dest;
        path.add(crawl);
        while (pred[crawl] != -1) {
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }

        System.out.println("Path is ::");
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }
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
    };
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
        score=0;
        level=1;
        gameOver=false;

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

}
