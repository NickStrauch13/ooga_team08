package ooga.controller;

import java.util.List;
import java.util.Map;

public interface JSONBuilder {
    /**
     * Constructs a 2d array of the gameObjects that were added to the gameBoard
     * so that they can be read into the file
     * @param userAdded
     */
    public void compileBoard(ArrayList<String> userAdded) {}

    /**
     * Gets the position of the gameObject that was added by the BoardBuilder
     * @param id
     * @param i
     * @return
     */
    public int getPosition(String id, int i ) {}

    /**
     * Gets the id of the gameObject that was added using the BoardBuilder
     * @param id
     * @return
     */
    public String getIDClass(String id) {}

    /**
     *Creates an array from the id string (used by get class and position)
     * @param id
     * @return
     */
    public String[] splitId(String id) {}

    /**
     * returns the board path so a new game can be created with the constructed file
     * @return
     */
    public String getBoardPath() {}
}