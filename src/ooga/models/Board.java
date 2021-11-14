package ooga.models;

import ooga.models.creatures.userControl.UserCreature;
import ooga.models.pickups.pickup;

public class Board {
    public Board(){

    }

    public void createBoard(){

    };

    /**
     * Updates necessary cells in the board in an optimized manner. Only cells around a Pacman can possibly change
     * their state. Maybe even only in the direction that Pacman is moving.
     */
    public void updateBoard(){

    };

    /**
     * Adds a pickup to a location on the back-end board. The controller will indicate when to do this.
     * @param pickupType
     */
    private void createPickup(String pickupType){

    };

    /**
     * Adds a Pacman to the board when launching the game.
     * @param creatureType
     */
    private void createCreature(String creatureType){

    };

    /**
     * Updates the contents of a cell in the board.
     */
    public void updateCellState(){

    };

    /**
     * gets the current state of the cell
     * @return integer that represents the state of the cell
     */
    public int getCellState(int row, int col){
        return 0;
    };
    /**
     * gets the current state of the cell
     * @return integer that represents the state of the cell
     */
    public pickup getPickup(int row, int col){
        return new pickup() {
            @Override
            public int pickUp(UserCreature userCreature) {
                return 0;
            }
        };
    };
}
