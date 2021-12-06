package ooga.controller;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;

import javafx.stage.Stage;
import java.lang.Integer;

import javafx.util.Pair;
import ooga.models.creatures.cpuControl.CPUCreature;
import ooga.models.game.Board;
import ooga.models.game.CollisionManager;
import ooga.models.game.Game;
import ooga.view.gameDisplay.center.BoardView;
import ooga.view.home.HomeScreen;
import ooga.view.popups.ErrorView;
import org.json.simple.parser.ParseException;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.List;

import ooga.view.gameDisplay.gamePieces.MovingPiece;

public class Controller implements CheatControllerInterface,BasicController, ViewerControllerInterface {

    // TODO: Constant values should be in a file probably - enum? -> settings.properties
    private final double ANIMATION_SPEED = 0.3;
    private final int HIGH_SCORE_VALS = 10;
    private final int WIDTH = 1000; // TODO: properties file
    private final int HEIGHT = 600;
    public final int CELL_SIZE = 25;
    private int cellSize;


    private final Dimension DEFAULT_SIZE = new Dimension(WIDTH, HEIGHT);

    // TODO: Should be put into a properties file?
    public static final String TITLE = "Start Screen";
    public static final String gameType = "Pacman"; //TODO update gameType variable

    // TODO: exceptions.properties
    // TODO: refactor into viewController and boardController if I have time

    private final String IOE_EXCEPTION_CSV = "IOE exceptions for CSV file path. Please check your CSV file";
    private final String IOE_EXCEPTION = "IOE exceptions";
    private final String NULL_POINTER_EXCEPTION = "Null pointer exception controller";
    private final String PARSE_EXCEPTION = "Parse exception!";
    private final String CLASS_NOT_FOUND = "Class not found!";
    private final String INVOCATION_TARGET = "Invocation target error!";
    private final String NO_SUCH_METHOD = "There is no such method! ";
    private final String INSTANTIATION_EXCEPTION = "Can't instantiate!";
    private final String ILLEGAL_ACCESS = "Access illegal! ";
    //    private final String EXCEPTION = "Something is wrong here!";

    private static final String DEFAULT_USERNAME = "Guest";
    private static final int MILLION = 1000000;
    private static final int ONE_HUNDRED = 100;
    private static final int FIVE_HUNDRED = 500;
    private static int DEFAULT_CELL_SIZE = 24;

    private static final String[] BLANK_ENTRY = new String[]{"", "-1"};

    private Game myGame;
    private Board myBoard;
    private BoardView myBoardView;
    private double animationSpeed;
    private ArrayList<MovingPiece> myMovingPieces;
    private HomeScreen myStartScreen;
    private CollisionManager collisionManager;
    private Map<Integer, String> creatureMap;
    private JSONReader myReader;
    private CSVReader myCSVReader;
    private CSVWriter myCSVWriter;
    private Map<Integer, String> gameObjectMap;
    private List<List<String>> stringBoard;
    private Stage myStage;
    private String myUsername;
    private ErrorView myErrorView;
    private ResourceBundle myLanguages;

    private GameSettings myGameSettings;
    private String language;
    private String cssFileName;

    private static final String LANGUAGE_RESOURCE_PACKAGE = "ooga.models.resources.";
    private static final String DEFAULT_CSS_FILE = "Default.css";
    private static final String DEFAULT_LANGUAGE = "ENGLISH";
    private final String SCORE_PATH = "./data/highscores/HighScores.csv";


    /**
     * The constructor of the game controller that starts and controls the overall communication between the frontend and backend
     *
     * @param stage the Stage object for the view
     */
    public Controller(Stage stage){
        cssFileName = DEFAULT_CSS_FILE;
        myLanguages = ResourceBundle.getBundle(LANGUAGE_RESOURCE_PACKAGE + "languages");
        language = myLanguages.getString(DEFAULT_LANGUAGE);

        myStartScreen = new HomeScreen(stage, DEFAULT_SIZE.width, DEFAULT_SIZE.height, this);
        collisionManager = new CollisionManager();

        myErrorView = new ErrorView(DEFAULT_LANGUAGE);
        initializeStage(stage);
        initializeCSVIO();

        myUsername = DEFAULT_USERNAME;
        cellSize = DEFAULT_CELL_SIZE;
    }

    private void initializeStage(Stage stage) {
        animationSpeed = ANIMATION_SPEED;
        myStage = stage;
        myStage.setTitle(TITLE);
        myStage.setScene(myStartScreen.createScene());
        myStage.show();
    }

    // TODO: need to handle incorrect CSV path as well
    /*
    Initialize the reader and writer for the CSV IO.
     */
    private void initializeCSVIO(){

        try {
            File scoreFile = new File(SCORE_PATH);
            myCSVReader = new CSVReader(new FileReader(scoreFile));
            myCSVWriter = new CSVWriter(new FileWriter(scoreFile, true), ',', CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
        }
        catch (IOException e){
            myErrorView.showError(IOE_EXCEPTION);
        }

    }

    // TODO: I think this should be private, and I definitely need to refactor this as well
    // TODO: Throw vs. try/catch here

    /**
     * Initialize a Pacman game
     *
     * @param path The directory of a layout file
     */
    public void initializeGame(String path) {
        try {
            myReader = new JSONReader(language, path);
            assembleBoards();
            //TODO get lives from JSON file
        } catch (ClassNotFoundException e) {
            myErrorView.showError(CLASS_NOT_FOUND);
        } catch (InvocationTargetException e) {
            myErrorView.showError(INVOCATION_TARGET);
        } catch (IllegalAccessException e) {
            myErrorView.showError(ILLEGAL_ACCESS);
        } catch (NoSuchMethodException e) {
            myErrorView.showError(NO_SUCH_METHOD);
        } catch (IOException e) {
            myErrorView.showError(IOE_EXCEPTION);
        } catch (InstantiationException e) {
            myErrorView.showError(INSTANTIATION_EXCEPTION);
        } catch (ParseException e) {
            myErrorView.showError(PARSE_EXCEPTION);
        }
//        catch (NullPointerException e) {
//            myErrorView.showError(NULL_POINTER_EXCEPTION);
//        }
    }

    private void assembleBoards() throws IOException, ParseException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        JSONContainer container = myReader.readJSONConfig();

        if (container != null && !container.isMissingContent()) {
            // TODO: if exception being thrown, shouldn't run the following code
            int numOfRows = container.getMyNumOfRows();
            int numOfCols = container.getMyNumOfCols();

            extractInfoFromContainer(container);

            constructBoard(numOfRows, numOfCols);

            myBoardView = new BoardView(this);
            initializeBoardView(numOfRows, numOfCols, gameObjectMap, stringBoard, myBoardView);

            myGame = new Game(myBoard, myBoard.getNumPickupsAtStart(), myBoard.getMyUser(), myBoard.getMyCPUCreatures(),
                    cellSize, myGameSettings.getGeneralSettings()); //TODO assigning pickups manually assign from file!!
        }
    }

    /*
    Extract setting information from the Container to set up the game
     */
    private void extractInfoFromContainer(JSONContainer container) {
        myGameSettings = container.getMyGameSettings();
        stringBoard = container.getMyStringBoard();
        language = myLanguages.getString(myGameSettings.getGeneralSettings().get("LANGUAGE").trim());
        cssFileName = myGameSettings.getGeneralSettings().get("CSS_FILE_NAME").trim();
        setCellSize(Integer.parseInt(myGameSettings.getGeneralSettings().get("CELL_SIZE").trim()));
    }

    /*
    Create objects required and construct the game board
     */
    private void constructBoard(int numOfRows, int numOfCols) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        gameObjectMap = createGameObjectMap();
        creatureMap = createCreatureMap();
        myBoard = new Board(numOfRows, numOfCols);
        initializeBoard(numOfRows, numOfCols, gameObjectMap, stringBoard);
    }

    public Map<Integer,String> createGameObjectMap() {
         return Map.ofEntries(Map.entry(0,"WALL"),Map.entry(1,"SCOREBOOSTER"),Map.entry(2 ,"STATECHANGER"),
                Map.entry(3 , "SCOREMULTIPLIER"),Map.entry( 6 , "PORTAL"),Map.entry( 7 , "GHOSTSLOWER"), Map.entry(8 , "EXTRALIFE"),
                Map.entry( 9 , "EMPTY"), Map.entry( 10, "INVINCIBILITY"),Map.entry(11 , "SPEEDCUTTER"), Map.entry(12 , "WINLEVEL"));

    }

    public Map<Integer,String> createCreatureMap() {
        return Map.ofEntries(Map.entry(4,"PACMAN"),Map.entry(5,"CPUGHOST"));
    }

    /*
    Initialize all game objects within the Board object
     */
    private void initializeBoard(int numOfRows, int numOfCols, Map<Integer, String> gameObjectMap, List<List<String>> stringBoard) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (int row = 0; row < numOfRows; row++) {
            for (int col = 0; col < numOfCols; col++) {
                String objectName = stringBoard.get(row).get(col);
                if (gameObjectMap.containsValue(objectName) && !objectName.equals("EMPTY")) {
                    myBoard.createGameObject(row, col, objectName);
                } else if (creatureMap.containsValue(objectName)) {
                    myBoard.createCreature(col * cellSize + 3, row * cellSize + 3, objectName,
                            cellSize - 5);
                }
            }
        }
    }

    /*
    Initialize all pieces within the BoardView object
     */
    private void initializeBoardView(int numOfRows, int numOfCols, Map<Integer, String> gameObjectMap, List<List<String>> stringBoard, BoardView boardView) {
        for (int row = 0; row < numOfRows; row++) {
            for (int col = 0; col < numOfCols; col++) {
                String objectName = stringBoard.get(row).get(col);//TODO MAKE SURE NOT SETTINGS
                if (!objectName.equals("EMPTY")) {
                    if (gameObjectMap.containsValue(objectName)) {
                        boardView.addBoardPiece(row, col, objectName, myGameSettings.getAllSettings().get(objectName));
                    } else {
                        if (objectName.equals("PACMAN")) { //TODO I added this in as a temporary fix. We need a way to tell if the creature is user controlled or CPU controlled. Maybe have the user specify what piece they want to control in the json file?
                            boardView.addUserCreature(row, col, objectName, myGameSettings.getAllSettings().get(objectName));
                        } else if (objectName.equals("CPUGHOST")) {
                            boardView.addCPUCreature(row, col, objectName, myGameSettings.getAllSettings().get(objectName));
                        }

                    }
                }
            }
        }
    }

    public int getCellCoordinate(double pixels) {
        return ((int) pixels) / cellSize;
    }

    /**
     * Get the number of lives remained
     *
     * @return the number of lives remained
     */
    public int getLives() {
        return myGame.getLives(); //TODO change this to the model's get lives
    }

    public Game getGame() {
        return myGame;
    }

    /**
     * Get the current game scores
     *
     * @return the current game scores
     */
    public int getScore() {
        return myGame.getScore();
    }


    public boolean getIsPoweredUp() {
        return myGame.getUser().isPoweredUp();
    }

    public boolean getIsInvincible() {
        return myGame.getUser().isInvincible();
    }

    /**
     * Get the BoardView object of the game
     *
     * @return the Boardview object
     */
    public BoardView getBoardView() {
        return myBoardView;
    }

    /**
     * Get the dimension of each cell
     *
     * @return the size of a cell in the board
     */
    public int getCellSize() {
        return cellSize;
    }

    /**
     * Update and sync each frame of the game with the last direction used
     *
     * @param direction the string value for the direction
     */
    public void step(String direction) {
        myGame.setLastDirection(direction);
        myGame.step();
    }

    /**
     * Access the current coordinates of the user
     *
     * @return (x, y) of the current position
     */
    public int[] getUserPosition() {
        int[] newPosition = {myBoard.getMyUser().getXpos(), myBoard.getMyUser().getYpos()};
        return newPosition;
    }

    /**
     * Gets the new ghost position of the ghost identified by the given ID
     *
     * @param nodeID
     * @return
     */
    public int[] getGhostPosition(String nodeID) {
        if (myBoard.getMyCPU(nodeID) != null) {
            int[] newPosition = {myBoard.getMyCPU(nodeID).getXpos(), myBoard.getMyCPU(nodeID).getYpos()};
            return newPosition;
        }
        //System.out.print("NOT FOUND IN CREATURE ARRAY");
        return null;
    }

    /**
     * METHOD ONLY FOR TESTFX TESTS. Needed some way to load in a file into the file chooser.
     */
    public void changeToGameScreen(String filePath) {
        myStartScreen.startNewGameForViewTests(filePath);
    }

    /**
     * Sends information about the collision to the backend
     *
     * @param nodeID
     * @return
     */
    public boolean handleCollision(String nodeID) {
        collisionManager.setCollision(nodeID);
        return myGame.dealWithCollision(collisionManager);
    }


    public void loadNextLevel(BoardView boardView) {
        myGame.resetGame();
        initializeBoardView(myBoard.getRows(), myBoard.getCols(), gameObjectMap, stringBoard, boardView);
    }

    /**
     * Receive the backend's command to reset the entire game
     */
    public void restartGame() {
        initializeGame(myReader.getMostRecentPath());
    }


    /**
     * Adds a new Username:Score combo to the high score CSV file
     *
     * @param nameAndScore String array where the first element is the name and the second element is the score
     */
    public void addScoreToCSV(String[] nameAndScore) {
        myCSVWriter.writeNext(nameAndScore);
        try {
            myCSVWriter.close();
        } catch (IOException e) {
            //TODO
            myErrorView.showError(IOE_EXCEPTION);
        }
    }

    /**
     * Read high score CSV and get the top ten scores.
     *
     * @return List of string arrays where each String array is a single username:score combo.
     */
    public List<String[]> getScoreData() {
        List allScoreData = new ArrayList();
        try {
            allScoreData = myCSVReader.readAll();
        } catch (IOException e) {
            //TODO
            myErrorView.showError(IOE_EXCEPTION);
        }
        return findTopTenScores(allScoreData);
    }


    private List<String[]> findTopTenScores(List<String[]> allScores) {
        List<String[]> topTen = new ArrayList<>();
        int numToDisplay = HIGH_SCORE_VALS;
        if (allScores.size() < HIGH_SCORE_VALS) {
            numToDisplay = allScores.size();
        }
        for (int i = 0; i < numToDisplay; i++) {
            topTen.add(BLANK_ENTRY);
        }
        optimizeTopTen(allScores, topTen, numToDisplay);
        return topTen;
    }

    private void optimizeTopTen(List<String[]> allScores, List<String[]> topTen, int numToDisplay) {
        for (String[] score : allScores) {
            for (int i = 0; i < numToDisplay; i++) {
                if (Integer.parseInt(score[1]) > Integer.parseInt(topTen.get(i)[1])) {
                    topTen.add(i, score);
                    break;
                }
            }
        }
        while (topTen.size() > HIGH_SCORE_VALS) {
            topTen.remove(topTen.size() - 1);
        }
    }

    public int getLevel() {
        return myGame.getLevel();
    }

    public boolean isGameOver() {
        return myGame.isGameOver();
    }

    public String getLanguage() {
        return language;
    }

    /**
     * Sets the username string for the game.
     *
     * @param username String inputted by user on the home screen. Defaults to "Guest"
     */
    public void setUsername(String username) {
        myUsername = username;
    }

    /**
     * Returns the username for the current game.
     *
     * @return String representing username
     */
    public String getUsername() {
        return myUsername;
    }

    public String getViewMode() {
        return cssFileName;
    }

    /**
     * Returns the current time of the game.
     *
     * @return Integer values representing time.
     */
    public int getGameTime() {
        return myGame.getTime();
    }

    public void addOneMillionPoints() {
        getGame().addScore(MILLION);
    }

    public void addOneHundredPoints() {
        getGame().addScore(ONE_HUNDRED);
    }

    public void addFiveHundredPoints() {
        getGame().addScore(FIVE_HUNDRED);
    }

    public void resetGhosts() {
        List<CPUCreature> ghosts = getGame().getCPUs();
        for (CPUCreature ghost : ghosts) {
            ghost.die();
        }
    }

    public void addLife() {
        getGame().addLives(1);
    }

    public void goToNextLevel() {
        getGame().nextLevel();
    }

    public void powerUp() {
        getGame().getUser().setPoweredUp(true);
    }

    public void FreezeGhosts() {
        getGame().getCPUs().removeAll(getGame().getCPUs());
    }//TODO (billion, billion)

    public void RemoveOneMillionPoints() {
        getGame().addScore(-MILLION);
    }

    public void resetUserPosition() {
        getGame().getUser().die();
    }

    public void loseLife() {
        getGame().addLives(-1);
    }

    public void gameOver() {
        getGame().endGame();
    }

    public int getTimer() {
        return Integer.parseInt(myGameSettings.getGeneralSettings().get("TIMER"));
    }

    public String getGameType() {
        return myGameSettings.getGeneralSettings().get("GAME_TITLE");
    }

    public void setCellSize(int newSize) {
        cellSize = newSize;
    }


}