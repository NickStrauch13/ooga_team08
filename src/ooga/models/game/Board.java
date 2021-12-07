package ooga.models.game;

import ooga.models.creatures.Creature;
import ooga.models.creatures.cpuControl.CPUCreature;
import ooga.models.creatures.userControl.UserCreature;
import ooga.models.gameObjects.GameObject;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Board {
    private GameObject[][] myBoardObjects;
    private int rows;
    private int cols;
    private int numPickupsAtStart=0;
    private int moveableNodes;
    private List<CPUCreature> activeCPUCreatures = new ArrayList<>();
    private UserCreature myUserControlled;
    private int cpuCount = 0;
    private ResourceBundle myGameObjects;
    private ArrayList<int[]> portalLocations = new ArrayList<int[]>();
    private ArrayList<int[]> wallLocations = new ArrayList<int[]>();
    private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.models.resources.";
    private static final String CPUSTRING = "CPU";
    private static final String WALLSTRING = "WALL";
    private static final String PORTALSTRING = "PORTAL";

    /**
     * Constructor for Board class
     * @param numRows number of rows on board
     * @param numCols number of columns on board
     */
    public Board(int numRows, int numCols){
        myBoardObjects = new GameObject[numRows][numCols];
        myGameObjects = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "gameObjects");
        rows = numRows;
        cols = numCols;
    }

    /**
     * Adds GameObjects to the board upon initialization
     * @param row row index of GameObjects on board
     * @param col col index of GameObjects on board
     * @param gameObjectType string referencing the type of gameObject
     * @throws ClassNotFoundException thrown if gameObjectType references a gameObject that does not exist
     * @throws NoSuchMethodException thrown if gameObjectType references a gameObject that does not exist
     * @throws InvocationTargetException thrown if gameObjectType references a gameObject that does not exist
     * @throws InstantiationException thrown if gameObjectType references a gameObject that does not exist
     * @throws IllegalAccessException thrown if gameObjectType references a gameObject that does not exist
     */
    public void createGameObject(int row, int col, String gameObjectType) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException{
        Class<?> gameObjectClass = Class.forName(myGameObjects.getString(gameObjectType));
        GameObject gameObject = (GameObject) gameObjectClass.getDeclaredConstructor(Integer.class, Integer.class).newInstance(row, col);
        myBoardObjects[row][col] = gameObject;
        if (gameObjectType.contains(WALLSTRING)){
            myBoardObjects[row][col].setWall(true);
            wallLocations.add(new int[]{row,col});
        }
        else{
            if (gameObjectType.contains(PORTALSTRING)){
                portalLocations.add(new int[]{row,col});
            }
            numPickupsAtStart++;
            moveableNodes++;
        }
    }

    /**
     * Adds creatures to the board when launching the game.
     * @param xPos initial x position of creature in pixels
     * @param yPos initial y position of creature in pixels
     * @param creatureType string referencing the type of creature
     * @param creatureSize size of creature
     * @throws ClassNotFoundException thrown if creatureType references a creature that does not exist
     * @throws NoSuchMethodException thrown if creatureType references a creature that does not exist
     * @throws InvocationTargetException thrown if creatureType references a creature that does not exist
     * @throws InstantiationException thrown if creatureType references a creature that does not exist
     * @throws IllegalAccessException thrown if creatureType references a creature that does not exist
     */
    public void createCreature(int xPos, int yPos, String creatureType, int creatureSize) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException{
        Class<Creature> creatureClass = (Class<Creature>)Class.forName(myGameObjects.getString(creatureType));
        Creature newCreature =  creatureClass.getDeclaredConstructor(Integer.class, Integer.class).newInstance(xPos, yPos);
        if (myGameObjects.getString(creatureType).contains(CPUSTRING)) {
            newCreature.setId(creatureType + cpuCount);
            activeCPUCreatures.add((CPUCreature)newCreature);
            cpuCount++;
        }
        else {
            myUserControlled = (UserCreature) newCreature;
        }
        newCreature.setSize(creatureSize);
        moveableNodes++;
    }

    /**
     * Generates a Map of each cell's index as key and list of non wall neighbors as value
     * @return Map of each cell's index as key and list of non wall neighbors as value
     */
    public Map<Integer,List<Integer>> generateAdjacencies(){
        Map<Integer,List<Integer>> myAdjacencies = new HashMap<>();
        for (int i=0;i<getCols()*getRows();i++){
            if (getNonWallNeighbors(i)!=null){
                myAdjacencies.put(i,getNonWallNeighbors(i));
            }
        }
        return myAdjacencies;
    }

    /**
     * Gets list of non-wall neighbors
     * @param index index of current cell
     * @return list of moveable neighbors that aren't walls
     */
    private ArrayList<Integer> getNonWallNeighbors(int index){
        int row = index/getCols();
        int col = index%getCols();
        ArrayList<Integer> acceptableNeighbors = new ArrayList<Integer>();
        if (getisWallAtCell(row,col)){return null;}

        for (int i=-1;i<=1;i++){
            for (int j=-1;j<=1;j++){
                if ((i==0 || j==0) && i!=j && row+i>=0 && row+i<getRows() && col+j>=0 && col+j<getCols()){
                    if (!getisWallAtCell(row+i,col+j)){
                        acceptableNeighbors.add((row+i)*getCols()+col+j);
                    }
                }
            }
        }
        return acceptableNeighbors;
    }
    /**
     * gets the current state of the cell
     * @return true if it's a wall
     */
    public boolean getisWallAtCell(int row, int col) {
        if (myBoardObjects[row][col]==null){
            return false;
        }
        return myBoardObjects[row][col].isWall();
    }

    /**
     * Sets wall at cell, used to dynamically update use of walls
     * @param position position of the cell in grid coordinates
     * @param set boolean referencing new state of wall
     */
    public void setWallatCell(int[] position, boolean set){
        myBoardObjects[position[0]][position[1]].setWall(set);
    }

    /**
     * Gets game object out at certain position
     * @param row row index of desired game object
     * @param col col index of desired game object
     * @return game object at row,col position
     */
    public GameObject getGameObject(int row, int col){
        return myBoardObjects[row][col];
    }

    /**
     * gets the number of columns on the board
     * @return number of columns on the board
     */
    public int getCols() {
        return cols;
    }

    /**
     * gets the number of rows on the board
     * @return number of rows on the board
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gets list of all CPU creatures
     * @return list of all CPU creatures objects
     */
    public List<CPUCreature> getMyCPUCreatures() {
        return activeCPUCreatures;
    }

    /**
     * Gets the user controlled object
     * @return user controlled object
     */
    public UserCreature getMyUser() {
        return myUserControlled;
    }

    /**
     * gets CPU object from certain ID
     * @param myID ID of desired CPU object
     * @return CPU object with ID myID
     */
    public CPUCreature getMyCPU(String myID) {
        for (CPUCreature cpu : activeCPUCreatures) {
            if (cpu.getId().equals(myID)) {
                return cpu;
            }
        }
        return null;
    }

    /**
     * gets number of pickups at start of game
     * @return number of pickups at start of game
     */
    public int getNumPickupsAtStart() {
        return numPickupsAtStart;
    }

    @Deprecated
    public GameObject[][] getGameObjects() {
        return myBoardObjects;
    }

    /**
     * Gets list of all portal locations on board
     * @return list of all portal locations on board
     */
    public ArrayList<int[]> getPortalLocations() {
        return portalLocations;
    }

    /**
     * Gets list of all wall locations on board
     * @return list of all wall locations on board
     */
    public ArrayList<int[]> getWallLocations() {
        return wallLocations;
    }
}
