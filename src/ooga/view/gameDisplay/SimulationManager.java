package ooga.view.gameDisplay;

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

    private void step() {

    }


}
