public interface UINodeBuilder {

    /**
     * Default constructor
     */
    UINodeBuilder();

    /**
     * Creates buttons for the UI.
     * @param property
     * @param icon
     * @param buttonStyle
     * @param ID
     * @param response
     * @return
     */
     Button makeButton(String property, ImageView icon, String buttonStyle, String ID, EventHandler<ActionEvent> response);

    /**
     * Makes a textfield with the given text, id, and initial value
     * @param ID
     * @param response
     * @param initial
     * @return
     */
    TextField makeInputField(String ID, Consumer<String> response, String initial);

    /**
     * Returns a label with the text given as a parameter
     * @param property
     * @return
     */
    Label makeLabel(String property);

    /**
     * Creates a row with a certain styling that contains all the buttons given as parameters
     * @param rowFormatting
     * @param nodes
     * @return
     */
    Node makeRow(String rowFormatting, Node ... nodes);

    /**
     * Creates a column with a certain styling that contains all the buttons given as parameters
     * @param rowFormatting
     * @param nodes
     * @return
     */
    Node makeCol(String rowFormatting, Node ... nodes);


    /**
     * Cretes an id which sets its text and used for TestFX purposes
     * @param id
     * @param node
     * @return
     */
     Node setID(String id, Node node);
}