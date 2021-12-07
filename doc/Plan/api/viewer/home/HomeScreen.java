public interface HomeScreentemp {

  /**
   * Creates the home screen scene.
   *
   * @return the created scene object
   */

  Scene createScene();

  /**
   * Sets the new scene which will show the home screen.
   */
  void setMainDisplay();

  /**
   * METHOD ONLY FOR TESTFX TESTS. Needed some way to load in a file in the testfx tests...
   */
  void startNewGameForViewTests(String filePath);
}