package ooga.view.gameDisplay.top;

import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ooga.controller.ViewerControllerInterface;
import ooga.view.UINodeFactory.UINodeFactory;

/**
 * Generates the player profile that is displayed on the GAME screen
 */
public class PlayerProfile {

  private static final String PROFILE_HEADER_ID = "ProfileHeaderID";
  public static final String PROFILE_SCORE_ID = "ProfileScoreID";
  public static final String PROFILE_HEADER = "ProfileHeader";
  public static final String PROFILE_V_BOX_ID = "ProfileVBoxID";
  private ViewerControllerInterface myController;
  private UINodeFactory myNodeBuilder;
  private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
  private ResourceBundle myResources;
  public PlayerProfile(ViewerControllerInterface controller){
    myController = controller;
    myNodeBuilder = new UINodeFactory(myController);
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myController.getLanguage());
  }

  /**
   * Creates a player profile to display. This included username and top score
   * @return VBox containing appropriate labels.
   */
  public Node generateProfile(){
    Label header = myNodeBuilder.makeLabel(String.format(myResources.getString(PROFILE_HEADER), myController.getUsername()),
        PROFILE_HEADER_ID);
    Label bestScore = myNodeBuilder.makeLabel(myController.getTopScoreForUser(), PROFILE_SCORE_ID);
    VBox profileVBox = myNodeBuilder.makeCol(PROFILE_V_BOX_ID, header, bestScore);
    profileVBox.setAlignment(Pos.CENTER);
    return profileVBox;
  }

}
