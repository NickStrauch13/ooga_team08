package ooga.models.game;

import ooga.models.gameObjects.GameObject;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

public class Board {
    private GameObject[][] myBoardObjects;
    private int rows;
    private int cols;
    private static final int WALL_STATE = 1;

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
//    private boolean createCreature(int xPos, int yPos, String creatureType) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException{
//        Class<?> creatureClass = Class.forName(myGameObjects.getString(creatureType));
//        Creature creature = (Creature)  creatureClass.getDeclaredConstructor(Integer.class,Integer.class).newInstance(xPos,yPos);
//        return true;
//    };


    /**
     * gets the current state of the cell
     * @return integer that represents the state of the cell
     */
    public boolean getisWallAtCell(int row, int col) {
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
}
