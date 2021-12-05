//package ooga.view.boardBuilder.bottom;
//
//import javafx.geometry.Pos;
//import javafx.scene.Node;
//import javafx.scene.control.Button;
//import javafx.scene.control.ColorPicker;
//import javafx.scene.control.Label;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.StackPane;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.paint.Paint;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.shape.Shape;
//import javafx.stage.Stage;
//import ooga.controller.ViewerControllerInterface;
//import ooga.view.UINodeFactory.UINodeFactory;
//import ooga.view.boardBuilder.BuilderDisplay;
//import ooga.view.gameDisplay.center.BoardView;
//import ooga.view.gameDisplay.gamePieces.*;
//import ooga.view.home.HomeScreen;
//import java.util.ArrayList;
//import java.util.ResourceBundle;
//
//public class BuilderButtons {
//    private ViewerControllerInterface myController;
//    private Stage myStage;
//    private int myWidth;
//    private int myHeight;
//    private ResourceBundle myResources;
//    private UINodeFactory myNodeBuilder;
//    private StackPane selectedPane;
//    private BoardView myBoardView;
//    private ArrayList<Node> objectList;
//    private Shape selectedShape;
//    private GamePiece selected;
//    private BuilderDisplay myBuilderDisplay;
//    private static final String DEFAULT_RESOURCE_PACKAGE = "ooga.view.resources.";
//    private int cellSize;
//
//
//    public BuilderButtons(Stage stage, int width, int height,ViewerControllerInterface controller, int size, BoardView boardView, BuilderDisplay builderDisplay){
//        myController = controller;
//        myStage = stage;
//        myWidth = width;
//        myHeight = height;
//        myNodeBuilder = new UINodeFactory(myController);
//        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + myController.getLanguage());
//        cellSize = size;
//        myBoardView = boardView;
//        objectList = new ArrayList<>();
//        myBuilderDisplay = builderDisplay;
//    }
//
//    public HBox makeColorPickerRow() {
//        ColorPicker wallColorPicker = myNodeBuilder.makeColorPicker("wallColorPicker", color -> updateShapeColor(color,(Shape)objectList.get(0)), Color.rgb(33, 33, 222));
//        ColorPicker powerUpColorPicker = myNodeBuilder.makeColorPicker("powerUpColorPicker", color -> updateShapeColor(color,(Shape)objectList.get(1)), Color.rgb(184,134, 11));
//        Node myHbox = myNodeBuilder.makeRow("statsHolder", wallColorPicker, powerUpColorPicker);
//        return (HBox) myHbox;
//    }
//
//    public VBox makeCenterVBox(HBox top, HBox bottom) {
//        return (VBox) myNodeBuilder.makeCol("statsHolder", top, bottom);
//    }
//
//    public VBox makeSelectedVBox()  {
//        Label selectedText = myNodeBuilder.makeLabel(myResources.getString("SelectedText"), "selectedTextID");
//        selectedPane = new StackPane();
//        Rectangle selectedHolder = new Rectangle(100.0, 100.0, Color.LIGHTGRAY);
//        WallPiece wallPiece = new WallPiece(cellSize);
//        selectedShape = (Shape)wallPiece.getPiece();
//        selectedPane.getChildren().addAll(selectedHolder, wallPiece.getPiece());
//        selected = wallPiece;
//        selectedPane.setAlignment(wallPiece.getPiece(), Pos.CENTER);
//        return (VBox)myNodeBuilder.makeCol("statsFormat", selectedText, selectedPane);
//    }
//
//    public HBox makeBottomHBox() {
//        Button buildBoardButton = myNodeBuilder.makeButton("Build Board", null, "homeScreenButton", "buildBoardButton", e -> myBuilderDisplay.buildBoardFile());
//        VBox center = makeCenterVBox(makeObjectRow(), makeColorPickerRow());
//        return (HBox) myNodeBuilder.makeRow("statsHolder",makeSelectedVBox(), center, buildBoardButton);
//    }
//
//    public StackPane createObjectDisplay(GamePiece gamePiece) {
//        StackPane objectDisplay = new StackPane();
//        objectDisplay.getChildren().add(gamePiece.getPiece());
//        objectDisplay.setAlignment(gamePiece.getPiece(), Pos.CENTER);
//        gamePiece.getPiece().setOnMouseClicked(e -> changeSelected(gamePiece));
//        return objectDisplay;
//    }
//
//    private void changeSelected(GamePiece gamePiece) {
//        selectedPane.getChildren().remove(1);
//        GamePiece newNode;
//        if (gamePiece instanceof MovingPiece) { //TODO get rid of instanceof
//            newNode = myBoardView.creatureReflection(gamePiece.getClass().toString().substring(6));
//        }
//        else {
//            newNode = myBoardView.pieceReflection(gamePiece.getClass().toString().substring(6));
//            selectedShape = (Shape)newNode.getPiece();
//            Shape clicked = (Shape)gamePiece.getPiece();
//            updateShapeColor(clicked.getFill(), selectedShape);
//        }
//        selectedPane.getChildren().add(newNode.getPiece());
//        selected = newNode;
//    }
//
//    public HBox makeObjectRow() {
//        Label wallText = myNodeBuilder.makeLabel(myResources.getString("WallText"), "wallTextID");
//        StackPane wallDisplay = createObjectDisplay(new WallPiece(cellSize));
//        objectList.add(wallDisplay.getChildren().get(0));
//        Label powerUpText = myNodeBuilder.makeLabel(myResources.getString("PowerUpText"), "powerUpTextID");
//        StackPane powerUpDisplay = createObjectDisplay(new ScoreboosterPiece(cellSize));
//        objectList.add(powerUpDisplay.getChildren().get(0));
//        Label ghostText = myNodeBuilder.makeLabel(myResources.getString("GhostText"), "ghostTextID");
//        StackPane ghostDisplay =  createObjectDisplay( new CpughostPiece(cellSize));
//        Label pacmanText = myNodeBuilder.makeLabel(myResources.getString("PacmanText"), "pacmanTextID");
//        StackPane pacmanDisplay = createObjectDisplay(new PacmanPiece(cellSize));
//        Node myHbox = myNodeBuilder.makeRow("statsHolder", wallText, wallDisplay, powerUpText, powerUpDisplay, ghostText,ghostDisplay, pacmanText,pacmanDisplay);
//        return (HBox) myHbox;
//    }
//
//    public void updateShapeColor(Paint color, Shape gamePiece) {
//        gamePiece.setFill(color);
//        if (gamePiece.getClass().toString().equals(selectedShape.getClass().toString())) {
//            selectedShape.setFill(color);
//        }
//    }
//
//    private void goHome() {
//        HomeScreen homeScreen = new HomeScreen(myStage, myWidth, myHeight, myController);
//        homeScreen.setMainDisplay("Home");
//    }
//
//    public GamePiece getSelected() {
//        return selected;
//    }
//
//}
