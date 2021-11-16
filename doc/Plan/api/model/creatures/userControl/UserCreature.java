public abstract interface UserCreature extends Creature{

    /**
     * Defined in the classes that implement creature. It alters an instance variable or in some way indicates that
     * the Creature is dead.
     */
    void abstract die();

    /**
     * Moves the creature to a new specified location
     */
    void abstract moveTo(int xPos, int yPos);

}