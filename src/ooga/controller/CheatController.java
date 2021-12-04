package ooga.controller;

public class CheatController {
    CheatControllerInterface myController;
    public CheatController(CheatControllerInterface C){
        myController=  C;
    }
    public void addOneMillionScore(){
        myController.getGame().addScore(1000000);
    }

}