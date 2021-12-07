public interface PopupFactorytemp {

  /**
   * Puts a popup onto the main stage that shows the high scores.
   *
   * @param title String to have in the popup
   */
  Popup makePopup(String title);

  /**
   * Displays the popup on the stage.
   *
   * @param stage      stage to show popup on.
   * @param scorePopup score popup Popup node
   */
  void showPopup(Stage stage, Popup scorePopup);

  /**
   * Adds information telling the suer how to close the window.
   *
   * @param exitString message to show user.
   * @param id         CSS ID for the message
   * @return VBox with the messages.
   */
  VBox addExitInfo(String exitString, String id);

  /**
   * Returns the Popup Vbox
   *
   * @return Popup VBox node
   */
  VBox getMyVBox();
}