public interface UINodeFactorytemp {

    /**
     * Creates buttons for the UI.
     *
     * @param property    The Text for the Button
     * @param icon        Button icon image path
     * @param buttonStyle CSS ID for the button
     * @param ID          The Node ID for the Button
     * @param response    Button action.
     * @return Button node
     */
    Button makeButton(String property, ImageView icon, String buttonStyle, String ID,
        EventHandler<ActionEvent> response);

    /**
     * Creates a text input field Node.
     *
     * @param ID       CSS ID and Node ID
     * @param response Action on entry
     * @param initial  Initial text in field
     * @return Text field Node
     */
    TextField makeInputField(String ID, Consumer<String> response, String initial);

    /**
     * Creates a label node.
     *
     * @param text text to put in label.
     * @param ID   CSS ID and NOde ID of label.
     * @return Label Node.
     */
    Label makeLabel(String text, String ID);

    /**
     * Makes an HBox Node.
     *
     * @param rowFormatting CSS ID for HBox.
     * @param nodes         Nodes to put into HBox.
     * @return Hbox full of nodes.
     */
    HBox makeRow(String rowFormatting, Node... nodes);

    /**
     * Creates a VBox Node.
     *
     * @param rowFormatting CSS ID for VBox.
     * @param nodes         Nodes to put in VBox.
     * @return VBox Node.
     */
    VBox makeCol(String rowFormatting, Node... nodes);

    /**
     * Creates choice box node.
     *
     * @param boxID   CSS ID for the choice box
     * @param entries The string entries to have in the box.
     * @return The choice box node
     */
    ChoiceBox makeChoiceBox(String boxID, String... entries);
}