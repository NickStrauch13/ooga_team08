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

    // TODO: Should be placed into properties files or enum?
    private static final String NUMBER_FORMAT_EXCEPTION_DIM = "Check the number format for the dimension value!";
    private static final String NUMBER_FORMAT_EXCEPTION_BOARD = "Check the number format for values within the board!";
    private static final String NUMBER_FORMAT_EXCEPTION_MAP = "Check the number format for keys in the map!";

    private static final String CLASS_CAST_EXCEPTION_DIM = "Make sure the data type for the dimension is correct!";
    private static final String CLASS_CAST_EXCEPTION_BOARD = "Make sure the data type within the board is correct!";
    private static final String CLASS_CAST_EXCEPTION_MAP = "Make sure the data type for the map is correct!";

    private final String NULL_POINTER_EXCEPTION_DIM = "Check your dimension names in the json file!";
    private final String NULL_POINTER_EXCEPTION_BOARD = "Check your board name in the json file!";
    private final String NULL_POINTER_EXCEPTION_MAP = "Check your names for maps in the json file!";

    private final String INDEX_OUT_BOUNDS_EXCEPTION = "Check if the dimension of the board is correct!";
    private final String IOE_EXCEPTION = "IOE exceptions";
    private final String PARSE_EXCEPTION = "Parse exceptions";

    /*
    TODO: Other exceptions maybe for the frontend or controller?
    MissingResourceException -> Wrong values in map,
    myGame NullPointer,
    ClassNotFoundException for wrong key in map
    How to handle values on board but not in map keys?
     */

    private final String myPath;
    private ErrorView myErrorView;

    /**
     * The constructor of setup.
     * Currently, setup takes the file path of the game config file as the input
     * @param filePath directory of the JSON file
     */
    public JSONReader(String language, String filePath) {
        myPath = filePath;
        myErrorView = new ErrorView(language);
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
        String gameType = (String) jsonData.get("GAME_TYPE");
        String language = (String) jsonData.get("LANGUAGE");

        List<List<Integer>> boardInfo = getBoardInfo(jsonData);

//        Map<Integer, String> conversionMap = getConversionMap(jsonData);
        Map<Integer, String> conversionMap = getConversionMap(jsonData, "OBJECT_MAP");
        Map<Integer, String> creatureMap = getConversionMap(jsonData, "CREATURE_MAP");
        List<List<String>> stringBoard = getStringBoard(boardInfo, conversionMap, creatureMap);

        return new JSONContainer(numOfRows, numOfCols,gameType, language ,boardInfo, stringBoard, conversionMap, creatureMap);
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
            myErrorView.showError(NULL_POINTER_EXCEPTION_MAP);
        }
        catch (NumberFormatException e){
            myErrorView.showError(NUMBER_FORMAT_EXCEPTION_MAP);
        }
        catch (ClassCastException e) {
            myErrorView.showError(CLASS_CAST_EXCEPTION_MAP);
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
            myErrorView.showError(NULL_POINTER_EXCEPTION_BOARD);
        }
        catch (NumberFormatException e){
            myErrorView.showError(NUMBER_FORMAT_EXCEPTION_BOARD);
        }
        catch (ClassCastException e) {
            myErrorView.showError(CLASS_CAST_EXCEPTION_BOARD);
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
            myErrorView.showError(NULL_POINTER_EXCEPTION_DIM);
        }
        catch (NumberFormatException e) {
            myErrorView.showError(NUMBER_FORMAT_EXCEPTION_DIM);
        }
        catch (ClassCastException e) {
            myErrorView.showError(CLASS_CAST_EXCEPTION_DIM);
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

    public String getMostRecentPath() {
        return myPath;
    }
}
