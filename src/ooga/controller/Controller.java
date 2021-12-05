package ooga.controller;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import javafx.stage.Stage;
import java.lang.Integer;

import ooga.models.game.Board;
import ooga.models.game.CollisionManager;
import ooga.models.game.Game;
import ooga.view.gameDisplay.center.BoardView;
import ooga.view.home.HomeScreen;
import ooga.view.popups.ErrorView;
import org.json.simple.parser.ParseException;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.List;

import ooga.view.gameDisplay.gamePieces.MovingPiece;

public class Controller implements CheatControllerInterface,BasicController, ViewerControllerInterface{

    // TODO: Constant values should be in a file probably - enum?
    public static final Dimension DEFAULT_SIZE = new Dimension(1000, 600);
    public static final String TITLE = "Start Screen";
    public static final String gameType = "Pacman"; //TODO update gameType variable
    public static final int CELL_SIZE = 25;

    // TODO: Should be put into a properties file?
    private final String IOE_EXCEPTION = "IOE exceptions";
    private final String NULL_POINTER_EXCEPTION = "Null pointer exception";
    private final String PARSE_EXCEPTION = "Parse exception!";
    private final String CLASS_NOT_FOUND = "Class not found!";
    private final String INVOCATION_TARGET = "Invocation target error!";
    private final String NO_SUCH_METHOD = "There is no such method! ";
    private final String INSTANTIATION_EXCEPTION = "Can't instantiate!";
    private final String ILLEGAL_ACCESS = "Access illegal! ";
    private final String EXCEPTION = "Something is wrong here!";
    private static final File SCORE_FILE = new File("./data/highscores/HighScores.csv");
    private static final int HIGH_SCORE_VALS = 10;
    private static final String[] BLANK_ENTRY = new String[]{"","-1"};
    private static final String DEFAULT_USERNAME = "Guest";


    private Game myGame;
    private Board myBoard;
    private BoardView myBoardView;
    private double animationSpeed;
    private ArrayList<MovingPiece> myMovingPieces;
    private HomeScreen myStartScreen;
    private CollisionManager collisionManager;
    private Map<Integer, String> creatureMap;
    private JSONReader reader;
    private CSVReader myCSVReader;
    private CSVWriter myCSVWriter;
    private Map<Integer, String> gameObjectMap;
    private List<List<String>> stringBoard;
    private Stage myStage;
    private String myUsername;
    private ErrorView myErrorView;
    private String language;
    private ResourceBundle myLanguages;
    private static final String LANGUAGE_RESOURCE_PACKAGE = "ooga.models.resources.";
    private static final String DEFAULT_LANGUAGE = "English";

    // TODO: Probably bad design to mix stage and board initialization at the same time. Will talk to my TA about this.
    // TODO: Maybe let the controller do readFile by moving readFile() from HomeScreen to Controller?
    /**
     * The constructor of the game controller that starts and controls the overall communication between the frontend and backend
     * @param stage the Stage object for the view
     * @throws IOException
     * @throws ParseException
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public Controller(Stage stage) throws IOException, ParseException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException  {
        language = DEFAULT_LANGUAGE;
        myStartScreen = new HomeScreen(stage, DEFAULT_SIZE.width, DEFAULT_SIZE.height, this);
        collisionManager = new CollisionManager();
        myStage = stage;
        myStage.setTitle(TITLE);
        myStage.setScene(myStartScreen.createScene());
        myStage.show();
        animationSpeed = 0.3;
        myErrorView = new ErrorView(language);
        myCSVReader = new CSVReader(new FileReader(SCORE_FILE));
        myCSVWriter = new CSVWriter(new FileWriter(SCORE_FILE, true),',',CSVWriter.NO_QUOTE_CHARACTER,
            CSVWriter.DEFAULT_ESCAPE_CHARACTER,
            CSVWriter.DEFAULT_LINE_END);
        myUsername = DEFAULT_USERNAME;
    }

    // TODO: I think this should be private, and I definitely need to refactor this as well
    // TODO: Throw vs. try/catch here
    /**
     * Initialize a Pacman game
     * @param path The directory of a layout file
     */
    public void initializeGame(String path) {
        int numOfRows, numOfCols;
        try {
            reader = new JSONReader(language, path);
            assembleBoards();
            //TODO get lives from JSON file
        }
        catch (ClassNotFoundException e) {
            myErrorView.showError(CLASS_NOT_FOUND);     // TODO: Need better exception handling if we are going with try/catch
        }
        catch (InvocationTargetException e) {
            myErrorView.showError(INVOCATION_TARGET);
        }
        catch (IllegalAccessException e) {
            myErrorView.showError(ILLEGAL_ACCESS);
        }
        catch (NoSuchMethodException e) {
            myErrorView.showError(NO_SUCH_METHOD);
        }
        catch (IOException e) {
            myErrorView.showError(IOE_EXCEPTION);
        }
        catch (InstantiationException e) {
            myErrorView.showError(INSTANTIATION_EXCEPTION);
        }
        catch (ParseException e) {
            myErrorView.showError(PARSE_EXCEPTION);
        }
        catch (NullPointerException e) {
            myErrorView.showError(NULL_POINTER_EXCEPTION);
        }
    }

    private void assembleBoards() throws IOException, ParseException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int numOfRows;
        int numOfCols;
        JSONContainer container = reader.readJSONConfig();
        numOfRows = container.getMyNumOfRows();
        numOfCols = container.getMyNumOfCols();
        gameObjectMap = container.getMyConversionMap();

        //TODO: Currently creatureMap is never accessed
        creatureMap = container.getMyCreatureMap();
        stringBoard = container.getMyStringBoard();
        myLanguages = ResourceBundle.getBundle(LANGUAGE_RESOURCE_PACKAGE + "languages");
        language = myLanguages.getString(container.getLanguage());
        myBoard = new Board(numOfRows, numOfCols);
        initializeBoard(numOfRows, numOfCols, gameObjectMap, stringBoard);
        myBoardView = new BoardView(this);
        initializeBoardView(numOfRows, numOfCols, gameObjectMap, stringBoard, myBoardView);

        myGame = new Game(myBoard,myBoard.getNumPickupsAtStart(), myBoard.getMyUser(),myBoard.getMyCPUCreatures() ,CELL_SIZE); //TODO assigning pickups manually assign from file!!
        myGame.setGameType(container.getGameType());
    }

    /*
    Initialize all game objects within the Board object
     */
    private void initializeBoard(int numOfRows, int numOfCols, Map<Integer, String> gameObjectMap, List<List<String>> stringBoard) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (int row = 0; row < numOfRows; row++) {
            for (int col = 0; col < numOfCols; col ++) {
                String objectName = stringBoard.get(row).get(col);
                if (gameObjectMap.containsValue(objectName) && !objectName.equals("EMPTY")) {
                    myBoard.createGameObject(row, col, objectName);
                }
                else if (creatureMap.containsValue(objectName)){
                    myBoard.createCreature(col*CELL_SIZE+3, row*CELL_SIZE+3, objectName,CELL_SIZE-5);
                }
            }
        }
    }

    /*
    Initialize all pieces within the BoardView object
     */
    private void initializeBoardView(int numOfRows, int numOfCols, Map<Integer, String> gameObjectMap, List<List<String>> stringBoard, BoardView boardView) {
        for (int row = 0; row < numOfRows; row++) {
            for (int col = 0; col < numOfCols; col ++) {
                String objectName = stringBoard.get(row).get(col);
                if (gameObjectMap.containsValue(objectName) && !objectName.equals("EMPTY")) {
                    boardView.addBoardPiece(row, col, objectName);
                }
                else {
                    if(objectName.equals("PACMAN")) { //TODO I added this in as a temporary fix. We need a way to tell if the creature is user controlled or CPU controlled. Maybe have the user specify what piece they want to control in the json file?
                        boardView.addUserCreature(row, col, objectName);
                    }
                    else if (objectName.equals("CPUGHOST")){
                        boardView.addCPUCreature(row, col, objectName);
                    }
                }
            }
        }
    }

    public int getCellCoordinate(double pixels){
        return ((int)pixels)/CELL_SIZE;
    }

    /**
     * Get the number of lives remained
     * @return the number of lives remained
     */
    public int getLives() {
        return myGame.getLives(); //TODO change this to the model's get lives
    }
    public Game getGame(){
        return myGame;
    }

    /**
     * Get the current game scores
     * @return the current game scores
     */
    public int getScore() {
        return myGame.getScore();
    }

    /**
     * Get the game category
     * @return the game category
     */
    public String getGameType() {
        return gameType;
    }

    public boolean getIsPowereredUp(){return myGame.getUser().isPoweredUp();}

    /**
     * Get the BoardView object of the game
     * @return the Boardview object
     */
    public BoardView getBoardView() {
        return myBoardView;
    }

    /**
     * Get the dimension of each cell
     * @return the size of a cell in the board
     */
    public int getCellSize() {
        return CELL_SIZE;
    }

    /**
     * Update and sync each frame of the game with the last direction used
     * @param direction the string value for the direction
     */
    public void step(String direction)  {
        myGame.setLastDirection(direction);
        myGame.step();
    }

    /**
     * Access the current coordinates of the user
     * @return (x,y) of the current position
     */
    public int[] getUserPosition() {
        int [] newPosition = {myBoard.getMyUser().getXpos(), myBoard.getMyUser().getYpos()};
        return newPosition;
    }

    /**
     * Gets the new ghost position of the ghost identified by the given ID
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
    public void changeToGameScreen(String filePath){
        myStartScreen.startNewGameForViewTests(filePath);
    }

    /**
     * Sends information about the collision to the backend
     * @param nodeID
     * @return
     */
    public boolean handleCollision(String nodeID){
        collisionManager.setCollision(nodeID);
        return myGame.dealWithCollision(collisionManager);
    }


    public void loadNextLevel(BoardView boardView) {
        myGame.resetGame();
        initializeBoardView(myBoard.getRows(), myBoard.getCols(), gameObjectMap, stringBoard,boardView);
    }

    /**
     * Receive the backend's command to reset the entire game
     */
    public void restartGame() {
        initializeGame(reader.getMostRecentPath());
    }


    /**
     * Adds a new Username:Score combo to the high score CSV file
     * @param nameAndScore String array where the first element is the name and the second element is the score
     */
    public void addScoreToCSV(String[] nameAndScore){
        myCSVWriter.writeNext(nameAndScore);
        try {
            myCSVWriter.close();
        }catch(IOException e){

        }
    }

    /**
     * Read high score CSV and get the top ten scores.
     * @return List of string arrays where each String array is a single username:score combo.
     */
    public List<String[]> getScoreData(){
        List allScoreData = new ArrayList();
        try {
            allScoreData = myCSVReader.readAll();
        }catch(IOException e){
            //TODO
        }
        return findTopTenScores(allScoreData);
    }


    private List<String[]> findTopTenScores(List<String[]> allScores){
        List<String[]> topTen = new ArrayList<>();
        int numToDisplay = HIGH_SCORE_VALS;
        if(allScores.size() < HIGH_SCORE_VALS){
            numToDisplay = allScores.size();
        }
        for(int i = 0; i < numToDisplay; i++){
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
     * @param username String inputted by user on the home screen. Defaults to "Guest"
     */
    public void setUsername(String username){
        myUsername = username;
    }

    /**
     * Returns the username for the current game.
     * @return String representing username
     */
    public String getUsername(){
        return myUsername;
    }

}
