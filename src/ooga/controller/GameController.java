package ooga.controller;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import ooga.models.creatures.cpuControl.CPUCreature;
import ooga.models.game.Game;
import ooga.view.popups.ErrorView;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    private final int MILLION = 1000000;
    private final int ONE_HUNDRED = 100;
    private final int FIVE_HUNDRED = 500;
    private final int HIGH_SCORE_VALS = 10;
    private final String SCORE_PATH = "./data/highscores/HighScores.csv";
    private final String IOE_EXCEPTION_CSV = "IOE exceptions for CSV file path. Please check your CSV file";

    private final String[] BLANK_ENTRY = new String[]{"", "-1"};

    private Game myGame;
    private CSVWriter myCSVWriter;
    private ErrorView myErrorView;


    public GameController (Game game, String language) {
        myGame = game;
        myErrorView = new ErrorView(language);
        initializeCSVIO();
    }

    /*
    Initialize the reader and writer for the CSV IO.
     */
    private void initializeCSVIO(){
        try {
            File scoreFile = new File(SCORE_PATH);
//            myCSVReader = new CSVReader(new FileReader(scoreFile));
            myCSVWriter = new CSVWriter(new FileWriter(scoreFile, true), ',', CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
        }
        catch (IOException e){
            myErrorView.showError(IOE_EXCEPTION_CSV);
        }
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
            myErrorView.showError(IOE_EXCEPTION_CSV);
        }
    }

    /**
     * Read high score CSV and get the top ten scores.
     *
     * @return List of string arrays where each String array is a single username:score combo.
     */
    public List<String[]> getScoreData() {
        List allScoreData = readCSV();
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

    /**
     * Returns the top score for the given username.
     * @return String value representing the integer score.
     */
    public String getTopScoreForUser(String username){
        List<String[]> scoreData = readCSV();
        String score = Integer.toString(0);
        for(int i=0; i<scoreData.size(); i++){
            if(Integer.parseInt(scoreData.get(i)[1]) > Integer.parseInt(score) && username.equals(scoreData.get(i)[0])){
                score = scoreData.get(i)[1];
            }
        }
        return score;
    }

    private List<String[]> readCSV(){
        List<String[]> allCSVData = new ArrayList<>();
        try {
            CSVReader csvReader = new CSVReader(new FileReader(new File(SCORE_PATH)));
            allCSVData = csvReader.readAll();
        } catch (IOException e) {
            myErrorView.showError(IOE_EXCEPTION_CSV);
        }
        return allCSVData;
    }

    public CSVWriter getMyCSVWriter() {
        return myCSVWriter;
    }

    /**
     * Get a game object
     * @return the Game object
     */
    public Game getGame() {
        return myGame;
    }

    /**
     * Get the number of lives remained
     *
     * @return the number of lives remained
     */
    public int getLives() {
        return myGame.getLives(); //TODO change this to the model's get lives
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

    public int getLevel() {
        return myGame.getLevel();
    }

    public boolean isGameOver() {
        return myGame.isGameOver();
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
}
