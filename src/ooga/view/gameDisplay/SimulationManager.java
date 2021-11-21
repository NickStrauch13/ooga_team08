package ooga.view.gameDisplay;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import ooga.controller.Controller;
import ooga.view.gameDisplay.center.BoardView;
import ooga.view.gameDisplay.keyActions.KeyViewAction;

public class SimulationManager {
    private static final String KEY_PATH = "ooga.view.gameDisplay.keyActions.%sKey";
    private Controller myController;
    private Timeline myAnimation;
    private double myAnimationRate;
    private static final double DELAY = .1;
    private String currentDirection;
    private BoardView myBoardView;

    public SimulationManager(Controller controller, BoardView boardView) {
        myController = controller;
        myBoardView = boardView;
        myAnimationRate = 10; //TODO link to json
        currentDirection = "RIGHT";//TODO allow user to set this value. Call the json key "Starting direction"
    }


    /**
     * Toggles the animation. This will be the method associated with the play/pause button.
     * @return false if the animation is paused.
     */
    public boolean playPause() {
        //TODO make toggleable with the pause
        if(myAnimation == null) {
            myAnimation = new Timeline();
            myAnimation.setCycleCount(Timeline.INDEFINITE);
            myAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(DELAY), e -> step()));
            myAnimation.setRate(myAnimationRate);
            myAnimation.play();
            currentDirection = "RIGHT"; //TODO allow user to set this value. Call the json key "Starting direction"
        }
        else if(myAnimation.getStatus() == Status.PAUSED){
            System.out.println("playing");
            myAnimation.play();
        }
        else{
            System.out.println("paused");
            myAnimation.pause();
        }
        return myAnimation.getStatus() != Status.PAUSED;
    }

    private void step() {
        if(myAnimation != null && myAnimation.getStatus() != Status.PAUSED) {
           myController.step(currentDirection);
           int[] newUserPosition = myController.getUserPosition();
           myBoardView.getUserPiece().updatePosition(newUserPosition[0], newUserPosition[1]);
           String nodeCollision = myBoardView.getUserCollision(); //TODO if too slow, only do this every 10ish steps and dont include nonpassible nodes in list
           myController.setCollision(nodeCollision);
           myBoardView.removeNode();
        }
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
