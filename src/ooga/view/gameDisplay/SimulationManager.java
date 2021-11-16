package ooga.view.gameDisplay;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import ooga.controller.Controller;
import ooga.view.gameDisplay.center.BoardView;

import java.util.Arrays;

public class SimulationManager {
    private Controller myController;
    private Timeline myAnimation;
    private double myAnimationRate;
    private static final double DELAY = .1;
    private String currentDirection;
    private BoardView myBoardView;

    public SimulationManager(Controller controller, BoardView boardView) {
        myController = controller;
        myBoardView = boardView;
        myAnimationRate = 10;
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
            currentDirection = "RIGHT";
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
           int[] newPacmanPosition = myController.getUserPosition();
           myBoardView.getPacman().updatePosition(newPacmanPosition[0], newPacmanPosition[1]);
        }
    }

    public void handleKeyInput(KeyCode code){
        currentDirection = code.toString();
    }

}
