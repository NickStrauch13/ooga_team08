package ooga.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader {

    private final String myPath;

    /**
     * The constructor of setup.
     * Currently, setup takes the file path of the game config file as the input
     * @param filePath directory of the JSON file
     */
    public JSONReader(String filePath) {
        myPath = filePath;
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

        Map<Integer, String> conversionMap = getConversionMap(jsonData);
        List<List<String>> stringBoard = getStringBoard(boardInfo, conversionMap);

        return new JSONContainer(numOfRows, numOfCols, boardInfo, stringBoard);
    }

    private List<List<String>> getStringBoard(List<List<Integer>> boardInfo, Map<Integer, String> conversionMap) {
        List<List<String>> stringBoard = new ArrayList<>();
        for (int i = 0; i < boardInfo.size(); i++) {
            List<String> innerList = new ArrayList<>();
            for (int j = 0; j < boardInfo.get(0).size(); j++) {
                int currentValue = boardInfo.get(i).get(j);
                innerList.add(conversionMap.get(currentValue));
            }
            stringBoard.addAll(Collections.singleton(innerList));
        }
        return stringBoard;
    }

    private Map<Integer, String> getConversionMap(JSONObject jsonData) {
        Map<Integer, String> conversionMap = new HashMap();
        Map JSONMap = ((HashMap) jsonData.get("map"));

        for (Object keyObject : JSONMap.keySet()) {
            String keyString = keyObject.toString().trim();
            int key = Integer.parseInt(keyString.trim());
            String stringValue = JSONMap.get(keyObject).toString().trim().toUpperCase();
            conversionMap.put(key, stringValue);
        }
        return conversionMap;
    }

    /**
     * Extract status information of the board from the JSON file
     * Credit: https://stackoverflow.com/questions/31285885/how-to-parse-a-two-dimensional-json-array-in-java
     * @param jsonData
     */
    private List<List<Integer>> getBoardInfo(JSONObject jsonData) {

        List<List<Integer>> boardInfo = new ArrayList<>();
        JSONArray JSONBoard = (JSONArray) jsonData.get("BOARD");
        Iterator<JSONArray> iterator = JSONBoard.iterator();

        while (iterator.hasNext()){
            List<Integer> innerList = new ArrayList<>();
            Iterator<String> innerIterator = iterator.next().iterator();
            while (innerIterator.hasNext()) {
                String nextToken = innerIterator.next();
                int nextNumber = Integer.parseInt(nextToken.trim());
                innerList.add(nextNumber);
            }
            boardInfo.addAll(Collections.singleton(innerList));
        }
        return boardInfo;
    }

    /*
    Extract information about the number of rows/columns from the json file
     */
    private int getDimension(JSONObject jsonData, String row_number) {
        String rowString = (String) jsonData.get(row_number);
        return Integer.parseInt(rowString.trim());
    }

    /*
    Extract the entire JSON object for further parsing
     */
    private JSONObject extractJSONObject() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object jsonContent = parser.parse(new FileReader(myPath));
        return (JSONObject) jsonContent;
    }

    //TODO: Will be moved to test later
    public static void main(String[] args) throws IOException, ParseException {
        // TODO: add this into json file or an enums as well. Try not to have any constant values at all
        final String FILE_PATH = "data/test/vanillaTest.json";

        JSONReader reader = new JSONReader(FILE_PATH);
        JSONContainer container = reader.readJSONConfig();

        System.out.println(container.getMyNumOfRows());
        System.out.println(container.getMyNumOfCols());
        System.out.println(container.getMyInfo());
        System.out.println(container.getMyStringBoard());
    }
}
