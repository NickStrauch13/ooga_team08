public abstract interface Wall extends GameObject {

    /**
     * handles interaction with user controlled object, called when intersecting with user
     */
    void abstract interact();

}