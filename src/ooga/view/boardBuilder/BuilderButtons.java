package ooga.view.boardBuilder;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ooga.controller.JSONBuilder;
import ooga.controller.ViewerControllerInterface;
import ooga.view.UINodeFactory.UINodeFactory;
import ooga.view.gameDisplay.center.BoardView;
import ooga.view.gameDisplay.gamePieces.*;
import ooga.view.home.HomeScreen;
import java.util.*;

/**
 * Builds the buttons and their functionality for the Board Builder
 * Author: Neil Mosca
 */
public class BuilderButtons {
    private ViewerControllerInterface myController;
    private Stage myStage;
    private int myWidth;
    private int myHeight;
    private ResourceBundle myResources;
    private UINodeFactory myNodeBuilder;
    private StackPane selectedPane;
    private BoardView myBoardView;
    private ArrayList<Node> objectList;
    private GamePiece selected;
    private BuilderDisplay myBuilderDisplay;
    private  Collection<String> classMap;
    private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
    private static final String GO_HOME_BUTTON = "GoHomeButton";
    private static final String GAME_OVER_HOME = "GameOverHome";
    private static final String GAME_OVER_HOME_ID = "GameOverHomeID";
    private static final String GAME_OBJECT_TEXT = "GameObjects";


    /**
     * Constructor initializes the screen's properties
     * @param stage
     * @param width
     * @param height
     * @param controller
     * @param boardView
     * @param builderDisplay
     */
    public BuilderButtons(Stage stage, int width, int height,ViewerControllerInterface controller, BoardView boardView, BuilderDisplay builderDisplay){
        myController = controller;
        myStage = stage;
        myWidth = width;
        myHeight = height;
        myNodeBuilder = new UINodeFactory(myController);
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myController.getLanguage());
        myBoardView = boardView;
        objectList = new ArrayList<>();
        myBuilderDisplay = builderDisplay;
        classMap = myController.createCreatureMap().values();
    }

    /**
     * Adds the gameObjects to the screen so they can be used to toggle between
     * The gameObject that will be added to the screen
     * @return
     */
    public HBox makeGameObjectsRow() {
        Object[] stringList = myController.createGameObjectMap().values().toArray();
        Label gameObjectText = myNodeBuilder.makeLabel(myResources.getString("GameObjects"), "powerUpTextID");
        HBox myHbox = myNodeBuilder.makeRow("statsHolder", gameObjectText);
        for (int i = 0; i < stringList.length; i++) {
            if (!stringList[i].toString().equals("EMPTY")) {
                Node myNode = createObjectDisplay(myBoardView.addBoardPiece(0,0, stringList[i].toString(),null));
                myHbox.getChildren().add(myNode);
            }
        }
        return myHbox;
    }

    /**
     * Creates the space where the selected gameObject is display. The displayed object
     * will be the object that will be added to the screen
     * @return
     */
    public VBox makeSelectedVBox()  {
        Label selectedText = myNodeBuilder.makeLabel(myResources.getString("SelectedText"), "selectedTextID");
        selectedPane = new StackPane();
        Rectangle selectedHolder = new Rectangle(100.0, 100.0, Color.LIGHTGRAY);
        GamePiece wallPiece = myBoardView.addBoardPiece(0,0,"WALL", null);
        selectedPane.getChildren().addAll(selectedHolder, wallPiece.getPiece());
        selected = wallPiece;
        selectedPane.setAlignment(wallPiece.getPiece(), Pos.CENTER);
        return myNodeBuilder.makeCol("statsFormat", selectedText, selectedPane);
    }

    /**
     * Creates the HBox which organizes all the buttons displayed in BuilderDisplay
     * @return
     */
    public HBox makeBottomHBox() {
        Button buildBoardButton = myNodeBuilder.makeButton("Build Board", null, "homeScreenButton", "buildBoardButton", e -> myBuilderDisplay.newGameWithBoard());
        VBox center = makeCenterVBox(makeCreatureRow(), makeGameObjectsRow());
        return myNodeBuilder.makeRow("statsHolder",makeSelectedVBox(), center, buildBoardButton);
    }

    /**
     * Creates the VBox for the two levels of Buttons in BuilderDisplay
     * @param top
     * @param bottom
     * @return
     */
    public VBox makeCenterVBox(HBox top, HBox bottom) {
        return myNodeBuilder.makeCol("statsHolder", top, bottom);
    }

    /**
     * Creates the containers that hold each gameObject button
     * @param gamePiece
     * @return
     */
    public StackPane createObjectDisplay(GamePiece gamePiece) {
        StackPane objectDisplay = new StackPane();
        objectDisplay.getChildren().add(gamePiece.getPiece());
        objectDisplay.setAlignment(gamePiece.getPiece(), Pos.CENTER);
        gamePiece.getPiece().setOnMouseClicked(e -> changeSelected(gamePiece));
        return objectDisplay;
    }

    /**
     * Updates the gameObject that will be added when the user clicks
     * @param gamePiece
     */
    private void changeSelected(GamePiece gamePiece) {
        selectedPane.getChildren().remove(1);
        GamePiece newGamePiece;
        if (classMap.contains(getClassName(gamePiece))) {
            newGamePiece = myBoardView.addBoardPiece(0,0,getClassName(gamePiece), null);
        }
        else {
            newGamePiece = myBoardView.addBoardPiece(0,0,getClassName(gamePiece), null);
            Node newNode = newGamePiece.getPiece();
            Node clicked = gamePiece.getPiece();
            newNode.getStyleClass().add(clicked.getStyle());
        }
        selectedPane.getChildren().add(newGamePiece.getPiece());
        selected = newGamePiece;
    }

    /**
     * Makes the row of gameCreatures so they can be selected and added to the board builder
     * @return
     */
    public HBox makeCreatureRow() {
        Label ghostText = myNodeBuilder.makeLabel(myResources.getString("GhostText"), "ghostTextID");
        StackPane ghostDisplay =  createObjectDisplay(myBoardView.addBoardPiece(0,0,"CPUGHOST", null));
        Label pacmanText = myNodeBuilder.makeLabel(myResources.getString("PacmanText"), "pacmanTextID");
        StackPane pacmanDisplay = createObjectDisplay(myBoardView.addBoardPiece(0,0,"PACMAN", null));
        Button homeButton = myNodeBuilder.makeButton(myResources.getString(GO_HOME_BUTTON), null,
                GAME_OVER_HOME, GAME_OVER_HOME_ID, e -> goHome());
        HBox myHbox = myNodeBuilder.makeRow("statsHolder", ghostText,ghostDisplay, pacmanText,pacmanDisplay,homeButton);
        return myHbox;
    }


    private void goHome() {
        HomeScreen homeScreen = new HomeScreen(myStage, myWidth, myHeight, myController);
        homeScreen.setMainDisplay();
    }

    /**
     * Used to produce that className string that is used in reflection to add gameObjects
     * @param gamePiece
     * @return
     */
    public String getClassName(GamePiece gamePiece) {
        String className = gamePiece.getClass().getSimpleName();
        return className.substring(0, className.length()-5).toUpperCase();
    }

    /**
     * returns the object that was most recently selected by the user
     * @return
     */
    public GamePiece getSelected() {
        return selected;
    }

}
