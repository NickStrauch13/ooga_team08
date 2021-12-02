package ooga.view.gameDisplay;

import java.lang.reflect.InvocationTargetException;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import ooga.controller.Controller;
import ooga.view.gameDisplay.center.BoardView;
import ooga.view.gameDisplay.gamePieces.MovingPiece;
import ooga.view.gameDisplay.keyActions.KeyViewAction;
import ooga.view.gameDisplay.top.GameStats;

public class SimulationManager {
    private static final String KEY_PATH = "ooga.view.gameDisplay.keyActions.%sKey";
    private Controller myController;
    private Timeline myAnimation;
    private double myAnimationRate;
    private static final double DELAY = .1;
    private String currentDirection;
    private BoardView myBoardView;
    private GameStats myGameStats;
    private boolean 

    public SimulationManager(Controller controller, GameStats gameStats, BoardView boardView) {
        myController = controller;
        myBoardView = boardView;
        myAnimationRate = 10; //TODO link to json
        currentDirection = "RIGHT";//TODO allow user to set this value. Call the json key "Starting direction"
        myGameStats = gameStats;
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
        myAnimation.stop();
    }

    private void step() {
        if(myAnimation != null && myAnimation.getStatus() != Status.PAUSED) {
           myController.step(currentDirection);
            updateMovingPiecePositions();
            String nodeCollision = myBoardView.getUserCollision();
            if (myController.handleCollision(nodeCollision) && nodeCollision.contains(",")) {
                myBoardView.removeNode(nodeCollision);
            }
            updateCreatureState();
            updateStats();
        }
    }

    private void updateCreatureState(){
        for (MovingPiece movingPiece : myBoardView.getCreatureList()) {
            if (!movingPiece.equals(myBoardView.getUserPiece()) && myController.getIsPowereredUp()) {
                Image image = new Image("ooga/view/resources/viewIcons/blueGhost.png");
                movingPiece.getMyCreature().setImage(image);
            }
        }
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
    }

    public void handleKeyInput(KeyCode code){
        currentDirection = code.toString();
        try {
            String reflectionCode =
                code.toString().substring(0, 1) + code.toString().toLowerCase().substring(1);
            Class<?> clazz = Class.forName(String.format(KEY_PATH, reflectionCode));
            KeyViewAction keyAction = (KeyViewAction) clazz.getDeclaredConstructor(BoardView.class).newInstance(myBoardView);
            keyAction.doAction();
        }catch(NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException | ClassNotFoundException e){
            //Unknown key pressed. No view action required.
        }
    }

}
