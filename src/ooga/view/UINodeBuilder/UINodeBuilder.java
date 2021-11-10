package ooga.view.UINodeBuilder;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class UINodeBuilder {
  private static final String DEFAULT_RESOURCE_PACKAGE = "cellsociety.view.resources.";



  public UINodeBuilder(){

  }

  /**
   * Creates buttons for the UI.
   * Adapted from previous project.
   * @param property
   * @param response
   * @return Button node
   */
  public Node makeButton(String property, EventHandler<ActionEvent> response) {
    Button result = new Button();
    //result.setText(resources.getString(property));
    result.setOnAction(response);
    result.getStyleClass().add("button");
    return setID(property, result);
  }



  private Node setID(String id, Node node) {
    node.setId(id);
    return node;
  }

}
