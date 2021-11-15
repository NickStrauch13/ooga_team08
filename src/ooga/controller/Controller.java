package ooga.controller;

import javafx.stage.Stage;
import ooga.models.Board;
import ooga.models.Game;
import ooga.view.gameDisplay.GameDisplay;
import ooga.view.home.HomeScreen;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Controller {
    public static final Dimension DEFAULT_SIZE = new Dimension(1400, 800);
    public static final String TITLE = "Start Screen";
    public static final String gameType = "Pacman"; //TODO update gameType variable

    //TODO: probably another properties/json file to store the directory
    final String FILE_PATH = "data/test/vanillaTest.json";
    JSONReader myReader;
    JSONContainer myContainer;

    public Game myGame;
    public Board myBoard;
    public HomeScreen myHomeScreen;
    public GameDisplay myGameDisplay;

    // TODO: Probably bad design to mix stage and board initialization at the same time. Will talk to my TA about this.
    public Controller(Stage stage) throws IOException, ParseException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        HomeScreen startScreen = new HomeScreen(stage, DEFAULT_SIZE.width, DEFAULT_SIZE.height, this);
        myBoard = initializeBoard();
        myGame = new Game(myBoard);
        stage.setTitle(TITLE);
        stage.setScene(startScreen.createScene());
        stage.show();

        myReader = new JSONReader(FILE_PATH);
        myContainer = myReader.readJSONConfig();

    }

    private Board initializeBoard() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        int numOfRows = myContainer.getMyNumOfRows();
        int numOfCols = myContainer.getMyNumOfCols();
        Board newBoard = new Board(numOfRows, numOfCols);

        List<List<String>> stringBoard = myContainer.getMyStringBoard();
        for (int row = 0; row < numOfRows; row++) {
            for (int col = 0; col < numOfCols; col ++) {
                String objectName = stringBoard.get(row).get(col);
                newBoard.createGameObject(row, col, objectName);
            }
        }

        return newBoard;
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

}
