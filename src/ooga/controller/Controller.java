package ooga.controller;

import javafx.stage.Stage;


import ooga.models.creatures.Creature;
import ooga.models.game.Board;
import ooga.models.game.Game;
import ooga.view.gameDisplay.center.BoardView;
import ooga.view.home.HomeScreen;
import org.json.simple.parser.ParseException;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import ooga.view.gameDisplay.gamePieces.MovingPiece;

public class Controller {

    // TODO: Constant values should be in a file probably - enum?
    public static final Dimension DEFAULT_SIZE = new Dimension(1400, 800);
    public static final String TITLE = "Start Screen";
    public static final String gameType = "Pacman"; //TODO update gameType variable
    public static final int CELL_SIZE = 25;

    private Game myGame;
    private Board myBoard;
    private BoardView myBoardView;
    private Map<Integer, String> gameObjectMap;
    private Map<Integer, String> creatureMap; //TODO: Currently creatureMap is never accessed
    private double animationSpeed;
    private ArrayList<MovingPiece> myMovingPieces;


    // TODO: Probably bad design to mix stage and board initialization at the same time. Will talk to my TA about this.
    // TODO: Maybe let the controller do readFile by moving readFile() from HomeScreen to Controller?
    public Controller(Stage stage) throws IOException, ParseException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        HomeScreen startScreen = new HomeScreen(stage, DEFAULT_SIZE.width, DEFAULT_SIZE.height, this);
        stage.setTitle(TITLE);
        stage.setScene(startScreen.createScene());
        stage.show();
        animationSpeed = 0.3;
    }

    // TODO: I think this should be private, and I definitely need to refactor this as well
    // TODO: Throw vs. try/catch here
    public void initializeBoard(String path) {
        int numOfRows, numOfCols;
        try {
            JSONReader reader = new JSONReader(path);
            JSONContainer container = reader.readJSONConfig();
            numOfRows = container.getMyNumOfRows();
            numOfCols = container.getMyNumOfCols();
//            Board newBoard = new Board(numOfRows, numOfCols);
            myBoard = new Board(numOfRows, numOfCols);
            myBoardView = new BoardView(this);
            myBoardView.makeBoard(numOfRows, numOfCols);
            gameObjectMap = reader.getMyConversionMap();
            creatureMap = reader.getMyCreatureMap();
            List<List<String>> stringBoard = container.getMyStringBoard();
            for (int row = 0; row < numOfRows; row++) {
                for (int col = 0; col < numOfCols; col++) {
                    String objectName = stringBoard.get(row).get(col);
                    if (gameObjectMap.containsValue(objectName)) {
                        myBoard.createGameObject(row, col, objectName);
                        myBoardView.addBoardPiece(row, col, objectName);
                    }
                    else {
                        myBoard.createCreature(col*CELL_SIZE, row*CELL_SIZE, objectName);
                        myBoardView.addCreature(row, col, objectName);
                    }
                }
            }
            myGame = new Game(myBoard, 47, myBoard.getMyUser(), CELL_SIZE); //TODO assigning pickups manually assign from file!!
            //TODO get lives from JSON file
        }
        catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException | IOException | ParseException | InstantiationException e) {
            e.printStackTrace();     // TODO: Need better exception handling if we are going with try/catch
        }
    }

    /**
     * Sets speed of animation
     *
     * @param animationSpeed
     */
    public void setAnimationSpeed(double animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    public int getLives() {
        return myGame.getLives(); //TODO change this to the model's get lives
    }

    public int getScore() {
        return myGame.getScore();
    }

    public String getGameType() {
        return gameType;
    }

    public BoardView getBoardView() {
        return myBoardView;
    }

    public int getCellSize() {
        return CELL_SIZE;
    }

    public int getRows() {
        return myBoard.getRows();
    }

    public int getCols() {
        return myBoard.getCols();
    }

    public double getAnimationSpeed() {
        return animationSpeed;
    }


    /**
     * Returns the hashmap containing the moving game objects "creatures"
     * @return the creature map
     */
    public Map getCreatureMap(){
        return creatureMap;
    }

    public void step(String direction) {
        myGame.setLastDirection(direction);
        myGame.step();
    }

    public int[] getUserPosition() {
        int [] newPosition = {myBoard.getMyUser().getXpos(), myBoard.getMyUser().getYpos()};
        return newPosition;
    }


}
