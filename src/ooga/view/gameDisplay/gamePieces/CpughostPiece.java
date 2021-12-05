package ooga.view.gameDisplay.gamePieces;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Map;

public class CpughostPiece extends MovingPiece {
    private static final String CSS_ID = "pacmanPiece";
    private String imagePath = "ooga/view/resources/viewIcons/ghostImage.png";
    private static final int CREATURE_SLOP_CAUSER = 5;

    //private String imagePath = ;
    public CpughostPiece(Integer cellSize, Map<String, String> myValues){
        super(cellSize);
        if(myValues.containsKey("CPU_IMAGE")){
            imagePath = myValues.get("CPU_IMAGE");
        }
        setMyPiece(makeNode());
    }

    @Override
    protected Node makeNode(){
        ImageView pacman = new ImageView(imagePath);
        pacman.setFitWidth(getCellSize()- CREATURE_SLOP_CAUSER);
        pacman.setFitHeight(getCellSize()- CREATURE_SLOP_CAUSER);
        setIDs(pacman, CSS_ID, getCellIndexID());
//        System.out.println(getCellSize());
        return pacman;
    }

    public void setPoweredUp() {
        Image image = new Image("ooga/view/resources/viewIcons/blueGhost.png");
        //pacman.setImage(image);
    }

    public void setNormal(){
        Image image = new Image("ooga/view/resources/viewIcons/ghostImage.png");
        //pacman.setImage(image);
    }

}
