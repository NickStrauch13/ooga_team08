package ooga.view.UINodeBuilder;


import java.util.ResourceBundle;
import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UINodeBuilder {
  private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
  private static final String DEFAULT_STYLESHEET =
      "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/") + "Default.css";
  private ResourceBundle myResources;
  public UINodeBuilder(){
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English"); //TODO add language here

  }

  /**
   * Creates buttons for the UI.
   * @param text
   * @param cssClass
   * @param cssId
   * @param response
   * @return
   */
  public Button makeButton(String text, String cssClass, String cssId, EventHandler<ActionEvent> response) {
    Button result = new Button();
    result.setText(text);
    result.setOnAction(response);
    result.getStyleClass().add(cssClass);
    result.getStyleClass().add(cssId);
    return (Button)setID(text, result);
  }

  public TextField makeInputField(String ID, Consumer<String> response, String initial) {
    TextField result = new TextField();
    result.getStyleClass().add("input-field");
    result.setOnKeyReleased(e -> response.accept(result.getText()));
    result.setText(initial);
    result.setId(ID);
    return result;
  }

  public Label makeLabel(String property) {
    Label label = new Label(myResources.getString(property));
    return (Label)setID(property, label);
  }

  public Node makeRow(String rowFormatting, Node ... nodes) {
    HBox row = new HBox();
    row.getChildren().addAll(nodes);
    row.setSpacing(5.0);
    row.getStyleClass().add(rowFormatting);
    return row;
  }

  public Node makeCol(String rowFormatting, Node ... nodes) {
    VBox col = new VBox();
    col.getChildren().addAll(nodes);
    col.setSpacing(5.0);
    col.getStyleClass().add(rowFormatting);
    return col;
  }

  private Node setID(String id, Node node) {
    node.setId(id);
    return node;
  }

}
