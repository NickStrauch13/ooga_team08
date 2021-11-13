package ooga.view.gameDisplay.center;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class BoardView {
  private GridPane myGrid;
  private static final int[][] fakeControllerBoard = {{0,0,0,0,0,0}, {0,1,1,1,1,0}, {0,0,0,1,0,0}, {0,1,1,2,1,0}, {0,1,0,1,1,0}, {0,0,0,0,0,0}};

  public BoardView(){
    myGrid = new GridPane();
    myGrid.setGridLinesVisible(true);
  }

  public GridPane makeBoard(){
    //TODO get board data from the controller
    //TODO Refactor conditionals out. Maybe make an abstraction of 'game pieces' then use reflection?
    int currentState; //may actually want this as a string
    for (int r = 0; r < 6; r++) { //Replace 6 with num rows and num cols
      for (int c = 0; c < 6; c++) {
        currentState = fakeControllerBoard[r][c]; //TODO replace with a myController.getState(r,c) method
        if(currentState == 0){ //Wall
          myGrid.add(new Rectangle(30,30), c, r);
        }
        if(currentState == 1){ //empty with dot pickup
          myGrid.add(new Circle(5, Color.CORNFLOWERBLUE), c, r);
        }
        if(currentState == 2){ //Pacman
          Circle testPacman = new Circle(15, Color.YELLOW);
          myGrid.add(testPacman, c,r);
        }
      }
    }


    return myGrid;
  }
}
