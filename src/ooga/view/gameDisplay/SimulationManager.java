package ooga.view.gameDisplay;

import java.lang.reflect.InvocationTargetException;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import ooga.controller.CheatController;
import ooga.controller.ViewerControllerInterface;
import ooga.view.gameDisplay.bottom.GameButtons;
import ooga.view.gameDisplay.center.BoardView;
import ooga.view.gameDisplay.gamePieces.MovingPiece;
import ooga.view.gameDisplay.keyActions.KeyViewAction;
import ooga.view.gameDisplay.top.GameStats;

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
    private GameButtons myGameButtons;
    private int currentLevel;
    private boolean poweredUpTemp = false;
    private boolean invincibilityTemp = false;
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

    private void step() {
        if(myAnimation != null && myAnimation.getStatus() != Status.PAUSED) {
           myController.step(currentDirection);
            if (checkLevel()) {return;}
            if (checkGameState()) {return;}
            updateMovingPiecePositions();
            dealWithCollisions();
            poweredUpTemp = powerUpGhosts(poweredUpTemp);
            invincibilityTemp = makePacmanInvincible(invincibilityTemp);
            updateStats();
        }
    }

    private void dealWithCollisions() {
        String nodeCollision = myBoardView.getUserCollision();
        if (myController.handleCollision(nodeCollision) && nodeCollision.contains(",")) {
            myBoardView.removeNode(nodeCollision);
        }
    }

    private boolean checkGameState() {
        if (myController.isGameOver()) {
           myController.addScoreToCSV(new String[]{myController.getUsername(),Integer.toString(myController.getScore())});
           myGameDisplay.showGameOverPopup();
           stopAnimation();
            return true;
       }
        return false;
    }

    private boolean checkLevel() {
        if (myController.getLevel() > currentLevel) {
            currentLevel = myController.getLevel();
            myAnimationRate += currentLevel/2.0;
            myAnimation.setRate(myAnimationRate);
            resetBoardView();
            stopAnimation();
            updateStats();
            return true;
        }
        return false;
    }

    private void resetBoardView() {
        for (MovingPiece movingPiece : myBoardView.getCreatureList()) {
            myBoardView.getInitialBoard().getChildren().remove(movingPiece.getPiece());
        }
        myBoardView.getInitialBoard().getChildren().remove(myBoardView.getMyGrid());
        myBoardView.resetBoardView();
        myController.loadNextLevel(myBoardView);
        myGameButtons.playPause();
    }


    private boolean makePacmanInvincible(boolean lastInvincible){
        for (MovingPiece movingPiece : myBoardView.getCreatureList()){
            if (movingPiece==myBoardView.getUserPiece() && lastInvincible!=myController.getIsInvincible()){
                if (myController.getIsInvincible()){
                    Image invinciblePacman = new Image("ooga/view/resources/viewIcons/invinciblePacman.png");
                    movingPiece.getMyCreature().setImage(invinciblePacman);
                }
                else{
                    Image normalPacman = new Image("ooga/view/resources/viewIcons/pacmanImage.png");
                    movingPiece.getMyCreature().setImage(normalPacman);
                }
            }
        }
        return myController.getIsInvincible();
    }

    private boolean powerUpGhosts(boolean lastPoweredUp){
        for (MovingPiece movingPiece : myBoardView.getCreatureList()) {
            if (lastPoweredUp!= myController.getIsPoweredUp() && movingPiece!=myBoardView.getUserPiece()){
                if (myController.getIsPoweredUp()){
                    Image blueGhost = new Image("ooga/view/resources/viewIcons/blueGhost.png");
                    movingPiece.getMyCreature().setImage(blueGhost);
                }
                else{
                    Image normalGhost = new Image("ooga/view/resources/viewIcons/ghostImage.png");
                    movingPiece.getMyCreature().setImage(normalGhost);
                }
            }
        }
        return myController.getIsPoweredUp();
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
        myGameStats.setTimeText(myController.getGameTime());
    }

    /**
     * Hadles the KeyAction reflection when the user presses a key.
     * @param code The code of the key pressed by the user.
     */
    public void handleKeyInput(KeyCode code){
        String prevDirection = currentDirection;
        try {
            String reflectionCode = code.toString();
            if(code.toString().length() > 1) {
                currentDirection = code.toString();
                reflectionCode =
                    code.toString().substring(0, 1) + code.toString().toLowerCase().substring(1);
            }
            Class<?> clazz = Class.forName(String.format(KEY_PATH, reflectionCode));
            KeyViewAction keyAction = (KeyViewAction) clazz.getDeclaredConstructor(BoardView.class, ViewerControllerInterface.class).newInstance(myBoardView,myController);
            keyAction.doAction();
        }catch(NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException | ClassNotFoundException e){
            currentDirection = prevDirection;
        }
    }

    public void linkGameButtons(GameButtons gameButtons){
        myGameButtons = gameButtons;
    }

}
