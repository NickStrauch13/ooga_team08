package ooga.controller;

import javafx.stage.Stage;

import ooga.models.game.Board;
import ooga.models.game.Game;
import ooga.view.gameDisplay.GameDisplay;
import ooga.view.gameDisplay.center.BoardView;
import ooga.view.home.HomeScreen;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.io.File;

public class Controller {
    public static final Dimension DEFAULT_SIZE = new Dimension(1400, 800);
    public static final String TITLE = "Start Screen";
    public static final String gameType = "Pacman"; //TODO update gameType variable
    public Game myGame;
    public Board myBoard;
    public BoardView myBoardView;


    // TODO: Probably bad design to mix stage and board initialization at the same time. Will talk to my TA about this.
    public Controller(Stage stage) throws IOException, ParseException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        HomeScreen startScreen = new HomeScreen(stage, DEFAULT_SIZE.width, DEFAULT_SIZE.height, this);
        stage.setTitle(TITLE);
        stage.setScene(startScreen.createScene());
        stage.show();
    }

    public Board initializeBoard(String path) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, ParseException {
        int numOfRows, numOfCols = 0;
        try {
            JSONReader reader = new JSONReader(path);
            JSONContainer container = reader.readJSONConfig();
            numOfRows = container.getMyNumOfRows();
            numOfCols = container.getMyNumOfCols();
            Board newBoard = new Board(numOfRows, numOfCols);
            myBoardView = new BoardView();
            myBoardView.makeBoard(numOfRows, numOfCols);
            List<List<String>> stringBoard = container.getMyStringBoard();
            for (int row = 0; row < numOfRows; row++) {
                for (int col = 0; col < numOfCols; col ++) {
                    String objectName = stringBoard.get(row).get(col);
                    newBoard.createGameObject(row, col, objectName);
                    myBoardView.addBoardPiece(row, col, objectName);
                }
            }
            myGame = new Game(myBoard);
            return newBoard;
        }
        catch (Exception e) {
            return null;
        }
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

    public int getRows() {
        return myBoard.getRows();
    }

    public int getCols() {
        return myBoard.getCols();
    }
}
