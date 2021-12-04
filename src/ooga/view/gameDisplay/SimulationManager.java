package ooga.view.gameDisplay;

import java.lang.reflect.InvocationTargetException;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Popup;
import javafx.util.Duration;
import ooga.controller.BasicController;
import ooga.controller.Controller;
import ooga.controller.ViewerControllerInterface;
import ooga.view.gameDisplay.center.BoardView;
import ooga.view.gameDisplay.gamePieces.MovingPiece;
import ooga.view.gameDisplay.keyActions.KeyViewAction;
import ooga.view.gameDisplay.top.GameStats;
import ooga.view.popups.PopupFactory;

public class SimulationManager {
    private static final String KEY_PATH = "ooga.view.gameDisplay.keyActions.%sKey";
    private ViewerControllerInterface myController;
    private Timeline myAnimation;
    private double myAnimationRate;
    private static final double DELAY = .1;
    private String currentDirection;
    private BoardView myBoardView;
    private GameStats myGameStats;
    private GameDisplay myGameDisplay;
    private int currentLevel;
    private boolean poweredUpTemp = false;
    private static final double initialAnimationRate =10.0;


    public SimulationManager(ViewerControllerInterface controller, GameStats gameStats, BoardView boardView, GameDisplay gameDisplay) {
        myController = controller;
        myBoardView = boardView;
        myAnimationRate = initialAnimationRate; //TODO link to json
        currentDirection = "RIGHT";//TODO allow user to set this value. Call the json key "Starting direction"
        myGameStats = gameStats;
        currentLevel = 1;
        myGameDisplay = gameDisplay;
    }


    /**
     * Toggles the animation. This will be the method associated with the play/pause button.
     * @return false if the animation is paused.
     */
    public boolean playPause() {
        if(myAnimation == null) {
            setupAnimation();
        }
        else if(myAnimation.getStatus() == Status.PAUSED){
            myAnimation.play();
        }
        else{
            myAnimation.pause();
        }
        return myAnimation.getStatus() != Status.PAUSED;
    }

    private void setupAnimation() {
        myAnimation = new Timeline();
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(DELAY), e -> step()));
        myAnimation.setRate(myAnimationRate);
        myAnimation.play();
        currentDirection = "RIGHT"; //TODO allow user to set this value. Call the json key "Starting direction"
    }

    /**
     * Stops the game animation. Typically, called when the user wants to return to the home screen.
     * This is a more 'permanent' version of pause.
     */
    public void stopAnimation(){
        playPause();
        myAnimation.stop();
        myAnimation = null;
    }

    private void step() { //TODO REFACTOR THIS METHOD :(
        if(myAnimation != null && myAnimation.getStatus() != Status.PAUSED) {
           myController.step(currentDirection);
           if (myController.getLevel() > currentLevel) {
               currentLevel = myController.getLevel();
               myAnimationRate += currentLevel/2.0;
               myAnimation.setRate(myAnimationRate);
               resetBoardView();
               stopAnimation();
               updateStats();
               return;
           }
           if (myController.isGameOver()) {
               myController.addScoreToCSV(new String[]{myController.getUsername(),Integer.toString(myController.getScore())});
               myGameDisplay.showGameOverPopup();
               stopAnimation();
               return;
           }
            updateMovingPiecePositions();
            String nodeCollision = myBoardView.getUserCollision();
            if (myController.handleCollision(nodeCollision) && nodeCollision.contains(",")) {
                myBoardView.removeNode(nodeCollision);
            }
            poweredUpTemp = updateCreatureState(poweredUpTemp);
            updateStats();
        }
    }

    private void resetBoardView() {
        for (MovingPiece movingPiece : myBoardView.getCreatureList()) {
            myBoardView.getInitialBoard().getChildren().remove(movingPiece.getPiece());
        }
        myBoardView.getInitialBoard().getChildren().remove(myBoardView.getMyGrid());
        myBoardView.resetBoardView();
        myController.loadNextLevel(myBoardView);
    }


    private boolean updateCreatureState(boolean lastPoweredUp){
        for (MovingPiece movingPiece : myBoardView.getCreatureList()) {
            if (lastPoweredUp!= myController.getIsPowereredUp() && movingPiece!=myBoardView.getUserPiece()){
                if (myController.getIsPowereredUp()){
                    Image blueGhost = new Image("ooga/view/resources/viewIcons/blueGhost.png");
                    movingPiece.getMyCreature().setImage(blueGhost);
                }
                else{
                    Image normalGhost = new Image("ooga/view/resources/viewIcons/ghostImage.png");
                    movingPiece.getMyCreature().setImage(normalGhost);
                }
            }
        }
        return myController.getIsPowereredUp();
    }

    private void updateMovingPiecePositions() {
        int[] newUserPosition = myController.getUserPosition();
        for (MovingPiece movingPiece : myBoardView.getCreatureList()) {
            if (movingPiece.equals(myBoardView.getUserPiece())) {
                movingPiece.updatePosition(newUserPosition[0], newUserPosition[1]);
            }
            else {
                int[] newGhostPosition = myController.getGhostPosition(movingPiece.getPiece().getId());
                if (newGhostPosition != null) {
                    movingPiece.updatePosition(newGhostPosition[0], newGhostPosition[1]);
                }
            }
        }
    }

    private void updateStats() {
        myGameStats.setScoreText(myController.getScore());
        myGameStats.setLivesText(myController.getLives());
        myGameStats.setLevelText(myController.getLevel());
    }

    public void handleKeyInput(KeyCode code){
        currentDirection = code.toString();
        try {
            String reflectionCode =
                code.toString().substring(0, 1) + code.toString().toLowerCase().substring(1);
            Class<?> clazz = Class.forName(String.format(KEY_PATH, reflectionCode));
            KeyViewAction keyAction = (KeyViewAction) clazz.getDeclaredConstructor(BoardView.class, ViewerControllerInterface.class).newInstance(myBoardView,myController);
            keyAction.doAction();
        }catch(NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException | ClassNotFoundException e){
            //Unknown key pressed. No view action required.
        }
    }

    public BoardView getMyBoardView() {
        return myBoardView;
    }

}
