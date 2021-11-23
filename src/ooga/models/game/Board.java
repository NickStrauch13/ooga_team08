package ooga.models.game;


import ooga.models.creatures.Creature;
import ooga.models.creatures.cpuControl.CPUCreature;
import ooga.models.creatures.userControl.UserCreature;
import ooga.models.gameObjects.GameObject;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Board {
    private GameObject[][] myBoardObjects;
    private int rows;
    private int cols;
    private static final int WALL_STATE = 1;
    private List<CPUCreature> activeCPUCreatures = new ArrayList<>();
    private UserCreature myUserControlled;
    private int cpuCount = 0;
    private ResourceBundle myGameObjects;
    private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.models.resources.";

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
        if (gameObjectType.contains("WALL")){
            myBoardObjects[row][col].setWall(true);
        }
    }

    /**
     * Adds a Pacman to the board when launching the game.
     * @param creatureType
     */
    public void createCreature(int xPos, int yPos, String creatureType, int creatureSize) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException{
        Class<Creature> creatureClass = (Class<Creature>)Class.forName(myGameObjects.getString(creatureType));
        Creature newCreature = (Creature) creatureClass.getDeclaredConstructor(Integer.class, Integer.class).newInstance(xPos, yPos);
        if (newCreature instanceof CPUCreature) { //TODO get rid of instance of
            newCreature.setId(creatureType + cpuCount);
            activeCPUCreatures.add((CPUCreature)newCreature);
            cpuCount++;
        }
        else {
            myUserControlled = (UserCreature) newCreature;
        }
        newCreature.setSize(creatureSize);
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
            System.out.println(cpu.getId());
            if (cpu.getId().equals(myID)) {
                return cpu;
            }
        }
        return null;
    }

}
