package ooga.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import ooga.controller.settings.Settings;
import ooga.view.popups.ErrorView;
import org.apache.commons.lang3.ObjectUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader {

    // TODO: Should be placed into properties files or enum?
    private final String NUMBER_FORMAT_EXCEPTION_DIM = "Check the number format for the dimension value!";
    private final String NUMBER_FORMAT_EXCEPTION_BOARD = "Check the number format for values within the board!";
    private final String NUMBER_FORMAT_EXCEPTION_MAP = "Check the number format for keys in the map!";
    private final String NUMBER_FORMAT_EXCEPTION_SETTING = "Check the number format for keys in the settings!";

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
    private final String MISSING_CONTENT = "Please check your game file because you are missing some inputs";
    private final String WRONG_BOARD_DIMENSION = "The dimension of the board does not match.";

//    private final List<String> SETTING_PARAMETERS = List.of(
//            "LANGUAGE", "GAME_TITLE",
//            "TIMER", "LIVES",
//            "CELL_SIZE", "CSS_FILE_NAME",
//            "USER_IS_PREDATOR", "HARD",
//            "IS_PICKUPS_A_VALID_WIN_CONDITION");
    private final List<String> PACMAN_PARAMETERS = List.of("USER_IMAGE");
    private final List<String> CPU_GHOST_PARAMETERS = List.of("CPU_IMAGE");
    private final List<String> WALL_PARAMETERS = List.of("WALL_COLOR");
    private final List<String> FOOD_PARAMETERS = List.of("POWERUP_COLOR", "POWERUP_SIZE");
    private final List<String> GAME_SETTINGS = List.of(
            "SETTINGS", "PACMAN",
            "CPUGHOST", "WALL",
            "SCOREBOOSTER", "STATECHANGER",
            "SCOREMULTIPLIER", "GHOSTSLOWER",
            "EXTRALIFE", "INVINCIBILITY",
            "PORTAL", "SPEEDCUTTER",
            "WINLEVEL");

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

    private final List<String> OBJECT_PARAMETERS =
            List.of(
                    "0", "1", "2", "3",
                    "6", "7", "8", "9",
                    "10", "11", "12"
                    );

    private final List<String> CREATURE_PARAMETERS =
            List.of("4", "5");


    /*
    TODO: Other exceptions maybe for the frontend or controller?
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
    //TODO: add deprecated methods
    public JSONContainer readJSONConfig() {
        JSONObject jsonData = extractJSONObject();

        // if cannot extract jsonData file
        if (jsonData == null){
            return null;
        }

        int numOfRows = getDimension(jsonData, "ROW_NUMBER");
        int numOfCols = getDimension(jsonData, "COL_NUMBER");

        List<List<Integer>> boardInfo = getBoardInfo(jsonData, numOfRows, numOfCols);

        Map<Integer, String> conversionMap = getConversionMap(jsonData, "OBJECT_MAP");
        Map<Integer, String> creatureMap = getConversionMap(jsonData, "CREATURE_MAP");
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
        // TODO: should all food items be a map?

        if (isMissingSettings(mapList)) return null;

        return new GameSettings(mapList);
    }

    private boolean isMissingSettings(Map<String, Map<String, String>> mapList) {

        for (String keyString : GAME_SETTINGS) {
            if (mapList.get(keyString) == null || mapList.get(keyString).isEmpty()) {
                myErrorView.showError(MISSING_CONTENT);
                return true;
            }
            else { // if all elements required in the mapList
                Set<String> parameterSet = mapList.get(keyString).keySet();
                for (String parameter : SETTING_PARAMETERS.get(keyString)) {
                    if (!parameterSet.contains(parameter)) {
                        myErrorView.showError(MISSING_CONTENT);
                        return true;
                    }
                }
                // TODO: concatenate all keysets into a large set
            }
        }
        return false;
    }

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
            if (isMissingObjects(conversionMap)) return null;
            return conversionMap;
        }

        catch (NullPointerException e) {myErrorView.showError(NULL_POINTER_EXCEPTION_SETTING);}
        catch (NumberFormatException e){myErrorView.showError(NUMBER_FORMAT_EXCEPTION_SETTING);}
        catch (ClassCastException e) {myErrorView.showError(CLASS_CAST_EXCEPTION_SETTING);}

        return null;
    }

    private boolean isMissingObjects(Map<String, String> conversionMap) {
        for (String keyString : conversionMap.keySet()) {
            if (conversionMap.get(keyString) == null || conversionMap.get(keyString).isEmpty()) {
                myErrorView.showError(MISSING_CONTENT);
                return true;
            }
        }
        return false;
    }

    /*
    Extract the name of each game object using the conversion map
     */
    private List<List<String>> getStringBoard(List<List<Integer>> boardInfo, Map<Integer, String> conversionMap, Map<Integer, String> creatureMap, int numOfRows, int numOfCols){
        List<List<String>> stringBoard = new ArrayList<>();
        try {
            for (int i = 0; i < boardInfo.size(); i++) {
                List<String> innerList = new ArrayList<>();
                for (int j = 0; j < boardInfo.get(0).size(); j++) {
                    int currentValue = boardInfo.get(i).get(j);
                    innerList.add(conversionMap.containsKey(currentValue) ? conversionMap.get(currentValue) : creatureMap.get(currentValue));
                }
                stringBoard.addAll(Collections.singleton(innerList));
            }

            if (isMissingBoardStrings(stringBoard, numOfRows, numOfCols)) return null;
            return stringBoard;
        }
        catch (IndexOutOfBoundsException e) {myErrorView.showError(INDEX_OUT_BOUNDS_EXCEPTION);}
        catch (NullPointerException e) {myErrorView.showError(NULL_POINTER_EXCEPTION_BOARD);}
        return null;
    }

    private boolean isMissingBoardStrings(List<List<String>> stringBoard, int numOfRows, int numOfCols) {
        if (stringBoard.size() != numOfRows) {
            myErrorView.showError(WRONG_BOARD_DIMENSION);
            return true;
        }
        for (List<String> row : stringBoard) {
            if (row == null || row.isEmpty() || row.size() != numOfCols) {
                myErrorView.showError(WRONG_BOARD_DIMENSION);
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
            if (isMissingValues(conversionMap)) return null;
            return conversionMap;
        }
        catch (NullPointerException e) {myErrorView.showError(NULL_POINTER_EXCEPTION_MAP);}
        catch (NumberFormatException e){myErrorView.showError(NUMBER_FORMAT_EXCEPTION_MAP);}
        catch (ClassCastException e) {myErrorView.showError(CLASS_CAST_EXCEPTION_MAP);}
        return null;
    }

    private boolean isMissingValues(Map<Integer, String> conversionMap) {
        for (Integer keyValue : conversionMap.keySet()) {
            if (conversionMap.get(keyValue) == null || conversionMap.get(keyValue).isEmpty()) {
                myErrorView.showError(MISSING_CONTENT);
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
            if (isMissingBoardInfo(boardInfo, numOfRows, numOfCols)) return null;
            return boardInfo;
        }
        catch (NullPointerException e) { myErrorView.showError(NULL_POINTER_EXCEPTION_BOARD);}
        catch (NumberFormatException e){myErrorView.showError(NUMBER_FORMAT_EXCEPTION_BOARD);}
        catch (ClassCastException e) {myErrorView.showError(CLASS_CAST_EXCEPTION_BOARD);}
        return null;
    }

    private boolean isMissingBoardInfo(List<List<Integer>> boardInfo, int numOfRows, int numOfCols) {
        if (boardInfo.size() != numOfRows) {
            myErrorView.showError(WRONG_BOARD_DIMENSION);
            return true;
        }
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
        catch (IOException e) {myErrorView.showError(IOE_EXCEPTION);}
        catch (ParseException e) {myErrorView.showError(PARSE_EXCEPTION);}
        return null;
    }

    public String getMostRecentPath() {
        return myPath;
    }
}
