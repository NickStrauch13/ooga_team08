package ooga.view.UINodeBuilder;

import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;


public class UINodeFactory {
  private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
  private static final String DEFAULT_STYLESHEET =
      "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/") + "Default.css";
  private ResourceBundle myResources;
  public UINodeFactory(){
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English"); //TODO add language here

  }

  /**
   * Creates buttons for the UI.
   * @param property
   * @param icon
   * @param buttonStyle
   * @param ID
   * @param response
   * @return
   */
  public Button makeButton(String property, ImageView icon, String buttonStyle, String ID, EventHandler<ActionEvent> response) {
    Button result = new Button(property, icon);
    result.setOnAction(response);
    result.getStyleClass().add(buttonStyle);
    result.getStyleClass().add(ID);
    result.setFocusTraversable(false);
    return (Button)setID(ID, result);
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
    Label label = new Label(property);
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

  public ColorPicker makeColorPicker(String id, Consumer<Color> response, Color color) {
    ColorPicker cp = new ColorPicker(color);
    cp.setOnAction(e -> response.accept(cp.getValue()));
    cp.getStyleClass().add("color-picker");
    cp.setMaxWidth(150.0);
    return (ColorPicker) setID(id, cp);
  }

  private Node setID(String id, Node node) {
    node.setId(id);
    return node;
  }

}
