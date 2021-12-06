package ooga.models.game;

import java.util.Map;
import ooga.controller.JSONContainer;
import ooga.controller.JSONReader;
import ooga.models.creatures.cpuControl.CPUCreature;
import ooga.models.creatures.userControl.UserCreature;
import ooga.models.creatures.userControl.UserPacman;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class GameTest {
    private Map<String,String> map ;
    private List<CPUCreature> creatureList;
    private Board newBoard;
    private Game g;
    private UserPacman userPacman;
    private int numPickups;

    @BeforeEach
    public void initializeGame() throws IOException, ParseException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        map=Map.of("LANGUAGE","ENGLISH","GAME_TITLE","PACMAN",
            "TIMER","-1","LIVES","3","CELL_SIZE","24","CSS_FILE_NAME","DEFAULT.CSS","USER_IS_PREDATOR","0","HARD","1","IS_PICKUPS_A_VALID_WIN_CONDITION","1");
        JSONReader reader = new JSONReader("English","data/test/vanillaTest.json");
        JSONContainer container = reader.readJSONConfig();

        int numOfRows = container.getMyNumOfRows();
        int numOfCols = container.getMyNumOfCols();
        newBoard = new Board(numOfRows, numOfCols);

        List<List<String>> stringBoard = container.getMyStringBoard();

        for (int row = 0; row < numOfRows; row++) {
            for (int col = 0; col < numOfCols; col ++) {
                String objectName = stringBoard.get(row).get(col);
                newBoard.createGameObject(row, col, objectName);
            }
        }
        numPickups = 10;
        userPacman = (UserPacman) newBoard.getMyUser();
        userPacman.moveTo(15,100);
        CPUCreature c1 = new CPUCreature(100,100);
        c1.setId("CREATURE123");
        c1.setCurrentDirection(new int[]{0,1});
        creatureList=new ArrayList<CPUCreature>();
        creatureList.add(c1);

    }
    @Test
    public void testGetUserControlled(){
        g=new Game(newBoard,numPickups,userPacman,creatureList, 25, map);
        UserCreature a = g.getUser();
        assert(a.equals(userPacman));
    }
    @Test
    public void testGetGameObject(){
        g=new Game(newBoard);
        assert (g.getGameObject(0,0).isWall());
        assert (!g.getGameObject(0,3).isWall());
    }
    @Test
    public void testGetCPUCreatures(){
        g=new Game(newBoard,numPickups,userPacman,creatureList, 25, map);
        assert(g.getCPUs().equals((new ArrayList<>())));
    }
    @Test
    public void testAddScore(){
        g=new Game(newBoard,numPickups,userPacman,creatureList, 25, map);
        g.addScore(2000);
        g.addScore(200);
        assert (g.getScore()==2200);
    }
    @Test
    public void testGetScore(){
        g=new Game(newBoard,numPickups,userPacman,creatureList, 25, map);
        g.addScore(2000);
        int num=g.getScore();
        assert (num==2000);
    }
    @Test
    public void testPickupsLeft() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        g=new Game(newBoard,1,userPacman,creatureList, 25, map);

        g.updatePickupsLeft();
        g.step();
        assert(g.getLevel()==2);
    }
    @Test
    public void testGetLevel() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        g=new Game(newBoard,1,userPacman,creatureList, 25, map);
        assert(g.getLevel()==1);
        g.updatePickupsLeft();
        g.step();
        assert(g.getLevel()==2);
    }
    @Test
    public void testDealWithCollisionCreatureNotPoweredUp(){
        g=new Game(newBoard,1,userPacman, creatureList,25, map);
        CollisionManager cm = new CollisionManager();
        cm.setCollision("CREATURE123");
        g.dealWithCollision(cm);
        assert (g.getLives()==2);
    }
    @Test
    public void testDealWithCollisionCreaturePoweredUp(){
        g=new Game(newBoard,1,userPacman, creatureList,25, map);
        userPacman.setPoweredUp(true);
        CollisionManager cm = new CollisionManager();
        cm.setCollision("CREATURE123");
        g.dealWithCollision(cm);
        assert (g.getScore()==400);
    }
    @Test
    public void testGameOver() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        g=new Game(newBoard,1,userPacman,creatureList, 25, map);
        CollisionManager cm = new CollisionManager();
        for(int i=0;i<3;i++) {
            cm.setCollision("CREATURE123");
            g.dealWithCollision(cm);
        }
        g.step();
        assert(g.isGameOver());
    }
    @Test
    public void testDealWithCollisionPickup(){
        g=new Game(newBoard,1,userPacman,creatureList, 25, map);
        userPacman.setPoweredUp(true);
        CollisionManager cm = new CollisionManager();
        cm.setCollision("3,3");
        g.dealWithCollision(cm);
        assert (g.getScore()==100);
    }
    @Test
    public void resetGame(){
        g=new Game(newBoard,1,userPacman,creatureList, 25, map);
        userPacman.setPoweredUp(true);
        CollisionManager cm = new CollisionManager();
        cm.setCollision("3,3");
        g.dealWithCollision(cm);
        g.resetGame();
        assert(g.getScore()==0);
        assert (g.getLives()==3);
        assert (g.getLevel()==1);

    }
    @Test
    public void testLevel() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        g=new Game(newBoard,1,userPacman,creatureList, 25, map);
        assert (g.getLevel()==1);
        g.updatePickupsLeft();
        g.step();

        assert (g.getLevel()==2);

    }
    @Test
    public void testSetLastDirection(){
        g=new Game(newBoard,1,userPacman,creatureList, 25, map);
        assert (g.setLastDirection("DOWN"));

    }
    @Test
    public void testMove() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        g=new Game(newBoard,1,userPacman, creatureList,25, map);
        g.setLastDirection("up");
        g.step();
        assert(g.getUser().getXpos()==15);
        assert(g.getUser().getYpos()==99);
    }
    @Test
    public void testMoveIntoWall() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        UserPacman temp = new UserPacman(90,65);
        g=new Game(newBoard,1,temp,creatureList, 25, map);
        g.setLastDirection("left");
        g.step();

        assert(g.getUser().getXpos()==90);
        assert(g.getUser().getYpos()==65);
    }
    @Test
    public void testCreatureMoveIntoWall() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        CPUCreature c2 = new CPUCreature(65,90);
        c2.setCurrentDirection(new int[]{0,-1});
        c2.setId("CREATURE123");
        creatureList=new ArrayList<CPUCreature>();
        creatureList.add(c2);
        UserPacman temp = new UserPacman(90,65);
        g=new Game(newBoard,1,temp, creatureList,25, map);
        g.setLastDirection("left");
        g.step();
        System.out.println(c2.getXpos());
        System.out.println(c2.getYpos());
        assert(c2.getYpos()!=89);
        assert(c2.getYpos()==91&&c2.getXpos()==65||
                (c2.getYpos()==90&&(c2.getXpos()==64||c2.getXpos()==66)));

    }

    @Test
    public void testGetMyBoard(){
        g=new Game(newBoard,numPickups,userPacman, creatureList,25, map);
        assert (g.getMyBoard().equals(newBoard));
    }










}
