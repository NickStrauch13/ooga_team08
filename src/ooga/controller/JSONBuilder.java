package ooga.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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


    private String classToInt(String className, Map<Integer, String> myMap) {
        for (Map.Entry<Integer, String> myClass : myMap.entrySet())
        {
            if (myClass.getValue().equals(className)) {
                return String.format("%d", myClass.getKey());
            }
        }
        return classToInt(className, myCreatureMap);
    }

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

    public String getBoardPath() {
        return FILE_PATH;
    }

}
