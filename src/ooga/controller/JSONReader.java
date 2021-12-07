package ooga.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import ooga.view.popups.ErrorView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader {

    private static final String ROW_NUMBER = "ROW_NUMBER";
    private static final String COL_NUMBER = "COL_NUMBER";
    private static final String OBJECT_MAP = "OBJECT_MAP";
    private static final String CREATURE_MAP = "CREATURE_MAP";
    private static final String COMMA = ",";
    private static final int ZERO = 0;
    private static final String BOARD = "BOARD";
    // TODO: Should be placed into properties files or enum?
    private final String NUMBER_FORMAT_EXCEPTION_DIM = "Check the number format for the dimension value!";
    private final String NUMBER_FORMAT_EXCEPTION_BOARD = "Check the number format for values within the board!";
    private final String NUMBER_FORMAT_EXCEPTION_STRING_BOARD = "Check the number format for values within the string board!";
    private final String NUMBER_FORMAT_EXCEPTION_MAP = "Check the number format for keys in the map!";
    private final String NUMBER_FORMAT_EXCEPTION_SETTING = "Check the number format for keys in the settings!";
    private final String NUMBER_FORMAT_EXCEPTION_VALUES = "Check the number format for values in the settings!";


    private final String CLASS_CAST_EXCEPTION_DIM = "Make sure the data type for the dimension is correct!";
    private final String CLASS_CAST_EXCEPTION_BOARD = "Make sure the data type within the board is correct!";
    private final String CLASS_CAST_EXCEPTION_MAP = "Make sure the data type for the map is correct!";
    private final String CLASS_CAST_EXCEPTION_SETTING = "Make sure the data type for the setting is correct!";

    private String NULL_POINTER_EXCEPTION_DIM = "Check your dimension names in the json file!";
    private String NULL_POINTER_EXCEPTION_BOARD = "Check your board content in the json file!";
    private String NULL_POINTER_EXCEPTION_MAP = "Check your map content in the json file!";
    private String NULL_POINTER_EXCEPTION_SETTING = "Check your names for settings in the json file!";

    private final String INDEX_OUT_BOUNDS_EXCEPTION = "Check if the dimension of the board is correct!";
    private final String IOE_EXCEPTION = "IOE exceptions";
    private final String PARSE_EXCEPTION = "Parse exceptions";
    private final String MISSING_CONTENT = "Please check your game file because you have empty strings";
    private final String MISSING_INDEX = "Please check your game object maps in the file because you are missing game objects";

    private final String WRONG_BOARD_DIMENSION = "The dimension of the board does not match.";
    private final String WRONG_COL_DIMENSION = "The column dimension of the board does not match.";
    private final String SPLIT_ERROR = "Recheck the data splitting!";
    private final int COLOR_CHANNELS = 3;

//    private final List<String> FOOD_PARAMETERS = List.of("POWERUP_COLOR", "POWERUP_SIZE");
    private final List<String> GAME_SETTINGS = List.of(
            "SETTINGS", "PACMAN",
            "CPUGHOST", "WALL",
            "SCOREBOOSTER", "STATECHANGER",
            "SCOREMULTIPLIER", "GHOSTSLOWER",
            "EXTRALIFE", "INVINCIBILITY",
            "PORTAL", "SPEEDCUTTER",
            "WINLEVEL");
    private final List<String> INTEGER_ELEMENTS = List.of("TIMER", "LIVES", "CELL_SIZE", "USER_IS_PREDATOR", "HARD", "IS_PICKUPS_A_VALID_WIN_CONDITION", "POWERUP_SIZE");
    private final List<String> PARSE_ELEMENTS = List.of("WALL_COLOR", "POWERUP_COLOR");

    private final Map<String, List<String>> SETTING_PARAMETERS =
             Map.ofEntries(
                     Map.entry("SETTINGS" ,List.of("LANGUAGE", "GAME_TITLE", "TIMER", "LIVES", "CELL_SIZE",
                             "CSS_FILE_NAME", "USER_IS_PREDATOR", "HARD", "IS_PICKUPS_A_VALID_WIN_CONDITION")),
                     Map.entry("PACMAN" , List.of("USER_IMAGE")),
                     Map.entry( "CPUGHOST" ,  List.of("CPU_IMAGE")),
                     Map.entry( "WALL" ,  List.of("WALL_COLOR")),
                     Map.entry("SCOREBOOSTER" ,  List.of("POWERUP_COLOR", "POWERUP_SIZE")),
                     Map.entry( "STATECHANGER" ,  List.of("POWERUP_COLOR", "POWERUP_SIZE")),
                     Map.entry( "SCOREMULTIPLIER",  List.of("POWERUP_COLOR", "POWERUP_SIZE")),
                     Map.entry("GHOSTSLOWER" ,  List.of("POWERUP_COLOR", "POWERUP_SIZE")),
                     Map.entry("EXTRALIFE" ,  List.of("POWERUP_COLOR", "POWERUP_SIZE")),
                     Map.entry("INVINCIBILITY" ,  List.of("POWERUP_COLOR", "POWERUP_SIZE")),
                     Map.entry("PORTAL" ,  List.of("POWERUP_COLOR", "POWERUP_SIZE")),
                     Map.entry("SPEEDCUTTER" ,  List.of("POWERUP_COLOR", "POWERUP_SIZE")),
                     Map.entry("WINLEVEL" ,  List.of("POWERUP_COLOR", "POWERUP_SIZE"))
                     );

    private final Map<String, List<Integer>> OBJECT_PARAMETERS =
            Map.ofEntries(
                    Map.entry(OBJECT_MAP,
                            List.of(
                                    ZERO, 1, 2, 3,
                                    6, 7, 8, 9,
                                    10, 11, 12
                            )
                    ),
                    Map.entry(CREATURE_MAP,List.of(4, 5))
            );

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
    public JSONContainer readJSONConfig() {
        JSONObject jsonData = extractJSONObject();

        // if cannot extract jsonData file
        if (jsonData == null){
            return null;
        }

        int numOfRows = getDimension(jsonData, ROW_NUMBER);
        int numOfCols = getDimension(jsonData, COL_NUMBER);

        List<List<Integer>> boardInfo = getBoardInfo(jsonData, numOfRows, numOfCols);

        Map<Integer, String> conversionMap = getConversionMap(jsonData, OBJECT_MAP);
        Map<Integer, String> creatureMap = getConversionMap(jsonData, CREATURE_MAP);
        List<List<String>> stringBoard = getStringBoard(boardInfo, conversionMap, creatureMap, numOfRows, numOfCols);
        GameSettings gameSettings = getGameSettings(jsonData);

        return new JSONContainer(numOfRows, numOfCols, boardInfo, stringBoard, conversionMap, creatureMap, gameSettings);
    }
    
    /*
    Extract setting information from the game input file
     */
    private GameSettings getGameSettings(JSONObject jsonData) {
        Map<String,Map<String,String>> mapList = new HashMap<>();
        for (String parameter: GAME_SETTINGS) {
            mapList.put(parameter, getSettingMap(jsonData, parameter));
        }

        if (isMissingSettings(mapList)) return null;

        return new GameSettings(mapList);
    }

    /*
    Check if any major setting map is missing
     */
    private boolean isMissingSettings(Map<String, Map<String, String>> mapList) {
//        System.out.println(GAME_SETTINGS);
        for (String keyString : mapList.keySet()) {
//            System.out.println(keyString);
            if (mapList.get(keyString) != null) {
                if (mapList.get(keyString).isEmpty()) {
                    myErrorView.showError(MISSING_CONTENT);
                    return true;
                }
                else {
                    if (isMissingItems(mapList, keyString)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*
    Check if any item is missing
     */
    private boolean isMissingItems(Map<String, Map<String, String>> mapList, String keyString) {
        Map<String, String> settings = mapList.get(keyString);
        Set<String> parameterSet = settings.keySet();

//        System.out.println(parameterSet);
        for (String parameter : parameterSet) {
//            System.out.println(parameter);
//            System.out.println(INTEGER_ELEMENTS.contains(parameter));
//            System.out.println(PARSE_ELEMENTS.contains(parameter));

            if (INTEGER_ELEMENTS.contains(parameter)) {
                try {
                    Integer values = Integer.parseInt(settings.get(parameter));
                }
                catch (NumberFormatException e) {
                    myErrorView.showError(NUMBER_FORMAT_EXCEPTION_VALUES);
                    return true;
                }
            }
            else if (PARSE_ELEMENTS.contains(parameter)) {
                String[] RGBs = settings.get(parameter).split(COMMA);

                if (RGBs.length != COLOR_CHANNELS) {
                    myErrorView.showError(SPLIT_ERROR);
                    return true;
                }
                for (String rbgValue : RGBs) {
                    try {
                        Integer values = Integer.parseInt(rbgValue);
                    }
                    catch (NumberFormatException ee) {
                        myErrorView.showError(NUMBER_FORMAT_EXCEPTION_VALUES);
                        return true;
                    }
                }
            }
        }
        return false;
    }


//        for (String parameter : SETTING_PARAMETERS.get(keyString)) {
//            if (!parameterSet.contains(parameter)) {
//                myErrorView.showError(MISSING_CONTENT);
//                return true;
//            }
//        }

    /*
    Extract information about the translation from String values to object names
     */
    private Map<String, String> getSettingMap(JSONObject jsonData, String objectType) {

        Map<String, String> conversionMap = new HashMap();
        try {
            Map<String,String> JSONMap = ((HashMap)jsonData.get(objectType));
            if (JSONMap != null) {
                for (Object keyObject : JSONMap.keySet()) {
                    String keyString = keyObject.toString().trim();
                    String stringValue = JSONMap.get(keyObject).trim().toUpperCase();
                    conversionMap.put(keyString, stringValue);
                }
            }
            return conversionMap;
        }
        catch (NullPointerException e) {myErrorView.showError(NULL_POINTER_EXCEPTION_SETTING);}
        catch (ClassCastException e) {myErrorView.showError(CLASS_CAST_EXCEPTION_SETTING);}

        return null;
    }

    /*
    Extract the name of each game object using the conversion map
     */
    private List<List<String>> getStringBoard(List<List<Integer>> boardInfo, Map<Integer, String> conversionMap, Map<Integer, String> creatureMap, int numOfRows, int numOfCols){
        List<List<String>> stringBoard = new ArrayList<>();
        try {
            for (int i = ZERO; i < boardInfo.size(); i++) {
                List<String> innerList = new ArrayList<>();
                for (int j = ZERO; j < boardInfo.get(ZERO).size(); j++) {
                    int currentValue = boardInfo.get(i).get(j);
                    innerList.add(conversionMap.containsKey(currentValue) ? conversionMap.get(currentValue) : creatureMap.get(currentValue));
                }
                stringBoard.addAll(Collections.singleton(innerList));
            }

//            if (isMissingBoardStrings(stringBoard, numOfRows, numOfCols)) return null;
            return stringBoard;
        }
        catch (IndexOutOfBoundsException e) {myErrorView.showError(INDEX_OUT_BOUNDS_EXCEPTION);}
        catch (NullPointerException e) {myErrorView.showError(NUMBER_FORMAT_EXCEPTION_STRING_BOARD);}
        return null;
    }

    /*
    Check if stringBoard is null or the dimension for rows matches
     */
    private boolean isMissingBoardStrings(List<List<String>> stringBoard, int numOfRows, int numOfCols) {
        if (stringBoard == null || stringBoard.size() != numOfRows) {
            myErrorView.showError(WRONG_BOARD_DIMENSION);
            return true;
        }
//        return isColMismatch(stringBoard, numOfCols);
        return false;
    }

    /*
    Check if the dimension for columns matches
     */
    private boolean isColMismatch(List<List<String>> stringBoard, int numOfCols) {
        for (List<String> row : stringBoard) {
            if (row == null || row.isEmpty() || row.size() != numOfCols) {
                myErrorView.showError(WRONG_COL_DIMENSION);
                return true;
            }
        }
        return false;
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
//            if (isMissingValues(conversionMap, objectType)) return null;
            return conversionMap;
        }
        catch (NullPointerException e) {myErrorView.showError(NULL_POINTER_EXCEPTION_MAP);}
        catch (NumberFormatException e){myErrorView.showError(NUMBER_FORMAT_EXCEPTION_MAP);}
        catch (ClassCastException e) {myErrorView.showError(CLASS_CAST_EXCEPTION_MAP);}
        return null;
    }

    /*
    Check if either game objects or creatures are missing in the json file
     */
    private boolean isMissingValues(Map<Integer, String> conversionMap, String objectType) {
        for (Integer keyValue : conversionMap.keySet()) {
            if (conversionMap.get(keyValue) == null || conversionMap.get(keyValue).isEmpty()) {
                myErrorView.showError(MISSING_INDEX);
                return true;
            }
//            else {
//                Set<Integer> indexSet = conversionMap.keySet();
//                List<Integer> objectIndices = OBJECT_PARAMETERS.get(objectType);
//                return isMissingIndices(indexSet, objectIndices);
//            }
        }
        return false;
    }

    /*
    Check if any index for game objects is missing
     */
    private boolean isMissingIndices(Set<Integer> indexSet, List<Integer> objectIndices) {
        for (int index : objectIndices) {
            if (!indexSet.contains(index)) {
                myErrorView.showError(MISSING_INDEX);
                return true;
            }
        }
        return false;
    }

    /**
     * Extract status information of the board from the JSON file
     * Credit: https://stackoverflow.com/questions/31285885/how-to-parse-a-two-dimensional-json-array-in-java
     * @param jsonData JSONObject that is extracted from the json file
     */
    private List<List<Integer>> getBoardInfo(JSONObject jsonData, int numOfRows, int numOfCols) {
        List<List<Integer>> boardInfo = new ArrayList<>();
        try {
            JSONArray JSONBoard = (JSONArray) jsonData.get(BOARD);
            updateBoardInfo(boardInfo, JSONBoard);
//            if (isMissingBoardInfo(boardInfo, numOfRows, numOfCols)) return null;
            return boardInfo;
        }
        catch (NullPointerException e) { myErrorView.showError(NULL_POINTER_EXCEPTION_BOARD);}
        catch (NumberFormatException e){myErrorView.showError(NUMBER_FORMAT_EXCEPTION_BOARD);}
        catch (ClassCastException e) {myErrorView.showError(CLASS_CAST_EXCEPTION_BOARD);}
        return null;
    }

    /*
    Write information from JSONBoard into boardInfo
     */
    private void updateBoardInfo(List<List<Integer>> boardInfo, JSONArray JSONBoard) {
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

    /*
    Check if the board information is incomplete
     */
    private boolean isMissingBoardInfo(List<List<Integer>> boardInfo, int numOfRows, int numOfCols) {
        if (boardInfo == null || boardInfo.size() != numOfRows) {
            myErrorView.showError(WRONG_BOARD_DIMENSION);
            return true;
        }
//        return isBoardColMismatches(boardInfo, numOfCols);
        return false;
    }


    /*
    Check if the dimension for columns matches
     */
    private boolean isBoardColMismatches(List<List<Integer>> boardInfo, int numOfCols) {
        for (List<Integer> row : boardInfo) {
            if (row == null || row.isEmpty() || row.size() != numOfCols) {
                myErrorView.showError(WRONG_BOARD_DIMENSION);
                return true;
            }
        }
        return false;
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
        catch (NullPointerException e){myErrorView.showError(NULL_POINTER_EXCEPTION_DIM);}
        catch (NumberFormatException e) {myErrorView.showError(NUMBER_FORMAT_EXCEPTION_DIM);}
        catch (ClassCastException e) {myErrorView.showError(CLASS_CAST_EXCEPTION_DIM);}
        return ZERO;
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
        catch (IOException e) {myErrorView.showError(IOE_EXCEPTION);}
        catch (ParseException e) {myErrorView.showError(PARSE_EXCEPTION);}
        return null;
    }

    /**
     * Access the most recent file path
     * @return most recent file path
     */
    public String getMostRecentPath() {
        return myPath;
    }
}
