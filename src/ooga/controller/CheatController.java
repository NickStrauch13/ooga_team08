package ooga.controller;

import ooga.models.creatures.Creature;
import ooga.models.creatures.cpuControl.CPUCreature;

import java.util.ArrayList;
import java.util.List;
@Deprecated
public class CheatController {
    private static final int ONE_LIFE = 1;
    CheatControllerInterface myController;
    private static final int MILLION = 1000000;
    private static final int ONE_HUNDRED = 100;
    private static final int FIVE_HUNDRED = 500;
    public CheatController(CheatControllerInterface C){
        myController=  C;
    }
    public void addOneMillionPoints(){
        myController.getGame().addScore(MILLION);
    }
    public void addOneHundredPoints(){
        myController.getGame().addScore(ONE_HUNDRED);
    }
    public void addFiveHundredPoints(){
        myController.getGame().addScore(FIVE_HUNDRED);
    }
    public void ghostsDie(){
        List<CPUCreature> ghosts =  myController.getGame().getCPUs();
        for(CPUCreature ghost: ghosts){
            ghost.die();
        }
    }
    public void addLife(){
        myController.getGame().addLives(ONE_LIFE);
    }
    public void goToNextLevel(){
        myController.getGame().nextLevel();
    }
    public void powerUp() {
        myController.getGame().getUser().setPoweredUp(true);
    }
    public void RemoveGhosts(){
        myController.getGame().getCPUs().removeAll(myController.getGame().getCPUs());
    }//TODO (billion, billion)
    public void RemoveOneMillionPoints(){
        myController.getGame().addScore(-MILLION);
    }
    public void resetUserPosition(){
        myController.getGame().getUser().die();
    }
    public void loseLife(){
        myController.getGame().addLives(-ONE_LIFE);
    }
    public void gameOver(){
        myController.getGame().endGame();
    }



}