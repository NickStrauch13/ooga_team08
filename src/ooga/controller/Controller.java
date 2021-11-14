package ooga.controller;

import javafx.stage.Stage;
import ooga.models.Board;
import ooga.models.Game;
import ooga.view.gameDisplay.GameDisplay;
import ooga.view.home.HomeScreen;

import java.awt.*;

public class Controller {
    public static final Dimension DEFAULT_SIZE = new Dimension(1400, 800);
    public static final String TITLE = "Start Screen";
    public static final String gameType = "Pacman"; //TODO update gameType variable

    public Game myGame;
    public Board myBoard;
    public HomeScreen myHomeScreen;
    public GameDisplay myGameDisplay;

    public Controller(Stage stage) {
        HomeScreen startScreen = new HomeScreen(stage, DEFAULT_SIZE.width, DEFAULT_SIZE.height, this);
        myBoard = new Board();
        myGame = new Game(myBoard);
        stage.setTitle(TITLE);
        stage.setScene(startScreen.createScene());
        stage.show();
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
