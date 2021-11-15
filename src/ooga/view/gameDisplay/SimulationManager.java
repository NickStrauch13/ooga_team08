package ooga.view.gameDisplay;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import ooga.controller.Controller;

public class SimulationManager {
    private Controller myController;
    private Timeline myAnimation;
    private double myAnimationRate;
    private static final double DELAY = .1;

    public SimulationManager(Controller controller) {
        myController = controller;
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
        }
        else if(myAnimation.getStatus() == Status.PAUSED){
            myAnimation.play();
        }
        else{
            myAnimation.pause();
        }
        return myAnimation.getStatus() != Status.PAUSED;
    }

    private void step() {
        if(myAnimation != null && myAnimation.getStatus() != Status.PAUSED) {
            //get positions from model, update positions in view, remove noes that need to be removed, update labels
        }
    }

    public void handleKeyInput(KeyCode code){
        System.out.println(code);
    }

}
