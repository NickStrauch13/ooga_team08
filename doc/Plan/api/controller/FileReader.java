public interface FileReader {
    /**
     * Read configuration information from a file
     */
    public InfoContainer readConfiguration();

    /**
     * Sets an ID so that this node can be identified by JavaFX and properties files.
     */
    void setID();

}