public interface BuilderButtons{
    HBox makeGameObjectsRow();
    VBox makeCenterVBox(HBox top, HBox bottom);
    VBox makeSelectedVBox();
    HBox makeBottomHBox();
    StackPane createObjectDisplay(GamePiece gamePiece);
    void changeSelected(GamePiece gamePiece);
    HBox makeCreatureRow();
    void goHome();
    String getClassName(GamePiece gamePiece);
    GamePiece getSelected();
}