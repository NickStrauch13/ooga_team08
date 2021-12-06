package ooga.view.gameDisplay.gamePieces;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

import java.util.Map;

public class PacmanPiece extends MovingPiece {
    private static final String CSS_ID = "pacmanPiece";
    private static final int CREATURE_SLOP_CAUSER = 3;
    private String imagePath="ooga/view/resources/viewIcons/pacmanImage.png";

    public PacmanPiece(Integer cellSize, Map<String, String> myValues){
        super(cellSize);
        if (myValues != null) {
            if(myValues.containsKey("USER_IMAGE")){
                imagePath=myValues.get("USER_IMAGE");
            }
        }
        setMyPiece(makeNode());
    }

    public String getMyClass() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected Node makeNode(){
        ImageView pacman = new ImageView(imagePath);
        pacman.setFitWidth(getCellSize()- getCellSize()/CREATURE_SLOP_CAUSER);
        pacman.setFitHeight(getCellSize()- getCellSize()/CREATURE_SLOP_CAUSER);
        setIDs(pacman, CSS_ID, getCellIndexID());
        //System.out.println(getCellSize());

        return pacman;
    }


}
