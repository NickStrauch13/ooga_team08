
public interface ErrorView {

    /**
     * Constructor sets up resource package for Error popups
     */
    public ErrorView();

    /**
     * Displays the error as an Alert in the GUI. Displays the Title of the
     * error as well as the error message to the user.
     * @param message is the String that is displayed on the Alert in the GUI
     */
    void showError(String message);

}
