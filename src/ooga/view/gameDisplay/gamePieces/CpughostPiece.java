package ooga.view.gameDisplay.gamePieces;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Map;

public class CpughostPiece extends MovingPiece {

    private static final String CSS_ID = "pacmanPiece";
    public static final String CPU_IMAGE = "CPU_IMAGE";
    public static final String BLUE_GHOST_PATH = "ooga/view/resources/viewIcons/blueGhost.png";
    public static final String GHOST_PATH = "ooga/view/resources/viewIcons/ghostImage.png";
    private String imagePath = GHOST_PATH;
    private static final int CREATURE_SLOP_CAUSER = 5;

    //private String imagePath = ;
    public CpughostPiece(Integer cellSize, Map<String, String> myValues){
        super(cellSize);
        if (myValues != null) {
            if(myValues.containsKey(CPU_IMAGE)){
                imagePath = myValues.get(CPU_IMAGE);
            }
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
        Image image = new Image(BLUE_GHOST_PATH);
        //pacman.setImage(image);
    }

    public void setNormal(){
        Image image = new Image(GHOST_PATH);
        //pacman.setImage(image);
    }

}
