package ooga.view.gameDisplay;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import ooga.controller.Controller;

public class SimulationManager {
    private Controller myController;
    private Timeline animation;

    public SimulationManager(Controller controller) {
        myController = controller;
    }
    /**
     * Starts a new simulation. Adapted from Professor Duvall's startSimulation method from an earlier
     * project
     */
    public void startSimulation() {
        if (animation != null) {
            animation.stop();
        }
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames()
                .add(new KeyFrame(Duration.seconds(1 - myController.getAnimationSpeed()), e -> step()));
        animation.play();
    }

    /**
     * Pause method adapted from one of Professor Duvall's earlier projects
     */
    protected void togglePause() {
        if (animation != null) {
            if (animation.getStatus() == Animation.Status.PAUSED) {
                animation.play();
            } else {
                animation.pause();
            }
        }
    }

    private void step() {
        //get positions from model, update positions in view, remove noes that need to be removed, update labels
    }

}
