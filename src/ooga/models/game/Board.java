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
    private static final int WALL_STATE = 1;
    private List<CPUCreature> activeCPUCreatures = new ArrayList<>();
    private UserCreature myUserControlled;
    private int cpuCount = 0;
    private ResourceBundle myGameObjects;
    private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.models.resources.";
    private ArrayList<int[]> portalLocations = new ArrayList<int[]>();
    private ArrayList<int[]> wallLocations = new ArrayList<int[]>();
    public ArrayList<int[]> getPortalLocations() {
        return portalLocations;
    }

    public void setPortalsGone(){
        portalLocations = null;
    }

    public ArrayList<int[]> getWallLocations() {
        return wallLocations;
    }

    public void removePortal(int[] portalLocation){
        int index=0;
        for (int[] portal: portalLocations){
            if (Arrays.equals(portalLocation,portal)){
                index = portalLocations.indexOf(portal);
            }
        }
        portalLocations.remove(index);
    }

    public Board(int numRows, int numCols){
        myBoardObjects = new GameObject[numRows][numCols];
        myGameObjects = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "gameObjects");
        rows = numRows;
        cols = numCols;
    }

    public void createGameObject(int row, int col, String gameObjectType) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException{
        Class<?> gameObjectClass = Class.forName(myGameObjects.getString(gameObjectType));
        GameObject gameObject = (GameObject) gameObjectClass.getDeclaredConstructor(Integer.class, Integer.class).newInstance(row, col);
        myBoardObjects[row][col] = gameObject;
        //System.out.println("("+row+","+col+")");
        if (gameObjectType.contains("WALL")){
            myBoardObjects[row][col].setWall(true);
            wallLocations.add(new int[]{row,col});
        }
        else{
            if (gameObjectType.contains("PORTAL")){
                portalLocations.add(new int[]{row,col});
            }
            numPickupsAtStart++;
            moveableNodes++;
        }
    }

    /**
     * Adds a Pacman to the board when launching the game.
     * @param creatureType
     */
    public void createCreature(int xPos, int yPos, String creatureType, int creatureSize) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException{
        Class<Creature> creatureClass = (Class<Creature>)Class.forName(myGameObjects.getString(creatureType));
        Creature newCreature =  creatureClass.getDeclaredConstructor(Integer.class, Integer.class).newInstance(xPos, yPos);
        if (myGameObjects.getString(creatureType).contains("CPU")) {
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

    public Map<Integer,List<Integer>> generateAdjacencies(){
        Map<Integer,List<Integer>> myAdjacencies = new HashMap<>();
        for (int i=0;i<getCols()*getRows();i++){
            if (getNonWallNeighbors(i)!=null){
                myAdjacencies.put(i,getNonWallNeighbors(i));
            }
        }
        return myAdjacencies;
    }

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

    public void setWallatCell(int[] position, boolean set){
        myBoardObjects[position[0]][position[1]].setWall(set);
    }

    public GameObject getGameObject(int row, int col){
        return myBoardObjects[row][col];
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public List<CPUCreature> getMyCPUCreatures() {
        return activeCPUCreatures;
    }

    public UserCreature getMyUser() {
        return myUserControlled;
    }


    public CPUCreature getMyCPU(String myID) {
        for (CPUCreature cpu : activeCPUCreatures) {
            if (cpu.getId().equals(myID)) {
                return cpu;
            }
        }
        return null;
    }

    public int getNumPickupsAtStart() {
        return numPickupsAtStart;
    }

    public GameObject[][] getGameObjects() {
        return myBoardObjects;
    }

}
