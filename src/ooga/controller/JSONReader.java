package ooga.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import ooga.view.popups.ErrorView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader {
    private final String NULL_POINTER_EXCEPTION = "Check your variable names in the json file!";
    private final String NUMBER_FORMAT_EXCEPTION = "Check the number format!";
    private final String INDEX_OUT_BOUNDS_EXCEPTION = "Check if the dimension of the board is correct!";
    private final String IOE_EXCEPTION = "IOE exceptions";
    private final String PARSE_EXCEPTION = "Parse exceptions";
    private final String CLASS_CAST_EXCEPTION = "Make sure the data type is correct!";

    /*
    TODO: Other exceptions maybe for the frontend or controller?
    MissingResourceException -> Wrong values in map,
    myGame NullPointer,
    ClassNotFoundException for wrong key in map
     */


    private final String myPath;
    private ErrorView myErrorView;

    /**
     * The constructor of setup.
     * Currently, setup takes the file path of the game config file as the input
     * @param filePath directory of the JSON file
     */
    public JSONReader(String filePath) {
        myPath = filePath;
        myErrorView = new ErrorView();
    }

    /**
     * Read data from JSON file into a JSONReader object
     * @return the returned JSONReader object with info from the JSON game configuration file
     * @throws IOException
     * @throws ParseException
     */
    public JSONContainer readJSONConfig() throws IOException, ParseException {
        JSONObject jsonData = extractJSONObject();

        int numOfRows = getDimension(jsonData, "ROW_NUMBER");
        int numOfCols = getDimension(jsonData, "COL_NUMBER");
        List<List<Integer>> boardInfo = getBoardInfo(jsonData);

//        Map<Integer, String> conversionMap = getConversionMap(jsonData);
        Map<Integer, String> conversionMap = getConversionMap(jsonData, "OBJECT_MAP");
        Map<Integer, String> creatureMap = getConversionMap(jsonData, "CREATURE_MAP");
        List<List<String>> stringBoard = getStringBoard(boardInfo, conversionMap, creatureMap);

        return new JSONContainer(numOfRows, numOfCols, boardInfo, stringBoard, conversionMap, creatureMap);
    }

    /*
    Extract the name of each game object using the conversion map
     */
    private List<List<String>> getStringBoard(List<List<Integer>> boardInfo, Map<Integer, String> conversionMap, Map<Integer, String> creatureMap) {
        List<List<String>> stringBoard = new ArrayList<>();
        try {
            for (int i = 0; i < boardInfo.size(); i++) {
                List<String> innerList = new ArrayList<>();
                for (int j = 0; j < boardInfo.get(0).size(); j++) {
                    int currentValue = boardInfo.get(i).get(j);

//                innerList.add(conversionMap.get(currentValue));
                    // TODO: Wait for refactoring from the backend
                    innerList.add(conversionMap.containsKey(currentValue) ? conversionMap.get(currentValue) : creatureMap.get(currentValue));

                }
                stringBoard.addAll(Collections.singleton(innerList));
            }
        }
        catch (IndexOutOfBoundsException e) {
            myErrorView.showError(INDEX_OUT_BOUNDS_EXCEPTION);
        }
        return stringBoard;
    }

    /*
    Extract information about the translation from integer values to object names
     */
    private Map<Integer, String> getConversionMap(JSONObject jsonData, String objectType) {

        Map<Integer, String> conversionMap = new HashMap();
        try {
            Map JSONMap = ((HashMap) jsonData.get(objectType));

            for (Object keyObject : JSONMap.keySet()) {
                String keyString = keyObject.toString().trim();
                int key = Integer.parseInt(keyString.trim());
                String stringValue = JSONMap.get(keyObject).toString().trim().toUpperCase();
                conversionMap.put(key, stringValue);
            }
        }
        catch (NullPointerException e) {
            myErrorView.showError(NULL_POINTER_EXCEPTION);
        }
        catch (NumberFormatException e){
            myErrorView.showError(NUMBER_FORMAT_EXCEPTION);
        }
        catch (ClassCastException e) {
            myErrorView.showError(CLASS_CAST_EXCEPTION);
        }

        return conversionMap;
    }

    /**
     * Extract status information of the board from the JSON file
     * Credit: https://stackoverflow.com/questions/31285885/how-to-parse-a-two-dimensional-json-array-in-java
     * @param jsonData JSONObject that is extracted from the json file
     */
    private List<List<Integer>> getBoardInfo(JSONObject jsonData) {
        List<List<Integer>> boardInfo = new ArrayList<>();
        try {
            JSONArray JSONBoard = (JSONArray) jsonData.get("BOARD");
            Iterator<JSONArray> iterator = JSONBoard.iterator();

            while (iterator.hasNext()){
                List<Integer> innerList = new ArrayList<>();
                Iterator<String> innerIterator = iterator.next().iterator();
                while (innerIterator.hasNext()) {
                    String nextToken = innerIterator.next();
                    innerList.add(Integer.parseInt(nextToken.trim()));
                }
                boardInfo.addAll(Collections.singleton(innerList));
            }
        }
        catch (NullPointerException e) {
            myErrorView.showError(NULL_POINTER_EXCEPTION);
        }
        catch (NumberFormatException e){
            myErrorView.showError(NUMBER_FORMAT_EXCEPTION);
        }
        catch (ClassCastException e) {
            myErrorView.showError(CLASS_CAST_EXCEPTION);
        }
        return boardInfo;
    }

    /*
    Extract information about the number of rows/columns from the json file
     */
    private int getDimension(JSONObject jsonData, String row_number) {
        String rowString = "";
        try {
            rowString = (String) jsonData.get(row_number);
            return Integer.parseInt(rowString.trim());
        }
        catch (NullPointerException e){
            myErrorView.showError(NULL_POINTER_EXCEPTION);
        }
        catch (NumberFormatException e) {
            myErrorView.showError(NUMBER_FORMAT_EXCEPTION);
        }
        catch (ClassCastException e) {
            myErrorView.showError(CLASS_CAST_EXCEPTION);
        }
        return 0;
    }

    /*
    Extract the entire JSON object for further parsing
     */
    private JSONObject extractJSONObject() {
        JSONParser parser = new JSONParser();
        try {
            Object jsonContent = parser.parse(new FileReader(myPath));
            return (JSONObject) jsonContent;
        }
        catch (IOException e) {
            myErrorView.showError(IOE_EXCEPTION);
        }
        catch (ParseException e) {
            myErrorView.showError(PARSE_EXCEPTION);
        }
        return null;
    }
}
