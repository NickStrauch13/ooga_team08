package ooga.controller;

import java.util.ArrayList;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Produces a JSON file using the user input data from the UI screen
 * Author: Neil Mosca
 */
public class JSONBuilder {

    private final String FILE_PATH = "./data/boardBuilderBoards/output.json";
    private static final int DEFAULT_BOARD_SIZE = 10;
    private static final int ROW = 0;
    private static final int COL = 1;
    private static final int CLASSNAME = 2;
    private static final String WALL = "WALL";
    private Controller myController;
    private  Map<Integer, String> myObjectMap;
    private  Map<Integer, String> myCreatureMap;
    private String[][] fileBoard;

    /**
     * Initializes the maps that are added to the file so the file can recognize the objects
     * that are added to the board
     * @param myController
     */
    public JSONBuilder(ViewerControllerInterface myController) {
        myObjectMap = myController.createGameObjectMap();
        myCreatureMap = myController.createCreatureMap();
    }

    private void createJSONFile() {
        JSONObject jsonObject = new JSONObject();
        JSONArray myJSONArray = new JSONArray();
        jsonObject.put("ROW_NUMBER", String.format("%d",fileBoard.length));
        jsonObject.put("COL_NUMBER", String.format("%d",fileBoard[0].length));
        compileJSONArray(myJSONArray);
        jsonObject.put("BOARD", myJSONArray);
        jsonObject.put("OBJECT_MAP", myCreatureMap);
        jsonObject.put("CREATURE_MAP", myObjectMap);
        try {
            FileWriter file = new FileWriter(FILE_PATH);
            file.write(jsonObject.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructs a 2d array of the gameObjects that were added to the gameBoard
     * so that they can be read into the file
     * @param userAdded
     */
    public void compileBoard(ArrayList<String> userAdded) {
        int row, col;
        String className;
        fileBoard = new String[DEFAULT_BOARD_SIZE][DEFAULT_BOARD_SIZE];
        for (String id : userAdded) {
            row = getPosition(id, ROW);
            col = getPosition(id, COL);
            className = getIDClass(id);
            if (inCreatureMap(className) || inObjectMap(className)) {
                String myMapInteger = classToInt(className, myObjectMap);
                fileBoard[row][col] = myMapInteger;
            }
        }
        createJSONFile();
    }


    /**
     * Gets the integer value that corresponds to a gameObject  or creatureObject
     * @param className
     * @param myMap
     * @return
     */
    private String classToInt(String className, Map<Integer, String> myMap) {
        for (Map.Entry<Integer, String> myClass : myMap.entrySet())
        {
            if (myClass.getValue().equals(className)) {
                return String.format("%d", myClass.getKey());
            }
        }
        return classToInt(className, myCreatureMap);
    }

    /**
     * Builds the JSON array that stores the board in the JSON file
     * @param myJSONArray
     */
    private void compileJSONArray(JSONArray myJSONArray) {
        for (int r = 0; r < fileBoard.length; r++) {
            JSONArray newRow = new JSONArray();
            myJSONArray.add(newRow);
            for (int c = 0; c < fileBoard[0].length; c++) {
                if (fileBoard[r][c] == null) {
                    String empty  = classToInt(WALL, myObjectMap);
                    newRow.add(empty);
                }
                else {
                    newRow.add(fileBoard[r][c]);
                }
            }
        }
    }

    /**
     * Gets the position of the gameObject that was added by the BoardBuilder
     * @param id
     * @param i
     * @return
     */
    public int getPosition(String id, int i ) {
        String[] position = splitId(id);
        return Integer.parseInt(position[i]);
    }

    /**
     * Gets the id of the gameObject that was added using the BoardBuilder
     * @param id
     * @return
     */
    public String getIDClass(String id) {
        String[] idArray = splitId(id);
        return idArray[CLASSNAME];
    }

    /**
     *Creates an array from the id string (used by get class and position)
     * @param id
     * @return
     */
    public String[] splitId(String id) {
        return id.split(",");
    }

    private boolean inObjectMap(String className) {
        return myObjectMap.values().contains(className);
    }

    private boolean inCreatureMap(String className) {
        return myCreatureMap.values().contains(className);
    }

    /**
     * returns the board path so a new game can be created with the constructed file
     * @return
     */
    public String getBoardPath() {
        return FILE_PATH;
    }

    public String[][] getFileBoard() {
        return fileBoard;
    }

}
