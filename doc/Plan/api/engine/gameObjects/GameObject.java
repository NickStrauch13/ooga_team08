public abstract interface Object {

    /**
     * handles interaction with user controlled object, called when intersecting with user
     */
    void abstract interact();

    /**
     * checks position on board for a wall
     * @param row row index of cell checked for wall
     * @param col column index of cell checked for wall
     * @return boolean representing if the position row,col is a wall
     */
    boolean isWall(int row, int col);

    /**
     * sets current cell to a wall object
     */
    void setWall();

}