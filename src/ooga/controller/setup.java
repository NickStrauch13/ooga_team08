package ooga.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class setup {

    private final String myPath;

    public setup(String filePath) {
        myPath = filePath;
    }

    /**
     * reads data from CSV file to CSV container
     * setup
     */
    public void readJSONConfig() throws IOException, ParseException {

        JSONObject jsonData = extractJSONObject();

        int numOfRows = getDimension(jsonData, "ROW_NUMBER");
        int numOfCols = getDimension(jsonData, "COL_NUMBER");
        List<List<Integer>> boardInfo = getBoardInfo(jsonData);

    }

    /**
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

    private int getDimension(JSONObject jsonData, String row_number) {
        String rowString = (String) jsonData.get(row_number);
        return Integer.parseInt(rowString.trim());
    }

    private JSONObject extractJSONObject() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object jsonContent = parser.parse(new FileReader(myPath));
        return (JSONObject) jsonContent;
    }

    public static void main(String[] args) throws IOException, ParseException {
        // TODO: add this into json file or an enums as well. Try not to have any constant values at all
        final String FILE_PATH = "data/test/vanillaTest.json";

        setup setup = new setup(FILE_PATH);
        setup.readJSONConfig();
    }
}
