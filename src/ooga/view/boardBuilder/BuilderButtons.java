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

    public HBox makeBottomHBox() {
        Button buildBoardButton = myNodeBuilder.makeButton("Build Board", null, "homeScreenButton", "buildBoardButton", e -> myBuilderDisplay.compileBoard());
        VBox center = makeCenterVBox(makeCreatureRow(), makeGameObjectsRow());
        return myNodeBuilder.makeRow("statsHolder",makeSelectedVBox(), center, buildBoardButton);
    }

    public VBox makeCenterVBox(HBox top, HBox bottom) {
        return myNodeBuilder.makeCol("statsHolder", top, bottom);
    }

    public StackPane createObjectDisplay(GamePiece gamePiece) {
        StackPane objectDisplay = new StackPane();
        objectDisplay.getChildren().add(gamePiece.getPiece());
        objectDisplay.setAlignment(gamePiece.getPiece(), Pos.CENTER);
        gamePiece.getPiece().setOnMouseClicked(e -> changeSelected(gamePiece));
        return objectDisplay;
    }

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

    public HBox makeCreatureRow() {
        Label ghostText = myNodeBuilder.makeLabel(myResources.getString("GhostText"), "ghostTextID");
        StackPane ghostDisplay =  createObjectDisplay(myBoardView.addBoardPiece(0,0,"CPUGHOST", null));
        Label pacmanText = myNodeBuilder.makeLabel(myResources.getString("PacmanText"), "pacmanTextID");
        StackPane pacmanDisplay = createObjectDisplay(myBoardView.addBoardPiece(0,0,"PACMAN", null));
        HBox myHbox = myNodeBuilder.makeRow("statsHolder", ghostText,ghostDisplay, pacmanText,pacmanDisplay);
        return myHbox;
    }


    private void goHome() {
        HomeScreen homeScreen = new HomeScreen(myStage, myWidth, myHeight, myController);
        homeScreen.setMainDisplay("Home");
    }

    public String getClassName(GamePiece gamePiece) {
        String className = gamePiece.getClass().getSimpleName();
        return className.substring(0, className.length()-5).toUpperCase();
    }

    public GamePiece getSelected() {
        return selected;
    }

}
