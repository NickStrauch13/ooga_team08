public abstract interface pickup extends GameObject{

    /**
     * handles interaction with user controlled object, called when intersecting with user
     */
    void abstract interact();

}