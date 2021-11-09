public abstract interface Creature {

    /**
     * Defined in the classes that implement creature. It alters an instance variable or in some way indicates that
     * the Creature is dead.
     */
    void abstract die();

    /**
     * Moves the creature. Moves are represented in doubles that will be handed to the front-end as a new pixel
     * location.
     */
    void abstract move();

    /**
     * Returns true if the after the move the creature is still inside the grid and not inside a wall or outside the grid. Otherwise false.
     */
    abstract boolean inBounds();
}