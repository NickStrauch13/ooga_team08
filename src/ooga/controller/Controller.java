package ooga.controller;

import javafx.stage.Stage;
import ooga.models.Board;
import ooga.models.Game;
import ooga.view.gameDisplay.center.BoardView;
import ooga.view.home.HomeScreen;
import org.json.simple.parser.ParseException;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class Controller {
    public static final Dimension DEFAULT_SIZE = new Dimension(1400, 800);
    public static final String TITLE = "Start Screen";
    public static final String gameType = "Pacman"; //TODO update gameType variable
    public static final int CELL_SIZE = 25;
    public Game myGame;
    public Board myBoard;
    public BoardView myBoardView;
    private Map<Integer, String> gameObjectMap;
    private Map<Integer, String> creatureMap;
    private double animationSpeed;


    // TODO: Probably bad design to mix stage and board initialization at the same time. Will talk to my TA about this.
    public Controller(Stage stage) throws IOException, ParseException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        HomeScreen startScreen = new HomeScreen(stage, DEFAULT_SIZE.width, DEFAULT_SIZE.height, this);
        stage.setTitle(TITLE);
        stage.setScene(startScreen.createScene());
        stage.show();
        animationSpeed = 0.3;
    }

    public Board initializeBoard(String path) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, ParseException {
        int numOfRows, numOfCols = 0;
        try {
            JSONReader reader = new JSONReader(path);
            JSONContainer container = reader.readJSONConfig();
            numOfRows = container.getMyNumOfRows();
            numOfCols = container.getMyNumOfCols();
            Board newBoard = new Board(numOfRows, numOfCols);
            myBoardView = new BoardView(this);
            myBoardView.makeBoard(numOfRows, numOfCols);
            gameObjectMap = reader.getConversionMap();
            creatureMap = reader.getCreaturesMap();
            List<List<String>> stringBoard = container.getMyStringBoard();
            for (int row = 0; row < numOfRows; row++) {
                for (int col = 0; col < numOfCols; col ++) {
                    String objectName = stringBoard.get(row).get(col);
                    if (gameObjectMap.containsValue(objectName)) {
                        newBoard.createGameObject(row, col, objectName);
                        myBoardView.addBoardPiece(row, col, objectName);
                    }
                    else {
                        newBoard.createCreature(row, col, objectName);
                        myBoardView.addCreature(row, col, objectName);
                    }

                }
            }
            myGame = new Game(myBoard);
            return newBoard;
        }
        catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
            return null;
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
}
