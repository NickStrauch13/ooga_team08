package ooga.controller;

import java.util.ArrayList;
import java.util.Map;

public class JSONBuilder {

    private static final int DEFAULT_BOARD_SIZE = 10;
    private static final int ROW = 0;
    private static final int COL = 1;
    private static final int CLASSNAME = 2;
    private Controller myController;
    private  Map<Integer, String> myObjectMap;
    private  Map<Integer, String> myCreatureMap;

    public JSONBuilder(ViewerControllerInterface myController) {
        myObjectMap = myController.createGameObjectMap();
        myCreatureMap = myController.createCreatureMap();
    }
    public void compileBoard(ArrayList<String> userAdded) {
        int row, col;
        String className;
        String[][] myBoard = new String[DEFAULT_BOARD_SIZE][DEFAULT_BOARD_SIZE];
        for (String id : userAdded) {
            row = getPosition(id, ROW);
            col = getPosition(id, COL);
            className = getIDClass(id);
            if (inCreatureMap(className) || inObjectMap(className)) {
                myBoard[row][col] = classToInt(className, myObjectMap);
                System.out.println(myBoard[row][col]);
            }
        }
    }

    private String classToInt(String className, Map<Integer, String> myMap) {
        for (Map.Entry<Integer, String> myClass : myMap.entrySet())
        {
            if (myClass.getValue().equals(className)) {
                return String.format("%d", myClass.getKey());
            }
        }
        return classToInt(className, myCreatureMap);
    }

    public int getPosition(String id, int i ) {
        String[] position = splitId(id);
        return Integer.parseInt(position[i]);
    }

    public String getIDClass(String id) {
        String[] idArray = splitId(id);
        return idArray[CLASSNAME];
    }

    public String[] splitId(String id) {
        return id.split(",");
    }

    private boolean inObjectMap(String className) {
        return myObjectMap.values().contains(className);
    }

    private boolean inCreatureMap(String className) {
        return myCreatureMap.values().contains(className);
    }



}
