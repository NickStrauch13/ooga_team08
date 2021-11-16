public interface FileReader {

    /**
     * Read configuration information from a file
     */
    public InfoContainer readConfiguration();

    /*
    Extract the name of each game object using the conversion map
     */
    List<List<String>> getStringBoard(List<List<Integer>> boardInfo, Map<Integer, String> conversionMap, Map<Integer, String> creatureMap);

    /*
    Extract information about the translation from integer values to object names
     */
    Map<Integer, String> getConversionMap(JSONObject jsonData, String objectType);

    /**
     * Extract status information of the board from the JSON file
     * Credit: https://stackoverflow.com/questions/31285885/how-to-parse-a-two-dimensional-json-array-in-java
     * @param jsonData JSONObject that is extracted from the json file
     */
    List<List<Integer>> getBoardInfo(JSONObject jsonData);

    /*
    Extract information about the number of rows/columns from the json file
     */
    int getDimension(JSONObject jsonData, String row_number);

    /*
    Extract the entire JSON object for further parsing
     */
    JSONObject extractJSONObject();

}