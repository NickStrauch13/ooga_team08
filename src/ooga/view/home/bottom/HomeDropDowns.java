package ooga.view.home.bottom;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ooga.controller.ViewerControllerInterface;
import ooga.view.UINodeFactory.UINodeFactory;
import ooga.view.home.HomeScreen;

public class HomeDropDowns {
  private ViewerControllerInterface myController;
  private UINodeFactory myNodeBuilder;
  private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
  private ResourceBundle myResources;
  private Stage myStage;
  private int myHeight;
  private int myWidth;

  public HomeDropDowns(ViewerControllerInterface controller, Stage stage, int width, int height){
    myController = controller;
    myNodeBuilder = new UINodeFactory(myController);
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myController.getLanguage());
    myStage = stage;
    myWidth = width;
    myHeight = height;
  }

  /**
   * Make the drop-down menus for the home screen.
   * @return HBox node containing all the drop-down boxes.
   */
  public Node makeDropDowns(){
    ChoiceBox languageBox = myNodeBuilder.makeChoiceBox("langBoxID", myResources.getString("English"),
        myResources.getString("Spanish"), myResources.getString("Italian"),myResources.getString("French"), myResources.getString("Esperanto"));
    languageBox.setOnAction(e->changeLanguage((String)languageBox.getSelectionModel().getSelectedItem()));
    Label langLabel = myNodeBuilder.makeLabel(myResources.getString("LangLabel"), "LangLabelID");
    Node langVBox = myNodeBuilder.makeCol("langColFormatID", langLabel, languageBox);
    Node bottomHBox = myNodeBuilder.makeRow("bottomRowFormatID", langVBox);
    return bottomHBox;
  }

  private void changeLanguage(String newLang){
    myController.setUILanguage(newLang);
    HomeScreen newHome  = new HomeScreen(myStage, myWidth, myHeight, myController);
    newHome.setMainDisplay();
  }

}
